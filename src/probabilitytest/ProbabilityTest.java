package probabilitytest;

import probabilitytest.behaviour.Probability;
import probabilitytest.gui.GuiBuilder;

public class ProbabilityTest {
    private GuiBuilder guiBuilder;
    
    public static void main(String[] args) {
        ProbabilityTest pt = new ProbabilityTest();
        pt.start();
    }

    private void start() {
        guiBuilder = new GuiBuilder();
    }
    
}
