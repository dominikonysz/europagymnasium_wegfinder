package adt;


import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Bjoern Heinrichs
 */
public class L_Iterator<T> implements Iterator{
    
    private List_extended<T> l;
    
    public L_Iterator(List_extended l){
        l.toFirst();
        this.l = l;
    }

    @Override
    public boolean hasNext() {
        return l.hasAccess();
    }

    @Override
    public T next() {
        if(hasNext()){
            T tmp = l.current.getObject();
            l.next();
            return tmp;
        } else {
            throw new NoSuchElementException("No next element.");
        }
    }

    @Override
    public void remove() {
        l.remove();
    }
    
    
    
}
