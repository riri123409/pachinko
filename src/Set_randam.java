import java.util.Random;

public class Set_randam {
    public int set_randam(int renge) {
        Random random = new Random();
        int randomValue = random.nextInt(renge);

        return randomValue;
    }
}
