package com.exuberant.calci.utility;

import java.util.Stack;

public class Expression {

    public static double evaluateExpression(String expression) {
        expression = infixToPostfix(expression);
        String[] inputs = expression.split(" ");

        Stack<Double> stack = new Stack<>();

        for(int i=0; i<inputs.length; i++) {
            if( isNumeric(inputs[i]) )
                stack.push( Double.parseDouble(inputs[i]) );
            else {
                double num2 = stack.pop();
                double num1 = stack.pop();

                double interAns;
                switch( inputs[i] ) {
                    case "+":
                        interAns = num1 + num2;
                        break;
                    case "-":
                        interAns = num1 - num2;
                        break;
                    case "x":
                        interAns = num1 * num2;
                        break;
                    case "/":
                        interAns = num1 / num2;
                        break;
                    default:
                        interAns = 0;
                }

                stack.push(interAns);
            }
        }

        return stack.pop();
    }

    private static String infixToPostfix(String expression) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for(int i=0; i<expression.length(); i++) {
            char c = expression.charAt(i);

            if(c == ' ') continue;

            if( Character.isDigit(c) || c == '.' ) {
                result.append(c);
            }

            else if(c == '(')
                stack.push(c);

            else if(c == ')') {
                result.append(' ');
                while(!stack.isEmpty() && stack.peek() != '(')
                    result.append(stack.pop());

                if( !stack.isEmpty() && stack.peek() != '(' )
                    throw new IllegalStateException();
                else stack.pop();
            }

            else {
                while(!stack.isEmpty() && priority(c) <= priority(stack.peek())) {
                    if(stack.peek() == '(') throw new IllegalStateException();
                    result.append(' ');
                    result.append(stack.pop());
                }
                result.append(' ');
                stack.push(c);
            }
        }

        while(!stack.isEmpty()) {
            if(stack.peek() == '(') throw new IllegalStateException();
            result.append(' ');
            result.append(stack.pop());
        }

        return result.toString().replaceAll("\\s+", " ");
    }

    private static boolean isNumeric(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch(NumberFormatException ex ) {
            return false;
        }
    }

    private static int priority(char c) {
        switch(c) {
            case '+':
            case '-':
                return 1;
            case 'x':
            case '/':
                return 2;
        }
        return -1;
    }
}
