package ex2;

public class CounterInc implements Runnable{
    private int contador;
    private int max;

    public CounterInc (){
        this.contador = 0;
        this.max = 0;
    }

    public void run(){
        for(int i = 0; i < this.max; i++)
            this.incrementar();
    }

    public synchronized void incrementar(){
        this.contador++;
    }

    public void executar(int N, int I){

        this.max = I;
        Thread[] threads = new Thread[N];
        int i;
        for(i = 0; i < N; i++)
            threads[i] = new Thread(this);

        for(i = 0; i < N; i++)
            threads[i].start();

        try {
            System.out.println("Esperando que as threads acabem...");
            for (i = 0; i < N; i++){
                threads[i].join();
                System.out.println("Thread " + threads[i].getId() + " - acabou.");
            }
            System.out.println("Fim.");
        }
        catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(this.contador);
    }
}
