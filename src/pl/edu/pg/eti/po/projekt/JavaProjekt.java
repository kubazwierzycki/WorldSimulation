package pl.edu.pg.eti.po.projekt;

import pl.edu.pg.eti.po.projekt.organizm.*;
import pl.edu.pg.eti.po.projekt.swiat.Swiat;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class JavaProjekt {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Czy chcesz wczytac zapisana gre: t/n");
        Scanner odp = new Scanner(System.in);
        char wczytaj = odp.next().charAt(0);
        if(wczytaj == 't'){
            Scanner odczyt = new Scanner(new File("gra.txt"));
            int w, h;
            String line = odczyt.nextLine();
            w = Integer.parseInt(line);
            line = odczyt.nextLine();
            h = Integer.parseInt(line);
            Swiat mapa = new Swiat(w, h);

            char ozn = ' ';
            int x = 0, y = 0, sil = 0, wie = 0, czasU = 0, inicjatywa = 0;
            int k = 0;

            while((line = odczyt.nextLine()) != null){
                if(k == 6){
                    if(ozn == 'C'){
                        Czlowiek nowy = new Czlowiek(x, y, wie, sil, inicjatywa, mapa, czasU);
                        mapa.dodajOrganizm(nowy);
                    }
                    if(ozn == 'W'){
                        Wilk nowy = new Wilk(x, y, wie, sil, inicjatywa, mapa);
                        mapa.dodajOrganizm(nowy);
                    }
                    if(ozn == 'O'){
                        Owca nowy = new Owca(x, y, wie, sil, inicjatywa, mapa);
                        mapa.dodajOrganizm(nowy);
                    }
                    if(ozn == 'A'){
                        Antylopa nowy = new Antylopa(x, y, wie, sil, inicjatywa, mapa);
                        mapa.dodajOrganizm(nowy);
                    }
                    if(ozn == 'L'){
                        Lis nowy = new Lis(x, y, wie, sil, inicjatywa, mapa);
                        mapa.dodajOrganizm(nowy);
                    }
                    if(ozn == 'Z'){
                        Zolw nowy = new Zolw(x, y, wie, sil, inicjatywa, mapa);
                        mapa.dodajOrganizm(nowy);
                    }
                    if(ozn == 'j'){
                        WilczeJagody nowy = new WilczeJagody(x, y, wie, sil, inicjatywa, mapa);
                        mapa.dodajOrganizm(nowy);
                    }
                    if(ozn == 'b'){
                        BarszczSosnowskiego nowy = new BarszczSosnowskiego(x, y, wie, sil, inicjatywa, mapa);
                        mapa.dodajOrganizm(nowy);
                    }
                    if(ozn == 't'){
                        Trawa nowy = new Trawa(x, y, wie, sil, inicjatywa, mapa);
                        mapa.dodajOrganizm(nowy);
                    }
                    if(ozn == 'm'){
                        Mlecz nowy = new Mlecz(x, y, wie, sil, inicjatywa, mapa);
                        mapa.dodajOrganizm(nowy);
                    }
                    if(ozn == 'g'){
                        Guarana nowy = new Guarana(x, y, wie, sil, inicjatywa, mapa);
                        mapa.dodajOrganizm(nowy);
                    }
                    mapa.setOnBoard(x, y, ozn);
                    k = 0;
                }

                if(k == 0){
                    ozn = line.charAt(0);
                }
                else if(k == 1){
                    x = Integer.parseInt(line);
                }
                else if(k == 2){
                    y = Integer.parseInt(line);
                }
                else if(k == 3){
                    sil = Integer.parseInt(line);
                }
                else if(k == 4){
                    wie = Integer.parseInt(line);
                }
                else if(k == 5){
                    inicjatywa = Integer.parseInt(line);
                    if(ozn == 'C'){
                        line = odczyt.nextLine();
                        czasU = Integer.parseInt(line);
                    }
                }
                k++;
            }
        }
        else if(wczytaj == 'n'){
            Swiat mapa = new Swiat();
        }

    }
}
