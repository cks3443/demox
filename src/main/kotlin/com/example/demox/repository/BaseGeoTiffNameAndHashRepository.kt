package com.example.demox.repository

import com.example.demox.domain.BaseGeoTiffNameAndHash
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface BaseGeoTiffNameAndHashRepository : CrudRepository<BaseGeoTiffNameAndHash, Long> {
}