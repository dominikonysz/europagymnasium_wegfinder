package abiturklassen.graphklassen;

import adt.List;

/**
 * <p>Materialien zu den zentralen
 * Abiturpruefungen im Fach Informatik ab 2012 in 
 * Nordrhein-Westfalen.</p>
 * <p>Klasse GraphNode</p>
 * <p>Ein ungerichteter Graph besteht aus einer Menge 
 * von Knoten und einer Menge von Kanten. Die Kanten 
 * verbinden jeweils zwei Knoten und koennen ein Gewicht haben.
 * Objekte der Klasse GraphNode sind Knoten eines Graphen. 
 * Ein Knoten hat einen Namen und kann markiert werden.</p>
 * 
 * <p>NW-Arbeitsgruppe: Materialentwicklung zum Zentralabitur 
 * im Fach Informatik</p>
 * 
 * @version 2010-05-10
 */
public class GraphNode {
  private String name;   
  private List edges ;   
  private boolean visited;
  private boolean marked;
  private double dist;
  private GraphNode prev;
  private int x, y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

  // Klasse Edge
  public class Edge {
    protected GraphNode neighbour; 
    protected double weight;
    protected boolean marked;

    public Edge(GraphNode pNeighbour,double pWeight)   { 
      neighbour=pNeighbour;
      weight=pWeight;
      marked=false;
    }

    public GraphNode getNeighbour() {
      return neighbour;
    }

    public double getWeight() {
      return weight;
    }

    public boolean isMarked()   {
      return marked;  
    }

  }

  // Ende Klasse Edge
  /**
   *Ein Knoten mit dem Namen pName wird erzeugt. 
   *Der Knoten ist nicht markiert. 
   *@param pName Bezeichnung des Knotens
   */
  public GraphNode(String pName, int pX, int pY) { 
    name=pName;          
    edges=new List(); 
    marked=false;
    dist=Double.MAX_VALUE;
    prev=this;
    x=pX;
    y=pY;
  }
  
  /**
   * Die Distanz des Knotens zu einem Startknoten 
   * im Graphen wird gesetzt.
   */  
  public void setDist(double pDist) {
    dist=pDist;
  }
  
  /**
   * Die Distanz des Knotens zu einem Startknoten 
   * im Graphen wird zurückgegeben.
   */  
  public double getDist() {
    return dist;
  }
  
  /**
   * Der Vprgänger des Knotens auf dem Weg
   * vom Startknoten im Graphen wird gesetzt.
   */  
  public void setPrev(GraphNode pG) {
    prev=pG;
  }
  
  /**
   * Der Vprgänger des Knotens auf dem Weg
   * vom Startknoten im Graphen wird zurückgegeben.
   */  
  public GraphNode getPrev() {
    return prev;
  }

  /**
   * Der Knoten wird markiert. Falls er 
   * nicht markiert ist, sonst bleibt er unveraendert.
   */  
  public void mark() {
    marked=true;
  }

  /**
   * Die Markierung des Knotens wird entfernt, falls er markiert ist, 
   * sonst bleibt er unveraendert.
   */
  public void unmark() {
    marked=false;
  }

  /**
   * Die Anfrage liefert den Wert true, wenn der Knoten markiert ist, 
   * sonst liefert sie den Wert false.
   * @return true falls markiert, sonst false
   */  
  public boolean isMarked() {
    return marked;

  }
  
  /**
   * Der Knoten wird als besucht vermerkt.
   */  
  public void setVisited(boolean v) {
    visited=v;
  }

  /**
   * Die Anfrage liefert den Wert true, wenn der Knoten besucht wurde, 
   * sonst liefert sie den Wert false.
   * @return true falls besucht, sonst false
   */  
  public boolean isVisited() {
    return visited;

  }

  /**
   * Die Anfrage liefert den Namen des Knotens.
   * @return Bezeichnung des Knotens
   */  
  public String getName()   {
    return name;
  }

  // Hilfsmethoden
  /**
   * Interne Methode
   */ 
  void addEdge_(GraphNode pNode,double pWeight) {
    Edge lEdge=new Edge(pNode,pWeight);  
    edges.append(lEdge);   
  }

  /**
   * Interne Methode
   */  
  double getEdgeWeight_(GraphNode pNode) {
    boolean ok = false;
    Edge e = null;
    edges.toFirst();
    while (!ok && edges.hasAccess()) {
      e = (Edge) edges.getObject();
      if (e.getNeighbour() == pNode)
        ok = true;
      edges.next();
    }
    if (!ok)
      return Double.NaN; // Not a Number
    else
      return e.getWeight();
  }

  /**
   * Interne Methode
   */  
  void removeEdge_(GraphNode pNode) {
    if (pNode!=null) {
      Edge e;
      edges.toFirst();
      while (edges.hasAccess()) {
        e = (Edge) edges.getObject();
        if (e.getNeighbour() == pNode)
          edges.remove();
        edges.next();  
      }
    }  
  }

  /**
   * Interne Methode
   */ 
  List<GraphNode> getNeighbours_() {   
    // liefert eine Liste mit den Nachbarknoten  
    List<GraphNode> lList=new List<>();
    edges.toFirst();
    while (edges.hasAccess()) {
      Edge lEdge=(Edge) edges.getObject();
      lList.append(lEdge.getNeighbour());
      edges.next();
    }
    return lList;   
  }

}
