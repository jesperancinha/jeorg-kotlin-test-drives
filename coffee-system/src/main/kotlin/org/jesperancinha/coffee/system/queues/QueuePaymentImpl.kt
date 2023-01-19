package org.jesperancinha.coffee.system.queues

import lombok.experimental.Accessors
import org.springframework.stereotype.Service

/**
 * Created by joaofilipesabinoesperancinha on 05-05-16.
 */
@Accessors(chain = true)
@Service
class QueuePaymentImpl : Queue()