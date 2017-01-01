package probabilitytest.gui;

import probabilitytest.behaviour.Probability;

public class GuiBuilder {
/*
 *The static startAnimation(int[] numbers, int numberOfChecks):HashMap<Integer, Double> method of the Probability class retrieves the final probabilites after the number of checks ran, mapping numbers to their probabilites. 
 *Call that after getting input from user, and use that to display the probabilites of each number.
 */
    public GuiBuilder() {
        int[] i = {1,1,1,1,2,3}; //can be null, too
        Probability.startAnimation(i, 500);
    }
    
}
