import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class InfixToPostfixConverter extends JFrame {
    private JLabel headerLabel;
    private JTextField expressionTextfield;
    private JButton CONVERTButton;
    private JButton EVALUATEButton;
    private JPanel panelMain;
    private JLabel applicationTitle;


    static int precedence(char c){
        return switch (c) { // switch case use to store the precedence of each operator
            case '+', '-' -> 1; //lowest precedence
            case '*', '/' -> 2;  //second-highest precedence
            case '^' -> 3;//highest precedence
            default ->
                    -1; //not an operator
        };
    }
    public InfixToPostfixConverter(){
        setTitle("Infix Converter");
        setSize(450,450);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setContentPane(panelMain);
        CONVERTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 String infixExpression = expressionTextfield.getText();

                String result = "";  //an empty string to hold results
                Stack<Character> stack; //declaring an empty stack of character called stack
                stack = new Stack<>();
                // iterates through each character in the given infix expression
                for (int i = 0; i <infixExpression.length() ; i++) {
                    char c = infixExpression.charAt(i);

                    //check if char is operator
                    if(precedence(c)>0){
                        //if stack is not empty and the operator at the top of the stack has a precedence that
                        // is greater than the incoming operator you pop else push into the stack
                        while(!stack.isEmpty() && precedence(stack.peek())>=precedence(c)){
                            result += stack.pop();
                        }
                        stack.push(c);
                    }else if(c==')'){ //if you encounter ")" pop until you get a "("
                        char x = stack.pop();
                        while(x!='('){
                            result += x;
                            x = stack.pop();
                        }
                    }else if(c=='('){
                        stack.push(c);
                    }else{
                        //character is neither operator nor (
                        result += c;
                    }
                }
                for (int i = 0; i <=stack.size() ; i++) {
                    result += stack.pop();
                }
                expressionTextfield.setText(result);
            }
        });
        EVALUATEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                postFixEvaluator evaluate = new postFixEvaluator();
                dispose();
            }
        });
    }


}
