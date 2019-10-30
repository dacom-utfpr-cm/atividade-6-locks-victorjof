/*
 *  Simple lock class implementation for learning purposes.
 * */
public class Lock {
    private boolean isLocked;
    private Thread locker;
    private int count_locked;

    public Lock() {
        isLocked = false;
        locker = null;
        count_locked = 0;
    }

    private synchronized boolean isLockedByCurrentThread(){
        return(this.locker == Thread.currentThread());
    }

    public synchronized void lock() {
        //Executes in case of a second lock without a prior unlock.
        while(isLocked && !isLockedByCurrentThread()){ // Second condition allows lock reentry
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.isLocked = true;
        this.count_locked+=1;
        this.locker = Thread.currentThread();
    }

    public synchronized void unlock(){

        if(isLockedByCurrentThread()){
            this.count_locked-=1;
            if(this.count_locked == 0){
                isLocked = false;
                this.locker = null;
                notify();
            }
        }//else does nothing -> allows reentry operation

    }


}

