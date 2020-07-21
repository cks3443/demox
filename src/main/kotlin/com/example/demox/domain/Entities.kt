package com.example.demox.domain

import javax.persistence.*

@Entity
data class BaseGeoTiffNameAndHash(
    @Id
    @GeneratedValue
    @Column(name = "TIFF_ID")
    var id: Long? = null,
    var GeoTiffName: String? = null,
    @Column
    var GeoTiffFileHash: String? = null
)

@Entity
data class Pak(
    @Id
    @Column(name = "PAK_ID")
    var PakId: Long? = null,
    var PakFileHash: String? = null,
    var Type: String? = null,
    var DlcVer: String? = null,
    @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    var BaseGeoTiffNameAndHashList: MutableList<BaseGeoTiffNameAndHash>? = null
)