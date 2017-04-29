package adt;

/**
 *
 * @author Bjoern Heinrichs
 */
public class Queue<T> {
    
    public Queue(){
        queue=new List_extended<>();
    }
    
    public boolean isEmpty(){
        return queue.isEmpty();
    }
    
    public void enqueue(T tObject){
        queue.append(tObject);
    }
    
    public void dequeue(){
        if(!isEmpty()){
            queue.toFirst();
            queue.remove();
        }
    }
    
    public T front(){
        queue.toFirst();
        return queue.getObject();
    }
    
    private List_extended<T> queue;
}