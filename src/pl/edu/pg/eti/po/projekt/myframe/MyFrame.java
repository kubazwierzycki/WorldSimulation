package pl.edu.pg.eti.po.projekt.myframe;

import pl.edu.pg.eti.po.projekt.organizm.*;
import pl.edu.pg.eti.po.projekt.swiat.Swiat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;

public class MyFrame extends JFrame implements ActionListener, KeyListener {

    private JButton bTura;
    private JButton bSave;
    private JButton bUmiejetnosc;
    private JButton[][] buttons;
    private Swiat mojSwiat;
    private JPanel org;
    private JButton[] organizmy;
    private JButton pole = new JButton();
    private int x;
    private int y;
    private int height;
    private int width;

    public MyFrame(int width, int height, Swiat mojSwiat){
        super("Jakub Zwierzycki");
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);
        this.mojSwiat = mojSwiat;
        this.width = width;
        this.height = height;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width * 30 + 180,height * 30 + 90);
        bTura = new JButton("Kolejna tura");
        bTura.setName("tura");
        bTura.addActionListener(this);
        bSave = new JButton("Zapisz");
        bSave.setName("save");
        bSave.addActionListener(this);
        bUmiejetnosc = new JButton("Umiejetnosc");
        bUmiejetnosc.setName("skill");
        bUmiejetnosc.addActionListener(this);
        JPanel p = new JPanel();
        p.setBounds(10, 10, width * 30, 50);
        p.add(bTura);
        p.add(bSave);
        p.add(bUmiejetnosc);

        JPanel board = new JPanel();
        board.setLayout(new GridLayout(height, width));
        board.setBounds(10,40,width * 30,height * 30);
        this.buttons = new JButton[height][width];
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                this.buttons[i][j] = new JButton();
                this.buttons[i][j].addActionListener(this);
                this.buttons[i][j].setMargin((new Insets(0,0,0,0)));
                board.add(this.buttons[i][j]);
            }
        }
        org = new JPanel();
        org.setBounds(width * 30 + 20, 40, 200, 300);
        org.setLayout(new BoxLayout(org, BoxLayout.Y_AXIS));

        organizmy = new JButton[10];
        for(int i = 0; i < 10; i++){
            organizmy[i] = new JButton();
            organizmy[i].addActionListener(this);
            organizmy[i].setBackground(Color.yellow);
            organizmy[i].setMargin(new Insets(0,0,0,0));
            org.add(organizmy[i]);
        }
        organizmy[0].setText("Wilk");
        organizmy[1].setText("Owca");
        organizmy[2].setText("Lis");
        organizmy[3].setText("Zolw");
        organizmy[4].setText("Antylopa");
        organizmy[5].setText("Trawa");
        organizmy[6].setText("Mlecz");
        organizmy[7].setText("Guarana");
        organizmy[8].setText("WilczeJagody");
        organizmy[9].setText("BarszczSosnowskiego");
        org.setVisible(false);

        this.add(org);
        this.add(board);
        this.add(p);
        setVisible(true);
        this.addKeyListener(this);
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();
        mojSwiat.setKierunekCzlowieka(c);
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == bTura){
            mojSwiat.wykonajTure();
        }
        else if(e.getSource() == bSave){
            System.out.println("save");
            try {
                mojSwiat.zapiszSwiat();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if(e.getSource() == bUmiejetnosc){
            mojSwiat.setAktywowano(true);
            System.out.println("umiejetnosc");
        }
        else if(e.getSource() == organizmy[0]){
            if(pole.getText().equals("")){
                pole.setText(String.valueOf('W'));
                Wilk nowy = new Wilk(x, y, mojSwiat);
                mojSwiat.dodajOrganizm(nowy);
            }
            org.setVisible(false);
        }
        else if(e.getSource() == organizmy[1]){
            if(pole.getText().equals("")){
                pole.setText(String.valueOf('O'));
                Owca nowy = new Owca(x, y, mojSwiat);
                mojSwiat.dodajOrganizm(nowy);
            }
            org.setVisible(false);
        }
        else if(e.getSource() == organizmy[2]){
            if(pole.getText().equals("")){
                pole.setText(String.valueOf('L'));
                Lis nowy = new Lis(x, y, mojSwiat);
                mojSwiat.dodajOrganizm(nowy);
            }
            org.setVisible(false);
        }
        else if(e.getSource() == organizmy[3]){
            if(pole.getText().equals("")){
                pole.setText(String.valueOf('Z'));
                Zolw nowy = new Zolw(x, y, mojSwiat);
                mojSwiat.dodajOrganizm(nowy);
            }
            org.setVisible(false);
        }
        else if(e.getSource() == organizmy[4]){
            if(pole.getText().equals("")){
                pole.setText(String.valueOf('A'));
                Antylopa nowy = new Antylopa(x, y, mojSwiat);
                mojSwiat.dodajOrganizm(nowy);
            }
            org.setVisible(false);
        }
        else if(e.getSource() == organizmy[5]){
            if(pole.getText().equals("")){
                pole.setText(String.valueOf('t'));
                Trawa nowy = new Trawa(x, y, mojSwiat);
                mojSwiat.dodajOrganizm(nowy);
            }
            org.setVisible(false);
        }
        else if(e.getSource() == organizmy[6]){
            if(pole.getText().equals("")){
                pole.setText(String.valueOf('m'));
                Mlecz nowy = new Mlecz(x, y, mojSwiat);
                mojSwiat.dodajOrganizm(nowy);
            }
            org.setVisible(false);
        }
        else if(e.getSource() == organizmy[7]){
            if(pole.getText().equals("")){
                pole.setText(String.valueOf('g'));
                Guarana nowy = new Guarana(x, y, mojSwiat);
                mojSwiat.dodajOrganizm(nowy);
            }
            org.setVisible(false);
        }
        else if(e.getSource() == organizmy[8]){
            if(pole.getText().equals("")){
                pole.setText(String.valueOf('j'));
                WilczeJagody nowy = new WilczeJagody(x, y, mojSwiat);
                mojSwiat.dodajOrganizm(nowy);
            }
            org.setVisible(false);
        }
        else if(e.getSource() == organizmy[9]){
            if(pole.getText().equals("") || pole.getText().equals(" ")){
                pole.setText(String.valueOf('b'));
                BarszczSosnowskiego nowy = new BarszczSosnowskiego(x, y, mojSwiat);
                mojSwiat.dodajOrganizm(nowy);
            }
            org.setVisible(false);
        }
        else{
            pole = (JButton) e.getSource();
            org.setVisible(true);
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (pole == buttons[i][j]) {
                        x = j;
                        y = i;
                    }
                }
            }
        }
        this.requestFocus();
    }
    public void setOnBoard(int x, int y, char oznaczenie){
        if(oznaczenie == ' '){
            buttons[y][x].setText("");
        }
        else {
            buttons[y][x].setText(String.valueOf(oznaczenie));
        }
    }
    public String getButtonText(int x, int y){
        return buttons[y][x].getText();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
