package com.company;

import java.util.Scanner;

public class Rozgrywka {
    static Scanner sc = new Scanner(System.in);


    public static String getUserInput() {
        return sc.nextLine();
    }

    private String[][] plansza;
    public Rozgrywka() {
        this.plansza=new String[3][3];
        for(int i=0; i< this.plansza.length; i++)
            for(int j=0; j< this.plansza[i].length; j++)
                this.plansza[i][j] = " ";
    }




    public String[][] getPlansza() {
        return plansza;
    }




    public void setPlansza(String[][] plansza) {
        this.plansza = plansza;
    }




    public void drukujPlansze() {
        System.out.println("  1 2 3");

        for (int i=0;i<3;i++) {
            System.out.print(i+1);
            for (int j=0;j<3;j++) {
                System.out.print(" "+this.getPlansza()[i][j]);
            }
            System.out.println("");

        }
        System.out.println("\n");
    }
    public void wstawX(int x, int y) {
        String[][] planszaTemp=this.getPlansza();
        planszaTemp[x-1][y-1]="x";
        this.setPlansza(planszaTemp);
    }
    public void wstawO(int x, int y) {
        String[][] planszaTemp=this.getPlansza();
        planszaTemp[x-1][y-1]="O";
        this.setPlansza(planszaTemp);
    }

    public boolean sprawdzCzyWygrana() {
        for (int i=0;i<3;i++) {
            String wiersz1=this.getPlansza()[i][0];
            String wiersz2=this.getPlansza()[i][1];
            String wiersz3=this.getPlansza()[i][2];

            String kolumna1=this.getPlansza()[0][i];
            String kolumna2=this.getPlansza()[1][i];
            String kolumna3=this.getPlansza()[2][i];

            if (!(wiersz1.equals(" ")) && wiersz1== wiersz2 && wiersz1 == wiersz3) {
                if (wiersz1.equals("x")) {
                    System.out.println("Wygrał gracz X\n");
                } else {
                    System.out.println("Wygrał gracz O\n");
                }
                return true;
            }else if (!(kolumna1.equals(" ")) && kolumna1== kolumna2 && kolumna1 == kolumna3) {
                if (kolumna1.equals("x")) {
                    System.out.println("Wygrał gracz X\n");
                } else {
                    System.out.println("Wygrał gracz O\n");
                }
                return true;
            }

        }

        if ((!this.getPlansza()[0][0].equals(" ")&&
                this.getPlansza()[0][0]==this.getPlansza()[1][1] &&
                this.getPlansza()[0][0]==this.getPlansza()[2][2])||(
                !this.getPlansza()[0][2].equals(" ")&&
                        this.getPlansza()[0][2]==this.getPlansza()[1][1] &&
                        this.getPlansza()[0][2]==this.getPlansza()[2][0])) {
            if (this.getPlansza()[1][1].equals("x")) {
                System.out.println("Wygrał gracz X\n");
            } else {
                System.out.println("Wygrał gracz O\n");
            }
            return true;
        }
        else
            return false;
    }
    public int zwrocWspolrzedna() {
        int wsp=0;
        do {
            try {
                wsp=Integer.valueOf(getUserInput());
            } catch (NumberFormatException nfe) {
                System.out.println("Wyjątek - zły format liczby");
            }
        }while(wsp==0);
        return wsp;
    }


    public void partia() {

        float i=(Math.round(Math.random()));

        do {
            this.drukujPlansze();
            int y=0;
            int x=0;
            if (i%2==0) {

                System.out.println("Gracz X");
                System.out.println("Podaj pierwszą współrzędną");
                x=zwrocWspolrzedna();
                while(!(x==1 || x==2 || x==3)){
                    System.out.println("Podano błędną współrzędną. Podaj ją jeszcze raz");
                    x=zwrocWspolrzedna();
                }
                System.out.println("Podaj drugą współrzędną");
                y=zwrocWspolrzedna();
                while(!(y==1 || y==2 || y==3)){
                    System.out.println("Podano błędną współrzędną. Podaj ją jeszcze raz");
                    y=zwrocWspolrzedna();
                }
                while(!this.getPlansza()[x-1][y-1].equals(" ")) {
                    System.out.println("Pole jest już zajęte, podaj współrzędne jeszcze raz");
                    System.out.println("Gracz X");
                    System.out.println("Podaj pierwszą współrzędną");
                    x=zwrocWspolrzedna();
                    while(!(x==1 || x==2 || x==3)){
                        System.out.println("Podano błędną współrzędną. Podaj ją jeszcze raz");
                        x=zwrocWspolrzedna();
                    }
                    System.out.println("Podaj drugą współrzędną");
                    y=zwrocWspolrzedna();
                    while(!(y==1 || y==2 || y==3)){
                        System.out.println("Podano błędną współrzędną. Podaj ją jeszcze raz");
                        y=zwrocWspolrzedna();
                    }
                }
                this.wstawX(x,y);
            }
            else {

                System.out.println("Gracz O");
                System.out.println("Podaj pierwszą współrzędną");
                x=zwrocWspolrzedna();
                while(!(x==1 || x==2 || x==3)){
                    System.out.println("Podano błędną współrzędną. Podaj ją jeszcze raz");
                    x=zwrocWspolrzedna();
                }
                System.out.println("Podaj drugą współrzędną");
                y=zwrocWspolrzedna();
                while(!(y==1 || y==2 || y==3)){
                    System.out.println("Podano błędną współrzędną. Podaj ją jeszcze raz");
                    y=zwrocWspolrzedna();
                }

                while(!this.getPlansza()[x-1][y-1].equals(" ")) {
                    System.out.println("Pole jest już zajęte, podaj współrzędne jeszcze raz");
                    System.out.println("Gracz O");
                    System.out.println("Podaj pierwszą współrzędną");
                    x=zwrocWspolrzedna();
                    while(!(x==1 || x==2 || x==3)){
                        System.out.println("Podano błędną współrzędną. Podaj ją jeszcze raz");
                        x=zwrocWspolrzedna();
                    }
                    System.out.println("Podaj drugą współrzędną");
                    y=zwrocWspolrzedna();
                    while(!(y==1 || y==2 || y==3)){
                        System.out.println("Podano błędną współrzędną. Podaj ją jeszcze raz");
                        y=zwrocWspolrzedna();
                    }
                }
                this.wstawO(x,y);
            }
            i++;

        } while(this.sprawdzCzyWygrana()==false);
        this.drukujPlansze();
        System.out.println("\nKONIEC GRY");
    }
}
