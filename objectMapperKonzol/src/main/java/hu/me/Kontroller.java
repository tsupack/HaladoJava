package hu.me;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Kontroller {

    private boolean fut;
    private Szamologep szamologep;
    private Szerviz szerviz;
    private Marshaller marshaller;
    private Scanner sc;

    public void fut(){
        fut = true;
        szamologep = new Szamologep();
        szerviz = new Szerviz(szamologep);
        marshaller = new Marshaller();

        int valasztas = 0;
        while (fut) {
            sc = new Scanner(System.in);
            System.out.println("\nValasszon az alabbi menupontok kozul (1-4)!");
            System.out.println("1 - Szamolas konzollal");
            System.out.println("2 - Szamolas JSON fajllal");
            System.out.println("3 - Szamolas YAML fajllal");
            System.out.println("4 - Kilepes");
            try {
                valasztas = Integer.parseInt(sc.nextLine());
            }
            catch (NumberFormatException e) {
                System.out.println("Hiba! - " + e.getMessage());
            }
            switch(valasztas){
                case 1: {
                    szamolasKonzollal();
                    break;
                }
                case 2: {
                    szamolasJsonnel();
                    break;
                }
                case 3: {
                    szamolasYamllal();
                    break;
                }
                case 4: {
                    sc.close();
                    fut = false;
                    break;
                }
                default: System.out.println("Nem letezo menupont!");
            }
        }
    }

    private void szamolasKonzollal() {
        Input input = new Input();
        Output output = new Output();
        double[] operandusok = new double[2];

        System.out.println("Gepelje be az 'osszead' , 'kivon' , 'szoroz' , 'oszt' szavak valamelyiket a megfelelo muvelethez!");
        String muvelet = sc.nextLine();

        if (!(muvelet.equals("osszead") || muvelet.equals("kivon") || muvelet.equals("szoroz") || muvelet.equals("oszt"))){
            output.setUzenet("Ervenytelen muvelet!");
            output.setHibakod(Hibakod.ERVENYTELENMUVELET);
            try {
                marshaller.marshalJson(output);
                System.out.println("Ervenytelen muvelet! Hibaleiro JSON elkeszult");
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            input.setMuvelet(muvelet);
            System.out.println("a = ");
            double a = Double.parseDouble(sc.nextLine());
            operandusok[0] = a;
            System.out.println("b = ");
            double b = Double.parseDouble(sc.nextLine());
            operandusok[1] = b;
            input.setOperandusok(operandusok);
            output = szerviz.szamol(input);
            if(output.getHibakod() == Hibakod.NULLAVALVALOOSZTAS){
                System.out.println("0-val valo osztas!");
                marshaller.marshalJson(output);
                System.out.println("Hibaleiro JSON elmentve!");
            } else {
                System.out.println("Eredmeny: " + output.getEredmeny());
                marshaller.marshalJson(output);
                System.out.println("Output JSON elmentve!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Hibas szam(ok)!");
        } catch (InputMismatchException e) {
            output.setUzenet("Ervenytelen bemenet!");
            output.setHibakod(Hibakod.ERVENYTELENBEMENET);
            try {
                marshaller.marshalJson(output);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            System.out.println("Ervenytelen bemenet! Hibaleiro JSON elmentve!!");
        } catch (IOException e) {
            System.out.println("Hiba a fajlmuvelet kozben!");
        }

    }

    private void szamolasJsonnel() {
        Output output;
        Input input;
        double eredmeny;
        try {
            input = marshaller.unMarshalJsonFile("Proba.json");
            System.out.println("Bemeneti JSON fajl tartalma: "+input.getMuvelet()+", "+input.getOperandus1()+", "+input.getOperandus2());
            output = szerviz.szamol(marshaller.unMarshalJsonFile("Proba.json"));
            eredmeny = output.getEredmeny();
            System.out.println("Az eredmeny: " + eredmeny);
            marshaller.marshalJson(output);
            System.out.println("Output JSON elmentve!");
        }
        catch (IOException e) {
            System.out.println("Hiba! - " + e.getMessage());
        }
    }

    private void szamolasYamllal() {
        Output output;
        Input input;
        double eredmeny;
        try {
            input = marshaller.unMarshalYamlFile("Proba.yaml");
            System.out.println("Bemeneti YAML fajl tartalma: "+input.getMuvelet()+", "+input.getOperandus1()+", "+input.getOperandus2());
            output = szerviz.szamol(marshaller.unMarshalYamlFile("Proba.yaml"));
            eredmeny = output.getEredmeny();
            System.out.println("Az eredmeny: " + eredmeny);
            marshaller.marshalYaml(output);
            System.out.println("Output YAML elmentve!");
        }
        catch (IOException e) {
            System.out.println("Hiba! - " + e.getMessage());
        }
    }
}
