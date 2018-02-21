import java.util.function.Consumer;

public class PinValidator {
    private final String pinCode = "1234";
    private int countInputPin =0;
    private boolean accountIsLocked = false;
    private int timer;

    public boolean validatePin(String userPinCode, Consumer<String> consumer) {
        try {

            if (accountIsLocked && countInputPin == 2) {
                throw new AccountIsLockedException("Account is locked. Time left: " + getTimer() + " seconds");
            }

            if (userPinCode.equals(pinCode)) {
                consumer.accept("Pin is valid");
            } else {
                countEqualThree(System.out::println);
            }
            return userPinCode.equals(pinCode);

        } catch (AccountIsLockedException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    private int getCountInputPin() {
        return countInputPin;
    }

    private void setCountInputPin(int countInputPin) {
        this.countInputPin = countInputPin;
    }

    private int getTimer() {
        return timer;
    }

    private void setAccountIsLocked() {
        this.accountIsLocked = true;

        new Thread(() ->{

            try {

                for (this.timer=5;this.timer>0;this.timer--) {
                    Thread.sleep(1000);
                }

                this.accountIsLocked = false;
                countInputPin = 0;

            } catch (InterruptedException v) {

                System.out.println(v);
            }

        }).start();
    }

    public void countEqualThree (Consumer<String> consumer) {

        if (getCountInputPin() >=2) {

            consumer.accept("Account is locked.");
            this.setAccountIsLocked();


        } else {

            this.setCountInputPin(getCountInputPin() + 1);
            consumer.accept("Pin is not valid, please try more...");
        }

    }

}

class AccountIsLockedException extends Exception {
    public AccountIsLockedException(String s) {
        super(s);
    }
}
