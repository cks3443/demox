package com.example.demox.service

import com.example.demox.domain.Pak
import com.example.demox.repository.PakRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PakServiceImpl : PakService {
  @Autowired
  lateinit var pakRepository: PakRepository
  override fun findAll(): MutableList<Pak> = pakRepository.findAll() as MutableList<Pak>
}