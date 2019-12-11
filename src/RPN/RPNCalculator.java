
package RPN;

import java.util.Deque;
import java.util.LinkedList;

/**
 *
 * @author Akram A Kh
 */
public class RPNCalculator
{
  public static final int NUMBER_OF_REGISTERS = 26;
  public Deque<Double> theStack = new LinkedList<Double>();
  public static LinkedList<String> Instructions = new LinkedList<String>();
  public Register[] register = new Register[NUMBER_OF_REGISTERS];
  
  private static RPNCalculator instance = null;

    private RPNCalculator() {
        for(int i=0;i<register.length;++i){
            register[i]= new Register(0.0,"no lable",""+(char)('A'+i));
        }
    }
    
    public static RPNCalculator getInstance()
    {
        if (instance == null)
            instance = new RPNCalculator();
        
        return instance; 
    }
    
    public void clearRegister(){
        for(int i=0;i<register.length;++i){
            register[i]= new Register(0.0,"no lable",""+(char)('A'+i));
        }
    }
    
    public boolean actOnCommand(String cmd) {
        if (this.theStack.size() < 2) {
                return true;
        }
        if (cmd.equals("Up")) {
            this.theStack.addLast(this.theStack.pop());
            return true;
        }
        else if (cmd.equals("Down")) {
            this.theStack.push(this.theStack.pollLast());
            return true;
        }
        else {
            final double num_1 = this.theStack.pop();
            final double num_2 = this.theStack.pop();
            switch(cmd){
                case "+": this.theStack.push(new Double(num_2 + num_1)); break;
                case "-": this.theStack.push(new Double(num_2 - num_1)); break;
                case "x": this.theStack.push(new Double(num_2 * num_1)); break;
                case "/": {
                            if (num_1 == 0.0) return false;
                            this.theStack.push(new Double(num_2 / num_1));
                            }; break;
                case "^": {
                            try { this.theStack.push(new Double(Math.pow(num_2, num_1))); }
                            catch (Exception e) { return false; }
                            }; break;
                case "%": this.theStack.push(new Double(num_2 % num_1)); break;
                default: return true;
            }
            return true;
        }
    }

    public Deque<Double> getTheStack() {
        return this.theStack;
    }

    public void setTheStack(Deque<Double> theStack) {
        this.theStack = theStack;
    }

    public Register[] getRegister() {
        return this.register;
    }

    public void setRegister(Register[] register) {
        this.register = register;
    }
    
}





