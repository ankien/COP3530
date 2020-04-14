import java.util.Stack;

public class BET {
    private BinaryNode<String> root;
    
    // Node for binary tree
    private static class BinaryNode<E> {
        private E element;
        private BinaryNode<E> left;
        private BinaryNode<E> right;
        
        // Constructor for binary tree node
        public BinaryNode(E value, BinaryNode<E> leftInput, BinaryNode<E> rightInput) {
            left = leftInput; right = rightInput; element = value;
        }
        
        public E getValue() { return element; }
        public E setValue(E input) { return element = input; }  
        
        // Get left and right nodes; not elements
        public BinaryNode<E> left() { return left; }
        public BinaryNode<E> right() { return right; }
        
        public boolean isLeaf() {
            if((left == null) && (right == null)) {
                return true;
            } return false;
        }
    }
    
    /// Private helper methods ///
    private <E> void printInfixExpression(BinaryNode<E> n) {
        if(n.right() != null) {
            System.out.print("( ");
            printInfixExpression(n.right());
        }
        
        System.out.print(n.getValue() + " ");
        
        if(n.left() != null) {
            printInfixExpression(n.left());
            System.out.print(") ");
        }
    }
    
    private <E> void makeEmpty(BinaryNode<E> t) {
        if(t != null) {
            t.setValue(null);
            makeEmpty(t.left());
            makeEmpty(t.right());
        }
    }
    
    private <E> void printPostfixExpression(BinaryNode<E> n) {
        if(n.right() != null) { printPostfixExpression(n.right()); }
        if(n.left()  != null) { printPostfixExpression(n.left());  }
        System.out.print(n.getValue() + " ");
    }
    
    private <E> int size(BinaryNode<E> t) {
        if(t == null) { return 0; }
        return 1 + size(t.left()) + size(t.right());
    }
    
    private <E> int leafNodes(BinaryNode<E> t) {
        if (t == null) { return 0; }
        if (t.isLeaf()) { return 1; }
        else { return leafNodes(t.left()) + leafNodes(t.right()); }
    }
    
    /// Constructors for BET ///
    public BET() { makeEmpty(root); }
    
    public BET(String expr, char mode) {
        switch(mode) {
            case 'p':
                if(buildFromPostfix(expr) == false) { throw new IllegalStateException("Invalid notation: " + expr); }
                break;
            case 'i':
                if(buildFromInfix(expr)   == false) { throw new IllegalStateException("Invalid notation: " + expr); }
                break;
        }
    }
    
    /// Public interface methods ///
    public boolean buildFromPostfix(String postfix) {
        Stack<BinaryNode<String>> postfixStack = new Stack<>();
        if(!isEmpty()) { makeEmpty(root); }
        
        String[] expressions = postfix.split(" ");

        try {
        
            for (int i = 0; i < expressions.length; i++) {
                switch (expressions[i]) {
                case "*":
                case "/":
                case "+":
                case "-":
                    BinaryNode<String> operator = new BinaryNode<String>(expressions[i], postfixStack.pop(),
                            postfixStack.pop());
                    postfixStack.push(operator);
                    root = operator;
                    break;
                default:
                    BinaryNode<String> operand = new BinaryNode<String>(expressions[i], null, null);
                    postfixStack.push(operand);
                    break;
                }
            }
        
        } catch(Exception e) { return false; }
        
        if (!(postfixStack.size() == 1)) { return false; }
            
        return true;
    }
    
    // I should've made some helper methods for repeated code, but oh well.
    public boolean buildFromInfix(String infix) {
        Stack<BinaryNode<String>> operators = new Stack<>();
        Stack<BinaryNode<String>> operands = new Stack<>();
        if(!isEmpty()) { makeEmpty(root); }
        
        String[] expressions = infix.split(" ");
        
        try {

            for (int i = 0; i < expressions.length; i++) {
                String nextExpression = "";
                BinaryNode<String> variableNode = new BinaryNode<String>(expressions[i], null, null);

                if (!operators.empty()) {
                    nextExpression = operators.peek().getValue();
                }

                switch (expressions[i]) {
                case "*":
                case "/":
                    if (nextExpression.equals("*") || nextExpression.equals("/")) {
                        BinaryNode<String> multiDivOnStack = new BinaryNode<String>(operators.pop().getValue(),
                                operands.pop(), operands.pop());
                        operands.push(multiDivOnStack);
                        root = multiDivOnStack;
                    }
                    operators.push(variableNode);
                    break;
                case "+":
                case "-":
                    if (nextExpression.equals("*") || nextExpression.equals("/") || nextExpression.equals("+")
                            || nextExpression.equals("-")) {
                        BinaryNode<String> operatorsOnStack = new BinaryNode<String>(operators.pop().getValue(),
                                operands.pop(), operands.pop());
                        operands.push(operatorsOnStack);
                        root = operatorsOnStack;
                    }
                    operators.push(variableNode);
                    break;
                case "(":
                    operators.push(variableNode);
                    break;
                case ")":
                    while (!nextExpression.equals("(")) {
                        BinaryNode<String> nextOperatorInParenthesis = new BinaryNode<String>(
                                operators.pop().getValue(), operands.pop(), operands.pop());
                        operands.push(nextOperatorInParenthesis);
                        root = nextOperatorInParenthesis;
                        nextExpression = operators.peek().getValue();
                    }
                    operators.pop();
                    break;
                default:
                    operands.push(variableNode);
                    break;
                }
            }

            while (!operators.empty()) {
                BinaryNode<String> operator = new BinaryNode<String>(operators.pop().getValue(), operands.pop(),
                        operands.pop());
                operands.push(operator);
                root = operator;
            }
            if (!(operators.empty() && (operands.size() == 1))) {
                return false;
            }

        } catch(Exception e) { return false; }
        return true;
    }
    
    public void printInfixExpression() {
        printInfixExpression(root);
        System.out.println();
    }
    
    public void printPostfixExpression() {
        printPostfixExpression(root);
        System.out.println();
    }
    
    public int size() {
        return size(root);
    }
    
    public boolean isEmpty() {
        return root == null;
    }
    
    public int leafNodes() {
        return leafNodes(root);
    }
}
