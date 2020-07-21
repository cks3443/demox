package com.example.demox

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController


@RestController
class MainController {
  
  @Autowired
  lateinit var jsonService: JsonService
  
  @GetMapping("/")
  @ResponseBody
  fun index(): String = "Hello World !!!"
  
  @GetMapping("/getgeojson")
  @ResponseBody
  fun getGeoJson(@RequestParam lat: Double, @RequestParam lon: Double) = jsonService.getGeoJsonInBox(lat, lon)
  
  @GetMapping("/getgeojsonlist")
  @ResponseBody
  fun getGeoJsonFileList(@RequestParam lat: Double, @RequestParam lon: Double) = jsonService.getGeoJsonFileList(lat, lon)
  
  @GetMapping("/gettiflist")
  @ResponseBody
  fun getTifImgList(@RequestParam lat: Double, @RequestParam lon: Double) = jsonService.getTifImgList(lat, lon)
}