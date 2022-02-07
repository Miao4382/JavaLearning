## Optimistic lock vs. pessimistic lock
**Optimistic lock**
- The optimistic lock strategy is when you read a record, take note of a stamp/version number. When you want to read/write to that record, check if the stamp has changed. If it has changed (it means the record is dirty), the transaction should be aborted
- This strategy is most applicable to high-volume systems and three-tier architectures where you do not necessarily maintain a connection to the database for your session. In this situation the client cannot actually maintain database locks as the connections are taken from a pool and you may not be using the same connection from one access to the next.
**Pessimistic lock**
- Pessimistic lock will lock the record for you exclusively until you have finished all the transaction operation. It has better integrity than optimistic lock at the expense of more overhead.

## How to resolve deadlocks in SQL server
- Deadlocks happen when two (or more) operations want to access resources locked by the other one. The involved operations will wait indefinitely for the other to give up locks.
- When deadlock happens, SQL server should select one of the operation as the victim, and force it abort and rollback all actions, release the locked resources (thus breaking the circular dependency on resources).
- More practically, the DBMS will analyze the operations whether they can create a deadlock situation or not. If they do, that transaction is never allowed to be executed. The deadlock prevention mechanism proposes two schemes:
  - Wait die scheme
    - In this scheme, If a transaction requests a resource that is locked by another transaction, then the DBMS simply checks the timestamp of both transactions and allows the older transaction to wait until the resource is available for execution. 
  - Wound wait scheme
    - In this scheme, if an older transaction requests for a resource held by a younger transaction, then an older transaction forces a younger transaction to kill the transaction and release the resource. 
    - If the younger transaction is requesting a resource that is held by an older one, then the younger transaction is asked to wait till the older one releases it. 

## Live lock
A live lock is a situation where a request for an exclusive lock is denied repeatedly due to many overlapping shared locks keep on interfering each other. The name of live lock suggests that there is no operations being blocked, but all the involved operations could not obtain the resources they require.

An easiest example of Livelock would be two people who meet face-to-face in a corridor, and both of them move aside to let the other pass. They end up moving from side to side without making any progress as they move the same way at the time. Here, they never cross each other.

## Distributed transaction design pattern: Saga
The Saga design pattern is a way to manage data consistency across microservices in distributed transaction scenarios. A saga is a sequence of transactions that updates each service and publishes a message or event to trigger the next transaction step. If a step in the sequence fails, the saga executes compensating transactions that counteract the preceding transactions.

**Transaction types in Saga**
- Compensable transaction: transactions that can potentially be reversed
- Pivot transaction: if the pivot transaction commits, the saga runs until completion.
- Retryable transactions: they are transactions follow the pivot transaction and are guaranteed to succeed.

**Two common implementation approaches**
- Choreography
  - Event based
  - Saga participants exchange events without a centralized control.
  - Each local transaction publishes domain events that trigger local transactions in other services.
- Orchestration
  - Command based
  - Use a centralized controller (saga orchestrator) tells the saga participants what local transactions to execute.
  - The saga orchestrator handles all the transactions and tells the participants which operations to perform based on the events. It also handles failure recovery with compensating transactions.
