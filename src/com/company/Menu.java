package com.company;

import java.util.Scanner;

public class Menu {
    static Scanner sc = new Scanner(System.in);


    public static String getUserInput() {
        return sc.nextLine();
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        while(true) {
            System.out.println("MENU");
            System.out.println("[N]OWA GRA");
            System.out.println("[W]YJSCIE\n");
            String odczyt=getUserInput();
            if (odczyt.equals("N")) {
                uruchomGre();
            }
            else if (odczyt.equals("W"))
                break;
            else
                System.out.println("Wpisano niepoprawną literę");
        }
    }
    public static void uruchomGre() {

        Rozgrywka nowaGra=new Rozgrywka();
        nowaGra.partia();
    }

}
