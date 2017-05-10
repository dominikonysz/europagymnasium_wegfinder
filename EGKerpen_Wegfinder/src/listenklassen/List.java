package listenklassen;

/**
 *
 * @author Bjoern Heinrichs
 */
public class List<T>
{
    protected ListElement<T> head = null;
    protected ListElement<T> tail = null;
    protected ListElement<T> current = null;
    
    public List()
    {
        
    }
    
    public void insert(T pObject)
    {
        if(!isEmpty())
        {
            ListElement<T> tmp = new ListElement<>();
            tmp.setObject(pObject);       
            ListElement<T> tmp2 = current;
            
            ListElement<T> beforeCurrent = null;
            
            toFirst();
            
            while(true)
            {
                beforeCurrent = current;
                next();
                
                if(current == tmp2)
                    break;
            }
            
            beforeCurrent.setNext(tmp);
            tmp.setNext(current);
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
            ListElement<T> tmp = current;
            ListElement<T> beforeCurrent = null;
            
            toFirst();
            
            while(true)
            {
                beforeCurrent = current;
                next();
                
                if(current == tmp)
                    break;
            }
            
            beforeCurrent.setNext(tmp.getNext());
            current = tmp.getNext();
            tmp = null;
        }
        else if(hasAccess() && current == head && current == tail)
        {
            head = null;
            tail = null;
            current = null;
        }
        else if(hasAccess() && current == tail)
        {
            ListElement<T> tmp = current;
            ListElement<T> beforeCurrent = null;
            
            toFirst();
            
            while(true)
            {
                beforeCurrent = current;
                next();
                
                if(current == tmp)
                    break;
            }
            
            tail = beforeCurrent;
            tail.setNext(null);
            current = null;
            tmp = null;
        }
        else if(hasAccess() && current == head)
        {
            head = head.getNext();
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
                tail = tail.getNext();
            }
        }
    }
    
    public void concat(List<T> pList)
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
}
