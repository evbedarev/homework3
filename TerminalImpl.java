import java.util.Scanner;

public class TerminalImpl {
    private final TerminalServer server;
    private final PinValidator pinValidator;

    public TerminalImpl(TerminalServer server, PinValidator pinValidator) {
        this.server = server;
        this.pinValidator = pinValidator;
    }

    public void run() {
        String input;
        Scanner scanner = new Scanner(System.in);
        for (; ; ) {
            System.out.println("Please enter pin code: ");
            input = scanner.nextLine();
                if (pinValidator.validatePin(input, System.out::println)) {
                    server.runTerminal(scanner);
                    break;
                }
        }
    }





    public static void main(String[] args) {
        TerminalImpl terminal = new TerminalImpl(new TerminalServer(),new PinValidator());
        terminal.run();

    }



}
