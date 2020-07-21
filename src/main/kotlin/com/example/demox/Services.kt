package com.example.demox

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class HrareaService {
  @Autowired
  lateinit var hrrepo: HrareaRepository
  
  fun findAll(): List<Hrarea> = hrrepo.findAll()
}

@Service
class LrtifService {
  @Autowired
  lateinit var lrRepository: LrRepository
  
  fun finAll(): List<Lrtif> = lrRepository.findAll()
}