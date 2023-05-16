package com.tammeoja.blog.config

import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.filter.AbstractRequestLoggingFilter

class WebRequestLoggingFilter: AbstractRequestLoggingFilter() {
  override fun beforeRequest(request: HttpServletRequest, message: String) {
    logger.debug(message)
  }

  override fun afterRequest(request: HttpServletRequest, message: String) {}

}