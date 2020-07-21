package com.example.demox

import org.springframework.stereotype.Component

@Component
data class JsonData (val re_getgeojsoninbox : String = "")

@Component
data class GeoJsonFileNameData ( val filename : String="")

@Component
data class  TifImgData ( val filename: String="")

@Component
data class Point(var x:Int = 0, var y:Int =0)