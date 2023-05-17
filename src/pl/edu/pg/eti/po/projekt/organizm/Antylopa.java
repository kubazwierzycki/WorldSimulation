package pl.edu.pg.eti.po.projekt.organizm;

import pl.edu.pg.eti.po.projekt.swiat.Swiat;

import java.util.Random;

public class Antylopa extends Zwierze{

    public Antylopa(int x, int y, Swiat mojSwiat){
        this(x, y, 0, 4, 4, mojSwiat);
    }

    public Antylopa(int x, int y, int wiek, int sila, int inicjatywa, Swiat mojSwiat) {
        super(x, y, 'A', wiek, sila, inicjatywa, mojSwiat);
    }

    @Override
    public void dodajOrganizm(int x, int y) {
        Antylopa nowy = new Antylopa(x, y, this.getMojSwiat());
        getMojSwiat().dodajOrganizm(nowy);
    }

    @Override
    public void dodajOrganizm(int x, int y, int wiek, int sila) {
        Antylopa nowy = new Antylopa(x, y, wiek, sila, getInicjatywa(), getMojSwiat());
        getMojSwiat().dodajOrganizm(nowy);
    }

    @Override
    public boolean czyUciekl() {
        Random gen = new Random();
        int szansa = gen.nextInt(2);
        if(szansa == 0){
            int x, y;
            int k = 0;
            do{
                x = getX() + 1 - gen.nextInt(3);
                y = getY() + 1 - gen.nextInt(3);
                k++;
                if(k > 10) return false;
            }while (!getMojSwiat().ifInMap(x, y) || !getMojSwiat().czyWolne(x, y));
            return true;
        }
        return false;
    }

    @Override
    public void akcja() {
        getMojSwiat().setOnBoard(getX(), getY(), ' ');
        int x = getX(), y = getY();
        Random kierunek = new Random();
        int kier = kierunek.nextInt(4);
        if(kier == 0 && getMojSwiat().ifInMap(x, y - 2)){
            y = y - 2;
        }
        else if(kier == 1 && getMojSwiat().ifInMap(x, y + 2)){
            y = y + 2;
        }
        else if(kier == 2 && getMojSwiat().ifInMap(x + 2, y)){
            x = x + 2;
        }
        else if(kier == 3 && getMojSwiat().ifInMap(x - 2, y)){
            x = x - 2;
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
