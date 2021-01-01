package ex2;

import java.util.Scanner;

public class MainInc {
    public static void main (String args[]){
        System.out.println("Quantas threads pretende criar?");
        Scanner scan = new Scanner(System.in);
        String aux = scan.next();
        int N = Integer.parseInt(aux);
        System.out.println("Quantas vezes pretende que cada thread incremente?");
        aux = scan.next();
        int I = Integer.parseInt(aux);

        CounterInc c = new CounterInc();
        c.executar(N, I);
    }
}