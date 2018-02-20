import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class PinValidator {
    private final String pinCode = "1234";
    private int countInputPin =0;
    private boolean accountIsLocked = false;

    public boolean validatePin(String userPinCode) {
        return userPinCode.equals(pinCode);
    }

    public int getCountInputPin() {
        return countInputPin;
    }

    public void setCountInputPin(int countInputPin) {
        this.countInputPin = countInputPin;
    }

    public boolean isAccountIsLocked() {
        return accountIsLocked;
    }

    public void setAccountIsLocked(boolean accountIsLocked) {
        this.accountIsLocked = accountIsLocked;
        new Thread(() ->{
            try {
                Thread.sleep(5000);
                this.accountIsLocked = false;
                countInputPin=0;
            } catch (InterruptedException v) {
                System.out.println(v);
            }
        }).start();
    }

}

//        ActionListener listner = event ->
//        {
//            this.accountIsLocked=false;
//            countInputPin=0;
//            System.out.println("Account is lock by method setAccountIsLocked");
//
//        };
//        Timer timer = new Timer();