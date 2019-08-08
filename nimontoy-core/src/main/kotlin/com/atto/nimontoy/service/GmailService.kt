package com.atto.nimontoy.service

import com.atto.nimontoy.service.GmailAccount.PASSWORD
import com.atto.nimontoy.service.GmailAccount.USERNAME
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Service
import java.io.UnsupportedEncodingException
import java.util.*
import javax.mail.*
import javax.mail.internet.AddressException
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

/**
 * Created by 00700mm@gmail.com on 2019-07-30
 * Blog : http://gyejoong.tistory.com
 * Github : http://github.com/Gyejoon
 */
object GmailAccount {
    const val USERNAME = "artoyworld"
    const val PASSWORD = "atoworld0520"
}

@Configuration
class GmailAuthentication : Authenticator() {

    @Bean
    fun passwordAuthentication() =
            PasswordAuthentication(
                    USERNAME,
                    PASSWORD
            )

    override fun getPasswordAuthentication() = passwordAuthentication()

}

@Service
class GmailService(
        val authentication: GmailAuthentication
) {

    fun send(
            email: String,
            subject: String,
            text: String
    ) {
        val properties = System.getProperties().apply {
            put("mail.smtp.starttls.enable", "true")
            put("mail.smtp.host", "smtp.gmail.com")      // smtp 서버 호스트
            put("mail.smtp.auth", "true")
            put("mail.smtp.port", "587")
        }

        val session = Session.getDefaultInstance(properties, authentication)
        val msg = MimeMessage(session)
        val fromName = "NimonToy"
        val from = InternetAddress(String(fromName.toByteArray(), Charsets.ISO_8859_1) + "<$USERNAME@gmail.com>")
        val to = InternetAddress(email)

        msg.sentDate = Date()
        msg.setFrom(from)
        msg.setRecipient(Message.RecipientType.TO, to)
        msg.subject = subject
        msg.setText(text)

        try {
            Transport.send(msg)
        } catch (addressException: AddressException) {
            addressException.printStackTrace()
        } catch (msgException: MessagingException) {
            msgException.printStackTrace()
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }

    }

}