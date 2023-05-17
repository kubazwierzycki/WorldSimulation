package pl.edu.pg.eti.po.projekt.organizm;

import pl.edu.pg.eti.po.projekt.swiat.Swiat;

public class Wilk extends Zwierze{

    public Wilk(int x, int y, Swiat mojSwiat){
        this(x, y, 0, 9, 5, mojSwiat);
    }

    public Wilk(int x, int y, int wiek, int sila, int inicjatywa, Swiat mojSwiat) {
        super(x, y, 'W', wiek, sila, inicjatywa, mojSwiat);
    }

    @Override
    public void dodajOrganizm(int x, int y) {
        Wilk nowy = new Wilk(x, y, this.getMojSwiat());
        getMojSwiat().dodajOrganizm(nowy);
    }

    @Override
    public void dodajOrganizm(int x, int y, int wiek, int sila) {
        Wilk nowy = new Wilk(x, y, wiek, sila, getInicjatywa(), getMojSwiat());
        getMojSwiat().dodajOrganizm(nowy);
    }
}
