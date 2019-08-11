package com.atto.nimontoy.service

import com.klaytn.caver.Caver
import com.klaytn.caver.crpyto.KlayCredentials
import com.klaytn.caver.methods.response.KlayTransactionReceipt
import com.klaytn.caver.tx.manager.PollingTransactionReceiptProcessor
import com.klaytn.caver.tx.manager.TransactionManager
import com.klaytn.caver.tx.model.ValueTransferTransaction
import com.klaytn.caver.utils.ChainId
import com.klaytn.caver.wallet.KlayWalletUtils
import org.springframework.stereotype.Service
import org.web3j.utils.Numeric
import java.io.File
import java.lang.Exception
import java.math.BigInteger
import java.security.MessageDigest


@Service
class KlaytnService {
    val baseDir: String = "./keystore"
    val mainCaver = Caver.build(Caver.MAINNET_URL)
    val testCaver = Caver.build(Caver.BAOBAB_URL)

    fun getCredentials(id: String, password: String): KlayCredentials {
        val property = setProperty(id, password)
        val pathFile = baseDir + "/" + property["filename"]
        val file = File(pathFile)

        setWallet(file, property["secret"])

        return KlayWalletUtils.loadCredentials(property["secret"], file)
    }

    private fun setProperty(id: String, password: String): Map<String, String> {
        val algo = "SHA3-256"

        val fileName = MessageDigest.getInstance(algo)
                .digest(id.toByteArray(Charsets.UTF_8))
                .fold("", {str, it -> str + "%02x".format(it)})

        val secret = MessageDigest.getInstance(algo)
                .digest(password.toByteArray(Charsets.UTF_8))
                .fold("", {str, it -> str + "%02x".format(it)})

        return mapOf("filename" to fileName, "secret" to secret)
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
                throw Exception("during keystore handling, unexpected file")
            }
        }

    }

    fun getPublicKeyToHexString(credentials: KlayCredentials): String {
        return Numeric.toHexStringWithPrefix(credentials.ecKeyPair.publicKey)
    }

    fun makeTransactionToBAOBAB(credentials: KlayCredentials, toAddress: String) {
        val txManager = TransactionManager.Builder(testCaver, credentials)
                .setChaindId(ChainId.BAOBAB_TESTNET).build()

        val valueTransferTx = ValueTransferTransaction.create(
                credentials.address,
                toAddress,
                BigInteger.ONE,
                BigInteger.valueOf(100_000)
        )
        valueTransferTx.memo("Nine-Monsters:)")
        println(valueTransferTx.amount)

        val rawTx = txManager.sign(valueTransferTx)
//        val txHash = txManager.send(rawTx)

//        val receiptProc = PollingTransactionReceiptProcessor(testCaver, 1000, 15)
//        val txReceipt = receiptProc.waitForTransactionReceipt(txHash)

//        return txReceipt

    }

    fun makeTransaction(credentials: KlayCredentials, toAddress: String) {
        val txManager = TransactionManager.Builder(mainCaver, credentials)
                .setChaindId(ChainId.MAINNET).build()

        val valueTransferTx = ValueTransferTransaction.create(
                credentials.address,
                toAddress,
                BigInteger.ONE,
                BigInteger.valueOf(100_000)
        )

        val rawTx = txManager.sign(valueTransferTx)

    }

    fun makeExecuteTransaction() {

    }

}
