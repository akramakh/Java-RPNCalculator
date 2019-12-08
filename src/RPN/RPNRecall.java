//
package RPN;

import java.awt.event.*; 
import java.awt.*;
import javax.swing.*; 

/**
 *
 * @author Akram A Kh
 */

class RPNRecall extends JFrame implements ActionListener
{   
    private Container c;
    private JScrollPane sp;
    private JList list; 
    private JPanel panel2;
    private JButton recall_b, close_b;
    private static Register[] R ;
//    private static RPNRecall instance = null;
    private static Register item;
    
    public RPNRecall(){
        this.R = RPNCalculator.getInstance().getRegister();
        String s[] = new String[26];
        for (int i = 0; i< this.R.length; i++){
            s[i] = this.R[i].toString();
        }
        this.setTitle("RPN Recall");
        this.setSize(640, 450);
        this.c = getContentPane();
        this.c.setLayout(null);
        this.panel2 = new JPanel();
        this.panel2.setBounds(20, 330, 600, 40);
        this.list = new JList(); 
        this.list.setListData(s);
        this.list.setSelectedIndex(0);
        this.list.setFont(new Font("Courier New", 1, 20));
        this.sp = new JScrollPane(list);
        this.sp.setBounds(20, 20, 600, 300);
        this.c.add(this.sp);
        this.c.add(this.panel2);
        
        this.recall_b = new JButton("Recall");
        this.recall_b.setBounds(100, 50, 100, 30);
        this.close_b = new JButton("Close");
        this.close_b.setBounds(100, 50, 100, 30);
        
        this.panel2.add(recall_b);
        this.panel2.add(close_b);
        
        this.recall_b.addActionListener(this);
        this.close_b.addActionListener(this);
        
        this.recall_b.setFont(new Font("Courier New", 1, 20));
        this.close_b.setFont(new Font("Courier New", 1, 20));
                
    }
    
//    public static RPNRecall getInstance(String data){
//        if (instance == null)
//            instance = new RPNRecall();
//        
//        return instance; 
//    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int x;
        switch(e.getActionCommand()){
            case "Recall":  this.recall();
                break;
            case "Close":  this.close();
                break;
            default: x = 2+2;
        }
    }
    
    private void close(){
        this.dispose();
    }
    
    private void clearAll(){
        for(int i=0; i<this.R.length; i++){
            this.R[i].setValue(0.0);
            this.R[i].setLable("no lable");
        }
        this.list.setListData(this.R);
    }
    
    private void recall() {
        Register r = RPNCalculator.getInstance().getRegister()[this.list.getSelectedIndex()];
        RPNCalculator.getInstance().getTheStack().addFirst(r.getValue());
        RPNForm.setValueAfterRecall(r.getValue());
    }
    
    public static Register getItem(){
        return RPNRecall.item;
    }
    
    
   
}
