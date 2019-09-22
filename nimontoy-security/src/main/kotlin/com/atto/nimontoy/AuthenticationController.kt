package com.atto.nimontoy

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import org.springframework.web.bind.annotation.*

/**
 * Created by 00700mm@gmail.com on 2019-08-15
 * Blog : http://gyejoong.tistory.com
 * Github : http://github.com/Gyejoon
 */
@RestController
@RequestMapping
class AuthenticationController(
        private val firebaseAuth: FirebaseAuth
) {

    data class LoginRequest(
            val email: String
    )

    @GetMapping("/user/me")
    fun me() {

    }

    @PostMapping("/auth/login")
    fun login(@RequestBody request: LoginRequest): Any {
        try {
            val user = firebaseAuth.getUserByEmail(request.email)

            return mapOf(
                    "token" to firebaseAuth.createCustomToken(user.uid)
            )
        } catch (e: FirebaseAuthException) {
            throw UnauthorizedException()
        }
    }

}