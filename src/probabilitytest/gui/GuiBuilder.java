package probabilitytest.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
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
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
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
    
    JPanel buttonPanel;
    JButton removeButton;
    JButton clearButton;
    JButton rollButton;
    
    JList itemList;
    DefaultListModel<Integer> listModel;
    JScrollPane pane;
    
    JLabel checkLabel;
    JSlider slider;
    
    static final int X_MIN = 0;
    static final int X_MAX = 9;
    static final int X_INIT = 3;
    
    JFreeChart chart;
    ChartPanel chartPanel;
    
    int rolls;
    
    public GuiBuilder() {
        rolls = X_INIT;
    }
    
    public void buildGui(){
        frame = new JFrame("ProbabilityTest");
        bgPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        frame.setContentPane(bgPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setSize(600,310);
        frame.setMinimumSize(new Dimension(300, 310));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-(frame.getSize().width/2), dim.height/2-frame.getSize().height/2);
        
        listPanel = new JPanel();
        graphPanel = new JPanel();
        
        
        bgPanel.add(listPanel);
        bgPanel.add(graphPanel);
        
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setSize(300,330);    
        
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
        
        buttonPanel = new JPanel();
        removeButton = new JButton("Remove");
        clearButton = new JButton("Clear");
        rollButton = new JButton("Roll");
        
        removeButton.addActionListener(new RemoveButtonListener());
        clearButton.addActionListener(new ClearButtonListener());
        rollButton.addActionListener(new RollButtonListener());
        
        buttonPanel.add(removeButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(rollButton);
        
        listPanel.add(addPanel);
        listPanel.add(pane);
        listPanel.add(Box.createVerticalGlue());
        listPanel.add(slider);
        listPanel.add(checkLabel);
        listPanel.add(Box.createVerticalGlue());
        listPanel.add(buttonPanel);
        
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
            addField.setText("");
            addField.requestFocus();
        }
    }
        
    class RemoveButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if(listModel.getSize()!= 0){
                int index = itemList.getSelectedIndex();
                listModel.remove(index);
            }            
        }        
    }
    
    class ClearButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            listModel.removeAllElements();
        }        
    }
    
    class RollButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if(listModel.getSize()!=0){
                int[] numbers = new int[listModel.getSize()];
                for(int i=0;i<listModel.getSize();i++){
                    numbers[i]=listModel.getElementAt(i).intValue();
                }
                displayChart(Probability.roll(numbers, slider.getValue()));
            }
        }
    }
    
    public static boolean isNumeric(String str){
        for (char c : str.toCharArray()){
            if (!Character.isDigit(c)) return false;
            }
        return true;
    }
    
    public void displayChart(HashMap<Integer,Double> values){
        graphPanel.removeAll();
        JFreeChart chart = ChartFactory.createBarChart(
         "Results", 
         "Category", "Value", 
         createDataset(values),PlotOrientation.VERTICAL, 
         false, false, false);
        chartPanel = new ChartPanel(chart,false);       
        graphPanel.add(chartPanel);
        frame.pack();
        graphPanel.validate();
    }
    
    public CategoryDataset createDataset(HashMap<Integer,Double> values){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(HashMap.Entry<Integer,Double> entry:values.entrySet()){
            dataset.addValue(entry.getValue(),"Roll" ,entry.getKey());
        }
        return dataset;
    }
}
