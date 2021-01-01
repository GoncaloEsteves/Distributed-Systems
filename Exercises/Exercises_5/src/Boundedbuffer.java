import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Boundedbuffer {
    private int[] values;
    private int max;
    private int used;
    private int debito;
    private ReentrantLock lock;
    private Condition cheio;
    private Condition vazio;

    public Boundedbuffer(int N){
        this.max = N;
        this.used = 0;
        this.debito = 0;
        this.values = new int[N];
        this.lock = new ReentrantLock();
        this.cheio = this.lock.newCondition();
        this.vazio = this.lock.newCondition();
    }

    public int getUsed(){
        return this.used;
    }

    public void put(int v) throws InterruptedException {
        this.lock.lock();
        while (this.used == this.max)
            this.cheio.await();
        this.values[this.used++] = v;
        this.debito++;
        this.vazio.signal();
        this.lock.unlock();
    }

    public int get() throws InterruptedException {
        this.lock.lock();
        int aux;
        while (this.used == 0)
            this.vazio.await();
        aux = this.values[0];
        this.used--;
        System.arraycopy(this.values, 1, this.values, 0, this.used);
        this.debito++;
        this.cheio.signal();
        this.lock.unlock();
        return aux;
    }
}