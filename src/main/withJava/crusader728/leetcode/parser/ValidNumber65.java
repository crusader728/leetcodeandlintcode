package withJava.crusader728.leetcode.parser;

import scala.concurrent.impl.FutureConvertersImpl.P;

public class ValidNumber65 {
    public boolean isNumber(String s) {
        State state = new Initial();
        try {
            for(int i = 0; i < s.length(); ++i) {
                state = state.read(s.charAt(i));
            }
        } catch(IllegalArgumentException e) {
            return false;
        }
        return state.isValidEndState();
    }

    private interface State {
        State read(char ch);
        default boolean isValidEndState() {
            return false;
        }
    }

    private static class Initial implements State {
        @Override
        public State read(char ch) {
            if(ch == '+' || ch == '-') {
                return new Sign();
            } else if(ch >= '0' && ch <= '9') {
                return new Integral();
            } else if(ch =='.') {
                return new Dot();
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    private static class Sign implements State {

        @Override
        public State read(char ch) {
            if(ch >= '0' && ch <= '9') {
                return new Integral();
            } else if(ch == '.') {
                return new Dot();
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    private static class Integral implements State {

        @Override
        public State read(char ch) {
            if(ch >= '0' && ch <= '9') {
                return this;
            } else if(ch == '.') {
                return new Decimal();
            } else if(ch == 'e' || ch == 'E') {
                return new Exp();
            } else {
                throw new IllegalArgumentException();
            }
        }

        @Override
        public boolean isValidEndState() {
            return true;
        }
    }

    private static class Dot implements State {
        @Override
        public State read(char ch) {
            if(ch >= '0' && ch <= '9') {
                return new Decimal();
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    private static class Decimal implements State {

        @Override
        public State read(char ch) {
            if(ch >= '0' && ch <= '9') {
                return this;
            } else if(ch == 'e' || ch == 'E') {
                return new Exp();
            } else {
                throw new IllegalArgumentException();
            }
        }

        @Override
        public boolean isValidEndState() {
            return true;
        }
    }

    private static class Exp implements State {
        @Override
        public State read(char ch) {
            if(ch == '+' || ch == '-') {
                return new PowerSign();
            } else if(ch >= '0' && ch <= '9') {
                return new PowerIntegral();
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    private static class PowerSign implements State {

        @Override
        public State read(char ch) {
            if(ch >= '0' && ch <= '9') {
                return new PowerIntegral();
            } else {
                throw new IllegalArgumentException();
            }
        }

    }

    private static class PowerIntegral implements State {

        @Override
        public State read(char ch) {
            if(ch >= '0' && ch <= '9') {
                return this;
            } else {
                throw new IllegalArgumentException();
            }
        }

        @Override
        public boolean isValidEndState() {
            return true;
        }

    }



    public static void main(String[] args) {
        ValidNumber65 validNumber65 = new ValidNumber65();
        validNumber65.isNumber("005047e+6");
    }

}
