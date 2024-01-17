package org.jesperancinha.ktd

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        CancellationWithException.main()
        UniversalLauncher.main()
        SupervisorIntended.main()
        TrainStationManager.main()
        NeuralNetworksCancelling.main()
        StructuredConcurrency.main()
        ValidationUsersEmail.main()
        ContractsExtension.main()
        org.jesperancinha.ktd.java.ValidationUsersEmail.main(emptyArray())
    }
}