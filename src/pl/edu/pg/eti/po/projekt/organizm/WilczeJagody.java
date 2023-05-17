package pl.edu.pg.eti.po.projekt.organizm;

import pl.edu.pg.eti.po.projekt.swiat.Swiat;

import java.util.Random;

public class WilczeJagody extends Roslina{
    public WilczeJagody(int x, int y, Swiat mojSwiat){
        this(x, y, 0, 99, 0, mojSwiat);
    }

    public WilczeJagody(int x, int y, int wiek, int sila, int inicjatywa, Swiat mojSwiat) {
        super(x, y, 'j', wiek, sila, inicjatywa, mojSwiat);
    }

    @Override
    public void dodajOrganizm(int x, int y) {
        WilczeJagody nowy = new WilczeJagody(x, y, this.getMojSwiat());
        getMojSwiat().dodajOrganizm(nowy);
    }

    @Override
    public void dodajOrganizm(int x, int y, int wiek, int sila) {
        WilczeJagody nowy = new WilczeJagody(x, y, wiek, sila, getInicjatywa(), getMojSwiat());
        getMojSwiat().dodajOrganizm(nowy);
    }

    @Override
    public void kolizja(Organizm wyzwalajacy) {
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
}
