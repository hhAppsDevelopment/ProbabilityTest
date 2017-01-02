package probabilitytest.behaviour;

import java.util.ArrayList;
import java.util.HashMap;
import probabilitytest.model.Dice;

public class Probability {

    public static HashMap<Integer, Double> roll(int[] numbers, int numberOfChecks){
        HashMap<Integer, Double> hashMap = new HashMap<>();
        Dice dice = new Dice(numbers);
        double[] probabilities = dice.throwDice(numberOfChecks);
        for(int i = 0; i<dice.getOldalSzam(); i++){
            ArrayList<Integer> sameIndices = new ArrayList<>();
            for(int j = 0; j<=i; j++){
                if(dice.getNumbers()[i] == dice.getNumbers()[j])sameIndices.add(j);
            }
            double previous = 0.0;
            if(hashMap.containsKey(dice.getNumbers()[sameIndices.get(0)]))previous = hashMap.get(dice.getNumbers()[sameIndices.get(0)]);
            hashMap.put(dice.getNumbers()[sameIndices.get(0)], previous + probabilities[i]); 
            
        }
        System.out.println(hashMap);
        return hashMap;
    }
    
}
