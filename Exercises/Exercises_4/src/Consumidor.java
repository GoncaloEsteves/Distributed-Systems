public class Consumidor implements Runnable {
    private Boundedbuffer buffer;
    private int id;

    public Consumidor(Boundedbuffer b, int i){
        this.buffer = b;
        this.id = i;
    }

    public void run(){
        try {
            for(int i = 0; i < 10; i++)
                System.out.println("Consumidor nº" + this.id + " recebeu " + this.buffer.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
