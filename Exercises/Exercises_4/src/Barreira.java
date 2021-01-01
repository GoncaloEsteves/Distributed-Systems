public class Barreira {
    int N;
    int atual;

    public Barreira(int max){
        this.N = max;
        this.atual = 0;
    }

    public void esperar() throws InterruptedException {
        this.atual++;
        while ((this.atual % this.N) != 0)
            wait();
        notifyAll();
    }
}
