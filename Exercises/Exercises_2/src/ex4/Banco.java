package ex4;

public class Banco {
    private int N;
    private Conta[] contas;

    public Banco(int x){
        this.N = x;
        this.contas = new Conta[x];
        for(int i = 0; i < this.N; i++)
            this.contas[i] = new Conta();
    }

    public double consultar(int i) {
        return this.contas[i].consulta();
    }

    public int levantar(int i, int x) {
        return this.contas[i].levanta(x);
    }

    public void depositar(int i, int x) {
        this.contas[i].deposita(x);
    }

    public int transferir(int i1, int i2, int x) {
        int max = Math.max(i1, i2);
        int min = Math.min(i1, i2);
        synchronized (this.contas[min]) {
            synchronized (this.contas[max]) {
                int aux = this.levantar(i1, x);
                if (aux == 0)
                    return 0;
                else {
                    this.depositar(i2, x);
                    return 1;
                }
            }
        }
    }
}
