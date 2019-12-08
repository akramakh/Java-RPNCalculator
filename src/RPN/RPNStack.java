
package RPN;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Akram A Kh
 */
public class RPNStack extends JFrame implements ActionListener {
    
    private Container c;
    private JScrollPane sp;
    private JLabel title_l, indicator_l;
    private JList list; 
    private JPanel panel2;
    private JButton up_b, down_b, copy_b, edit_b, delete_b, clear_b, close_b;
    private static Register[] R ;
    private static RPNStack instance = null;
    
    public RPNStack(){
        this.init();
    }
    private void init(){
        this.setTitle("RPN Stack");
        this.setSize(700, 500);
        this.c = getContentPane();
        this.c.setLayout(null);
        this.panel2 = new JPanel();
        this.panel2.setBounds(540, 80, 140, 350);
        this.panel2.setLayout(null);
        this.list = new JList(); 
        this.list.setListData(RPNCalculator.getInstance().theStack.toArray());
        this.list.setSelectedIndex(0);
        this.list.setFont(new Font("Courier New", 1, 16));
        this.sp = new JScrollPane(list);
        this.sp.setBounds(130, 80, 400, 300);
        this.c.add(this.sp);
        this.c.add(this.panel2);
        
        this.title_l = new JLabel("The Stack");
        this.title_l.setBounds(140, 30, 300, 30);
        this.title_l.setFont(new Font("Courier New", 1, 18));
        this.title_l.setForeground(Color.blue);
        this.indicator_l = new JLabel("Top >>>");
        this.indicator_l.setBounds(20, 80, 120, 30);
        this.indicator_l.setFont(new Font("Courier New", 1, 18));
        this.indicator_l.setForeground(Color.green);
        
        
        this.c.add(this.title_l);
        this.c.add(this.indicator_l);
        
        this.up_b = new JButton("Up");
        this.up_b.setBounds(20, 20, 100, 30);
        this.down_b = new JButton("Down");
        this.down_b.setBounds(20, 60, 100, 30);
        this.copy_b = new JButton("Copy");
        this.copy_b.setBounds(20, 100, 100, 30);
        this.edit_b = new JButton("Edit");
        this.edit_b.setBounds(20, 140, 100, 30);
        this.delete_b = new JButton("Delete");
        this.delete_b.setBounds(20, 180, 100, 30);
        this.clear_b = new JButton("Clear");
        this.clear_b.setBounds(20, 220, 100, 30);
        
        this.close_b = new JButton("Close");
        this.close_b.setBounds(260, 400, 100, 30);
        
        this.panel2.add(up_b);
        this.panel2.add(down_b);
        this.panel2.add(copy_b);
        this.panel2.add(edit_b);
        this.panel2.add(delete_b);
        this.panel2.add(clear_b);
        this.c.add(close_b);
        
        this.up_b.addActionListener(this);
        this.down_b.addActionListener(this);
        this.copy_b.addActionListener(this);
        this.edit_b.addActionListener(this);
        this.delete_b.addActionListener(this);
        this.clear_b.addActionListener(this);
        this.close_b.addActionListener(this);
                
        this.up_b.setFont(new Font("Courier New", 1, 18));
        this.down_b.setFont(new Font("Courier New", 1, 18));
        this.copy_b.setFont(new Font("Courier New", 1, 18));
        this.edit_b.setFont(new Font("Courier New", 1, 18));
        this.delete_b.setFont(new Font("Courier New", 1, 18));
        this.clear_b.setFont(new Font("Courier New", 1, 18));
        this.close_b.setFont(new Font("Courier New", 1, 18));
        
        this.list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
            checkButtonsAvailability();
            }
        });
        
        this.checkButtonsAvailability();
                
    }

//    public static RPNStack getInstance(){
//        if (instance == null)
//            instance = new RPNStack();
//        return instance;
//    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Up":  this.upDown(e.getActionCommand());
                break;
            case "Down":  this.upDown(e.getActionCommand());
                break;
            case "Copy":  this.none();
                break;
            case "Edit":  this.editElement();
                break;
            case "Delete":  this.deleteElement();
                break;
            case "Clear":  this.clearStack();
                break;
            case "Close":  this.close();
                break;
            default: this.none();
        }
    }
    
    
    
    private void close(){
        this.dispose();
    }
    
    private void upDown(String actionCommand){
        Double temp = null;
        int index = this.list.getSelectedIndex();
        Object[] arr = RPNCalculator.getInstance().getTheStack().toArray();
        temp = (double) arr[index];
        
        if (actionCommand.equals("Up")) {
            if(index > 0){
                arr[index] = arr[index-1];   
                arr[index-1] = temp;
                temp = null;
                RPNCalculator.getInstance().setTheStack( new LinkedList(Arrays.asList(arr)));
                this.list.setListData(RPNCalculator.getInstance().getTheStack().toArray());
                this.list.setSelectedIndex(index-1);
            }
            
        }
        else if (actionCommand.equals("Down")) {
            if(index < arr.length -1){
                arr[index] = arr[index+1]; 
                arr[index+1] = temp;
                temp = null;
                RPNCalculator.getInstance().setTheStack( new LinkedList(Arrays.asList(arr)));
                this.list.setListData(RPNCalculator.getInstance().getTheStack().toArray());
                this.list.setSelectedIndex(index+1);
            }
        }
        this.checkButtonsAvailability();
    }
    
    private void clearStack(){
        RPNCalculator.getInstance().getTheStack().clear();
        this.list.setListData(RPNCalculator.getInstance().getTheStack().toArray());
        this.checkButtonsAvailability();
    }
    
    private void deleteElement(){
        int index = this.list.getSelectedIndex();
        Object[] arr = RPNCalculator.getInstance().getTheStack().toArray();
        Object[] result = new Object[arr.length-1];
        for (int i=index; i<arr.length-1; i++){
            arr[i] = arr[i+1];
        }
        for (int i=0; i<result.length; i++){
            result[i] = arr[i];
        }
        RPNCalculator.getInstance().setTheStack( new LinkedList(Arrays.asList(result)));
        this.list.setListData(RPNCalculator.getInstance().getTheStack().toArray()); 
        this.checkButtonsAvailability();
    }
    
    private void checkButtonsAvailability(){
        int index = this.list.getSelectedIndex();
        int size = RPNCalculator.getInstance().getTheStack().size();
        this.copy_b.setEnabled(size > 0 && index > -1);
        this.edit_b.setEnabled(size > 0 && index > -1);
        this.delete_b.setEnabled(size > 0 && index > -1);
        this.clear_b.setEnabled(size > 0);
        this.up_b.setEnabled(index > 0);
        this.down_b.setEnabled(index > -1 && index < RPNCalculator.getInstance().getTheStack().size()-1);
    }
    
    private void copyElement(){
        int index = this.list.getSelectedIndex();
        String s = RPNCalculator.getInstance().getTheStack().toArray()[index].toString();
    }
    
    private void editElement(){
        String s = JOptionPane.showInputDialog(this, "Enter your Value",JOptionPane.QUESTION_MESSAGE);
        
        int index = this.list.getSelectedIndex();
        Object[] arr = RPNCalculator.getInstance().getTheStack().toArray();
        try{
            double value = Double.parseDouble(s);
            arr[index] = value;
        }catch(NumberFormatException nfe){
            JOptionPane.showMessageDialog(this, "Enter a valid Number ","Invalid Value" ,JOptionPane.ERROR_MESSAGE);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "ERROR >> "+e.getMessage(),"Invalid Value" ,JOptionPane.ERROR_MESSAGE);
        }
        RPNCalculator.getInstance().setTheStack( new LinkedList(Arrays.asList(arr)));
        this.list.setListData(RPNCalculator.getInstance().getTheStack().toArray());
        this.list.setSelectedIndex(index);

        
    }
    
    private void none(){
        
    }
    
}
