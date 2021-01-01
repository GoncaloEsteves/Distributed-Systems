import java.util.concurrent.locks.ReentrantLock;

public class Conta {
    private double total;
    private ReentrantLock lockConta;

    public Conta(){
        this.total = 0;
        this.lockConta = new ReentrantLock();
    }

    public Conta(double t){
        this.total = t;
    }

    public synchronized double consulta(){
        return this.total;
    }

    public synchronized void deposita(int x){
        this.total += x;
    }

    public synchronized int levanta(int x){
        if(this.total < x)
            return 0;
        else {
            this.total -= x;
            return 1;
        }
    }

    public void abrir(){
        this.lockConta.unlock();
    }

    public void fechar(){
        this.lockConta.lock();
    }
}
