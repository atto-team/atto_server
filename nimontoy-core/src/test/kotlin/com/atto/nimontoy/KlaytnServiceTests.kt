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
        val credentials = klayService.getCredentials("test", "pass")
        println(credentials.address)
//        println(klayService.getPublicKeyToHexString(credentials))
    }

    @Test
    fun makeTx() {
        val credentials = klayService.getCredentials("test", "pass")
        val receipt = klayService.makeTransactionToBAOBAB(credentials, credentials.address)

    }

}
