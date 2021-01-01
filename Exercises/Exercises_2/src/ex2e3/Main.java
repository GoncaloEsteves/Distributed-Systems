package ex2e3;

public class Main {
    public static void main(String args[]){
        Banco b = new Banco(2);
        b.debito(0, 1000);
        Thread t1 = new Thread(new Cliente1(b));
        Thread t2 = new Thread(new Cliente2(b));

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        }
        catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(b.consulta(1));
    }
}
