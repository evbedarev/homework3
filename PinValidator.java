import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;

public class PinValidator {
    private final String pinCode = "1234";
    private int countInputPin =0;
    private boolean accountIsLocked = false;
    private int timer;

    public boolean validatePin(String userPinCode, Consumer<String> consumer) {
        if (accountIsLocked && countInputPin==3) {
            consumer.accept("Account is locked. Time left: " + getTimer() + " seconds");
            return false;
        }
        if (userPinCode.equals(pinCode)) {consumer.accept("Pin is valid");}

        else {countEqualThree(System.out::println);}

        return userPinCode.equals(pinCode);
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

        if (getCountInputPin() >=3) {

            consumer.accept("Account is locked.");
            this.setAccountIsLocked();


        } else {

            this.setCountInputPin(getCountInputPin() + 1);
            consumer.accept("Pin is not valid, please try more...");
        }

    }

}
