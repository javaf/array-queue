import java.nio.*;
import java.util.concurrent.locks.*;

// Array stack is a bounded lock-based stack using an
// array. It uses a common lock for both push and pop
// operations.

class ArrayStack<T> {
  Lock lock;
  T[] data;
  int top;
  // lock: common lock for push, pop
  // data: array of values in stack
  // top: top of stack (0 if empty)

  @SuppressWarnings("unchecked")
  public ArrayStack(int capacity) {
    lock = new ReentrantLock();
    data = (T[]) new Object[capacity];
    top = 0;
  }

  // 1. Lock stack.
  // 2. Try push.
  // 3. Unlock stack.
  public void push(T x) throws BufferOverflowException {
    try {
    lock.lock();   // 1
    tryPush(x);    // 2
    } finally {
    lock.unlock(); // 3
    }
  }

  // 1. Lock stack.
  // 2. Try pop.
  // 3. Unlock stack.
  public T pop() throws BufferUnderflowException {
    try {
    lock.lock();     // 1
    return tryPop(); // 2
    } finally {
    lock.unlock();   // 3
    }
  }

  // 1. Ensure stack is not full
  // 2. Save data at top.
  // 3. Increment top.
  protected void tryPush(T x)
    throws BufferOverflowException {
    if (top == data.length)                // 1
      throw new BufferOverflowException(); // 1
    data[top++] = x; // 2, 3
  }

  // 1. Ensure stack is not empty.
  // 2. Decrement top.
  // 3. Return data at top.
  protected T tryPop()
    throws BufferUnderflowException {
    if (top == 0)                           // 1
      throw new BufferUnderflowException(); // 1
    return data[--top]; // 2, 3
  }
}
