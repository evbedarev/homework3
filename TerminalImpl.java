import java.util.Scanner;

public class TerminalImpl {
    private final TerminalServer server;
    private final PinValidator pinValidator;

    private TerminalImpl(TerminalServer server, PinValidator pinValidator) {
        this.server = server;
        this.pinValidator = pinValidator;
    }

    private void run() {
        String input;
        Scanner scanner = new Scanner(System.in);
        for (; ; ) {
            System.out.println("Please enter pin code: ");
            input = scanner.nextLine();
                if (pinValidator.validatePin(input)) {
                    server.runTerminal(scanner);
                    break;
                }
        }
        scanner.close();
    }


    public static void main(String[] args) {
        TerminalImpl terminal = new TerminalImpl(new TerminalServer(),new PinValidator());
        terminal.run();

    }



}
