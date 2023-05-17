package pl.edu.pg.eti.po.projekt.organizm;

import pl.edu.pg.eti.po.projekt.swiat.Swiat;

public class Guarana extends Roslina{
    public Guarana(int x, int y, Swiat mojSwiat){
        this(x, y, 0, 0, 0, mojSwiat);
    }

    public Guarana(int x, int y, int wiek, int sila, int inicjatywa, Swiat mojSwiat) {
        super(x, y, 'g', wiek, sila, inicjatywa, mojSwiat);
    }

    @Override
    public void dodajOrganizm(int x, int y) {
        Guarana nowy = new Guarana(x, y, this.getMojSwiat());
        getMojSwiat().dodajOrganizm(nowy);
    }

    @Override
    public void dodajOrganizm(int x, int y, int wiek, int sila) {
        Guarana nowy = new Guarana(x, y, wiek, sila, getInicjatywa(), getMojSwiat());
        getMojSwiat().dodajOrganizm(nowy);
    }

    @Override
    public void kolizja(Organizm wyzwalajacy) {
        wyzwalajacy.upgradeSila(3);
        getMojSwiat().usunOrganizm(this);
    }
}
