package com.tammeoja.blog.config

import com.tammeoja.blog.utils.Request
import org.springframework.web.filter.AbstractRequestLoggingFilter

class WebRequestLoggingFilter: AbstractRequestLoggingFilter() {
  override fun beforeRequest(request: Request, message: String) {
    logger.debug(message)
  }

  override fun afterRequest(request: Request, message: String) {}

}