public class Produtor implements Runnable {
    private Boundedbuffer buffer;
    private int id;

    public Produtor(Boundedbuffer b, int i){
        this.buffer = b;
        this.id = i;
    }

    public void run(){
        try {
            for(int i = 0; i < 10; i++)
                this.buffer.put((i+1)*id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
