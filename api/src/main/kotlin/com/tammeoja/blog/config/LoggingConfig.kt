package com.tammeoja.blog.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class LoggingConfig {

  @Bean
  fun webRequestFilter(): WebRequestLoggingFilter {
    return WebRequestLoggingFilter().apply {
      setIncludeQueryString(true)
      setIncludeClientInfo(true)
      setIncludePayload(false)
      setIncludeHeaders(false)
      setBeforeMessageSuffix("")
      setBeforeMessagePrefix("")
    }
  }
}