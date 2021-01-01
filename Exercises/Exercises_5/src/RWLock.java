import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class RWLock {
    private ReentrantLock lock;
    private Condition write;
    private Condition read;
    private int writers;
    private int readers;
    private int writeRequest;

    public RWLock(){
        this.lock = new ReentrantLock();
        this.write = this.lock.newCondition();
        this.read = this.lock.newCondition();
        this.writers = 0;
        this.readers = 0;
        this.writeRequest = 0;
    }

    public void readLock() throws InterruptedException {
        this.lock.lock();
        try{
            while(this.writers > 0 || this.writeRequest > 0)
                this.read.await();
            this.readers++;
        }
        finally {
            this.lock.unlock();
        }
    }

    public void readUnlock(){
        this.lock.lock();
        try {
            this.readers--;
            this.read.signalAll();
        }
        finally {
            this.lock.unlock();
        }
    }

    public void writeLock() throws InterruptedException{
        this.lock.lock();
        try {
            while(this.writers > 0 || this.readers > 0){
                this.writeRequest++;
                this.write.await();
            }
            this.writers++;
            this.writeRequest--;
        }
        finally {
            this.lock.unlock();
        }

    }

    public void writeUnlock(){
        this.lock.lock();
        try {
            this.writers--;
            this.write.signal();
            this.read.signalAll();
        }
        finally {
            this.lock.unlock();
        }

    }
}
