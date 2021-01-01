public class Boundedbuffer {
    private int[] values;
    private int max;
    private int used;
    private int debito;

    public Boundedbuffer(int N){
        this.max = N;
        this.used = 0;
        this.debito = 0;
        this.values = new int[N];
    }

    public int getUsed(){
        return this.used;
    }

    public synchronized void put(int v) throws InterruptedException {
        while (this.used == this.max)
            this.wait();
        this.values[this.used++] = v;
        this.debito++;
        this.notifyAll();
    }

    public synchronized int get() throws InterruptedException {
        int aux;
        while (this.used == 0)
            this.wait();
        aux = this.values[0];
        this.used--;
        System.arraycopy(this.values, 1, this.values, 0, this.used);
        this.debito++;
        this.notifyAll();
        return aux;
    }
}
