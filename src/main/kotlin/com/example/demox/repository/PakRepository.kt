package com.example.demox.repository

import com.example.demox.domain.Pak
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface PakRepository : JpaRepository<Pak, Long> {
  @Query("SELECT p FROM Pak p WHERE p.PakId=:_pakid")
  fun findPakByPakId(_pakid: Long): Pak?
}