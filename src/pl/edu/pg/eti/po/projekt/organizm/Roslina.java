package pl.edu.pg.eti.po.projekt.organizm;

import pl.edu.pg.eti.po.projekt.swiat.Swiat;

import java.util.Random;

public abstract class Roslina extends Organizm{

    public Roslina(int x, int y, char oznaczenie, int wiek, int sila, int inicjatywa, Swiat mojSwiat) {
        super(x, y, oznaczenie, wiek, sila, inicjatywa, mojSwiat);
    }

    @Override
    public void akcja() {
        Random gen = new Random();
        int zasiac = gen.nextInt(23);
        if(zasiac == 0) {
            int x, y, k = 0;
            do {
                k++;
                x = getX() + 1 - gen.nextInt(3);
                y = getY() + 1 - gen.nextInt(3);
                if (k >= 15) return;
            } while (!getMojSwiat().ifInMap(x, y) || !getMojSwiat().czyWolne(x, y));
            this.dodajOrganizm(x, y);
            getMojSwiat().setOnBoard(x, y, getOznaczenie());
        }
    }

    @Override
    public void kolizja(Organizm wyzwalajacy) {
        getMojSwiat().usunOrganizm(this);
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
