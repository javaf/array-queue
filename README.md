Array stack is a bounded lock-based stack using an
array. It uses a common lock for both push and pop
operations.

```java
push():
1. Lock stack.
2. Try push.
3. Unlock stack.
```

```java
pop():
1. Lock stack.
2. Try pop.
3. Unlock stack.
```

```java
tryPush():
1. Ensure stack is not full
2. Save data at top.
3. Increment top.
```

```java
tryPop():
1. Ensure stack is not empty.
2. Decrement top.
3. Return data at top.
```

```bash
## OUTPUT
Starting 10 threads with sequential stack
7: failed pop
1: failed pop
5: failed pop
8: failed pop
9: failed pop
1: popped 0/1000 values
5: popped 158/1000 values
7: popped 0/1000 values
8: popped 0/1000 values
9: popped 31/1000 values
Was LIFO? false

Starting 10 threads with array stack
Was LIFO? true
```

See [ArrayStack.java] for code, [Main.java] for test, and [repl.it] for output.

[ArrayStack.java]: https://repl.it/@wolfram77/array-stack#ArrayStack.java
[Main.java]: https://repl.it/@wolfram77/array-stack#Main.java
[repl.it]: https://array-stack.wolfram77.repl.run


### references

- [The Art of Multiprocessor Programming :: Maurice Herlihy, Nir Shavit](https://dl.acm.org/doi/book/10.5555/2385452)
