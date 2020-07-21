package com.example.demox.repository

import com.example.demox.domain.Pak
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PakRepository : CrudRepository<Pak, Long> {
}