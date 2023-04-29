package org.jesperancinha.ktd.json2.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

@JsonRootName("Stamp")
@JacksonXmlRootElement
data class Stamp(
    val id: Long? = null,
    @JacksonXmlProperty(localName = "description", isAttribute = true)
    val description: String? = null,
    val year: Long? = null,
    val value: Long? = null,
    val heightMM: Long? = null,
    val widthMM: Long? = null,

    @JsonProperty("Material")
    val materials: List<String> = emptyList(),
)