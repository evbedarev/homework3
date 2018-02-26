import java.util.Random;

public class RandomException {
    public void random () throws NetworkProblemException {
        Random random = new Random();
        if (random.nextInt(10)==2) { throw new NetworkProblemException("Network problems. Please try later...");}
        if (random.nextInt(10)==3) { throw new NetworkProblemException("Hardware problems.  Please try later...");}
    }

}

class NetworkProblemException extends Exception {
    NetworkProblemException(String s) {
        super(s);
    }
}

class HardwareProblemException extends Exception {
    HardwareProblemException(String s) {
        super(s);
    }
}