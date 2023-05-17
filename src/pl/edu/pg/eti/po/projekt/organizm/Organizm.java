package pl.edu.pg.eti.po.projekt.organizm;

import com.sun.org.apache.xpath.internal.operations.Or;
import pl.edu.pg.eti.po.projekt.swiat.Swiat;

import java.util.Random;

public abstract class Organizm {
    private int id;
    private int sila;
    private int inicjatytwa;
    private int polX;
    private int polY;
    private int wiek;
    private char oznaczenie;
    private Swiat mojSwiat;

    public Organizm(int x, int y, char oznaczenie, int wiek, int sila, int inicjatywa, Swiat mojSwiat){
        Random r = new Random();
        this.id =r.nextInt(9337);
        this.polY = y;
        this.polX = x;
        this.wiek = wiek;
        this.sila = sila;
        this.inicjatytwa = inicjatywa;
        this.mojSwiat = mojSwiat;
        this.oznaczenie = oznaczenie;
    }

    public Swiat getMojSwiat(){
        return mojSwiat;
    }
    public int getID() {
        return id;
    }
    public int getSila() {
        return sila;
    }
    public int getInicjatywa() {
        return inicjatytwa;
    }
    public int getX() {
        return polX;
    }
    public int getY() {
        return polY;
    }
    public void setX(int x) {
        this.polX = x;
    }
    public void setY(int y) {
        this.polY = y;
    }
    public int getWiek() {
        return wiek;
    }
    public void upgradeSila(int oIle) {
        this.sila = (this.sila + oIle);
    }
    public char getOznaczenie() {
        return oznaczenie;
    }
    public void zwiekszWiek() {
        this.wiek = (this.wiek + 1);
    }
    public abstract void akcja();
    public abstract void kolizja(Organizm wyzwalajacy);
    public abstract boolean czyUciekl();
    public abstract boolean czyOdparl(Organizm raz);
    public abstract void dodajOrganizm(int x, int y);
    public abstract void dodajOrganizm(int x, int y, int wiek, int sila);
    public abstract boolean czyUmiejetnoscAktywna();
    public abstract int getCzasUmiejetnosci();
}