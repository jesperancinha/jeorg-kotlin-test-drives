package org.jesperancinha.coffee.system.objects

import lombok.Builder
import lombok.Getter

/**
 * Created by joao on 8-5-16.
 */
@Builder
@Getter
class ActionDescriptor {
    private val description: String? = null
    private val time: Int? = null
}