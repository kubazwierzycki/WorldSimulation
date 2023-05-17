package pl.edu.pg.eti.po.projekt.swiat;

import pl.edu.pg.eti.po.projekt.myframe.MyFrame;
import pl.edu.pg.eti.po.projekt.organizm.Czlowiek;
import pl.edu.pg.eti.po.projekt.organizm.Organizm;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;


public class Swiat {
    private int width;
    private int height;
    private MyFrame board;
    private Vector<Organizm> organizmy = new Vector<>();
    private int iloscOrganizmow;
    private int kierunekCzlowieka;
    private boolean aktywowano;
    private Comparator<Organizm> compare = Comparator.comparing(Organizm::getInicjatywa).thenComparing(Organizm::getWiek);

    public Swiat(){
        this.iloscOrganizmow = 0;
        this.width = 20;
        this.height = 20;
        this.board = new MyFrame(width, height, this);
        Czlowiek nowy = new Czlowiek(width / 2, height / 2, this);
        this.dodajOrganizm(nowy);
        this.setOnBoard(width / 2, height / 2, nowy.getOznaczenie());
        this.kierunekCzlowieka = 0;
    }
    public Swiat(int width, int height){
        this.iloscOrganizmow = 0;
        this.width = width;
        this.height = height;
        this.board = new MyFrame(width, height, this);
        this.kierunekCzlowieka = 0;
    }
    public void wykonajTure(){
        Collections.sort(organizmy, compare);
        Collections.reverse(organizmy);
        for(int i = 0; i < iloscOrganizmow; i++){
            organizmy.get(i).zwiekszWiek();
            organizmy.get(i).akcja();
        }
    }
    public void dodajOrganizm(Organizm nowy){
        if(iloscOrganizmow < width * height) {
            organizmy.add(0,nowy);
            iloscOrganizmow++;
        }
    }
    public void usunOrganizm(Organizm usun){
        if(usun.getOznaczenie() == 'C'){
            System.out.println("GAME OVER! Gracz zostal pokonany");
            System.exit(0);
        }
        else{
            organizmy.remove(usun);
            iloscOrganizmow--;
        }
    }
    public void setOnBoard(int x, int y, char oznaczenie){
        board.setOnBoard(x, y, oznaczenie);
    }
    public boolean czyWolne(int x, int y){
        for(int i = 0; i < iloscOrganizmow; i++){
            if(organizmy.get(i).getX() == x && organizmy.get(i).getY() == y){
                return false;
            }
        }
        return true;
    }
    public boolean ifInMap(int x, int y){
        if(x >= 0 && x < width){
            if(y >= 0 && y < height){
                return true;
            }
        }
        return false;
    }
    public Organizm znajdz(int x, int y){
        for(int i = 0; i < iloscOrganizmow; i++){
            if(organizmy.get(i).getX() == x && organizmy.get(i).getY() == y){
                return organizmy.get(i);
            }
        }
        return null;
    }
    public void zapiszSwiat() throws FileNotFoundException{
        PrintWriter zapis = new PrintWriter("gra.txt");
        zapis.println(width);
        zapis.println(height);
        for(int i = 0; i < iloscOrganizmow; i++){
            zapis.println(organizmy.get(i).getOznaczenie());
            zapis.println(organizmy.get(i).getX());
            zapis.println(organizmy.get(i).getY());
            zapis.println(organizmy.get(i).getSila());
            zapis.println(organizmy.get(i).getWiek());
            zapis.println(organizmy.get(i).getInicjatywa());
            if(organizmy.get(i).getOznaczenie() == 'C'){
                zapis.println(organizmy.get(i).getCzasUmiejetnosci());
            }
        }
        zapis.close();
    }
    public void setKierunekCzlowieka(int c){
        this.kierunekCzlowieka = c;
    }
    public int getKierunekCzlowieka(){
        return kierunekCzlowieka;
    }

    public boolean getAktywowano(){
        return aktywowano;
    }
    public void setAktywowano(boolean a){
        this.aktywowano = a;
    }
}
