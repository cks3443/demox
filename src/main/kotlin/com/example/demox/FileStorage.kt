package com.example.demox

import org.springframework.core.io.Resource
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Path
import java.util.stream.Stream

interface FileStorage {
  fun store(file: MultipartFile)
  fun loadFile(filenm: String): Resource
  fun init()
  fun loadFiles(): Stream<Path>
}