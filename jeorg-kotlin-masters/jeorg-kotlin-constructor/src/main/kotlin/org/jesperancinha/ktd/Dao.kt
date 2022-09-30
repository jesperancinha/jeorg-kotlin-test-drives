package org.jesperancinha.ktd

import org.hibernate.validator.constraints.Range


data class Book(
    @field:Range(min = 10, max = 30) val pages: Long? = 0
)

data class BadBook(
    @Range(min = 10, max = 30) val pages: Long? = 0
)
