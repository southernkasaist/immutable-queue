# Immutable Queue

The Java implementation of immutable queue.

# Usage

```java
// [1, 2, 3]
Queue<Integer> q = ImmutableCollections.<Integer>newQueue().enQueue(1).enQueue(2).enQueue(3);
while (!q.isEmpty()) {
    System.out.println(q.head());
    q = q.deQueue();
}
```

Output:
```
1
2
3
```