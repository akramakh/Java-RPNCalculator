
package RPN;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Akram A Kh
 */

public class RPNForm extends JFrame implements ActionListener {
  
  private final Container contentPane;
  private final JPanel displayPanel;
  private final JLabel RPNLabel;
  public static JTextField displayTextField;
  private final JPanel buttonPanel;
  private String inString = "";
  
  private final JButton[][] buttonGrid;
  
  private boolean isHelp = false, 
          number_entered = false, 
          display_error = false,
          allow_write = true;
  
  private final HashMap<String, String> helpMessages = new HashMap<String, String>();

  public static void main(String[] args) {
    RPNForm gui = new RPNForm();
    gui.setVisible(true);
  }

  
  
  
  public RPNForm() {  
    setSize(660, 330);
    setDefaultCloseOperation(3);
    setTitle("RPN Calculator");
    this.contentPane = getContentPane();
    this.contentPane.setLayout(new BorderLayout());
    
    this.displayPanel = new JPanel();
    this.displayPanel.setLayout(new BoxLayout(this.displayPanel, 0));
    this.displayPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(204, 153, 255)));
    
    this.RPNLabel = new JLabel(" RPN ");
    this.RPNLabel.setBackground(new Color(51, 0, 102));
    this.RPNLabel.setFont(new Font("Courier New", 1, 36));
    this.RPNLabel.setForeground(new Color(102, 51, 102));
    this.displayPanel.add(this.RPNLabel);
    
    RPNForm.displayTextField = new JTextField("");
    RPNForm.displayTextField.setFont(new Font("Courier New", 1, 24));
    RPNForm.displayTextField.setHorizontalAlignment(4);
    RPNForm.displayTextField.setActionCommand("Enter");
    RPNForm.displayTextField.addActionListener(this);
    this.displayPanel.add(RPNForm.displayTextField);
    this.contentPane.add(this.displayPanel, "North");
    
    this.buttonPanel = new JPanel();
    this.buttonPanel.setBackground(new Color(255, 239, 223));
    this.buttonPanel.setLayout(new GridLayout(4, 8));
    this.buttonPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(153, 255, 255)));
    
    this.buttonGrid = new JButton[4][8];
    
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 8; j++) {
        this.buttonGrid[i][j] = new JButton();
        this.buttonGrid[i][j].setFont(new Font("Courier New", 1, 16));
        this.buttonGrid[i][j].addActionListener(this);
        this.buttonGrid[i][j].setBorder(BorderFactory.createBevelBorder(1));
        this.buttonPanel.add(this.buttonGrid[i][j]);
      } 
    } 
    this.buttonGrid[0][0].setText("eXit");
    this.buttonGrid[0][1].setText("C");
    this.buttonGrid[0][2].setText("CE");
    this.buttonGrid[0][3].setFont(new Font("Courier New", 1, 20));
    this.buttonGrid[0][3].setBackground(new Color(240, 246, 255));
    this.buttonGrid[0][3].setForeground(new Color(153, 0, 102));
    this.buttonGrid[0][3].setText("7");
    this.buttonGrid[0][4].setFont(new Font("Courier New", 1, 20));
    this.buttonGrid[0][4].setBackground(new Color(240, 246, 255));
    this.buttonGrid[0][4].setForeground(new Color(153, 0, 102));
    this.buttonGrid[0][4].setText("8");
    this.buttonGrid[0][5].setFont(new Font("Courier New", 1, 20));
    this.buttonGrid[0][5].setBackground(new Color(240, 246, 255));
    this.buttonGrid[0][5].setForeground(new Color(153, 0, 102));
    this.buttonGrid[0][5].setText("9");
    this.buttonGrid[0][6].setText("+");
    this.buttonGrid[0][7].setText("x");
    this.buttonGrid[1][0].setText("STO");
    this.buttonGrid[1][1].setText("RCL");
    this.buttonGrid[1][2].setText("Up");
    this.buttonGrid[1][3].setFont(new Font("Courier New", 1, 20));
    this.buttonGrid[1][3].setBackground(new Color(240, 246, 255));
    this.buttonGrid[1][3].setForeground(new Color(153, 0, 102));
    this.buttonGrid[1][3].setText("4");
    this.buttonGrid[1][4].setFont(new Font("Courier New", 1, 20));
    this.buttonGrid[1][4].setBackground(new Color(240, 246, 255));
    this.buttonGrid[1][4].setForeground(new Color(153, 0, 102));
    this.buttonGrid[1][4].setText("5");
    this.buttonGrid[1][5].setFont(new Font("Courier New", 1, 20));
    this.buttonGrid[1][5].setBackground(new Color(240, 246, 255));
    this.buttonGrid[1][5].setForeground(new Color(153, 0, 102));
    this.buttonGrid[1][5].setText("6");
    this.buttonGrid[1][6].setText("-");
    this.buttonGrid[1][7].setText("/");
    this.buttonGrid[2][0].setText("Load");
    this.buttonGrid[2][1].setText("Save");
    this.buttonGrid[2][2].setText("Down");
    this.buttonGrid[2][3].setFont(new Font("Courier New", 1, 20));
    this.buttonGrid[2][3].setBackground(new Color(240, 246, 255));
    this.buttonGrid[2][3].setForeground(new Color(153, 0, 102));
    this.buttonGrid[2][3].setText("1");
    this.buttonGrid[2][4].setFont(new Font("Courier New", 1, 20));
    this.buttonGrid[2][4].setBackground(new Color(240, 246, 255));
    this.buttonGrid[2][4].setForeground(new Color(153, 0, 102));
    this.buttonGrid[2][4].setText("2");
    this.buttonGrid[2][5].setFont(new Font("Courier New", 1, 20));
    this.buttonGrid[2][5].setBackground(new Color(240, 246, 255));
    this.buttonGrid[2][5].setForeground(new Color(153, 0, 102));
    this.buttonGrid[2][5].setText("3");
    this.buttonGrid[2][6].setText("^");
    this.buttonGrid[2][7].setText("%");
    this.buttonGrid[3][0].setText("BSP");
    this.buttonGrid[3][1].setText("Stack");
    this.buttonGrid[3][2].setText("?");
    this.buttonGrid[3][3].setText("+/-");
    this.buttonGrid[3][4].setFont(new Font("Courier New", 1, 20));
    this.buttonGrid[3][4].setBackground(new Color(240, 246, 255));
    this.buttonGrid[3][4].setForeground(new Color(153, 0, 102));
    this.buttonGrid[3][4].setText("0");
    this.buttonGrid[3][5].setText(".");
    this.buttonGrid[3][6].setText("1/n");
    this.buttonGrid[3][7].setFont(new Font("Courier New", 1, 15));
    this.buttonGrid[3][7].setBackground(new Color(246, 240, 255));
    this.buttonGrid[3][7].setForeground(new Color(153, 0, 102));
    this.buttonGrid[3][7].setText("Enter");
    
    this.contentPane.add(this.buttonPanel, "Center");
    
    this.helpMessages.put("eXit", "eXit: close this program");
    this.helpMessages.put("C", "C: removes all elements from stack");
    this.helpMessages.put("CE", "CE: removes top element from stack");
    this.helpMessages.put("+", "+: adds the top  2 elements");
    this.helpMessages.put("x", "x: multiplies he top 2 elements");
    this.helpMessages.put("-", "-: subtracts the top 2 elements");
    this.helpMessages.put("/", "/: divides the top 2 elements");
    this.helpMessages.put("^", "^: exponentof top 2 elements");
    this.helpMessages.put("%", "%: modula of top 2 elements");
    this.helpMessages.put(".", ".: change numbet to fraction");
    this.helpMessages.put("+/-", "+/-: changes sign of number");
    this.helpMessages.put("1/n", "1/n: mult. inverse of nember");
    this.helpMessages.put("?", "?: get help for nex key press");
    this.helpMessages.put("Up", "Up: Rotates stack up");
    this.helpMessages.put("Down", "Down: Rotates stack down");
    this.helpMessages.put("Save", "Save: Saves current state to files");
    this.helpMessages.put("Load", "Load: Loads saved state from files");
    this.helpMessages.put("Enter", "Enter: push element to stack");
    this.helpMessages.put("STO", "STO: show store registers");
    this.helpMessages.put("RCL", "RCL: show recall registers");
    this.helpMessages.put("Stack", "Stack: display stack content");
    this.helpMessages.put("BSP", "BSP: remove last char (Backspace)");
    
    
    displayTextField.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {

      }

      @Override
      public void keyPressed(KeyEvent e) {

      }

      @Override
      public void keyReleased(KeyEvent e) {
          if(!(e.getKeyChar() == '+' || e.getKeyChar() == '-' 
                 || e.getKeyChar() == '*' || e.getKeyChar() == '/' || e.getKeyChar() == '^' || e.getKeyChar() == '%' 
                 || e.getKeyChar() == KeyEvent.VK_ENTER || e.getKeyChar() == KeyEvent.VK_BACK_SPACE 
                 || e.getKeyChar() == 'X' || e.getKeyChar() == 'x' || e.getKeyChar() == '.'
                 || e.getKeyChar() == '?' || e.getKeyChar() == 'c' || Character.isDigit(e.getKeyChar()))){
            RPNForm.displayTextField.setText("");
         }else{
             switch(e.getKeyChar()){
                 case 'x': if (RPNCalculator.getInstance().getTheStack().size()< 2  ){
                                RPNForm.displayTextField.setText("");
                            }
                            else if(RPNCalculator.getInstance().actOnCommand(String.valueOf(e.getKeyChar()))) {
                                RPNForm.displayTextField.setText(RPNCalculator.getInstance().theStack.peekFirst().toString());
                            } break;
                 case 'X': if (RPNCalculator.getInstance().getTheStack().size()< 2  ){
                                RPNForm.displayTextField.setText("");
                            }
                            else if (RPNCalculator.getInstance().actOnCommand(String.valueOf(e.getKeyChar()).toLowerCase())) {
                                RPNForm.displayTextField.setText(RPNCalculator.getInstance().theStack.peekFirst().toString());
                            } break;
                 case '*': if (RPNCalculator.getInstance().getTheStack().size()< 2  ){
                                RPNForm.displayTextField.setText("");
                            }
                            else if (RPNCalculator.getInstance().actOnCommand("x")) {
                                RPNForm.displayTextField.setText(RPNCalculator.getInstance().theStack.peekFirst().toString());
                            } break;
                 case '/': if (RPNCalculator.getInstance().getTheStack().size()< 2  ){
                                RPNForm.displayTextField.setText("");
                            }
                            else if (RPNCalculator.getInstance().actOnCommand(String.valueOf(e.getKeyChar()))) {
                                RPNForm.displayTextField.setText(RPNCalculator.getInstance().theStack.peekFirst().toString());
                            } break;
                 case '+': if (RPNCalculator.getInstance().getTheStack().size()< 2  ){
                                RPNForm.displayTextField.setText("");
                            }
                            else if (RPNCalculator.getInstance().actOnCommand(String.valueOf(e.getKeyChar()).toLowerCase())) {
                                RPNForm.displayTextField.setText(RPNCalculator.getInstance().theStack.peekFirst().toString());
                            } break;
                 case '-': if (RPNCalculator.getInstance().getTheStack().size()< 2  ){
                                RPNForm.displayTextField.setText("");
                            }
                            else if (RPNCalculator.getInstance().actOnCommand(String.valueOf(e.getKeyChar()))) {
                                RPNForm.displayTextField.setText(RPNCalculator.getInstance().theStack.peekFirst().toString());
                            } break;
                 case '%': if (RPNCalculator.getInstance().getTheStack().size()< 2  ){
                                RPNForm.displayTextField.setText("");
                            }
                            else if (RPNCalculator.getInstance().actOnCommand(String.valueOf(e.getKeyChar()))) {
                                RPNForm.displayTextField.setText(RPNCalculator.getInstance().theStack.peekFirst().toString());
                            } break;
                 case '^': if (RPNCalculator.getInstance().getTheStack().size()< 2  ){
                                RPNForm.displayTextField.setText("");
                            }
                            else if (RPNCalculator.getInstance().actOnCommand(String.valueOf(e.getKeyChar()))) {
                                RPNForm.displayTextField.setText(RPNCalculator.getInstance().theStack.peekFirst().toString());
                            } break;
                 case '.': {RPNCalculator.Instructions.addLast(String.valueOf(e.getKeyChar()));
                           if(RPNForm.displayTextField.getText().indexOf(".") <= 0) RPNForm.displayTextField.setText("0.");
                           else if(!Double.isNaN(Double.valueOf(RPNForm.displayTextField.getText())) && RPNForm.displayTextField.getText().indexOf(".") < 0)
                               RPNForm.displayTextField.setText(RPNForm.displayTextField.getText() + ".");
                           if(number_entered) number_entered = false;
                            } break;
                 case 'c': clear(true, String.valueOf(e.getKeyChar())); break;
                 case '?': isHelp = true; RPNForm.displayTextField.setText("Press key to get help ..."); break;
                 default: initCheck(String.valueOf(e.getKeyChar()));
             }
         }

      }
  });
            
  }


  
    public void actionPerformed(ActionEvent event) { dealWith(event.getActionCommand()); }
  
    
    private boolean initCheck(String actionCommand){
       
        if(!allow_write){
            return false;
        }
        if (this.display_error) {
            this.display_error = false;
            RPNForm.displayTextField.setText("");
        }
        if (this.isHelp) {
            this.displayHelp(actionCommand);
            this.isHelp = false;
            return false;
        }
        this.inString = RPNForm.displayTextField.getText();
        return true;
    }


    public void dealWith(String actionCommand) {
        
        if(this.initCheck(actionCommand)){
        try {
            
            switch(actionCommand){
                case "?": this.isHelp = true; RPNForm.displayTextField.setText("Press key to get help ..."); break;
                case "eXit": System.exit(0); break;
                case "STO": this.store(); break;
                case "RCL": this.recall(); break;
                case "BSP": this.doBackspace(); break;
                case "Stack": this.openStack(); break;
                case "Save": this.save(); break;
                case "Load": this.load(); break;
                case "+/-": this.invertSign(actionCommand); break;
                case "CE": this.clear(false, actionCommand); break;
                case "C": this.clear(true, actionCommand); break;
                case "Enter": {RPNCalculator.Instructions.addLast(actionCommand);
                               this.numberEntered(this.inString);
                            } break;
                case "1/n": {RPNCalculator.Instructions.addLast(actionCommand);
                               this.invert(this.inString);
                            } break;
                case ".": {RPNCalculator.Instructions.addLast(actionCommand);
                           if(RPNForm.displayTextField.getText().equals("")) RPNForm.displayTextField.setText("0.");
                           else if(!Double.isNaN(Double.valueOf(RPNForm.displayTextField.getText())) && RPNForm.displayTextField.getText().indexOf(".") < 0)
                               RPNForm.displayTextField.setText(RPNForm.displayTextField.getText() + ".");
                           if(this.number_entered) this.number_entered = false;
                            } break;
                default: this.defaultCheck(actionCommand);
            }
        }
        catch (Exception ex) {
            RPNForm.displayTextField.setText("");
            this.number_entered = false;
        }
        }
    }
    
    private void store(){
        new RPNStore().setVisible(true);
    }
    
    private void recall(){
        new RPNRecall().setVisible(true);
    }
    
    public static void setValueAfterRecall(double value){
        displayTextField.setText(Double.toString(value));
    }
    
    private void openStack(){
        new RPNStack().setVisible(true);
    }
    
    private void save() {
        try {
            final PrintWriter reg_file = new PrintWriter(new FileOutputStream("reg_file.txt"));
            Register r[] = RPNCalculator.getInstance().getRegister();
            for (int i = 0; i < r.length; ++i) {
                reg_file.println(r[i].getValue()+","+r[i].getLabel());
            }
            reg_file.close();
        }
        catch (IOException e) {
            System.out.println("Register file error>>" + e.toString());
        }     
        try {
            final PrintWriter stack_file = new PrintWriter(new FileOutputStream("stack_file.txt"));
            Object arr[] = RPNCalculator.getInstance().getTheStack().toArray();
            for (int i = 0; i < arr.length; ++i) {
                stack_file.println(arr[i]);
            }
            stack_file.close();
        }
        catch (IOException e) {
            System.out.println("Stack file error>>" + e.toString());
        }
    }
    
    private void load() {
        try {
            final File reg_file = new File("reg_file.txt");
            Scanner inScan = null;
            if (reg_file.exists() && reg_file.canRead()) {
                inScan = new Scanner(new File("reg_file.txt"));
                RPNCalculator.getInstance().clearRegister();
                String data[];
                int index = 0;
                while (inScan.hasNextLine()) {
                    String line = inScan.nextLine();
                    int i = line.indexOf(",");
                    RPNCalculator.getInstance().getRegister()[index].setValue(Double.parseDouble(line.substring(0, i)));
                    RPNCalculator.getInstance().getRegister()[index].setLable(line.substring(i+1, line.length()));
                    index ++;
                }
                index = 0;
                inScan.close();
            }
        }
        catch (IOException e) {
            System.out.println("Register File.txt error>>" + e.toString());
        }        
        
        try {
            final File stack_file = new File("stack_file.txt");
            Scanner inScan = null;
            if (stack_file.exists() && stack_file.canRead()) {
                inScan = new Scanner(new File("stack_file.txt"));
                RPNCalculator.getInstance().getTheStack().clear();
                Deque<Double> data = new LinkedList<Double>();
                int index = 0;
                while (inScan.hasNextLine()) {
                    String line = inScan.nextLine();
                    int i = line.indexOf(",");
                    data.addLast(Double.parseDouble(line));
                    index ++;
                }
                index = 0;
                RPNCalculator.getInstance().setTheStack(data);
                inScan.close();
            }
        }
        catch (IOException e) {
            System.out.println("Stack File error>>" + e.toString());
        }
    }

        
    
    private void clear(boolean all, String actionCommand){
        this.number_entered = true;
        RPNCalculator.Instructions.addLast(actionCommand);
        if(all){
            RPNCalculator.getInstance().getTheStack().clear();
            RPNForm.displayTextField.setText("");
        }else{
            if (RPNCalculator.getInstance().getTheStack().isEmpty()) {
                RPNForm.displayTextField.setText("");
            }
            else {
                RPNCalculator.getInstance().getTheStack().removeFirst();
                if (RPNCalculator.getInstance().getTheStack().isEmpty()) {
                    RPNForm.displayTextField.setText("");
                }
                else {
                    RPNForm.displayTextField.setText(RPNCalculator.getInstance().getTheStack().peekFirst().toString());
                }
            }
        }
        
    }
    
    private void doBackspace(){
        String s = this.inString;
        if(s.length() > 0){
            this.inString = s.substring(0, s.length()-1);
            RPNForm.displayTextField.setText(this.inString);
        }
    }
    
    private void invertSign(String actionCommand){

        RPNCalculator.Instructions.addLast(actionCommand);

        if (!this.inString.equals("")) {
            if (Double.valueOf(RPNForm.displayTextField.getText()) == 0.0) {
                return;
            }
            try {
                 RPNForm.displayTextField.setText(this.inString = String.valueOf(Double.valueOf(this.inString)*(-1.0)));
            }
            catch (Exception e) {
                RPNForm.displayTextField.setText(this.inString = "");
            }       
        }
    }
    
    private void defaultCheck(String actionCommand){
        
        if (Character.isDigit(actionCommand.charAt(0))) {

            RPNCalculator.Instructions.addLast(actionCommand);
            
            if (this.number_entered) {
                this.number_entered = false;
                RPNForm.displayTextField.setText(this.inString = "");
            }
            try {
                if (RPNForm.displayTextField.getText().equals("") || !Double.isNaN(Double.valueOf(RPNForm.displayTextField.getText()))) {
                    RPNForm.displayTextField.setText(this.inString + actionCommand);
                }
                else {
                    RPNForm.displayTextField.setText(this.inString);
                }
            }
            catch (Exception e2) {
                RPNForm.displayTextField.setText(this.inString = "");
            }
        }
        else {
            
            RPNCalculator.Instructions.addLast(actionCommand);
            
            if (!this.number_entered) {
                this.numberEntered(this.inString);
            }
            if (RPNCalculator.getInstance().actOnCommand(actionCommand)) {
                RPNForm.displayTextField.setText(RPNCalculator.getInstance().theStack.peekFirst().toString());
            }
            else {
                this.display_error = true;
                RPNForm.displayTextField.setText("error");
                RPNForm.displayTextField.setForeground(new Color(0, 153, 102));
            }
        }
    }
    
    private void numberEntered(String inString) {
        double numberIn = 0.0;
        if (inString.length() > 0 && inString.lastIndexOf(".") == inString.length() - 1) {
            inString = inString.substring(0, inString.length() - 1);
            RPNForm.displayTextField.setText(inString);
        }
        try {
            if (Double.isNaN(Double.valueOf(inString))) {
                RPNForm.displayTextField.setText(inString = "");
            }
            if (!RPNForm.displayTextField.getText().equals("")) {
                numberIn = Double.valueOf(inString);
                RPNCalculator.getInstance().theStack.addFirst(new Double(numberIn));
                if (RPNCalculator.getInstance().theStack.isEmpty()) {
                    RPNForm.displayTextField.setText("");
                }
                else {
                    RPNForm.displayTextField.setText(RPNCalculator.getInstance().theStack.peekFirst().toString());
                }
                this.number_entered = true;
            }
        }
        catch (Exception e) {
            if (RPNCalculator.getInstance().theStack.isEmpty()) {
                RPNForm.displayTextField.setText("");
            }
            else {
                RPNForm.displayTextField.setText(RPNCalculator.getInstance().theStack.peekFirst().toString());
            }
            this.number_entered = true;
        }
    }
    
    
    private void displayHelp(final String actionCommand) {
        this.display_error = true;
        String m = this.helpMessages.get(actionCommand);
        RPNForm.displayTextField.setText(m);
        
    }
    
    private void invert(String inString) {
        double numberIn = 0.0;
        if (inString.length() > 0 && inString.lastIndexOf(".") == inString.length() - 1) {
            inString = inString.substring(0, inString.length() - 1);
            RPNForm.displayTextField.setText(inString);
        }
        try {
            if (Double.isNaN(Double.valueOf(inString))) {
                RPNForm.displayTextField.setText(inString = "");
            }
            if (!RPNForm.displayTextField.getText().equals("")) {
                numberIn = Double.valueOf(inString);
                if (numberIn == 0.0) {
                    this.display_error = true;
                    RPNForm.displayTextField.setText("error");
                    RPNForm.displayTextField.setForeground(new Color(0, 153, 102));
                }
                else {
                    numberIn = 1.0 / numberIn;
                    RPNForm.displayTextField.setText(Double.toString(numberIn));
                }
            }
        }
        catch (Exception e) {
            if (RPNCalculator.getInstance().theStack.isEmpty()) {
                RPNForm.displayTextField.setText("");
            }
            else {
                RPNForm.displayTextField.setText(RPNCalculator.getInstance().theStack.peekFirst().toString());
            }
            this.number_entered = true;
        }
    }

    
}