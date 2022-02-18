# Homework 5.1

`Future` vs. `CompletableFuture`
- `Future` is used as a reference to the result of an asynchronous computation. We can use `isDone()` to check if the computation is done or not. To get the result of the computation, we can use `get()`.
- `CompletableFuture` is an extension to `Future`. It is a class that implements `Future` interface and `CompletionStage` interface, providing additional APIs for managing asynchronous computation, e.g. manually completion, creating, chaining and combining multiple `Futures`.

Please check the demo code for more details.
