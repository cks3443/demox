package com.example.demox

import com.google.gson.Gson
import com.vividsolutions.jts.geom.Coordinate
import org.geotools.geometry.jts.JTS
import org.geotools.geometry.jts.JTSFactoryFinder
import org.geotools.referencing.CRS
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import org.opengis.referencing.crs.CoordinateReferenceSystem
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.FileReader




@Service
class JsonService {

    @Autowired
    lateinit var jsonRepo : JsonRepository

//    private val _Path2 = "C:\\geojson2\\"
    private val _Path4 = "C:\\geojson4\\"
//    private val _Path3 = "C:\\geojson3\\"

    private fun getBoundZero(x:Int) : Int {
        val xx = (x / 1000).toInt()
        return (xx-1)*1000
    }

    @Throws
    private fun convertLatLonToUTMK(lat:Double, lon:Double) : List<Int> {

        val point = jsonRepo.getPointInEpsg5179(lat, lon)

        var x = point.x
        var y = point.y

        x = getBoundZero(x)
        y = getBoundZero(y)

        val list = listOf<Int>(x,y)

        return list
    }



    @Throws
    fun getGeoJsonInBox(lat:Double, lon:Double): String {

        val list = convertLatLonToUTMK(lat, lon)
        val x = list[0]
        val y = list[1]

        return this.jsonRepo.getGeoJson(x, y, x+3000, y+3000).re_getgeojsoninbox
    }

    @Throws
    fun getGeoJsonFileList(lat:Double, lon:Double) : String {
        val list = convertLatLonToUTMK(lat, lon)
        val X = list[0] + 1000
        val Y = list[1] + 1000
        val jlist = jsonRepo.getGeoJsonFileList(X, Y)

        val filelist = mutableListOf<String>()

        val parser = JSONParser()

        for (jj in jlist) {

            val obj: JSONObject = parser.parse(FileReader(_Path4 +  jj.filename)) as JSONObject

            val features: JSONArray? = obj.get("features") as JSONArray?

            val feature: JSONObject? = features!!.get(0) as JSONObject?

            val geometry: JSONObject? = feature!!.get("geometry") as JSONObject?

            val coordinate: JSONArray? = geometry!!.get("coordinates") as JSONArray?

            if (coordinate!!.size != null && coordinate!!.size != 0) {
                filelist.add(jj.filename)
            }
        }


        val json = Gson().toJson( filelist )
        return json
    }

    @Throws
    fun getTifImgList(lat:Double, lon:Double) : String {
        val list = convertLatLonToUTMK(lat, lon)
        val X = list[0] + 1000
        val Y = list[1] + 1000
        val ImgList = jsonRepo.getTifImgList(X, Y)

        val filelist = mutableListOf<String>()

        for (tif in ImgList) {
            filelist.add(tif.filename)
        }

        val json = Gson().toJson( filelist )
        return json
    }
}