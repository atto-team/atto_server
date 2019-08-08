package com.atto.nimontoy

import com.atto.nimontoy.service.GmailAuthentication
import com.atto.nimontoy.service.GmailService
import org.junit.Before
import org.junit.Test

/**
 * Created by 00700mm@gmail.com on 2019-08-09
 * Blog : http://gyejoong.tistory.com
 * Github : http://github.com/Gyejoon
 */
class GmailServiceTests {

    lateinit var gmailService: GmailService

    @Before
    fun setUp() {
        gmailService = GmailService(GmailAuthentication())
    }

    @Test
    fun send() {
        gmailService.send(
                "00700mm@gmail.com",
                "test",
                "hello"
        )
    }

}