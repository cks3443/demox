package com.example.demox

import org.slf4j.LoggerFactory
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.util.stream.Stream

@Service
class FileStorageImpl : FileStorage {
  val log = LoggerFactory.getLogger(this::class.java)
//  val rootLocation = Paths.get("C:/unrealPakFiles")
  val rootLocation = Paths.get("C:/testUploadFiles")
  
  override fun store(file: MultipartFile) {
    if (!Files.isDirectory(this.rootLocation)) {
      init()
    }
    try {
//      println(file.originalFilename)
      Files.copy(file.inputStream, this.rootLocation.resolve(file.originalFilename), StandardCopyOption.REPLACE_EXISTING)
    } catch (e: Exception) {
      e.printStackTrace()
    }
    
  }
  
  override fun loadFile(filenm: String): Resource {
    val file = rootLocation.resolve(filenm)
    val resource = UrlResource(file.toUri())
    
    if (resource.exists() || resource.isReadable) {
      return resource
    } else {
      throw RuntimeException("FAIL!")
    }
  }
  
  override fun init() {
    Files.createDirectories(rootLocation)
  }
  
  override fun loadFiles(): Stream<Path> {
    return Files.walk(this.rootLocation, 1)
        .filter { path -> !path.equals(this.rootLocation) }
        .map(this.rootLocation::relativize)
  }
}