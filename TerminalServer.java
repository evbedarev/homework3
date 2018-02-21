import java.util.Scanner;
import java.util.function.Consumer;

public class TerminalServer {
    private int account = 0;

    public int getAccount() {
        return account;
    }

    public void setAccount(int account, Consumer<String> consumer) {
        if (account % 100==0) {
            this.account += account;
            consumer.accept("Add " + account + " to your account. Total: " + this.account);
        } else {
            consumer.accept("Enter number multiple one hundred");
        }
    }

    public void setAccountMinus (int account, Consumer<String> consumer) {
        if (account % 100==0) {
            this.account -= account;
            consumer.accept("Withdraw " + account + " . Total: " + this.account);
        } else {
            consumer.accept("Enter number multiple one hundred");
        }
    }

    public void runTerminal(Scanner scanner) {
        for (;;) {

            System.out.println("=====================");
            System.out.println("Please enter command:");
            System.out.println("1 - check account");
            System.out.println("2 - add money");
            System.out.println("3 - take money");
            System.out.println("4 - quit");
            System.out.println("=====================");


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
                System.out.println(terminalServer.getAccount());
                System.out.println("Press any key");
                scanner.nextLine();
            }
        },
        TWO("2"){
            public void value(TerminalServer terminalServer, Scanner scanner) {
                System.out.println("Please enter value: ");
                String input = scanner.nextLine();
                terminalServer.setAccount(Integer.valueOf(input),System.out::println);
                System.out.println("Press any key");
                scanner.nextLine();

            }

        },
        THREE("3") {
            public void value(TerminalServer terminalServer, Scanner scanner) {
                System.out.println("Please enter value: ");
                String input = scanner.nextLine();
                terminalServer.setAccountMinus(Integer.valueOf(input),System.out::println);
                System.out.println("Press any key");
                scanner.nextLine();
            }
        };

        String num;

        Choise(String num) {
            this.num = num;
        }

        public abstract void value(TerminalServer terminalServer, Scanner scanner);
    }


}
