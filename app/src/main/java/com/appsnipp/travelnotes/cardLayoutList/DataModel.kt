package com.appsnipp.travelnotes.cardLayoutList


class DataModel(name: String, version: String, id_: Int, image: Int) {


    var name: String
        internal set
    var version: String
        internal set
    var id: Int = 0
        internal set
    var image: Int = 0
        internal set

    init {
        this.name = name
        this.version = version
        this.id = id_
        this.image = image
    }
}