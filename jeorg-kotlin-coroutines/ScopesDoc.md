1. Deep Dive into CoroutineScope
   CoroutineScope vs. GlobalScope vs. custom scopes
   When to use supervisorScope vs. coroutineScope
   Structured concurrency and why it's important
2. Lifecycle & Cancellation
   How coroutine scopes handle cancellation
   Job vs. SupervisorJob in scopes
   Handling exceptions: CoroutineExceptionHandler and supervisorScope
   Cooperative cancellation (isActive, ensureActive(), yield())
3. Context Propagation & Inheritance
   CoroutineContext structure and key elements (Job, Dispatcher, etc.)
   How Job and CoroutineDispatcher are inherited across scopes
   Using withContext() effectively vs. launching a new coroutine
   Context switching pitfalls
4. Testing Coroutine Scopes
   Using TestScope and runTest
   Controlling time with TestDispatcher
   Avoiding leaks with advanceTimeBy() and pauseDispatcher()
5. Performance Considerations & Best Practices
   Scope management in Android & backend applications
   Avoiding memory leaks (viewModelScope, lifecycleScope, etc.)
   Optimizing coroutine scope usage in high-load systems
   Alternatives to coroutine scopes (flow, channel, actors)