package java.crusader728.leetcode;

import java.util.Stack;

public class DesignExpressionTreeWithEvalFunction {
    abstract static class Node {
        public abstract int evaluate();
    }

    abstract static class Operator extends Node {
        protected Node left;
        protected Node right;

        Operator(Node left, Node right) {
            this.left = left;
            this.right = right;
        }
    }

    static class Plus extends Operator {
        Plus(Node left, Node right) {
            super(left, right);
        }

        @Override
        public int evaluate() {
            return this.right.evaluate() + this.left.evaluate();
        }
    }

    static class Minus extends Operator {

        Minus(Node left, Node right) {
            super(left, right);
        }

        @Override
        public int evaluate() {
            return this.left.evaluate() - this.right.evaluate();
        }
    }

    static class Multiple extends Operator {

        Multiple(Node left, Node right) {
            super(left, right);
        }

        @Override
        public int evaluate() {
            return this.left.evaluate() * this.right.evaluate();
        }
    }

    static class Divide extends Operator {
        Divide(Node left, Node right) {
            super(left, right);
        }

        @Override
        public int evaluate() {
            return this.left.evaluate() / this.right.evaluate();
        }
    }

    static class Lit extends Node {
        private final int value;

        Lit(int value) {
            this.value = value;
        }
        @Override
        public int evaluate() {
            return value;
        }
    }

    static class TreeBuilder {
        Node buildTree(String[] postfix) {
            Stack<Node> stack = new Stack<>();
            Node left, right;
            for (String s : postfix) {
                switch (s) {
                    case "+":
                        right = stack.pop();
                        left = stack.pop();
                        Node plus = new Plus(left, right);
                        stack.push(plus);
                        break;
                    case "-":
                        right = stack.pop();
                        left = stack.pop();
                        Node minus = new Minus(left, right);
                        stack.push(minus);
                        break;
                    case "*":
                        right = stack.pop();
                        left = stack.pop();
                        Node mul = new Multiple(left, right);
                        stack.push(mul);
                        break;
                    case "/":
                        right = stack.pop();
                        left = stack.pop();
                        Node div = new Divide(left, right);
                        stack.push(div);
                        break;
                    default:
                        Node lit = new Lit(Integer.parseInt(s));
                        stack.push(lit);
                        break;
                }
            }
            if(stack.size() != 1) {
                throw new RuntimeException();
            } else {
                return stack.pop();
            }
        }
    }
}
