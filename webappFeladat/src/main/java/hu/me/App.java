package hu.me;

import hu.me.exceptions.InvalidUserInformationException;
import hu.me.exceptions.LengthChecker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc;
        boolean fut = true;
        int valasztas = 0;

        ArrayList<Checker> checkers = new ArrayList<Checker>();
        NoSpaceChecker noSpaceChecker = new NoSpaceChecker();
        LengthChecker lengthChecker = new LengthChecker();
        checkers.add(noSpaceChecker);
        checkers.add(lengthChecker);

        Service service = new Service();
        Controller controller = new Controller();
        controller.setCheckers(checkers);
        User user;

        while (fut) {
            sc = new Scanner(System.in);
            System.out.println("\nValasszon az alabbi menupontok kozul (1-2)!");
            System.out.println("1 - Belepes");
            System.out.println("2 - Kilepes");
            try {
                valasztas = Integer.parseInt(sc.nextLine());
            }
            catch (NumberFormatException e) {}
            switch(valasztas){
                case 1: {
                    System.out.println("Kerem adja meg a belepo nevet!");
                    String username = sc.nextLine();
                    System.out.println("Kerem adja meg a jelszavat!");
                    String password = sc.nextLine();
                    try {
                        if (service.save(controller.check(username, password, checkers))){
                            System.out.println("Sikeres belepes!");
                            //user = controller.check(username,password);
                            user = service.load();
                            System.out.println("\nUsername: "+user.getUsername());
                        }
                    } catch (InvalidUserInformationException e){
                        System.out.println("Ervenytelen belepesi adatok!" + e.getMessage());
                    } catch (IOException e){
                        System.out.println("Hibas fajlmuvelet!" + e.getMessage());
                    }
                    break;
                }
                case 2: {
                    sc.close();
                    fut = false;
                    break;
                }
                default: System.out.println("Nem letezo menupont!");
            }
        }
    }
}

