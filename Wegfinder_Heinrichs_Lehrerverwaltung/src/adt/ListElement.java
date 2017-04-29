package adt;

/**
 *
 * @author Bjoern Heinrichs
 */
public class ListElement<T>
{
    private T tObject = null;
    private ListElement<T> nextElement = null;
    private ListElement<T> previousElement = null;
    
    public T getObject()
    {
        return tObject;
    }
    
    public void setObject(T tObject)
    {
        this.tObject = tObject;
    }
    
    public ListElement<T> getNext()
    {
        return nextElement;
    }
    
    public void setNext(ListElement<T> n)
    {
        nextElement = n;
    }
    
    public ListElement<T> getPrevious()
    {
        return previousElement;
    }
    
    public void setPrevious(ListElement<T> n)
    {
        previousElement = n;
    }
}
