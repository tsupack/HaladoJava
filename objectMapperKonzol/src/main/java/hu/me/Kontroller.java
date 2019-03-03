package hu.me;

import hu.me.exceptions.DivisionByZeroException;
import hu.me.exceptions.InvalidProcedureException;

import java.io.IOException;
import java.util.Scanner;

public class Kontroller {

    private boolean fut;
    private Szerviz szerviz;
    private Marshaller marshaller;
    private Scanner sc;

    public void fut(){
        fut = true;
        int valasztas = 0;
        marshaller = new Marshaller();
        szerviz = new Szerviz();
        while (fut) {
            sc = new Scanner(System.in);
            System.out.println("\nValasszon az alabbi menupontok kozul (1-6)!");
            System.out.println("1 - Szamolas konzollal");
            System.out.println("2 - Szamolas JSON fajllal");
            System.out.println("3 - Szamolas YAML fajllal");
            System.out.println("4 - JSON fajl keszitese szamolashoz");
            System.out.println("5 - YAML fajl keszitese szamolashoz");
            System.out.println("6 - Kilepes");
            try {
                valasztas = Integer.parseInt(sc.nextLine());
            }
            catch (NumberFormatException e) {}
            switch(valasztas){
                case 1: {szamolasKonzollal(); break;}
                case 2: {
                    System.out.println("Bemeneti JSON fajl neve?");
                    szamolasJsonnel(sc.nextLine());
                    break;
                }
                case 3: {
                    System.out.println("Bemeneti YAML fajl neve?");
                    szamolasYamllal(sc.nextLine());
                    break;
                }
                case 4: {
                    System.out.println("Menteni kivant JSON fajl neve?");
                    jsonKeszit(sc.nextLine());
                    break;
                }
                case 5: {
                    System.out.println("Menteni kivant YAML fajl neve??");
                    yamlKeszit(sc.nextLine());
                    break;
                }
                case 6: { sc.close(); fut = false; break;}
                default: System.out.println("Nem letezo menupont!");
            }
        }
    }

    private void szamolasKonzollal(){
        System.out.println("Gepelje be az 'osszead' , 'kivon' , 'szoroz' , 'oszt' szavak valamelyiket a megfelelo muvelethez!");
        String muvelet = sc.nextLine();
        if (!(muvelet.equals("osszead")||muvelet.equals("kivon")||muvelet.equals("szoroz")||muvelet.equals("oszt")))
            try {
                throw new InvalidProcedureException();
            }
            catch (InvalidProcedureException e) {
                System.out.println("Ervenytelen muvelet!");
                return;
            }
        try {
            System.out.println("a = ");
            int a = Integer.parseInt(sc.nextLine());
            System.out.println("b = ");
            int b = Integer.parseInt(sc.nextLine());
            double eredmeny = szerviz.szamol(marshaller.ujInput(muvelet, a, b));
            System.out.println("Eredmeny: " + eredmeny);
        }
        catch (NumberFormatException e) {
            System.out.println("Hibas szam(ok)!");
        }
        catch (InvalidProcedureException e) {
            System.out.println("Ervenytelen muvelet!");
        }
        catch (DivisionByZeroException e) {
            System.out.println("0-val valo osztas!");
        }
    }

    private void szamolasJsonnel(String jsonFajl) {
        try {
            marshaller.marshalJson(szerviz.szamol(marshaller.unMarshalJson(jsonFajl + ".json")), "", 0);
            Input input;
            double eredmeny;
            input = marshaller.unMarshalJson(jsonFajl + ".json");
            eredmeny = szerviz.szamol(input);
            System.out.println("Az eredmeny: " + eredmeny);
        }
        catch (IOException | InvalidProcedureException | DivisionByZeroException e) {
            System.out.println("Hiba! - " + e.getMessage());
        }
    }

    private void szamolasYamllal(String yamlFajl) {
        try {
            marshaller.marshalYaml(szerviz.szamol(marshaller.unMarshalYaml(yamlFajl + ".yaml")), "", 0);
            Input input;
            double eredmeny;
            input = marshaller.unMarshalYaml(yamlFajl + ".yaml");
            eredmeny = szerviz.szamol(input);
            System.out.println("Az eredmeny: " + eredmeny);
        }
        catch (IOException | InvalidProcedureException | DivisionByZeroException e) {
            System.out.println("Hiba! - " + e.getMessage());
        }
    }

    private void jsonKeszit(String jsonFajl) {
        System.out.println("Geplje be soronkent az alabbi muveletek egyiket 'osszead,kivon,szoroz,oszt' majd az elso es a masodik operandus erteket!");
        try {
            marshaller.keszitsInputJsont(jsonFajl,sc.nextLine(),Integer.parseInt(sc.nextLine()),Integer.parseInt(sc.nextLine()));
        } catch (InvalidProcedureException | IOException | NumberFormatException e) {
            System.out.println("Hiba! - " + e.getMessage());
        }
    }

    private void yamlKeszit(String yamlFajl) {
        System.out.println("Geplje be soronkent az alabbi muveletek egyiket 'osszead,kivon,szoroz,oszt' majd az elso es a masodik operandus erteket!");
        try {
            marshaller.keszitsInputYamlt(yamlFajl,sc.nextLine(),Integer.parseInt(sc.nextLine()),Integer.parseInt(sc.nextLine()));
        } catch (InvalidProcedureException | IOException | NumberFormatException e) {
            System.out.println("Hiba! - " + e.getMessage());
        }
    }
}