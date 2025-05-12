package org.jesperancinha.string.paradigm.api

abstract class Region(val regionName: String, val assignedNumber: String) {
    open lateinit var description: String
}