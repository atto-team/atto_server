package com.atto.nimontoy

import com.klaytn.caver.tx.manager.TransactionManager
import org.springframework.context.annotation.Configuration
import com.klaytn.caver.tx.model.ValueTransferTransaction
import com.klaytn.caver.utils.ChainId
import org.springframework.context.annotation.Bean
import java.math.BigInteger
import com.klaytn.caver.Caver


@Configuration
class KlaytnConfig {

//    @Bean
//    fun valueTransferTransaction() {
//        val caver = Caver.build(Caver.BAOBAB_URL)
//        val credentials = ""
//
//        TransactionManager
//                .Builder(caver, credentials)
//                .setChaindId(ChainId.BAOBAB_TESTNET).build()
//
//
//        return ValueTransferTransaction.create(
//                credentials.getAddress(), // fromAddress
//                "0xe97f27e9a5765ce36a7b919b1cb6004c7209217e", // toAddress
//                BigInteger.ONE, // value
//                BigInteger.valueOf(100000)  // gasLimit
//        )
//    }

}