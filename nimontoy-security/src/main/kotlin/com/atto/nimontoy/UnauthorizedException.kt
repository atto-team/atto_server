package com.atto.nimontoy

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
class UnauthorizedException(
        override val message: String = "Unauthorized User"
) : RuntimeException()