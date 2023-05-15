package com.tammeoja.blog.db

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

//abstract class DatabaseConfig<T>(table: String,
//  private val jdbcTemplate: JdbcTemplate
//) {
//  val mapper: RowMapper<T> = RowMapper<T> {
//    rs: ResultSet, rowNum: Int ->
//  }
//  fun upsert(query: String) = jdbcTemplate.execute()
//}