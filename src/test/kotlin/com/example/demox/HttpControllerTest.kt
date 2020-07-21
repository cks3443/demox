package com.example.demox

import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class HttpControllerTest {
  @Autowired
  lateinit var hrareaRepository: HrareaRepository
  
  @Test
  fun testHttpController() {
    val hrareas = hrareaRepository.findAll()
    println(hrareas.toString())
  }
}