package probabilitytest.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import probabilitytest.behaviour.Probability;

public class GuiBuilder {
/*
 *The static startAnimation(int[] numbers, int numberOfChecks):HashMap<Integer, Double> method of the Probability class retrieves the final probabilites after the number of checks ran, mapping numbers to their probabilites. 
 *Call that after getting input from user, and use that to display the probabilites of each number.
 */
    JFrame frame;   // Application frame
    JPanel bgPanel;
    
    JPanel listPanel;   // Left panel
    JPanel graphPanel;  // Right panel
    
    JPanel addPanel;
    JLabel addLabel;
    JTextField addField;
    JButton addButton;
    JButton removeButton;
    JButton clearButton;
    JButton rollButton;
    
    JList itemList;
    DefaultListModel listModel;
    JScrollPane pane;
    
    JLabel checkLabel;
    JSlider slider;
    
    static final int X_MIN = 0;
    static final int X_MAX = 9;
    static final int X_INIT = 3;
    
    JFreeChart chart;
    ChartPanel chartPanel;
    
    Integer[] numbers;
    int rolls;
    
    public GuiBuilder() {
        rolls = X_INIT;
    }
    
    public void buildGui(){
        frame = new JFrame("ProbabilityTest");
        bgPanel = new JPanel(new GridLayout(1,2));
        
        frame.setContentPane(bgPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setSize(600,300);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-(frame.getSize().width/2), dim.height/2-frame.getSize().height/2);
        
        listPanel = new JPanel();
        graphPanel = new JPanel();
        
        
        bgPanel.add(listPanel);
        bgPanel.add(graphPanel);
        
        //listPanel.setBackground(Color.RED);
        //graphPanel.setBackground(Color.YELLOW);
        
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        
        addPanel = new JPanel();
        addLabel = new JLabel("Add new entries here:");
        addField = new JTextField(5);
        addButton = new JButton("Add");    
        
        addButton.addActionListener(new AddButtonListener());
        
        addPanel.add(addLabel);
        addPanel.add(addField);
        addPanel.add(addButton);
                
        slider = new JSlider(JSlider.HORIZONTAL,X_MIN,X_MAX,X_INIT);
        slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(new SliderListener());
        checkLabel = new JLabel();
        updateRolls();
        
        listModel = new DefaultListModel();
        itemList = new JList(listModel);
        itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        itemList.setLayoutOrientation(JList.VERTICAL);
        pane = new JScrollPane(itemList);
        
        listPanel.add(addPanel);
        listPanel.add(pane);
        listPanel.add(Box.createVerticalGlue());
        listPanel.add(slider);
        listPanel.add(checkLabel);
        
        slider.setAlignmentX(Component.CENTER_ALIGNMENT);
        checkLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
               
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        frame.setVisible(true);
    }
    
    public void updateRolls(){
        rolls = slider.getValue();
        checkLabel.setText("Number of rolls: 10^"+rolls);
    }
    
    class SliderListener implements ChangeListener{
        public void stateChanged(ChangeEvent e){
            updateRolls();
        }
    }
    
    class AddButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if(isNumeric(addField.getText())){
                listModel.addElement(Integer.parseInt(addField.getText()));
            }            
        }
    }
        
    
    public static boolean isNumeric(String str){
        for (char c : str.toCharArray()){
            if (!Character.isDigit(c)) return false;
            }
        return true;
    }
}
