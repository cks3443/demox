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
  
  @GetMapping("/getgeojson_in_range")
  @ResponseBody
  fun getGeoJsonInRange(
    @RequestParam lat: Double,
    @RequestParam lon: Double,
    @RequestParam range: Int
  ) =
    jsonService.getGeoJsonInRange(lat, lon, range)
  
  @GetMapping("/getgeojsonlist")
  @ResponseBody
  fun getGeoJsonFileList(@RequestParam lat: Double, @RequestParam lon: Double) =
    jsonService.getGeoJsonFileList(lat, lon)
  
  @GetMapping("/getgeojsonlist_in_range")
  @ResponseBody
  fun getGeoJsonFileListInRange(
    @RequestParam lat: Double,
    @RequestParam lon: Double,
    @RequestParam range: Int
  ) =
    jsonService.getGeoJsonFileListInRange(lat, lon, range)
  
  @GetMapping("/gettiflist")
  @ResponseBody
  fun getTifImgList(@RequestParam lat: Double, @RequestParam lon: Double) = jsonService.getTifImgList(lat, lon)
}