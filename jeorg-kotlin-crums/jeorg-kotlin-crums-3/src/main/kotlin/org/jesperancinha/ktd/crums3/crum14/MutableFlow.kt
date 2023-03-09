package org.jesperancinha.ktd.crums3.crum14

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

class MutableFlow {

    companion object {
        val mutableFlow: MutableSharedFlow<String> by lazy { MutableSharedFlow() }
        val mutableFlowWithReplay: MutableSharedFlow<String> by lazy { MutableSharedFlow(replay = 2) }

        private val logger = object {
            fun info(logText: Any?) = synchronized(this) { ConsolerizerComposer.out().magenta(logText) }
            fun infoText(logText: Any?) = synchronized(this) { ConsolerizerComposer.out().green(logText) }
            fun infoTitle(logText: String) = synchronized(this) {
                ConsolerizerComposer.outSpace()
                    .cyan(ConsolerizerComposer.title(logText))
            }

            fun infoSubTitle(logText: String) = synchronized(this) {
                ConsolerizerComposer.outSpace()
                    .orange(ConsolerizerComposer.title(logText))
            }
        }

        @JvmStatic
        fun main(args: Array<String>): Unit = runBlocking {
            logger.infoSubTitle("Testing with no replay. For each query, the stream becomes empty")
            CoroutineScope(Dispatchers.IO).launch {
                sendFishes(mutableFlow)
                logger.infoTitle("Mutable Flows")
                logger.infoText("We start our flow with unknown elements but we know that there are 2")
                logger.info("The first fish we get is ${mutableFlow.firstOrNull { it.contains("ardine") }}")
                sendFishes(mutableFlow)
                logger.info("The second fish we get is ${mutableFlow.firstOrNull { it.contains("od") }}")
            }
            delay(5000)
            logger.infoSubTitle("Testing with replay. For each query, the stream replays the last replay elements")
            CoroutineScope(Dispatchers.IO).launch {
                sendFishes(mutableFlowWithReplay)
                logger.infoTitle("Mutable Flows")
                logger.infoText("We start our flow with unknown elements but we know that there are 2")
                logger.info("The first fish we get is ${mutableFlowWithReplay.firstOrNull { it.contains("ardine") }}")
                logger.info("The second fish we get is ${mutableFlowWithReplay.firstOrNull { it.contains("od") }}")
                sendExtraFishes(mutableFlowWithReplay)
                logger.info("The first fish we get is ${mutableFlowWithReplay.firstOrNull { it.contains("ardine") }}")
                logger.info("The second fish we get is ${mutableFlowWithReplay.firstOrNull { it.contains("od") }}")
                logger.info("The third fish we get is ${mutableFlowWithReplay.firstOrNull { it.contains("una") }}")
                logger.info("The fourth fish we get is ${mutableFlowWithReplay.firstOrNull { it.contains("alo") }}")

            }
            delay(5000)
        }

        private fun CoroutineScope.sendFishes(mutableFlow: MutableSharedFlow<String>) {
            listOf(
                async {
                    logger.infoText("Sending fishes...")
                    delay(2000)
                    mutableFlow.emit("Sardine")
                    mutableFlow.emit("Codfish")
                    logger.infoText("Fishes sent!")
                }
            )
        }
        private fun CoroutineScope.sendExtraFishes(mutableFlow: MutableSharedFlow<String>) {
            listOf(
                async {
                    logger.infoText("Sending extra lux fishes...")
                    delay(2000)
                    mutableFlow.emit("Sardine")
                    mutableFlow.emit("Codfish")
                    mutableFlow.emit("Tuna")
                    mutableFlow.emit("Robalo")
                    logger.infoText("Fishes sent!")
                }
            )
        }
    }
}