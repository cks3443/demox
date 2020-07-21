package com.example.demox


import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select
import org.springframework.stereotype.Repository

@Mapper
@Repository
interface HrareaRepository {
  
  @Select("SELECT id, fname, hash FROM hrarea ORDER BY id ASC")
  fun findAll(): List<Hrarea>
}