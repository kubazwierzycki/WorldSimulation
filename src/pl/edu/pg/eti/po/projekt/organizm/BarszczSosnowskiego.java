package pl.edu.pg.eti.po.projekt.organizm;

import pl.edu.pg.eti.po.projekt.swiat.Swiat;

import java.util.Random;

public class BarszczSosnowskiego extends Roslina{
    public BarszczSosnowskiego(int x, int y, Swiat mojSwiat){
        this(x, y, 0, 10, 0, mojSwiat);
    }

    public BarszczSosnowskiego(int x, int y, int wiek, int sila, int inicjatywa, Swiat mojSwiat) {
        super(x, y, 'b', wiek, sila, inicjatywa, mojSwiat);
    }

    @Override
    public void dodajOrganizm(int x, int y) {
        BarszczSosnowskiego nowy = new BarszczSosnowskiego(x, y, this.getMojSwiat());
        getMojSwiat().dodajOrganizm(nowy);
    }

    @Override
    public void dodajOrganizm(int x, int y, int wiek, int sila) {
        BarszczSosnowskiego nowy = new BarszczSosnowskiego(x, y, wiek, sila, getInicjatywa(), getMojSwiat());
        getMojSwiat().dodajOrganizm(nowy);
    }

    @Override
    public void akcja() {
        for(int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++){
                Organizm temp = getMojSwiat().znajdz(getX() + i, getY() + j);
                if(temp != null){
                    if(temp.getInicjatywa() > 0){
                        if(temp.czyUmiejetnoscAktywna()){
                            int x, y;
                            getMojSwiat().setOnBoard(temp.getX(), temp.getY(), ' ');
                            Random gen = new Random();
                            do{
                                x = temp.getX() + 2 - gen.nextInt(5);
                                y = temp.getY() + 2 - gen.nextInt(5);
                            }while (!getMojSwiat().ifInMap(x, y) || !getMojSwiat().czyWolne(x, y));
                            temp.setX(x);
                            temp.setY(y);
                            getMojSwiat().setOnBoard(x, y, temp.getOznaczenie());
                        }
                        else {
                            getMojSwiat().usunOrganizm(temp);
                        }
                    }
                }
            }
        }

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
