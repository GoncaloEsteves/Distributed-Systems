package ex2e3;

public class Banco {
    private int N;
    private double[] contas;

    public Banco(int x){
        this.N = x;
        this.contas = new double[x];
        for(int i = 0; i < this.N; i++)
            this.contas[i] = 0;
    }

    public double consulta(int i) {
        synchronized (this.contas) {
            return this.contas[i];
        }
    }

    public int credito(int i, int x) {
        synchronized (this.contas) {
            if (this.contas[i] < x)
                return 0;
            else{
                this.contas[i] -= x;
                return 1;
            }
        }
    }

    public void debito(int i, int x) {
        synchronized (this.contas) {
            this.contas[i] += x;
        }
    }

    public synchronized int transferir(int i1, int i2, int x){
        int aux = this.credito(i1, x);
        if(aux == 0)
            return 0;
        else {
            this.debito(i2, x);
            return 1;
        }
    }
}
