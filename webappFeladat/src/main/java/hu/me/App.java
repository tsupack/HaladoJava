package hu.me;

import hu.me.controller.Controller;
import hu.me.core.User;
import hu.me.service.exceptions.InvalidUserInformationException;
import hu.me.service.implementation.Service;
import hu.me.utils.implementation.LengthChecker;
import hu.me.utils.Checker;
import hu.me.utils.implementation.NoSpaceChecker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        boolean fut = true;
        int valasztas = 0;

        Collection<Checker> checkers = new ArrayList<>();
        checkers.add(new LengthChecker());
        checkers.add(new NoSpaceChecker());

        Service service = new Service(checkers);
        Controller controller = new Controller(service);
        Scanner sc = new Scanner(System.in);

        while (fut) {
            System.out.println("\nValasszon az alabbi menupontok kozul (1-3)!");
            System.out.println("1 - Belepes");
            System.out.println("2 - Osszes bevitt adat kiolvasasa");
            System.out.println("3 - Kilepes");
            try {
                valasztas = Integer.parseInt(sc.nextLine());
            }
            catch (NumberFormatException e) {}
            switch(valasztas){
                case 1: {
                    User user = new User();
                    System.out.println("Kerem adja meg a belepo nevet!");
                    String username = sc.nextLine();
                    user.setUsername(username);
                    System.out.println("Kerem adja meg a jelszavat!");
                    String password = sc.nextLine();
                    user.setPassword(password);
                    try {
                        controller.saveUser(user);
                        System.out.println("Sikeres belepes!");
                        user = service.load();
                        System.out.println("\nUsername: "+user.getUsername());
                    } catch (InvalidUserInformationException e){
                        System.out.println("Ervenytelen belepesi adatok!" + e.getMessage());
                    } catch (IOException e){
                        System.out.println("Hibas fajlmuvelet!" + e.getMessage());
                    }
                    break;
                }
                case 2: {
                    for (User i : service.getUserStorage()) {
                        System.out.println(i);
                    }
                    break;
                }
                case 3: {
                    sc.close();
                    fut = false;
                    break;
                }
                default: System.out.println("Nem letezo menupont!");
            }
        }
    }
}