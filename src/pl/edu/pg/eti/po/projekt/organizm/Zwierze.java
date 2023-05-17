package pl.edu.pg.eti.po.projekt.organizm;

import pl.edu.pg.eti.po.projekt.swiat.Swiat;

import java.util.Random;

public abstract class Zwierze extends Organizm{

    public Zwierze(int x, int y, char oznaczenie, int wiek, int sila, int inicjatywa, Swiat mojSwiat) {
        super(x, y, oznaczenie, wiek, sila, inicjatywa, mojSwiat);
    }

    @Override
    public void akcja() {
        getMojSwiat().setOnBoard(getX(), getY(), ' ');
        int x = getX(), y = getY();
        Random kierunek = new Random();
        int kier = kierunek.nextInt(4);
        if(kier == 0 && getMojSwiat().ifInMap(x, y - 1)){
            y = y - 1;
        }
        else if(kier == 1 && getMojSwiat().ifInMap(x, y + 1)){
            y = y + 1;
        }
        else if(kier == 2 && getMojSwiat().ifInMap(x + 1, y)){
            x = x + 1;
        }
        else if(kier == 3 && getMojSwiat().ifInMap(x - 1, y)){
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

    @Override
    public void kolizja(Organizm wyzwalajacy) {
        if(wyzwalajacy.getOznaczenie() == this.getOznaczenie()){
            int x, y;
            int k = 0;
            Random gen = new Random();
            do{
                x = getX() + 1 - gen.nextInt(3);
                y = getY() + 1 - gen.nextInt(3);
                k++;
                if(k > 10) return;
            }while (!getMojSwiat().ifInMap(x, y) || !getMojSwiat().czyWolne(x, y));
            this.dodajOrganizm(x, y);
        }
        else if(this.czyUciekl()){
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
        else{
            if(this.czyUmiejetnoscAktywna()){
                int x, y;
                Random gen = new Random();
                do{
                    x = this.getX() + 2 - gen.nextInt(5);
                    y = this.getY() + 2 - gen.nextInt(5);
                }while (!getMojSwiat().ifInMap(x, y) || !getMojSwiat().czyWolne(x, y));
                this.setX(x);
                this.setY(y);
                getMojSwiat().setOnBoard(x, y, this.getOznaczenie());
            }
            else{
                getMojSwiat().usunOrganizm(this);
            }
        }
    }

    @Override
    public boolean czyUciekl() {
        return false;
    }

    @Override
    public boolean czyOdparl(Organizm raz) {
        return false;
    }

    @Override
    public boolean czyUmiejetnoscAktywna() {
        return false;
    }

    @Override
    public int getCzasUmiejetnosci() {
        return 0;
    }
}
