package com.tammeoja.blog.utils

class TextGenerator {
  companion object {
    private val specialChars = listOf('$', '#', '%', '&', '!', '?', '+', '=', '@', '-', '^', '(', ')')
    private val alphabet: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9') + specialChars
    fun random(length: Int = 64): String = (1..length).map { alphabet.random() }.joinToString("")
  }
}