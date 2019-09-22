package com.atto.nimontoy.service

import com.klaytn.caver.Caver
import com.klaytn.caver.crpyto.KlayCredentials
import com.klaytn.caver.methods.response.KlayTransactionReceipt
import com.klaytn.caver.tx.manager.PollingTransactionReceiptProcessor
import com.klaytn.caver.tx.manager.TransactionManager
import com.klaytn.caver.tx.model.KlayRawTransaction
import com.klaytn.caver.tx.model.ValueTransferTransaction
import com.klaytn.caver.utils.ChainId
import com.klaytn.caver.wallet.KlayWalletUtils
import org.springframework.stereotype.Service
import org.web3j.utils.Numeric
import java.io.File
import java.io.FileNotFoundException
import java.math.BigInteger
import java.security.MessageDigest

data class RewardTables(
        val like: Int = 5,
        val comment: Int = 10,
        val shared: Int = 50,
        val write: Int = 100,
        val signUp: Int = 500,
        val recommend: Int = 300,
        val revisit: Int = 100
)


@Service
class KlaytnService {

    private lateinit var ephemeralKeystore: KlayCredentials
    private var baseDir: String = "./keystore"
    private val tables = RewardTables()
    val mainCaver = Caver.build(Caver.MAINNET_URL)
    val testCaver = Caver.build(Caver.BAOBAB_URL)

    fun getCredentials(id: String, password: String): KlayCredentials {
        val property = setProperty(id, password) // readonly map?
        val pathFile = baseDir + "/" + property["filename"]
        // /keystore / hash32(id)
        // /keystore / 36f028580bb02cc8272a9a020f4200e346e276ae664e45ee80745574e2f5ab80

        val file = File(pathFile)

        val hasUsableCredential: Boolean = file.exists() and ::ephemeralKeystore.isInitialized

        when (hasUsableCredential) {
            true -> return ephemeralKeystore
            false -> {
                // make and load wallet
                setWallet(file, password)
                ephemeralKeystore = KlayWalletUtils.loadCredentials(password, file)
                return ephemeralKeystore
            }
        }
    }

    private fun setProperty(id: String, password: String): Map<String, String> {
        val algo = "SHA3-256"

        val fileName = MessageDigest.getInstance(algo)
                .digest(id.toByteArray(Charsets.UTF_8))
                .fold("", {str, it -> str + "%02x".format(it)})

//        val secret = MessageDigest.getInstance(algo)
//                .digest(password.toByteArray(Charsets.UTF_8))
//                .fold("", {str, it -> str + "%02x".format(it)})

        return mapOf("filename" to fileName)  // "secret" to secret
    }

    private fun setWallet(file: File, secret: String?) {
        val dir = File(baseDir)

        if (!dir.exists()) {
            dir.mkdir()
        }

        if (!file.exists()) {
            val touchPath = KlayWalletUtils.generateNewWalletFile(secret, dir)
            val tempWallet = File(touchPath)
            val isMove = tempWallet.renameTo(file)

            if (!isMove) {
                throw FileNotFoundException("during keystore handling, unexpected file")
            }
        }

    }

    fun getPublicKeyToHexString(credentials: KlayCredentials): String {
        return Numeric.toHexStringWithPrefix(credentials.ecKeyPair.publicKey)
    }

    private fun getTransactionManager(credentials: KlayCredentials, caver: Caver, id: Int): TransactionManager {
        return TransactionManager.Builder(caver, credentials)
                .setChaindId(id).build()
    }

    fun makeSignedTransactionToBaobab(
            credentials: KlayCredentials, toAddress: String, value: String
    ): KlayRawTransaction {
        // TestNet Transaction!!

        val manager = getTransactionManager(credentials, testCaver, ChainId.BAOBAB_TESTNET)

        val valueTransferTx = ValueTransferTransaction.create(
                credentials.address,
                toAddress,
                value.toBigInteger(),
                BigInteger.valueOf(100_000)
        )
        valueTransferTx.memo("Nine-Monsters :)")

        return manager.sign(valueTransferTx)
    }

    fun makeSignedTransactionToCypress(
            credentials: KlayCredentials, toAddress: String, value: String
    ): KlayRawTransaction {
        // MainNet Transaction !!
        val manager = getTransactionManager(credentials, mainCaver, ChainId.MAINNET)


        val valueTransferTx = ValueTransferTransaction.create(
                credentials.address,
                toAddress,
                value.toBigInteger(),
                BigInteger.valueOf(100_000)
        )

        return manager.sign(valueTransferTx)
    }

    fun makeExecuteTransaction() {
        //todo
    }

    fun withdrawalFromReward() {
        //todo
    }

    fun giveRewardFromActivity() {
        //todo
    }



    fun sendTransactionToBaobab(credentials: KlayCredentials, rawTransaction: KlayRawTransaction): String {
        // send -> return transaction hash
        val manager = getTransactionManager(credentials, testCaver, ChainId.BAOBAB_TESTNET)
        return manager.send(rawTransaction)
    }

    fun sendTransactionToCypress(credentials: KlayCredentials, rawTransaction: KlayRawTransaction): String {
        val manager = getTransactionManager(credentials, mainCaver, ChainId.MAINNET)
        return manager.send(rawTransaction)
    }

    fun getReceiptFromBaobab(txHash: String): KlayTransactionReceipt.TransactionReceipt {
        // polling receipt...  from Test Net
        val polling = PollingTransactionReceiptProcessor(testCaver, 1000, 15)
        return polling.waitForTransactionReceipt(txHash)
    }

    fun getReceiptFromCypress(txHash: String): KlayTransactionReceipt.TransactionReceipt {
        // polling receipt... from Main Net
        val polling = PollingTransactionReceiptProcessor(mainCaver, 1000, 15)
        return polling.waitForTransactionReceipt(txHash)
    }

}
