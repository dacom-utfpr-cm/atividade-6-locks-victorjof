import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ArrayListThreadSafe{
    private ArrayList<Integer> array_list;
    private final ReentrantReadWriteLock rwl;
    private final Lock read_lock;
    private final Lock write_lock;


    public ArrayListThreadSafe() {

        array_list = new ArrayList<Integer>();
        rwl = new ReentrantReadWriteLock();
        read_lock = rwl.readLock();
        write_lock = rwl.writeLock();

    }

    public void add(int value){
        write_lock.lock();

        try{
            array_list.add(value);
        } finally {
            write_lock.unlock();
        }
    }


    public boolean isEmpty(){
        Boolean aux = true;

        read_lock.lock();
        try {
            aux = array_list.size() == 0;
        }finally {
            read_lock.unlock();
        }
        return aux;
    }


    public int remove_head(){
        write_lock.lock();

        try {
            if (!isEmpty()) {
                return array_list.remove(0);
            } else {
                return -1;//null
            }
        }finally {
            write_lock.unlock();
        }
    }


    public int get_head(){
        read_lock.lock();

        try{
            if (array_list.size() > 0) {//although its reentrant there's no need to acquire another read lock(i,e. by using isEmpty())
                return array_list.get(0);
            }
            else{
                return -1;
            }
        }finally {
            read_lock.unlock();
        }
    }

    public String toString() {
        read_lock.lock();

        try {
            String aux = "";
            aux += "[";

            for (Object value : this.array_list) {
                aux += (String.valueOf(value));
                aux += " ";
            }
            aux += "]";
            return aux;
        }finally {
            read_lock.unlock();
        }

    }
}
