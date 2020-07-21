package com.example.demox

import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVPrinter
import java.io.IOException
import java.io.PrintWriter
import java.util.*

class CSVGenerator {
  
  companion object {
    fun hrareaToCSV(writer: PrintWriter, hrareas: List<Hrarea>?) {
      var csvPrinter: CSVPrinter? = null
      
      try {
        csvPrinter = CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("Id", "fname", "hash"))
        
        if (hrareas != null) {
          for (hr in hrareas) {
            val data = Arrays.asList(
                hr.id,
                hr.fname,
                hr.hash)
            
            csvPrinter.printRecord(data)
          }
        }

//        println("Write CSV successfully!")
      } catch (e: Exception) {
        println("Writing CSV error!")
        e.printStackTrace()
      } finally {
        try {
          csvPrinter!!.close()
        } catch (e: IOException) {
          println("Flushing/closing error!")
          e.printStackTrace()
        }
      }
    }
    
    
    fun lrareaToCSV(writer: PrintWriter, lrtif: List<Lrtif>?) {
      var csvPrinter: CSVPrinter? = null
      
      try {
        csvPrinter = CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("Id", "tifname", "hash"))
        
        if (lrtif != null) {
          for (hr in lrtif) {
            val data = Arrays.asList(
                hr.id,
                hr.tifname,
                hr.hash)
            
            csvPrinter.printRecord(data)
          }
        }

//        println("Write CSV successfully!")
      } catch (e: Exception) {
        println("Writing CSV error!")
        e.printStackTrace()
      } finally {
        try {
          csvPrinter!!.close()
        } catch (e: IOException) {
          println("Flushing/closing error!")
          e.printStackTrace()
        }
      }
    }
  }
}