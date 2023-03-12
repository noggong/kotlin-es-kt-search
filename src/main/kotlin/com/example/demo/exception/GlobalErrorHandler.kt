package com.example.demo.exception

import com.example.demo.dto.ErrorResponseDto
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import kotlin.Exception

@Component
@ControllerAdvice
class GlobalErrorHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(PriceNotFoundException::class)
    fun handleInputRequestError(ex: PriceNotFoundException, request: WebRequest): ResponseEntity<ErrorResponseDto> {
        logger.info("Exception occurred: ${ex.message} on request: $request")
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(ErrorResponseDto(ex.message ?: "현재 상품에 문제가 생겼습니다. 빠르게 해결하겠습니다."))
    }

    @ExceptionHandler(Exception::class)
    fun handleAllExceptions(ex: Exception, request: WebRequest) : ResponseEntity<ErrorResponseDto> {
        logger.error("😡Exception observed : ${ex.message}", ex)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ErrorResponseDto(ex.message ?: "현재 서비스가 정상적으로 작동되지 않고 있습니다. 빠르게 해결하겠습니다. 문제가 지속된다면 연락주세요."))
    }
}