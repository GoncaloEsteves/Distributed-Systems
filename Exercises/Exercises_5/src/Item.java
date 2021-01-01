import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Item {
    private ReentrantLock lock;
    private Condition isEmpty;
    private int quantidade;

    public Item(){
        this.lock = new ReentrantLock();
        this.isEmpty = this.lock.newCondition();
        this.quantidade = 0;
    }

    public void supply(int q){
        this.lock.lock();
        this.quantidade += q;
        this.isEmpty.signal();
        this.lock.unlock();
    }

    public void consume() throws InterruptedException {
        this.lock.unlock();
        while (this.quantidade == 0)
            this.isEmpty.await();
        this.quantidade--;
        this.lock.unlock();
    }
}
