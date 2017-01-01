package probabilitytest.behaviour;

import java.util.HashMap;
import probabilitytest.model.Dice;

public class Probability {

    public static HashMap<Integer, Double> startAnimation(int[] numbers, int numberOfChecks){
        HashMap<Integer, Double> hashMap = new HashMap<>();
        Dice dice = new Dice(numbers);
        double[] probabilities = dice.throwDice(numberOfChecks);
        for(int i = 0; i<numbers.length; i++){
            hashMap.put(numbers[i], probabilities[i]);
        }
        return hashMap;
    }
    
}
