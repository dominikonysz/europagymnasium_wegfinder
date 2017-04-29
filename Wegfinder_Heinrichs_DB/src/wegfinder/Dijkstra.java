/**
 * This class implements Dijkstra's algorithm for shortest paths in a graph.
 */

package wegfinder;

import abiturklassen.graphklassen.Graph;
import abiturklassen.graphklassen.GraphNode;
import adt.List;
import adt.Stack;

/**
 *
 * @author Bjoern Heinrichs
 */
public class Dijkstra {
    
    private Graph g;
    
    /**
     * Constructor. Initialises the attribute g.
     * 
     * @param g The graph in that the shortest paths are searched for.
     */
    public Dijkstra(Graph g){
        this.g = g;
    }
    
    /**
     * This method generates the shortest path and the distance 
     * from a GraphNode 's' to every other node in the graph.
     * 
     * @param s The GraphNode from which the shortest paths should be found. 
     */
    private void dijkstra(GraphNode s){
        g.resetMarks();
        
        List<GraphNode> l = g.getNodes();
        l.toFirst();
        
        while(l.hasAccess()){
            l.getObject().setDist(-1);
            l.next();
        }
        
        g.getNode(s.getName()).setDist(0);
        
        while(!g.allNodesMarked()){
            GraphNode cur = findCur();
            g.getNode(cur.getName()).mark();
        
            List<GraphNode> l2 = g.getNeighbours(g.getNode(cur.getName()));
        
            l2.toFirst();
            
            while(l2.hasAccess()){
                if(!l2.getObject().isMarked() && (l2.getObject().getDist() == -1 || 
                        l2.getObject().getDist() > (cur.getDist() + g.getEdgeWeight(cur, g.getNode(l2.getObject().getName()))))){
                    g.getNode(l2.getObject().getName()).setDist(cur.getDist() + g.getEdgeWeight(cur, l2.getObject()));
                    g.getNode(l2.getObject().getName()).setPrev(cur);
                }
                
                l2.next();
            }
        }
    }
    
    /**
     * This method returns the path from the GraphNode 's' to the 
     * GraphNode 't'.
     * 
     * @param s The GraphNode where the path starts.
     * @param t The GraphNode where the paths ends.
     * @return A list containing the nodes in the path from 's' to 't'.
     */
    public List<GraphNode> getPath(GraphNode s, GraphNode t){
        if(!g.hasNode(s.getName()) || !g.hasNode(t.getName()))
            return null;
        
        dijkstra(s);
        
        Stack<GraphNode> st = new Stack<>();
        
        GraphNode cur = t;
            
        while(!cur.getName().equals(s.getName())){
            if(g.hasNode(cur.getName())){
                st.push(cur);
                cur = cur.getPrev();
            } else return null;
        }
          
        st.push(s);
        
        List<GraphNode> res = new List<>();
        
        while(!st.isEmpty()){
            res.append(st.top());
            st.pop();
        }
        
        return res;
    }
    
    /**
     * This method returns the distance between the GraphNodes 's' and 't'.
     * 
     * @param s The GraphNode where the path starts.
     * @param t The GraphNode where the paths ends.
     * @return The distance between 's' and 't' as a double.
     */
    public double getDist(GraphNode s, GraphNode t){
        if(!g.hasNode(s.getName()) || !g.hasNode(t.getName()))
            return Double.NaN;
        
        dijkstra(s);
        
        return t.getDist();
    }

    /**
     * This method searches for the new current node in Dijkstra's algorithm.
     * 
     * @return The new current GraphNode.
     */
    private GraphNode findCur() {
        GraphNode res = null;
        double dist = Double.MAX_VALUE;
        
        List<GraphNode> l = g.getNodes();
        l.toFirst();
        
        while(l.hasAccess()){
            if(!l.getObject().isMarked() && l.getObject().getDist() != -1 && l.getObject().getDist() < dist){
                res = l.getObject();
                dist = l.getObject().getDist();
            }
            
            l.next();
        }
        
        return res;
    }
    
}
