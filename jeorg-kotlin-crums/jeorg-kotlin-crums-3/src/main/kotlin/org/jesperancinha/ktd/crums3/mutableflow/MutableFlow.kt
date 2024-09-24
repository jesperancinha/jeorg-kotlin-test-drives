package org.jesperancinha.ktd.crums3.mutableflow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.firstOrNull
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

class MutableFlow {

    companion object {
        val mutableFlow: MutableSharedFlow<String> by lazy { MutableSharedFlow() }
        val mutableFlowWithReplay: MutableSharedFlow<String> by lazy { MutableSharedFlow(replay = 4) }

        private val logger = object {
            fun info(logText: Any?) = synchronized(this) { ConsolerizerComposer.out().magenta(logText) }
            fun infoText(logText: Any?) = synchronized(this) { ConsolerizerComposer.out().green(logText) }
            fun infoTitle(logText: String) = synchronized(this) {
                ConsolerizerComposer.outSpace()
                    .cyan(ConsolerizerComposer.title(logText))
            }

            fun infoSubTitle(logText: String) = synchronized(this) {
                ConsolerizerComposer.outSpace()
                    .orange().doubleFrame(logText).toConsoleLn()
            }
        }

        @JvmStatic
        fun main(args: Array<String> = emptyArray()): Unit = runBlocking {
            logger.infoSubTitle("Testing with no replay. For each query, the stream becomes empty")
            CoroutineScope(Dispatchers.IO).launch {
                logger.infoTitle("Mutable Flows no replay")
                sendFishes(mutableFlow)
                logger.info("The first fish we get is ${mutableFlow.firstOrNull { it.contains("ardine") }}")
                sendFishes(mutableFlow)
                logger.info("The second fish we get is ${mutableFlow.firstOrNull { it.contains("od") }}")
            }
            delay(5000)
            logger.infoTitle("We get Sardine and Codfish")
            logger.infoSubTitle("Testing with no replay and filtering without emitting")
            CoroutineScope(Dispatchers.IO).launch {
                logger.infoTitle("Mutable Flows no replay but sending after")
                logger.info("The first fish we get is ${mutableFlow.firstOrNull { it.contains("ardine") }}")
                sendFishes(mutableFlow)
                logger.info("The second fish we get is ${mutableFlow.firstOrNull { it.contains("od") }}")
                sendFishes(mutableFlow)
            }
            delay(5000)
            logger.infoTitle("We get nothing")
            logger.infoSubTitle("Testing with replay. For each query, the stream replays the last 4 replay elements")
            CoroutineScope(Dispatchers.IO).launch {
                sendFishes(mutableFlowWithReplay)
                logger.infoTitle("Mutable Flows with replay 4")
                logger.info("The first fish we get is ${mutableFlowWithReplay.firstOrNull { it.contains("ardine") }}")
                logger.info("The second fish we get is ${mutableFlowWithReplay.firstOrNull { it.contains("od") }}")
                sendExtraFishes(mutableFlowWithReplay)
                delay(200)
                logger.info("The first fish we get is ${mutableFlowWithReplay.firstOrNull { it.contains("ardine") }}")
                logger.info("The second fish we get is ${mutableFlowWithReplay.firstOrNull { it.contains("od") }}")
                logger.info("The third fish we get is ${mutableFlowWithReplay.firstOrNull { it.contains("una") }}")
                logger.info("The fourth fish we get is ${mutableFlowWithReplay.firstOrNull { it.contains("alo") }}")

            }
            delay(5000)
            logger.infoTitle("We get only Sardine and Codfish. The 4 fishes later sent have no sardine and are now cached")
            CoroutineScope(Dispatchers.IO).launch {
                logger.infoTitle("Mutable Flows with replay 4 ... continued ... We now get the last 4 fishes")
                listOf( async {
                    delay( 200)
                    sendExtraFishes(mutableFlowWithReplay)
                }
                )
                logger.info("The first fish we get is ${mutableFlowWithReplay.firstOrNull { it.contains("apia") }}")
                logger.info("The second fish we get is ${mutableFlowWithReplay.firstOrNull { it.contains("almon") }}")
                logger.info("The third fish we get is ${mutableFlowWithReplay.firstOrNull { it.contains("una") }}")
                logger.info("The fourth fish we get is ${mutableFlowWithReplay.firstOrNull { it.contains("alo") }}")
            }
            delay(5000)
            logger.infoTitle("We get all the extra fishes")
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
                    mutableFlow.emit("Tilapia")
                    mutableFlow.emit("Salmon")
                    mutableFlow.emit("Tuna")
                    mutableFlow.emit("Robalo")
                    logger.infoText("Fishes sent!")
                }
            )
        }
    }
}