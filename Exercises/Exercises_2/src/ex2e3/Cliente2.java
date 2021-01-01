package ex2e3;

public class Cliente2 implements Runnable {
    private Banco banco;

    public Cliente2(Banco b){
        this.banco = b;
    }
    public void run(){
        for(int i = 0; i < 100000; i++)
            banco.credito(0, 5);
        //this.banco.credito(1, 1000);
    }
}