package adt;

/**
 *
 * @author Bjoern Heinrichs
 */
public class Stack<T> {
    
    public Stack(){
        stack=new List_extended<>();
    }
    
    public boolean isEmpty(){
        return stack.isEmpty();
    }
    
    public void push(T tObject){
        stack.append(tObject);
    }
    
    public void pop(){
        if(!isEmpty()){
            stack.toLast();
            stack.remove();
        }
    }
    
    public T top(){
        stack.toLast();
        return stack.getObject();
    }
    
    private List_extended<T> stack;
}
