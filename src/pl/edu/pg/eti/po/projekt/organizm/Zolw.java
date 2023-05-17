package pl.edu.pg.eti.po.projekt.organizm;

import pl.edu.pg.eti.po.projekt.swiat.Swiat;

import java.util.Random;

public class Zolw extends Zwierze{

    public Zolw(int x, int y, Swiat mojSwiat){
        this(x, y, 0, 2, 1, mojSwiat);
    }

    public Zolw(int x, int y, int wiek, int sila, int inicjatywa, Swiat mojSwiat) {
        super(x, y, 'Z', wiek, sila, inicjatywa, mojSwiat);
    }

    @Override
    public void dodajOrganizm(int x, int y) {
        Zolw nowy = new Zolw(x, y, this.getMojSwiat());
        getMojSwiat().dodajOrganizm(nowy);
    }

    @Override
    public void dodajOrganizm(int x, int y, int wiek, int sila) {
        Zolw nowy = new Zolw(x, y, wiek, sila, getInicjatywa(), getMojSwiat());
        getMojSwiat().dodajOrganizm(nowy);
    }

    @Override
    public boolean czyOdparl(Organizm raz) {
        if(raz.getSila() < 5){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public void akcja() {
        getMojSwiat().setOnBoard(getX(), getY(), ' ');
        int x = getX(), y = getY();
        Random kierunek = new Random();
        int kier = kierunek.nextInt(4);
        if(kier == 0) {
            kier = kierunek.nextInt(4);
            if (kier == 0 && getMojSwiat().ifInMap(x, y - 1)) {
                y = y - 1;
            }
            else if (kier == 1 && getMojSwiat().ifInMap(x, y + 1)) {
                y = y + 1;
            }
            else if (kier == 2 && getMojSwiat().ifInMap(x + 1, y)) {
                x = x + 1;
            }
            else if (kier == 3 && getMojSwiat().ifInMap(x - 1, y)) {
                x = x - 1;
            }
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
