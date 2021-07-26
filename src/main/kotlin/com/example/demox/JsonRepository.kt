package com.example.demox

import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import org.springframework.stereotype.Repository


@Mapper
@Repository
interface JsonRepository {
  
  @Select(
    "SELECT * FROM RE_GetGeoJsonInBox(ST_Transform(ST_SetSRID(" +
        "ST_MakeBox2D(ST_Point(#{x}, #{y}), ST_Point(#{x1}, #{y1})), " +
        "5179), 4326), 5179)"
  )
  fun getGeoJson(@Param("x") x: Int, @Param("y") y: Int, @Param("x1") x1: Int, @Param("y1") y1: Int): JsonData
  
  @Select("SELECT * FROM RE_GetGeoJsonFileList(#{X}, #{Y})")
  fun getGeoJsonFileList(@Param("X") X: Int, @Param("Y") Y: Int): List<GeoJsonFileNameData>
  
  @Select(
    """
    SELECT *
    FROM re_getgeojsonfilelist_in_range(#{X}, #{Y}, #{R})
    """
  )
  fun getGeoJsonFileListInRange(
    @Param("X") X: Int,
    @Param("Y") Y: Int,
    @Param("R") r: Int
  ): List<GeoJsonFileNameData>
  
  @Select("SELECT * FROM RE_GetTifImgList(#{X}, #{Y})")
  fun getTifImgList(@Param("X") X: Int, @Param("Y") Y: Int): List<TifImgData>
  
  @Select(
    "SELECT CAST(ST_X(POINT) AS INT) x, CAST(ST_Y(POINT) AS INT) y " +
        "FROM ST_Transform(ST_SetSRID(ST_Point(#{lon}, #{lat}), 4326), 5179) AS POINT"
  )
  fun getPointInEpsg5179(@Param("lat") lat: Double, @Param("lon") lon: Double): Point
}