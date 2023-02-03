import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class postFixEvaluator extends JFrame {

    private JButton POSTCONVERTButton;
    private JButton POSTEVALUATEbutton;
    private JTextField postExpressionTextField;
    private JLabel postFixTitle;
    private JLabel appNote;
    private JPanel postMainPanel;
    private JLabel TeamNo;

    public static Double evaluate(double a, double b, char operator) {
        switch (operator) {
            case '+' -> {
                return b + a;
            }
            case '-' -> {
                return b - a;
            }
            case '*' -> {
                return b * a;
            }
            case '/' -> {
                if (a == 0)
                    throw new
                            UnsupportedOperationException("Cannot divide by zero");
                return b / a; // 2/4
            }
        }
        return 0.0;
    }

    public postFixEvaluator() {
        setTitle("welcome");
        setSize(450, 450);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setContentPane(postMainPanel);
        POSTEVALUATEbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Stack<Double> stack = new Stack<>();
                String postExpression =postExpressionTextField.getText();
                //grabs text from textfield and stores it in a variable called postExpression
                for (int i = 0; i < postExpression.length(); i++) {
                    char c = postExpression.charAt(i); //2 4 /

                    //check if it is a space (separator)
                    if(c==' ')
                        continue;

                    if (c == '*' || c == '/' || c == '^' || c == '+' || c == '-') {
                        double s1 = stack.pop();
                        double s2 = stack.pop();
                        double temp = evaluate(s1, s2, c);
                        stack.push(temp);
                    } else {
                        //if here means, it is a digit
                        double num = 0;

                        //extract the characters and store it in num
                        while(Character.isDigit(c)) {
                            num = num*10 + (c-'0');
                            i++;
                            c = postExpression.charAt(i);
                        }
                        i--;
                        //push the number in stack
                        stack.push(num);
                    }
                }
                double result;
                result = stack.pop();
                postExpressionTextField.setText(String.valueOf(result));
            }
        });
        POSTCONVERTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InfixToPostfixConverter convert = new InfixToPostfixConverter();
                dispose();
            }
        });
    }

}
