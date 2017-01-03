package probabilitytest.behaviour;

import java.util.ArrayList;
import java.util.Collections;
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
    
    public static HashMap<Integer, Double> continousRoll(int[] numbers, int maxNumberOfChecks, int numberToTest){
        HashMap<Integer, Double> hashMap = new HashMap<>();
        Dice dice = new Dice(numbers);
        int d = 1;
        
        ArrayList<Integer> ali = new ArrayList<>();
        for(int i = 0; i<numbers.length; i++){
            if(numbers[i] == numberToTest)ali.add(i);
        }
        double lastProb = 0.0;
        for(int i = 1; i<=10; i++){
            double prob = 0.0;
            double[] probabilities = dice.throwDice(maxNumberOfChecks/10);
            for(Integer j: ali){
                prob = prob + probabilities[j];
            }
            lastProb = (lastProb*(i-1)+prob)/i;
            hashMap.put(i, lastProb);
        }
        
        System.out.println(hashMap);
        return hashMap;
    }
    
}
