import java.util.Scanner;

public class Main {
    public static void main(String args[]) throws InterruptedException {
        System.out.println("Tamanho do array:");
        Scanner scan = new Scanner(System.in);
        String aux = scan.next();
        int N = Integer.parseInt(aux);
        Boundedbuffer buffer = new Boundedbuffer(N);

        System.out.println("Número de consumidores:");
        aux = scan.next();
        int Nc = Integer.parseInt(aux);
        Thread consumidores[] = new Thread[Nc];
        int i;
        for(i = 0; i < Nc; i++)
            consumidores[i] = new Thread(new Consumidor(buffer, i));

        System.out.println("Número de produtores:");
        aux = scan.next();
        int Np = Integer.parseInt(aux);
        Thread produtores[] = new Thread[Np];
        for(i = 0; i < Np; i++)
            produtores[i] = new Thread(new Produtor(buffer, i));

        for(i = 0; i < Np; i++)
            produtores[i].start();

        for(i = 0; i < Nc; i++)
            consumidores[i].start();

        try {
            for(i = 0; i < Np; i++)
                produtores[i].join();
            for(i = 0; i < Nc; i++)
                consumidores[i].join();
        }
        catch (InterruptedException e){
            System.out.println(e.getMessage());
        }

        System.out.println(buffer.getUsed());
    }
}
