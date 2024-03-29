package com.example.demox

import com.example.demox.domain.Pak
import com.example.demox.domain.PakList
import com.example.demox.repository.PakRepository
import com.example.demox.service.PakService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import javax.servlet.http.HttpServletResponse

@RestController
class HttpController {
  
  @Autowired
  lateinit var hrsrv: HrareaService
  
  @Autowired
  lateinit var lrtifService: LrtifService
  
  @Autowired
  lateinit var fileStorage: FileStorage
  
  @Autowired
  lateinit var pakRepository: PakRepository
  
  @Autowired
  lateinit var pakService: PakService
  
  @GetMapping("/hello")
  fun home(): String {
    return "Hello World !!!"
  }
  
  @GetMapping("/{res}/files")
  fun exportCsv(@PathVariable("res") res: String, response: HttpServletResponse) {
    
    response.contentType = "text/csv"
    
    when (res) {
      "hr" -> {
        response.setHeader("Content-Disposition", "attachment; filename=hrfiles.csv")
        CSVGenerator.hrareaToCSV(response.writer, hrsrv.findAll())
      }
      "lr" -> {
        response.setHeader("Content-Disposition", "attachment; filename=lrfiles.csv")
        CSVGenerator.lrareaToCSV(response.writer, lrtifService.finAll())
      }
      else -> {
        response.status = HttpServletResponse.SC_BAD_REQUEST
      }
    }
  }
  
  @PostMapping("/{res}/uploadfile")
  fun uploadMultipartFile(@RequestParam("file") file: MultipartFile,
                          @PathVariable("res") res: String,
                          model: Model): String {
    return try {
      when (res) {
        "hr", "lr" -> {
          fileStorage.store(file)
          "ok"
        }
        else -> {
          "error"
        }
      }
    } catch (e: Exception) {
      e.printStackTrace()
      "fail"
    }
  }
  
  @GetMapping("/file/{filenm}")
  fun downloadFile(@PathVariable("filenm") filenm: String): ResponseEntity<Resource> {
    val file = fileStorage.loadFile(filenm)
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.filename + "\"")
        .body(file)
  }
  
  @PostMapping("/upload/pakdb")
  @Transactional
  fun uploadPakDb(@RequestBody pakdb: PakList): String {
    return try {
      val _paklist = pakdb.PakDB
      pakRepository.saveAll(_paklist!!)
      "ok"
    } catch (e: Exception) {
      "fail"
    }
    
  }
  
  @PostMapping("/download/pakdb")
  fun downloadPakDb(): PakList? {
    return try {
      var paks = PakList()
      paks.PakDB = this.pakService.findAll()
      paks
    } catch (e: Exception) {
      null
    }
  }
  
  @PostMapping("/getpakhashs")
  fun getPakHashs(@RequestBody pakdb: PakList): PakList {
    val pak_container = mutableListOf<Pak>()
    val _paklist = pakdb.PakDB
    
    _paklist?.forEach { pak ->
      val pakFromRepo: Pak? = pakRepository.findPakByPakId(pak.PakId!!)
      pakFromRepo?.let { pak_container.add(it) }
    }
    
    val paklist = PakList()
    paklist.PakDB = pak_container
    
    return paklist
  }
}