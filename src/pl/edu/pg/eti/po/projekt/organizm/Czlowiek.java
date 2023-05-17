package pl.edu.pg.eti.po.projekt.organizm;

import pl.edu.pg.eti.po.projekt.swiat.Swiat;

import java.util.Random;

public class Czlowiek extends Zwierze {

    private int czasUmiejetnosci;

    public Czlowiek(int x, int y, Swiat mojSwiat){
        this(x, y, 0, 5, 4, mojSwiat, -5);
    }

    public Czlowiek(int x, int y, int wiek, int sila, int inicjatywa, Swiat mojSwiat, int czasUmiejetnosci) {
        super(x, y, 'C', wiek, sila, inicjatywa, mojSwiat);
        this.czasUmiejetnosci = czasUmiejetnosci;
    }

    @Override
    public void dodajOrganizm(int x, int y) {
        Czlowiek nowy = new Czlowiek(x, y, this.getMojSwiat());
        getMojSwiat().dodajOrganizm(nowy);
    }

    @Override
    public void dodajOrganizm(int x, int y, int wiek, int sila) {
        Czlowiek nowy = new Czlowiek(x, y, wiek, sila, getInicjatywa(), getMojSwiat(), 0);
        getMojSwiat().dodajOrganizm(nowy);
    }

    @Override
    public int getCzasUmiejetnosci(){
        return czasUmiejetnosci;
    }

    @Override
    public boolean czyUmiejetnoscAktywna(){
        if(czasUmiejetnosci >= 0) return true;
        else return false;
    }

    @Override
    public void kolizja(Organizm wyzwalajacy){
        if(this.czyUciekl()){
            return;
        }
        else if(this.getSila() > wyzwalajacy.getSila()){
            if(wyzwalajacy.czyUmiejetnoscAktywna()){
                int x, y;
                Random gen = new Random();
                do{
                    x = wyzwalajacy.getX() + 2 - gen.nextInt(5);
                    y = wyzwalajacy.getY() + 2 - gen.nextInt(5);
                }while (!getMojSwiat().ifInMap(x, y) || !getMojSwiat().czyWolne(x, y));
                wyzwalajacy.setX(x);
                wyzwalajacy.setY(y);
                getMojSwiat().setOnBoard(x, y, wyzwalajacy.getOznaczenie());
            }
            else{
                getMojSwiat().usunOrganizm(wyzwalajacy);
            }
        }
        else {
            if (this.czyUmiejetnoscAktywna()) {
                int x, y;
                Random gen = new Random();
                do {
                    x = this.getX() + 2 - gen.nextInt(5);
                    y = this.getY() + 2 - gen.nextInt(5);
                } while (!getMojSwiat().ifInMap(x, y) || !getMojSwiat().czyWolne(x, y));
                this.setX(x);
                this.setY(y);
                getMojSwiat().setOnBoard(x, y, this.getOznaczenie());
            } else {
                getMojSwiat().usunOrganizm(this);
            }
        }
    }
    @Override
    public void akcja() {
        if(czasUmiejetnosci <= -5 && getMojSwiat().getAktywowano() == true){
            czasUmiejetnosci = 5;
            getMojSwiat().setAktywowano(false);
        }
        czasUmiejetnosci--;
        getMojSwiat().setOnBoard(getX(), getY(), ' ');
        int c = getMojSwiat().getKierunekCzlowieka();
        int x = getX();
        int y = getY();
        if (c == 38 && getMojSwiat().ifInMap(x, y - 1)) {    //UP
            y = y - 1;
        }
        else if (c == 40 && getMojSwiat().ifInMap(x, y + 1)) { //DOWN
            y = y + 1;
        }
        else if (c == 39 && getMojSwiat().ifInMap(x + 1, y)) { //RIGHT
            x = x + 1;
        }
        else if (c == 37 && getMojSwiat().ifInMap(x - 1, y)) { //LEFT
            x = x - 1;
        }
        if(getMojSwiat().znajdz(x, y) != null) {
            Organizm atakowany = getMojSwiat().znajdz(x, y);
            if (this.getID() != atakowany.getID()) {
                if(this.getOznaczenie() == atakowany.getOznaczenie()){
                    atakowany.kolizja(this);
                    getMojSwiat().setOnBoard(getX(), getY(), getOznaczenie());
                }
                else if(!atakowany.czyOdparl(this)){
                    atakowany.kolizja(this);
                    if(x == getX() && y == getY()) {
                        setY(y);
                        setX(x);
                    }
                    getMojSwiat().setOnBoard(getX(), getY(), getOznaczenie());
                }
            }
            else{
                getMojSwiat().setOnBoard(getX(), getY(), getOznaczenie());
            }
        }
        else {
            setX(x);
            setY(y);
            getMojSwiat().setOnBoard(x, y, getOznaczenie());
        }
    }
}
