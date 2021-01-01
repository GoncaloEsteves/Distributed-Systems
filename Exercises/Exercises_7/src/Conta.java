import java.util.concurrent.locks.ReentrantLock;

public class Conta {
    private double saldo;
    private ReentrantLock l;
    private boolean closed;

    public Conta(double saldo) {
        this.l = new ReentrantLock();
        this.saldo = saldo;
        this.closed = false;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public boolean closed() {
        return this.closed;
    }
    public void setClosed(boolean b){
        this.closed = b;
    }

    public void lock(){
        this.l.lock();
    }

    public void unlock(){
        this.l.unlock();
    }

    public void levantar(double total) throws SaldoInsuficiente {
        this.l.lock();
        if(this.saldo < total) {
            this.l.unlock();
            throw new SaldoInsuficiente();
        }
        else{
            this.saldo -= total;
            this.l.unlock();
        }
    }

    public void depositar(double total){
        this.l.lock();
        this.saldo += total;
        this.l.unlock();
    }
}