package hu.me;

import hu.me.controller.impl.Controller;
import hu.me.core.User;
import hu.me.utils.ErrorMessage;
import hu.me.utils.impl.*;
import hu.me.utils.Checker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        boolean fut = true;
        int valasztas = 0;

        Collection<Checker> checkers = new ArrayList<>();
        checkers.add(new NullCheckerUsername());
        checkers.add(new NullCheckerPassword());
        checkers.add(new LengthCheckerUsername());
        checkers.add(new LengthCheckerPassword());
        checkers.add(new NoSpaceCheckerUsername());
        checkers.add(new NoSpaceCheckerPassword());

        Collection<ErrorMessage> messages;

        Controller controller = new Controller(checkers);
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

                    boolean hibatlan = true;
                    messages = controller.storeUser(user);
                    if (!(messages.isEmpty())){
                        hibatlan = false;
                    }

                    if (hibatlan) {
                        System.out.println("Sikeres belepes!");
                    }
                    else {
                        System.out.println("Sikertelen belepes! Hibak:");
                        for ( ErrorMessage message : messages ){
                            System.out.println(message);
                        }
                    }
                    break;
                }
                case 2: {
                    for (User i : controller.getUserStorage()) {
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