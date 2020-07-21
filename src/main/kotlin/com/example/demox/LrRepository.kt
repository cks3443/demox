package com.example.demox

import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select
import org.springframework.stereotype.Repository

@Mapper
@Repository
interface LrRepository {
  @Select("SELECT id, tifname, hash FROM tiffs ORDER BY id ASC")
  fun findAll(): List<Lrtif>
}