package com.atto.nimontoy.exception

import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

/**
 * Created by 00700mm@gmail.com on 2019-09-23
 * Blog : http://gyejoong.tistory.com
 * Github : http://github.com/Gyejoon
 */
@RestControllerAdvice
class ExceptionControllerAdvice {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DuplicateException::class)
    fun dupliatcateException(duplicateException: DuplicateException) =
            ErrorResponse(duplicateException.message, "duplicate", HttpStatus.CONFLICT.value())

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException::class)
    fun badRequsetException(badRequestException: BadRequestException) =
            ErrorResponse(badRequestException.message, "badRequest", HttpStatus.BAD_REQUEST.value())

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingKotlinParameterException::class)
    fun defaultHandlerException(missingKotlinParameterException: MissingKotlinParameterException) =
            ErrorResponse(buildMissingKotlinParameterExceptionErrorMessage(missingKotlinParameterException), "not null", HttpStatus.BAD_REQUEST.value())

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException::class)
    fun resourceNotFoundException(resourceNotFoundException: ResourceNotFoundException) =
            ErrorResponse(resourceNotFoundException.message, resourceNotFoundException.code.toString(), HttpStatus.NOT_FOUND.value())

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(AppException::class)
    fun appException(appException: AppException) =
            ErrorResponse(appException.message, "internalServerError", HttpStatus.INTERNAL_SERVER_ERROR.value())

    fun buildMissingKotlinParameterExceptionErrorMessage(missingKotlinParameterException: MissingKotlinParameterException) =
            "파라미터 ${missingKotlinParameterException.parameter.name}은 필수로 입력해야 합니다."

}
