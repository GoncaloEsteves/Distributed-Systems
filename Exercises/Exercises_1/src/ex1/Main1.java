package ex1;

import java.util.Scanner;

public class Main1 {
    public static void main (String args[]){
        System.out.println("Quantas threads pretende criar?");
        Scanner scan = new Scanner(System.in);
        String aux = scan.next();
        int N = Integer.parseInt(aux);
        System.out.println("Quantas numeros pretende imprimir?");
        aux = scan.next();
        int I = Integer.parseInt(aux);

        Thread[] threads = new Thread[N];
        int i;
        for(i = 0; i < N; i++)
            threads[i] = new Thread(new AuxT(I, i));

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
    }
}
