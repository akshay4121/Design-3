/*
 * TC: Amortized O(1) for hasNext()
 *     O(1) fro next()
 * SC: O(d) where d is total no. of nesting in the nestedList which would be the size of stack.
 * 
 * Approach: i will use the basic iterator to design the nested iterator. Intitally, i will push the entire nestedList into my stack.
 * and keep moving my pointer/cursor. at each pass i will have 3 possiblities. 
 *  1. hasNext() is null => pop my stack.
 *  2. next() has a integer => return true and all assign value to my nextEl.
 *  3. else push the list to the stack.
 */


/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {
    Stack<Iterator<NestedInteger>> s;
    NestedInteger nextEl;

    public NestedIterator(List<NestedInteger> nestedList) {
        this.s = new Stack<>();
        s.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextEl.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!s.isEmpty()){
            if(!s.peek().hasNext()) s.pop();
            else if( (nextEl = s.peek().next()).isInteger()){
                return true;
            }else{
                s.push(nextEl.getList().iterator());
            }
        }
        
    return false;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */