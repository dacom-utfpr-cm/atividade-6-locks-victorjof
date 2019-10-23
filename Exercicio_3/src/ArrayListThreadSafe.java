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
        try{
            write_lock.lock();
            array_list.add(value);
        } finally {
            write_lock.unlock();
        }
    }


    public int remove_head(){
        try {
            write_lock.lock();
            if (array_list.size() > 0) {
                return array_list.remove(0);
            } else {
                return -1;
            }
        }finally {
            write_lock.unlock();
        }
    }


    public int get_head(){
        try{
            read_lock.lock();
            if (array_list.size() > 0) {
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
        try {
            read_lock.lock();
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
