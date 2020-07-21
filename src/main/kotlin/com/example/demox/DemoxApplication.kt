package com.example.demox

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DemoxApplication

fun main(args: Array<String>) {
  runApplication<DemoxApplication>(*args)
}
