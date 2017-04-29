package adt;


import java.util.Iterator;

/**
 *
 * @author Bjoern Heinrichs
 */
public class List_extended<T> implements Iterable
{
    protected ListElement<T> head = null;
    protected ListElement<T> tail = null;
    protected ListElement<T> current = null;
    
    public List_extended()
    {
        
    }
    
    public void insert(T pObject)
    {
        if(!isEmpty())
        {
            ListElement<T> tmp = new ListElement<>();
            tmp.setObject(pObject);       
            tmp.setNext(current);
            tmp.setPrevious(current.getPrevious());
            current.getPrevious().setNext(tmp);
            current.setPrevious(tmp);
        }
        else
        {
            ListElement<T> tmp = new ListElement<>();
            tmp.setObject(pObject);

            head = tmp;
            tail = tmp;
        }
    }
    
    public void remove()
    {
        if(hasAccess() && current != tail && current != head)
        {
            current.getPrevious().setNext(current.getNext());
            current.getNext().setPrevious(current.getPrevious());
            current = current.getNext();
        }
        else if(hasAccess() && current == head && current == tail)
        {
            head = null;
            tail = null;
            current = null;
        }
        else if(hasAccess() && current == tail)
        {
            tail = tail.getPrevious();
            tail.setNext(null);
            current = null;
        }
        else if(hasAccess() && current == head)
        {
            head = head.getNext();
            head.setPrevious(null);
        }
    }
    
    public T getObject()
    {
        if(hasAccess())
            return current.getObject();
        else
            return null;
    }
    
    public void setObject(T pObject)
    {
        if(hasAccess() && pObject != null)
            current.setObject(pObject);
    }
    
    public void next()
    {
        if(!isEmpty() && current != null && current != tail)
            current = current.getNext();
        else
            current = null;
    }
    
    public void previous()
    {
        if(!isEmpty() && current != null && current != head)
            current = current.getPrevious();
        else
            current = null;
    }
    
    public void toFirst()
    {
        if(!isEmpty())
            current = head;
    }
    
    public void toLast()
    {
        if(!isEmpty())
            current = tail;
    }
    
    public boolean isEmpty()
    {
        if(head == null && tail == null && current == null)
            return true;
        else
            return false;
    }
    
    public void append(T pObject)
    {
        if(pObject != null)
        {
            if(isEmpty())
            {
                ListElement<T> tmp = new ListElement<>();
                tmp.setObject(pObject);

                head = tmp;
                tail = tmp;
            }
            else
            {
                ListElement<T> tmp = new ListElement<>();
                tmp.setObject(pObject);
                
                tail.setNext(tmp);
                tmp.setPrevious(tail);
                tail = tail.getNext();
            }
        }
    }
    
    public void concat(List_extended<T> pList)
    {
        if(pList != null && !pList.isEmpty())
        {
            ListElement<T> tmp = pList.tail;
            pList.toFirst();
            append(pList.current.getObject());
            tail = tmp;
            pList = null;
        }
    }
    
    public boolean hasAccess()
    {
        if(current == null)
            return false;
        else
            return true;
    }

    @Override
    public Iterator iterator() {
        L_Iterator lIterator = new L_Iterator(this);
        return lIterator;
    }
}
