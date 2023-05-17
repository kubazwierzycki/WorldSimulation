package pl.edu.pg.eti.po.projekt.organizm;

import pl.edu.pg.eti.po.projekt.swiat.Swiat;

public class Trawa extends Roslina{
    public Trawa(int x, int y, Swiat mojSwiat){
        this(x, y, 0, 0, 0, mojSwiat);
    }

    public Trawa(int x, int y, int wiek, int sila, int inicjatywa, Swiat mojSwiat) {
        super(x, y, 't', wiek, sila, inicjatywa, mojSwiat);
    }

    @Override
    public void dodajOrganizm(int x, int y) {
        Trawa nowy = new Trawa(x, y, this.getMojSwiat());
        getMojSwiat().dodajOrganizm(nowy);
    }

    @Override
    public void dodajOrganizm(int x, int y, int wiek, int sila) {
        Trawa nowy = new Trawa(x, y, wiek, sila, getInicjatywa(), getMojSwiat());
        getMojSwiat().dodajOrganizm(nowy);
    }
}
