public class Counter {
    private int count;
    int past_value;
    Lock lock;

    public Counter() {
        count = 0;
        lock = new Lock();
    }

    public void increment() {
        lock.lock();//avoids race condition

        try {
            past_value = count;
            this.count += 1;
            System.out.printf("Incremented value %d to %d%n", past_value, this.count);
        } finally {
            lock.unlock();//Prevents starvation of other threads in case something goes wrong inside the try block
        }
    }

    public void decrement() {
        lock.lock();//avoids race condition

        try {
            past_value = count;
            this.count -= 1;
            System.out.printf("Decremented value %d to %d%n", past_value, this.count);
        } finally {
            lock.unlock();//Prevents starvation of other threads in case something goes wrong inside the try block
        }

    }

}
