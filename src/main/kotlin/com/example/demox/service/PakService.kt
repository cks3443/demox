package com.example.demox.service

import com.example.demox.domain.Pak

interface PakService {
  fun findAll():MutableIterable<Pak>
}