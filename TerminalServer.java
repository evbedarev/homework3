import org.omg.CORBA.INTERNAL;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.function.BiFunction;

public class TerminalServer {
    private int account = 0;
    private ShowMessage showMessage = new ShowMessage(this);
    RandomException randomException = new RandomException();

    protected int getAccount() {
        return account;
    }

/*
*Метод проверяющий кратность введенного числа ста, считающий операции.
*
 */
    private void setAccount (Scanner scanner, String message, BiFunction<Integer, Integer, Integer> biFunction) {
        showMessage.print("Please enter value: ");

        String input = scanner.nextLine();

        if (Integer.valueOf(input) % 100==0) {

            this.account = biFunction.apply(this.account, Integer.valueOf(input));

            showMessage.print(message + Integer.valueOf(input) +  ". Total: " + this.account);

            scanner.nextLine();

        } else {

            showMessage.print("Enter number multiple one hundred");
        }

        showMessage.printPressAnyKey();
    }
/*
Основной метод терминала, бесконечный цикл с выходом если пользователь ввёл 4.
Сравнивает введенное значение со значением enum, если значения совпадает вызывается метод enum.value
Пробрасывает NetworkProblemException
 */


    public void runTerminal (Scanner scanner) throws Exception {


        for (;;) {

            try {

                randomException.random();
                showMessage.menu(); //Отображает меню.
                String input = scanner.nextLine();

                if (input.matches("[1234]")) {

                    for (Choise ch : Choise.values()) {
                        if (ch.num.equals(input)) {
                            ch.value(this, scanner);
                        }
                    }

                    if (input.equals("4")) {
                        break;
                    }

                } else {

                    showMessage.print("Not valid input. Please enter a number between 1 and 4");
                }

            } catch (NumberFormatException e) {

                sleep("Error. Please enter nubmer");

            }
        }
    }

    public enum Choise {
        ONE("1") {
            public void value(TerminalServer terminalServer, Scanner scanner) {
                terminalServer.showMessage.print("On your account: " + terminalServer.getAccount());
                terminalServer.showMessage.printPressAnyKey();
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


    public final void sleep(String msg) {
        try {
            System.out.println(msg);
            Thread.sleep(3000);
        } catch (InterruptedException v) {
            System.out.println(v);
        }

    }

}


