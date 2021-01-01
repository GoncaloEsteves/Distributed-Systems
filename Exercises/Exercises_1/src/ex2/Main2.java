package ex2;

import java.util.Scanner;

public class Main2 {
    public static void main (String args[]){
        System.out.println("Quantas threads pretende criar?");
        Scanner scan = new Scanner(System.in);
        String aux = scan.next();
        int N = Integer.parseInt(aux);
        System.out.println("Quantas vezes pretende que cada thread incremente?");
        aux = scan.next();
        int I = Integer.parseInt(aux);

        Counter1 c = new Counter1();
        c.executar(N, I);
    }
}