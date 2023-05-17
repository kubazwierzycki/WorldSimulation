package pl.edu.pg.eti.po.projekt.organizm;

import pl.edu.pg.eti.po.projekt.swiat.Swiat;

import java.util.Random;

public class Mlecz extends Roslina{
    public Mlecz(int x, int y, Swiat mojSwiat){
        this(x, y, 0, 0, 0, mojSwiat);
    }

    public Mlecz(int x, int y, int wiek, int sila, int inicjatywa, Swiat mojSwiat) {
        super(x, y, 'm', wiek, sila, inicjatywa, mojSwiat);
    }

    @Override
    public void dodajOrganizm(int x, int y) {
        Mlecz nowy = new Mlecz(x, y, this.getMojSwiat());
        getMojSwiat().dodajOrganizm(nowy);
    }

    @Override
    public void dodajOrganizm(int x, int y, int wiek, int sila) {
        Mlecz nowy = new Mlecz(x, y, wiek, sila, getInicjatywa(), getMojSwiat());
        getMojSwiat().dodajOrganizm(nowy);
    }

    @Override
    public void akcja() {
        Random gen = new Random();
        for(int i = 0 ; i < 3; i++) {
            int zasiac = gen.nextInt(23);
            if (zasiac == 0) {
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
    }
}
