import org.omg.CORBA.INTERNAL;

import java.util.Scanner;
import java.util.function.BiFunction;

public class TerminalServer {
    private int account = 0;

    private int getAccount() {
        return account;
    }


    private void setAccount (Scanner scanner, String message, BiFunction<Integer, Integer, Integer> biFunction) {
        System.out.println("Please enter value: ");

        String input = scanner.nextLine();

        if (Integer.valueOf(input) % 100==0) {

            this.account = biFunction.apply(this.account, Integer.valueOf(input));

            System.out.println(message + Integer.valueOf(input) +  ". Total: " + this.account);

            scanner.nextLine();

        } else {

            System.out.println("Enter number multiple one hundred");
        }

        System.out.println("Press any key");
    }


    public void runTerminal(Scanner scanner) {
        for (;;) {

            menu();
            String input = scanner.nextLine();

            if (input.matches("\\d+")) {

                for (Choise ch:Choise.values()) {
                    if (ch.num.equals(input)) {
                        ch.value(this, scanner);
                    }
                }

                if (input.equals("4")) {break;}

            }
        }
    }

    public enum Choise {
        ONE("1") {
            public void value(TerminalServer terminalServer, Scanner scanner) {
                System.out.println("On your account: " + terminalServer.getAccount());
                System.out.println("Press any key");
                scanner.nextLine();
            }
        },

        TWO("2"){
            public void value(TerminalServer terminalServer, Scanner scanner) {
                terminalServer.setAccount(scanner, "Add to your account: ", (x,y) -> x + y);

            }
        },

        THREE("3") {
            public void value(TerminalServer terminalServer, Scanner scanner) {
                terminalServer.setAccount(scanner, "Withdraw from your account: ", (x,y) -> x - y);
            }
        };

        String num;

        Choise(String num) {
            this.num = num;
        }

        public abstract void value(TerminalServer terminalServer, Scanner scanner);
    }

    private final void menu() {
        System.out.println("=====================");
        System.out.println("Please enter command:");
        System.out.println("1 - check account");
        System.out.println("2 - add money");
        System.out.println("3 - take money");
        System.out.println("4 - quit");
        System.out.println("=====================");
    }


}
