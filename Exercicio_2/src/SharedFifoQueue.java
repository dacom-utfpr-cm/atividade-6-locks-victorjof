import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


public class SharedFifoQueue {
    private Queue queue;
    private int max_size;
    private ReentrantLock lock;
    private Condition full_lock;
    private Condition empty_lock;


    public SharedFifoQueue(int max_size) {
        queue = new LinkedList<Integer>();
        this.max_size = max_size;
        lock = new ReentrantLock();
        full_lock = lock.newCondition();
        empty_lock = lock.newCondition();
    }

    private boolean isFull() {
        return (queue.size() >= this.max_size);
    }

    public void add(int value) throws InterruptedException {
        lock.lock();
        try {
            while (isFull()) {//if the queue is full blocks the add operation. Also deals with Spurious Wakeup
                full_lock.await();//awaits some remove operation
            }

            queue.add(value);//adds an element
            empty_lock.signal();//added one element, so it`s guaranteed that the queue is not empty -> notify it
        } finally {//Prevents starvation of other threads(competing for the lock/resource)
            lock.unlock();
        }
    }

    public int remove() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()) {// if the queue is full you can't remove an element
                empty_lock.await(); //awaits some add operation
            }
            int temp = (int) queue.remove();
            full_lock.signal(); //signals that the queue is not full anymore
            return temp;

        } finally {//Prevents starvation of other threads(competing for the lock/resource)
            lock.unlock();
        }
    }

    public String toString() {
        lock.lock();
        try {
            String aux = "";
            aux += "[";

            for (Object value : this.queue) {
                aux += (String.valueOf(value));
                aux += " ";
            }
            aux += "]";
            return aux;

        } finally {
            lock.unlock();

        }
    }

}
