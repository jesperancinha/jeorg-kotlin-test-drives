package org.jesperancinha.ktd.domain

import com.fasterxml.jackson.annotation.JsonProperty

data class Coffee (
    @JsonProperty
    val type: String ?= null,
    @JsonProperty
    val qty: Long ?= null
)