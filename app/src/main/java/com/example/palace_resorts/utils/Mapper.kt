package com.example.palace_resorts.utils

interface Mapper<in Source, out Destination> {

    fun executeMapping(type: Source?): Destination?

}