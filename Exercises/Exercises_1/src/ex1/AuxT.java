package ex1;

public class AuxT implements Runnable{
    private int I;
    private int id;

    public AuxT (int a, int i){
        this.I = a;
        this.id = i;
    }

    public void run (){
        for(int i = 1; i <= I; i++)
            System.out.println("Thread nº: " + this.id + " - " + i);
    }
}