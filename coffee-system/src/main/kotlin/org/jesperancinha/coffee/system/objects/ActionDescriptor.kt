package org.jesperancinha.coffee.system.objects

import lombok.Builder
import lombok.Getter

/**
 * Created by joao on 8-5-16.
 */
data class ActionDescriptor(
    val description: String? = null,
    val time: Int
)