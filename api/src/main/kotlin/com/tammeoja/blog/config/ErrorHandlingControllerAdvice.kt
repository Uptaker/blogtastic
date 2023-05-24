package com.tammeoja.blog.config

//@ControllerAdvice
//class ErrorHandlingControllerAdvice {
//
//  @ExceptionHandler
//  fun handle(ex: Exception): ProblemDetail {
//    val problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST.value())
//    problem.detail = ex.message
//    return problem
//  }
//}