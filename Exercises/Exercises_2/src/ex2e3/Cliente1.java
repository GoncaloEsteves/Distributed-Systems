package ex2e3;

public class Cliente1 implements Runnable {
    private Banco banco;

    public Cliente1(Banco b){
        this.banco = b;
    }
    public void run(){
        for(int i = 0; i < 100000; i++)
            banco.debito(0, 5);
        //this.banco.transferir(0, 1, 1000);
    }
}
