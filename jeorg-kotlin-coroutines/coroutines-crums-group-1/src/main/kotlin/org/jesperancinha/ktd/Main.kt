package org.jesperancinha.ktd

import NoInlineRun
import org.jesperancinha.ktd.exceptions.HandlingExceptions
import org.jesperancinha.ktd.interpolist.Passed
import org.jesperancinha.ktd.overloads.GreetJava

object Main {
    @JvmStatic
    fun main(args: Array<String> = emptyArray()) {
        HandlingExceptions.main()
        CancellationWithException.main()
        UniversalLauncher.main()
        SupervisorIntended.main()
        TrainStationManager.main()
        NeuralNetworksCancelling.main()
        StructuredConcurrency.main()
        ValidationUsersEmail.main()
        ContractsExtension.main()
        org.jesperancinha.ktd.java.ValidationUsersEmail.main(emptyArray())
        CancellingCounterCoroutines.main()
        RunningFold.main()
        TicketCollectors.main()
        SimpleConcurrency.main()
        GlobalScopeCoroutine.main()
        MainDispatcherLauncher.main()
        UnconfinedCats.main()
        CoroutineJob.main()
        SupervisorJobTest.main()
        NoInlineRun.main()
        SupervisorHandlingExceptions.main()
        SuspendCoroutine.main()
        CoroutinesBuffer.main()
        FlowExceptions.main()
        YieldCoroutines.main()
        LambdasWithCoroutines.main()
        DaemonThread.main()
        FlowsCombined.main()
        UncollectedFlows.main()
        FlowOperations.main()
        ConflatedChannel.main()
        BufferedFlows.main()
        FlowOnEach.main()
        TryEmitTrySend.main()
        SendFlows.main()
        GreetJava.main(args)
        Passed.main()
        CoroutinesChannelFlowBuffer.main()
        Debouncing.main()
        StateSharedFlow.main()
        CollectLatestCoroutines.main()
        ClosedChannel.main()
        ActorsAndProducers.main()
        FlowWithRetries.main()
        MergeFlows.main()
        ZipVsCombine.main()
        ConflatedFlow.main()
        FlatMapConcat.main()
    }
}