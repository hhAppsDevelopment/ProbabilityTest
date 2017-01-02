package probabilitytest.model;

/*
The dice represents the subject of the probability check. Originally it has 6 sides, and user-defined numbers on each.
*/
public class Dice {
    private final int oldalSzam;
    private int[] numbers;    
    
    
    public Dice(int[] numbers) {
        this.numbers = numbers;
        this.oldalSzam = numbers.length;
    }
    
    public double[] throwDice (int numberOfChecks){
        double[] probabilities = new double[numbers.length];
        for(int i = 0; i<numbers.length; i++)probabilities[i] = 0.0;
        for(int i = 0; i<numberOfChecks; i++){
            int which = (int) Math.floor(Math.random()*numbers.length);
            probabilities[which]++;
        }
        for(int i = 0; i<numbers.length; i++){
            probabilities[i] = probabilities[i] /((double)numberOfChecks);
        }
        return probabilities;
    }

    
    public int getOldalSzam() {
        return oldalSzam;
    }

    public int[] getNumbers() {
        return numbers;
    }
    
}
