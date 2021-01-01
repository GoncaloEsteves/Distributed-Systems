package ex4;

public class Conta {
    double total;

    public Conta(){
        this.total = 0;
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
}