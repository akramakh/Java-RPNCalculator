//
package RPN;

import java.awt.event.*; 
import java.awt.*; 
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.*; 

/**
 *
 * @author Akram A Kh
 */

class RPNStore extends JFrame implements ActionListener
{   
    private Container c;
    private JScrollPane sp;
    private JList list; 
    private JPanel panel1, panel2;
    private JButton plus_b, minus_b, mul_b, div_b, store_b, edit_b, clear_b, close_b;
    String week[]= { "Monday","Tuesday","Wednesday", 
                         "Thursday","Friday","Saturday","Sunday"}; 
    private static Register[] R ;
    private static RPNStore instance = null;
    
    public RPNStore(){
        this.R = RPNCalculator.getInstance().getRegister();
        String s[] = new String[26];
        for (int i = 0; i< this.R.length; i++){
            s[i] = this.R[i].toString();
        }
        this.setTitle("RPN Store");
        this.setSize(640, 500);
        this.c = getContentPane();
        this.c.setLayout(null);
        this.panel1 = new JPanel();
        this.panel1.setBounds(20, 350, 600, 40);
        this.panel2 = new JPanel();
        this.panel2.setBounds(20, 400, 600, 40);
        this.list = new JList(); 
        this.list.setListData(s);
        this.list.setSelectedIndex(0);
        this.list.setFont(new Font("Courier New", 1, 20));
        this.sp = new JScrollPane(list);
        this.sp.setBounds(20, 20, 600, 300);
        this.c.add(this.sp);
        this.c.add(this.panel1);
        this.c.add(this.panel2);
        
        this.plus_b = new JButton("+");
        this.plus_b.setBounds(100, 50, 100, 30);
        this.minus_b = new JButton("-");
        this.minus_b.setBounds(100, 50, 100, 30);
        this.mul_b = new JButton("x");
        this.mul_b.setBounds(100, 50, 100, 30);
        this.div_b = new JButton("/");
        this.div_b.setBounds(100, 50, 100, 30);
        this.store_b = new JButton("Store");
        this.store_b.setBounds(100, 50, 100, 30);
        this.edit_b = new JButton("Edit Label");
        this.edit_b.setBounds(100, 50, 100, 30);
        this.clear_b = new JButton("Clear All");
        this.clear_b.setBounds(100, 50, 100, 30);
        this.close_b = new JButton("Close");
        this.close_b.setBounds(100, 50, 100, 30);
        
        this.panel1.add(plus_b);
        this.panel1.add(minus_b);
        this.panel1.add(mul_b);
        this.panel1.add(div_b);
        this.panel2.add(store_b);
        this.panel2.add(edit_b);
        this.panel2.add(clear_b);
        this.panel2.add(close_b);
        
        this.plus_b.addActionListener(this);
        this.minus_b.addActionListener(this);
        this.mul_b.addActionListener(this);
        this.div_b.addActionListener(this);
        this.store_b.addActionListener(this);
        this.edit_b.addActionListener(this);
        this.clear_b.addActionListener(this);
        this.close_b.addActionListener(this);
        
        this.plus_b.setFont(new Font("Courier New", 1, 20));
        this.minus_b.setFont(new Font("Courier New", 1, 20));
        this.mul_b.setFont(new Font("Courier New", 1, 20));
        this.div_b.setFont(new Font("Courier New", 1, 20));
        this.store_b.setFont(new Font("Courier New", 1, 20));
        this.edit_b.setFont(new Font("Courier New", 1, 20));
        this.clear_b.setFont(new Font("Courier New", 1, 20));
        this.close_b.setFont(new Font("Courier New", 1, 20));
        
        this.checkButtonsAvailability();
                
    }
    
//    public static RPNStore getInstance(String data){
//        if (instance == null)
//            instance = new RPNStore();
//        
//        return instance; 
//    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int x;
        switch(e.getActionCommand()){
            case "+":  this.store(e.getActionCommand());
                break;
            case "-":  this.store(e.getActionCommand());
                break;
            case "x":  this.store(e.getActionCommand());
                break;
            case "/":  this.store(e.getActionCommand());
                break;
            case "Store":  this.store(e.getActionCommand());
                break;
            case "Edit Label":  this.editLabel();
                break;
            case "Clear All":  this.clearAll();
                break;
            case "Close":  this.close();
                break;
            default: x = 2+2;
        }
    }
    
    private void close(){
        this.dispose();
    }
    
    private void editLabel(){
        int i = this.list.getSelectedIndex();
        String in = JOptionPane.showInputDialog(null, "Enter Your Label here", this.R[i].getLabel());
        this.R[i].setLable(in);
        this.list.setListData(this.R);
        this.list.setSelectedIndex(i);
        this.checkButtonsAvailability();
    }
    
    private void clearAll(){
        for(int i=0; i<this.R.length; i++){
            this.R[i].setValue(0.0);
            this.R[i].setLable("no lable");
        }
        this.list.setListData(this.R);
        this.checkButtonsAvailability();
    }
    
    private void store(String actionCommand){
        double new_value = RPNCalculator.getInstance().getTheStack().peekFirst();
        int i = this.list.getSelectedIndex();
        double curr_value = this.R[i].getValue();
        switch(actionCommand){
            case "+": this.R[i].setValue(curr_value + new_value);
                break;
            case "-": this.R[i].setValue(curr_value - new_value);
                break;
            case "x": this.R[i].setValue(curr_value * new_value);
                break;
            case "/": try{
                        this.R[i].setValue(curr_value / new_value);
                    }catch(Exception e){
                        JOptionPane.showMessageDialog(null, "Invalid Dividion Operation", "Alert",JOptionPane.WARNING_MESSAGE);
                    }
                break;
            default: this.R[this.list.getSelectedIndex()].setValue(new_value);
        }
        this.list.setListData(this.R);
        this.list.setSelectedIndex(i);
        this.checkButtonsAvailability();
    }
    
    private void write() {
        try {
            final PrintWriter fileOutput = new PrintWriter(new FileOutputStream("RegisterFile.txt"));
            for (int i = 0; i < this.R.length; ++i) {
                fileOutput.println(this.R[i]);
            }
            fileOutput.close();
        }
        catch (IOException e) {
            System.out.println("(MacroFile error: " + e.toString());
        }
        this.checkButtonsAvailability();
    }
    
    private void checkButtonsAvailability(){
        int index = this.list.getSelectedIndex();
        int size = RPNCalculator.getInstance().getTheStack().size();
        System.out.println(size +" "+ index);
        this.plus_b.setEnabled(size > 0 && index > -1);
        this.minus_b.setEnabled(size > 0 && index > -1);
        this.mul_b.setEnabled(size > 0 && index > -1);
        this.div_b.setEnabled(size > 0 && index > -1);
        this.store_b.setEnabled(size > 0 && index > -1);
        this.edit_b.setEnabled(size > 0 && index > -1);
        this.clear_b.setEnabled(size > 0);
    }
   
}
