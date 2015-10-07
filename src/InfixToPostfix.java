import java.util.Scanner;
import java.util.Stack;

/**
 * Created by heath on 15-10-6.
 */
public class InfixToPostfix {
    /**
     *中缀表达式转后缀表达式
     *@param expression 中缀表达式
     *@return 后缀表达式
     **/
    private  String convertExpr(String expression) {
        Stack<Character> s = new Stack<>();
        String result = new String();
        for (int i = 0; i < expression.length(); i++) {
            char temp = expression.charAt(i);
            //操作数直接输出
            if (temp >= 'a' && temp <= 'z') {
                result += temp;
            } else if (temp == '(') {  //左括号入栈
                s.push(temp);
            } else if (temp == ')') {  //遇到右括号则出栈，直到左括号，左括号只出栈不输出
                while (!s.empty() && s.peek() != '(') {
                    result += s.pop();
                }
                s.pop();
            } else if (temp == '+' || temp == '-') { //遇到"+","-"操作符则一直出栈直到栈空或遇到左括号
                while (!s.empty() && s.peek() != '(') {
                    result += s.pop();
                }
                s.push(temp);
            } else if (temp == '*' || temp == '/') {//遇到"*","/"操作符则一直出栈直到遇到优先级较低的运算符或者左括号
                while (!s.empty() && (s.peek() == '*' || s.peek() == '/')) {
                    result += s.pop();
                }
                s.push(temp);
            }
        }
        while (!s.empty()) {
            result += s.pop();
        }
        return result;
    }
    public static void main(String[] args) {
        System.out.println("Please input an valid infix expression and" +
                " press ENTER:");
        Scanner scanner = new Scanner(System.in);
        String exp = scanner.nextLine();
        InfixToPostfix infixToPostfix = new InfixToPostfix();
        System.out.println("====Here is the result====");
        System.out.println(infixToPostfix.convertExpr(exp));
    }
}
