package com.atto.nimontoy

import com.atto.nimontoy.util.loggerOf
import org.codehaus.jackson.annotate.JsonProperty
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.http.HttpRequest
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.http.client.ClientHttpRequestExecution
import org.springframework.http.client.ClientHttpRequestInterceptor
import java.time.Duration

/**
 * Created by 00700mm@gmail.com on 2019-08-12
 * Blog : http://gyejoong.tistory.com
 * Github : http://github.com/Gyejoon
 */

@Component
class KakaoAuth(
        private val restTemplateBuilder: RestTemplateBuilder
) {
    private val log = loggerOf(this::class)
    private val restTemplate: RestTemplate = restTemplateBuilder
            .rootUri("https://kapi.kakao.com")
            .setConnectTimeout(Duration.ofSeconds(5))
            .build()
    
    fun getUserInfo(token: String): KakaoUserInfo {
        restTemplate.interceptors.add(ClientHttpRequestInterceptor { request: HttpRequest, body: ByteArray, execution: ClientHttpRequestExecution ->
            request.headers.apply {
                set("Authorization", "Bearer $token")
            }
            execution.execute(request, body)
        })

        return restTemplate.getForObject("/v2/user/me", KakaoUserInfo::class.java) ?: throw UnauthorizedException()
    }

}

data class KakaoUserInfo(
        val id: Int,
        val properties: KakaoProperties,
        @get:JsonProperty("kakao_account")
        val kakaoAccount: KakaoAccount
)

data class KakaoProperties(
        val nickname: String,
        @get:JsonProperty("thumbnail_image")
        val thumbnailImage: String,
        @get:JsonProperty("profile_image")
        val profileImage: String
)

data class KakaoAccount(
        val isEmailValid: Boolean,
        val isEmailVerified: Boolean,
        val email: String
)