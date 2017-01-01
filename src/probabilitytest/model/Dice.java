package probabilitytest.model;

/*
The dice represents the subject of the probability check. Originally it has 6 sides, and user-defined numbers on each.
*/
public class Dice {
    private final int oldalSzam = 6;
    private int[] numbers;    
    
    
    public Dice(int[] numbers) {
        if(numbers != null && numbers.length >= oldalSzam)this.numbers = numbers;  //If null or not enough arguments are given, the original value of 1..6 is given to the dice
        else{
            this.numbers = new int[oldalSzam];
            for(int i = 0; i < this.oldalSzam; i++){
                this.numbers[i] = i + 1;
            }
        }
    }
    
    public double[] throwDice (int numberOfCkecks){
        double[] probabilities = new double[numbers.length];
        for(int i = 0; i<numbers.length; i++)probabilities[i] = 0.0;
        for(int i = 0; i<numberOfCkecks; i++){
            int which = (int) Math.floor(Math.random()*numbers.length);
            probabilities[which]++;
        }
        for(int i = 0; i<numbers.length; i++){
            probabilities[i] = probabilities[i] /((double)numberOfCkecks);
        }
        return probabilities;
    }

    
    public int getOldalSzam() {
        return oldalSzam;
    }

    public int[] getNumbers() {
        return numbers;
    }
    
    public void setNumbers(int[] numbers) {
        this.numbers = numbers;
    }
}
