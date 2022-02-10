package withJava.crusader728.leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

public class FlattenNestedListIterator341 {
    class NestedIterator implements Iterator<Integer> {
        private Deque<NestedInteger> nestedIntegerStack;
        public NestedIterator(List<NestedInteger> nestedList) {
            if(nestedList == null || nestedList.size() == 0) {
                nestedIntegerStack = new ArrayDeque<>();
            }
            else {
                nestedIntegerStack = new ArrayDeque<>();
                for(int i = nestedList.size() - 1; i >= 0; --i) {
                    nestedIntegerStack.push(nestedList.get(i));
                }

                while(!nestedIntegerStack.isEmpty() && !nestedIntegerStack.peek().isInteger()) {
                    if(nestedIntegerStack.peek().getList().isEmpty()) {
                        nestedIntegerStack.pop();
                    } else {
                        NestedInteger peek = nestedIntegerStack.pop();
                        for(int i = peek.getList().size() - 1; i >= 0; i--) {
                            nestedIntegerStack.push(peek.getList().get(i));
                        }
                    }
                }
            }
        }

        @Override
        public Integer next() {
            return nestedIntegerStack.pop().getInteger();
        }

        @Override
        public boolean hasNext() {
            while(!nestedIntegerStack.isEmpty() && !nestedIntegerStack.peek().isInteger()) {
                if(nestedIntegerStack.peek().getList().isEmpty()) {
                    nestedIntegerStack.pop();
                } else {
                    NestedInteger peek = nestedIntegerStack.pop();
                    for(int i = peek.getList().size() - 1; i >= 0; i--) {
                        nestedIntegerStack.push(peek.getList().get(i));
                    }
                }
            }
            return !nestedIntegerStack.isEmpty();
        }
    }

    public interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a
        // nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a
        // single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested
        // list
        // Return empty list if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }

}
