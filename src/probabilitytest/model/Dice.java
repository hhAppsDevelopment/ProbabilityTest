package probabilitytest.model;

/*
The dice represents the subject of the probability check. Originally it has 6 sides, and user-defined numbers on each.
*/
public class Dice {
    private final int oldalSzam = 6;
    private int[] numbers;    

    public void setNumbers(int[] numbers) {
        if(numbers != null)this.numbers = numbers;  //If null is given, the original value of 1..6 is given to the dice
        else{
            for(int i = 0; i < this.oldalSzam; i++){
                this.numbers[i] = i + 1;
            }
        }
    }
    
    public double[] throwDice (int numberOfCkecks){
        double[] probabilities = new double[numbers.length];
        for(double d : probabilities)d = 0.0;
        for(int i = 0; i<numberOfCkecks; i++){
            //random probability
        }
        return probabilities;
    }

    public Dice(int[] numbers) {
        this.numbers = numbers;
    }

    public int getOldalSzam() {
        return oldalSzam;
    }

    public int[] getNumbers() {
        return numbers;
    }
}
