package com.atto.nimontoy

import com.atto.nimontoy.service.KlaytnService
import org.junit.Before
import org.junit.Test
import java.math.BigInteger


class KlaytnServiceTests {
    lateinit var klayService: KlaytnService

    @Before
    fun setup() {
        klayService = KlaytnService()
    }

    @Test
    fun credentials() {
        val kly = klayService
        val credentials = kly.getCredentials("test", "pass")
        println(credentials.address)
        println(klayService.getPublicKeyToHexString(credentials))

    }

    @Test
    fun makeTx() {
        val value = "1000000000000000000000000000"
        val credentials = klayService.getCredentials("test", "pass")
        val tx = klayService.makeSignedTransactionToCypress(credentials, credentials.address, value)
        println(tx.signatureData)
    }

}
