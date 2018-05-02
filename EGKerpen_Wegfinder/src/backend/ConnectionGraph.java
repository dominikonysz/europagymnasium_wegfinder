/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import graphklassen.Dijkstra;
import graphklassen.Graph;
import graphklassen.GraphNode;
import java.awt.Point;
import listenklassen.List;

/**
 *
 * @author Dominik Onyszkiewicz
 */
public class ConnectionGraph extends Graph {
    
    Dijkstra d;
    
    public ConnectionGraph() {
        buildGraph();
        d = new Dijkstra(this);
    }
    
    public List<GraphNode> getPath(String from, String to) {
        List<GraphNode> out = null;
        if(this.hasNode(from) && this.hasNode(to)) 
            out = d.getPath(this.getNode(from), this.getNode(to));
        return out;
    }
    
    /**
     * Returns the closest room to the given position
     * @param pos
     * @return 
     */
    public String getClosestRoom(Point pos) {
        String room = "";
        double minDistance = Double.MAX_VALUE, distance;
        GraphNode node;
        List<GraphNode> nodes = getNodes();
        nodes.toFirst();
        while(nodes.hasAccess()) {
            node = nodes.getObject();
            if(node.getName().startsWith("T") || node.getName().startsWith("P")) {
                nodes.next();
                continue;
            }
            distance = Math.sqrt(Math.pow(node.getX() - pos.getX(), 2) + Math.pow(node.getY() - pos.getY(), 2));
            if(distance < minDistance) {
                minDistance = distance;
                room = node.getName();
            }
            nodes.next();
        }
        return room;
    }
    
    /**
     * This method builds the graph containing rooms, waypoints and stairs.
     */
    private void buildGraph(){
                // Nodes:
                
            // Basement:
            
        // Stairs:
        
        this.addNode(new GraphNode("T4_-1", 170, 718));
        this.addNode(new GraphNode("T5_-1", 265, 718));
        
        // Waypoints:
        
        this.addNode(new GraphNode("P-100", 170, 735)); // Spielekeller
        this.addNode(new GraphNode("P-101", 170, 750)); // K53
        this.addNode(new GraphNode("P-102", 265, 750)); // K59 + K62
        
        // Rooms:
        
        this.addNode(new GraphNode("Saftbar", 154, 735));
        this.addNode(new GraphNode("Spielekeller", 98, 742));
        this.addNode(new GraphNode("Kunstraum 53", 180, 750));
        this.addNode(new GraphNode("Kunstraum 59", 244, 750)); 
        this.addNode(new GraphNode("Technikraum 62", 275, 750)); 
        
            // Ground floor:
        
        // Stairs:
        
        this.addNode(new GraphNode("T1_0", 520, 656));
        this.addNode(new GraphNode("T2_0", 640, 670));
        this.addNode(new GraphNode("T3_0", 632, 608));
        this.addNode(new GraphNode("T4_0", 551, 519));
        this.addNode(new GraphNode("T5_0", 608, 520));
        this.addNode(new GraphNode("T6_0", 536, 805)); // Finished
        
        // Waypoints:
        
        this.addNode(new GraphNode("P000", 539, 684));
        this.addNode(new GraphNode("P001", 530, 684));
        this.addNode(new GraphNode("P002", 483, 684));
        this.addNode(new GraphNode("P003", 466, 684));
        this.addNode(new GraphNode("P004", 540, 656));
        this.addNode(new GraphNode("P005", 550, 656));
        this.addNode(new GraphNode("P007", 589, 683));
        this.addNode(new GraphNode("P008", 538, 632));
        this.addNode(new GraphNode("P010", 506, 584));
        this.addNode(new GraphNode("P011", 506, 574));
        this.addNode(new GraphNode("P012", 506, 534));
        this.addNode(new GraphNode("P013", 543, 548));
        this.addNode(new GraphNode("P014", 543, 504));
        this.addNode(new GraphNode("P016", 543, 472));
        this.addNode(new GraphNode("P017", 543, 457));
        this.addNode(new GraphNode("P018", 558, 448));
        this.addNode(new GraphNode("P019", 608, 448));
        this.addNode(new GraphNode("P020", 632, 448));
        this.addNode(new GraphNode("P021", 649, 448));
        this.addNode(new GraphNode("P022", 645, 467));
        this.addNode(new GraphNode("P023", 645, 472));
        this.addNode(new GraphNode("P024", 642, 535));
        this.addNode(new GraphNode("P025", 607, 535));
        this.addNode(new GraphNode("P026", 632, 625));
        this.addNode(new GraphNode("P027", 654, 625));
        this.addNode(new GraphNode("P028", 700, 625));
        this.addNode(new GraphNode("P029", 720, 625));
        this.addNode(new GraphNode("P030", 754, 625));
        this.addNode(new GraphNode("P032", 787, 625));
        this.addNode(new GraphNode("P033", 810, 625));
        this.addNode(new GraphNode("P034", 647, 659));
        this.addNode(new GraphNode("P035", 670, 659));
        this.addNode(new GraphNode("P036", 670, 686));
        this.addNode(new GraphNode("P037", 703, 686));
        this.addNode(new GraphNode("P039", 736, 686));
        this.addNode(new GraphNode("P040", 742, 686));
        this.addNode(new GraphNode("P041", 742, 711));
        this.addNode(new GraphNode("P042", 766, 686));
        this.addNode(new GraphNode("P043", 797, 686));
        this.addNode(new GraphNode("P044", 797, 718)); // Finished
        
        // Rooms:
        
        this.addNode(new GraphNode("Bibliothek", 573, 683));
        this.addNode(new GraphNode("Foyer", 589, 648));
        this.addNode(new GraphNode("WC Herren", 482, 672));
        this.addNode(new GraphNode("WC Damen", 466, 672));
        this.addNode(new GraphNode("Mensa", 433, 684));
        this.addNode(new GraphNode("Raum 38", 493, 586));
        this.addNode(new GraphNode("Raum 39", 493, 562));
        this.addNode(new GraphNode("Raum 42", 493, 522));
        this.addNode(new GraphNode("Werkraum 8", 542, 440));
        this.addNode(new GraphNode("Werkraum 14", 559, 440));
        this.addNode(new GraphNode("Werkraum 15", 608, 440));
        this.addNode(new GraphNode("Werkraum 17", 632, 440));
        this.addNode(new GraphNode("Werkraum 20", 649, 440));
        this.addNode(new GraphNode("Werkraum 21", 652, 467));
        this.addNode(new GraphNode("Werkraum 24", 658, 486));
        this.addNode(new GraphNode("Werkraum 27", 633, 472));
        this.addNode(new GraphNode("Werkraum 32", 551, 472));
        this.addNode(new GraphNode("Raum 63", 700, 617));
        this.addNode(new GraphNode("Raum 64", 719, 617));
        this.addNode(new GraphNode("Raum 65", 754, 617));
        this.addNode(new GraphNode("Raum 66", 784, 617));
        this.addNode(new GraphNode("Raum 67", 812, 617));
        this.addNode(new GraphNode("Raum 70", 812, 636));
        this.addNode(new GraphNode("Raum 71", 794, 636));
        this.addNode(new GraphNode("Raum 72", 756, 636));
        this.addNode(new GraphNode("Raum 73", 735, 636));
        this.addNode(new GraphNode("Raum 87", 738, 676));
        this.addNode(new GraphNode("Raum 88", 766, 676));
        this.addNode(new GraphNode("Raum 89", 799, 676));
        this.addNode(new GraphNode("Raum 90", 809, 687));
        this.addNode(new GraphNode("Raum 92", 809, 718));
        this.addNode(new GraphNode("Raum 93", 798, 729));
        this.addNode(new GraphNode("Raum 94", 788, 713));
        this.addNode(new GraphNode("Raum 95", 751, 718));
        this.addNode(new GraphNode("Raum 96", 734, 718));
        this.addNode(new GraphNode("Raum 97", 703, 720)); // Finished
        
            // First floor:
        
        // Stairs:
        
        this.addNode(new GraphNode("T1_1", 135, 469));
        this.addNode(new GraphNode("T2_1", 285, 478));
        this.addNode(new GraphNode("T3_1", 261, 400));
        this.addNode(new GraphNode("T4_1", 156, 286));
        this.addNode(new GraphNode("T5_1", 233, 289));
        this.addNode(new GraphNode("T6_1", 156, 625));
        this.addNode(new GraphNode("T7_1", 16, 574));
        this.addNode(new GraphNode("T8_1", 441, 495));
        this.addNode(new GraphNode("T9_1", 441, 427)); // Finished
        
        // Waypoints:
        
        this.addNode(new GraphNode("P100", 39, 574));
        this.addNode(new GraphNode("P101", 52, 574));
        this.addNode(new GraphNode("P102", 90, 574));
        this.addNode(new GraphNode("P103", 113, 574));
        this.addNode(new GraphNode("P104", 128, 574));
        this.addNode(new GraphNode("P105", 155, 574));
        this.addNode(new GraphNode("P106", 155, 595));
        this.addNode(new GraphNode("P107", 155, 500));
        this.addNode(new GraphNode("P108", 143, 500));
        this.addNode(new GraphNode("P109", 83, 500));
        this.addNode(new GraphNode("P110", 54, 500));
        this.addNode(new GraphNode("P111", 143, 469));
        this.addNode(new GraphNode("P112", 155, 469));
        this.addNode(new GraphNode("P113", 155, 451));
        this.addNode(new GraphNode("P114", 155, 418));
        this.addNode(new GraphNode("P115", 155, 388));
        this.addNode(new GraphNode("P116", 155, 357));
        this.addNode(new GraphNode("P117", 155, 339));
        this.addNode(new GraphNode("P118", 155, 319));
        this.addNode(new GraphNode("P119", 155, 302));
        this.addNode(new GraphNode("P120", 185, 451));
        this.addNode(new GraphNode("P121", 207, 451));
        this.addNode(new GraphNode("P122", 232, 451));
        this.addNode(new GraphNode("P123", 232, 426));
        this.addNode(new GraphNode("P124", 232, 370));
        this.addNode(new GraphNode("P125", 232, 332));
        this.addNode(new GraphNode("P126", 232, 304));
        this.addNode(new GraphNode("P127", 262, 426));
        this.addNode(new GraphNode("P128", 316, 426));
        this.addNode(new GraphNode("P130", 321, 426));
        this.addNode(new GraphNode("P131", 348, 426));
        this.addNode(new GraphNode("P132", 366, 426));
        this.addNode(new GraphNode("P133", 387, 426));
        this.addNode(new GraphNode("P134", 408, 426));
        this.addNode(new GraphNode("P135", 422, 427));
        this.addNode(new GraphNode("P136", 285, 464));
        this.addNode(new GraphNode("P137", 316, 464));
        this.addNode(new GraphNode("P138", 316, 498));
        this.addNode(new GraphNode("P139", 346, 498));
        this.addNode(new GraphNode("P140", 346, 509));
        this.addNode(new GraphNode("P141", 389, 498));
        this.addNode(new GraphNode("P142", 423, 498)); // Finished
        
        // Rooms:
        
        this.addNode(new GraphNode("Lehrerzimmer", 52, 507));
        this.addNode(new GraphNode("Büro der Schulleitung/Sekretariat", 55, 489));
        this.addNode(new GraphNode("Büro der stellv. Schulleitung", 84, 490));
        this.addNode(new GraphNode("Raum 104", 164, 594));
        this.addNode(new GraphNode("Raum 106", 128, 583));
        this.addNode(new GraphNode("Raum 107", 112, 583));
        this.addNode(new GraphNode("Raum 108", 90, 583));
        this.addNode(new GraphNode("Raum 109", 52, 583));
        this.addNode(new GraphNode("Raum 110", 39, 583));
        this.addNode(new GraphNode("Raum 132", 147, 420));
        this.addNode(new GraphNode("Raum 134", 147, 388));
        this.addNode(new GraphNode("Raum 135", 147, 357));
        this.addNode(new GraphNode("Raum 136", 147, 339));
        this.addNode(new GraphNode("Raum 137", 147, 302));
        this.addNode(new GraphNode("Raum 141", 164, 303));
        this.addNode(new GraphNode("Raum 142", 164, 319));
        this.addNode(new GraphNode("Raum 144", 185, 444));
        this.addNode(new GraphNode("Raum 145", 208, 444));
        this.addNode(new GraphNode("Raum 150", 224, 320));
        this.addNode(new GraphNode("Raum 151", 224, 304));
        this.addNode(new GraphNode("Raum 154", 241, 303));
        this.addNode(new GraphNode("Raum 155", 241, 345));
        this.addNode(new GraphNode("Raum 156", 241, 368));
        this.addNode(new GraphNode("Raum 166", 321, 417));
        this.addNode(new GraphNode("Raum 167", 348, 417));
        this.addNode(new GraphNode("Raum 168", 366, 417));
        this.addNode(new GraphNode("Raum 169", 384, 417));
        this.addNode(new GraphNode("Raum 170", 407, 417));
        this.addNode(new GraphNode("Raum 171", 422, 417));
        this.addNode(new GraphNode("Raum 174", 422, 438));
        this.addNode(new GraphNode("Raum 175", 392, 438));
        this.addNode(new GraphNode("Raum 184", 392, 484));
        this.addNode(new GraphNode("Raum 185", 426, 484));
        this.addNode(new GraphNode("Raum 188", 420, 507));
        this.addNode(new GraphNode("Raum 189", 386, 507));
        this.addNode(new GraphNode("Raum 190", 354, 510));
        this.addNode(new GraphNode("Raum 191", 331, 515));
        this.addNode(new GraphNode("Raum 192/193", 305, 498)); // Finished
        
            // Second floor:
        
        // Stairs:
        
        this.addNode(new GraphNode("T1_2", 502, 223));
        this.addNode(new GraphNode("T2_2", 658, 238));
        this.addNode(new GraphNode("T3_2", 636, 159));
        this.addNode(new GraphNode("T4_2", 530, 51));
        this.addNode(new GraphNode("T5_2", 606, 50));
        this.addNode(new GraphNode("T6_2", 530, 383));
        this.addNode(new GraphNode("T7_2", 395, 331));
        this.addNode(new GraphNode("T8_2", 807, 253));
        this.addNode(new GraphNode("T9_2", 807, 190)); // Finished
        
        // Waypoints:
        
        this.addNode(new GraphNode("P200", 434, 331));
        this.addNode(new GraphNode("P201", 449, 331));
        this.addNode(new GraphNode("P202", 454, 331));
        this.addNode(new GraphNode("P203", 481, 331));
        this.addNode(new GraphNode("P204", 502, 331));
        this.addNode(new GraphNode("P205", 530, 331));
        this.addNode(new GraphNode("P206", 530, 331));
        this.addNode(new GraphNode("P207", 530, 365));
        this.addNode(new GraphNode("P208", 530, 302));
        this.addNode(new GraphNode("P209", 530, 270));
        this.addNode(new GraphNode("P210", 515, 257));
        this.addNode(new GraphNode("P211", 473, 257));
        this.addNode(new GraphNode("P212", 451, 257));
        this.addNode(new GraphNode("P213", 422, 257));
        this.addNode(new GraphNode("P214", 515, 223));
        this.addNode(new GraphNode("P215", 530, 223));
        this.addNode(new GraphNode("P216", 530, 213));
        this.addNode(new GraphNode("P217", 530, 183));
        this.addNode(new GraphNode("P218", 530, 143));
        this.addNode(new GraphNode("P219", 530, 119));
        this.addNode(new GraphNode("P220", 530, 96));
        this.addNode(new GraphNode("P221", 530, 85));
        this.addNode(new GraphNode("P222", 530, 66));
        this.addNode(new GraphNode("P223", 555, 213));
        this.addNode(new GraphNode("P225", 585, 213));
        this.addNode(new GraphNode("P227", 606, 213));
        this.addNode(new GraphNode("P228", 606, 190));
        this.addNode(new GraphNode("P229", 606, 119));
        this.addNode(new GraphNode("P230", 606, 93));
        this.addNode(new GraphNode("P231", 606, 68));
        this.addNode(new GraphNode("P232", 636, 190));
        this.addNode(new GraphNode("P233", 685, 190));
        this.addNode(new GraphNode("P234", 685, 223));
        this.addNode(new GraphNode("P235", 685, 253));
        this.addNode(new GraphNode("P236", 699, 253));
        this.addNode(new GraphNode("P237", 724, 253));
        this.addNode(new GraphNode("P238", 658, 223));
        this.addNode(new GraphNode("P239", 754, 253));
        this.addNode(new GraphNode("P240", 788, 253));
        this.addNode(new GraphNode("P241", 696, 190));
        this.addNode(new GraphNode("P242", 717, 190));
        this.addNode(new GraphNode("P243", 730, 190));
        this.addNode(new GraphNode("P244", 756, 190));
        this.addNode(new GraphNode("P246", 788, 190));
        this.addNode(new GraphNode("P247", 415, 331));
        this.addNode(new GraphNode("P248", 673, 256)); // Finished
        
        // Rooms:
        
        this.addNode(new GraphNode("Raum 200", 541, 269));
        this.addNode(new GraphNode("Raum 203/204", 540, 301));
        this.addNode(new GraphNode("Raum 205", 540, 331));
        this.addNode(new GraphNode("Raum 206", 540, 364));
        this.addNode(new GraphNode("Raum 208", 502, 339));
        this.addNode(new GraphNode("Raum 209", 481, 339));
        this.addNode(new GraphNode("Raum 210", 453, 339));
        this.addNode(new GraphNode("Raum 212", 434, 339));
        this.addNode(new GraphNode("Raum 213", 416, 339));
        this.addNode(new GraphNode("Raum 215", 416, 322));
        this.addNode(new GraphNode("Raum 216", 450, 322));
        this.addNode(new GraphNode("Raum 217", 449, 268));
        this.addNode(new GraphNode("Raum 218", 418, 268));
        this.addNode(new GraphNode("Raum 219", 414, 259));
        this.addNode(new GraphNode("Raum 220", 422, 247));
        this.addNode(new GraphNode("Raum 222", 451, 247));
        this.addNode(new GraphNode("Raum 223", 473, 247));
        this.addNode(new GraphNode("Raum 227", 519, 183));
        this.addNode(new GraphNode("Raum 228", 519, 143));
        this.addNode(new GraphNode("Raum 229", 519, 118));
        this.addNode(new GraphNode("Raum 230", 519, 96));
        this.addNode(new GraphNode("Raum 231", 519, 65));
        this.addNode(new GraphNode("Raum 235", 539, 67));
        this.addNode(new GraphNode("Raum 236", 539, 85));
        this.addNode(new GraphNode("Raum 237", 555, 204));
        this.addNode(new GraphNode("Raum 238", 585, 204));
        this.addNode(new GraphNode("Raum 240", 559, 222));
        this.addNode(new GraphNode("Raum 241", 606, 222));
        this.addNode(new GraphNode("Raum 242", 618, 222));
        this.addNode(new GraphNode("Raum 250", 595, 84));
        this.addNode(new GraphNode("Raum 251", 595, 67));
        this.addNode(new GraphNode("Raum 253", 615, 67));
        this.addNode(new GraphNode("Raum 254", 615, 103));
        this.addNode(new GraphNode("Raum 255", 615, 118));
        this.addNode(new GraphNode("Raum 262", 696, 179));
        this.addNode(new GraphNode("Raum 263", 717, 179));
        this.addNode(new GraphNode("Raum 264", 730, 179));
        this.addNode(new GraphNode("Raum 266", 751, 179));
        this.addNode(new GraphNode("Raum 267", 789, 179));
        this.addNode(new GraphNode("Raum 269", 787, 200));
        this.addNode(new GraphNode("Raum 270", 762, 200));
        this.addNode(new GraphNode("Raum 283", 762, 242));
        this.addNode(new GraphNode("Raum 284", 788, 242));
        this.addNode(new GraphNode("Raum 286", 791, 263));
        this.addNode(new GraphNode("Raum 288", 747, 263));
        this.addNode(new GraphNode("Raum 289", 724, 263));
        this.addNode(new GraphNode("Raum 290", 698, 263));
        this.addNode(new GraphNode("Raum 293", 673, 265)); // Finished
        
            // Third floor:
        
        // Stairs:
        
        this.addNode(new GraphNode("T1_3", 71, 159));
        this.addNode(new GraphNode("T2_3", 281, 171));
        this.addNode(new GraphNode("T3_3", 260, 54));
        
        // Waypoints:
        
        this.addNode(new GraphNode("P300", 105, 159));
        this.addNode(new GraphNode("P301", 105, 163));
        this.addNode(new GraphNode("P302", 105, 138));
        this.addNode(new GraphNode("P303", 120, 138));
        this.addNode(new GraphNode("P304", 144, 138));
        this.addNode(new GraphNode("P305", 165, 138));
        this.addNode(new GraphNode("P306", 169, 138));
        this.addNode(new GraphNode("P307", 193, 138));
        this.addNode(new GraphNode("P308", 213, 138));
        this.addNode(new GraphNode("P309", 228, 138));
        this.addNode(new GraphNode("P310", 234, 138));
        this.addNode(new GraphNode("P311", 257, 138));
        this.addNode(new GraphNode("P312", 263, 138));
        this.addNode(new GraphNode("P313", 281, 138));
        this.addNode(new GraphNode("P314", 263, 113)); // Finished
        
        // Rooms:
        
        this.addNode(new GraphNode("Raum 302", 105, 175));
        this.addNode(new GraphNode("Raum 303", 122, 163));
        this.addNode(new GraphNode("Raum 304", 166, 151));
        this.addNode(new GraphNode("Raum 305", 193, 151));
        this.addNode(new GraphNode("Raum 306", 228, 151));
        this.addNode(new GraphNode("Raum 307", 258, 151));
        this.addNode(new GraphNode("Raum 308", 294, 138));
        this.addNode(new GraphNode("Raum 309", 275, 113));
        this.addNode(new GraphNode("Raum 310", 233, 126));
        this.addNode(new GraphNode("Raum 311", 213, 126));
        this.addNode(new GraphNode("Raum 312", 168, 126));
        this.addNode(new GraphNode("Raum 313", 144, 126));
        this.addNode(new GraphNode("Raum 314", 120, 126)); // Finished
                
                // Edges:
            
            // Stairs to stairs:
        
        // Basement to ground floor:
        
        this.addEdge(this.getNode("T4_-1"), this.getNode("T4_0"), 17);
        this.addEdge(this.getNode("T5_-1"), this.getNode("T5_0"), 17);
            
        // Ground floor to first floor:
        
        this.addEdge(this.getNode("T1_0"), this.getNode("T1_1"), 17);
        this.addEdge(this.getNode("T2_0"), this.getNode("T2_1"), 17);
        this.addEdge(this.getNode("T3_0"), this.getNode("T3_1"), 17);
        this.addEdge(this.getNode("T4_0"), this.getNode("T4_1"), 17);
        this.addEdge(this.getNode("T5_0"), this.getNode("T5_1"), 17);
        this.addEdge(this.getNode("T6_0"), this.getNode("T6_1"), 17);
        
        // First floor to second floor:
        
        this.addEdge(this.getNode("T1_1"), this.getNode("T1_2"), 17);
        this.addEdge(this.getNode("T2_1"), this.getNode("T2_2"), 17);
        this.addEdge(this.getNode("T3_1"), this.getNode("T3_2"), 17);
        this.addEdge(this.getNode("T4_1"), this.getNode("T4_2"), 17);
        this.addEdge(this.getNode("T5_1"), this.getNode("T5_2"), 17);
        this.addEdge(this.getNode("T6_1"), this.getNode("T6_2"), 17);
        this.addEdge(this.getNode("T7_1"), this.getNode("T7_2"), 17);
        this.addEdge(this.getNode("T8_1"), this.getNode("T8_2"), 17);
        this.addEdge(this.getNode("T9_1"), this.getNode("T9_2"), 17);
        
        // Second floor to third floor:
        
        this.addEdge(this.getNode("T1_2"), this.getNode("T1_3"), 17);
        this.addEdge(this.getNode("T2_2"), this.getNode("T2_3"), 17);
        this.addEdge(this.getNode("T3_2"), this.getNode("T3_3"), 17);
        
            // Stairs to waypoints:
            
        // Basement:
        
        this.addEdge(this.getNode("T4_-1"), this.getNode("P-100"), 1);
        this.addEdge(this.getNode("T5_-1"), this.getNode("P-102"), 1);
        
        // Ground floor:
        
        this.addEdge(this.getNode("T1_0"), this.getNode("P005"), 1);
        this.addEdge(this.getNode("T2_0"), this.getNode("P034"), 1);
        this.addEdge(this.getNode("T3_0"), this.getNode("P026"), 4);
        this.addEdge(this.getNode("T4_0"), this.getNode("P014"), 2);
        this.addEdge(this.getNode("T5_0"), this.getNode("P025"), 2);
        this.addEdge(this.getNode("T6_0"), this.getNode("P000"), 34.5);
        
        // First floor:
        
        this.addEdge(this.getNode("T1_1"), this.getNode("P111"), 1);
        this.addEdge(this.getNode("T2_1"), this.getNode("P136"), 1);
        this.addEdge(this.getNode("T3_1"), this.getNode("P127"), 3);
        this.addEdge(this.getNode("T4_1"), this.getNode("P119"), 1);
        this.addEdge(this.getNode("T5_1"), this.getNode("P126"), 1);
        this.addEdge(this.getNode("T6_1"), this.getNode("P106"), 6);
        this.addEdge(this.getNode("T7_1"), this.getNode("P100"), 1);
        this.addEdge(this.getNode("T8_1"), this.getNode("P142"), 1);
        this.addEdge(this.getNode("T9_1"), this.getNode("P135"), 1);
        
        // Second floor:
        
        this.addEdge(this.getNode("T1_2"), this.getNode("P214"), 1);
        this.addEdge(this.getNode("T2_2"), this.getNode("P238"), 1);
        this.addEdge(this.getNode("T3_2"), this.getNode("P232"), 1);
        this.addEdge(this.getNode("T4_2"), this.getNode("P222"), 0.5);
        this.addEdge(this.getNode("T5_2"), this.getNode("P231"), 0.5);
        this.addEdge(this.getNode("T6_2"), this.getNode("P207"), 1.5);
        this.addEdge(this.getNode("T7_2"), this.getNode("P247"), 2.5);
        this.addEdge(this.getNode("T8_2"), this.getNode("P240"), 1.5);
        this.addEdge(this.getNode("T9_2"), this.getNode("P246"), 1.5);
        
        // Third floor:
        
        this.addEdge(this.getNode("T1_3"), this.getNode("P300"), 1);
        this.addEdge(this.getNode("T2_3"), this.getNode("P313"), 3);
        this.addEdge(this.getNode("T3_3"), this.getNode("P314"), 6.5);
        
            // Waypoints to waypoints:
        
        // Basement:
        
        this.addEdge(this.getNode("P-100"), this.getNode("P-101"), 2);
            
        // Ground floor:
        
        this.addEdge(this.getNode("P000"), this.getNode("P001"), 3.5);
        this.addEdge(this.getNode("P000"), this.getNode("P005"), 7.5);
        this.addEdge(this.getNode("P001"), this.getNode("P002"), 17);
        this.addEdge(this.getNode("P002"), this.getNode("P003"), 4);
        this.addEdge(this.getNode("P004"), this.getNode("P005"), 3.5);
        this.addEdge(this.getNode("P004"), this.getNode("P008"), 10);
        this.addEdge(this.getNode("P008"), this.getNode("P010"), 17);
        this.addEdge(this.getNode("P010"), this.getNode("P011"), 3);
        this.addEdge(this.getNode("P011"), this.getNode("P012"), 15);
        this.addEdge(this.getNode("P011"), this.getNode("P013"), 11);
        this.addEdge(this.getNode("P013"), this.getNode("P014"), 12);
        this.addEdge(this.getNode("P014"), this.getNode("P016"), 10.5);
        this.addEdge(this.getNode("P016"), this.getNode("P017"), 9);
        this.addEdge(this.getNode("P017"), this.getNode("P018"), 2.5);
        this.addEdge(this.getNode("P018"), this.getNode("P019"), 23);
        this.addEdge(this.getNode("P019"), this.getNode("P020"), 5);
        this.addEdge(this.getNode("P020"), this.getNode("P021"), 3.5);
        this.addEdge(this.getNode("P021"), this.getNode("P022"), 8);
        this.addEdge(this.getNode("P022"), this.getNode("P023"), 1);
        this.addEdge(this.getNode("P023"), this.getNode("P024"), 22);
        this.addEdge(this.getNode("P024"), this.getNode("P025"), 13);
        this.addEdge(this.getNode("P026"), this.getNode("P027"), 13);
        this.addEdge(this.getNode("P027"), this.getNode("P028"), 9);
        this.addEdge(this.getNode("P027"), this.getNode("P035"), 11.5);
        this.addEdge(this.getNode("P028"), this.getNode("P029"), 12);
        this.addEdge(this.getNode("P029"), this.getNode("P030"), 9);
        this.addEdge(this.getNode("P030"), this.getNode("P032"), 9);
        this.addEdge(this.getNode("P032"), this.getNode("P033"), 9);
        this.addEdge(this.getNode("P034"), this.getNode("P035"), 8);
        this.addEdge(this.getNode("P035"), this.getNode("P036"), 9);
        this.addEdge(this.getNode("P036"), this.getNode("P037"), 12);
        this.addEdge(this.getNode("P037"), this.getNode("P039"), 9);
        this.addEdge(this.getNode("P039"), this.getNode("P040"), 7);
        this.addEdge(this.getNode("P040"), this.getNode("P041"), 6);
        this.addEdge(this.getNode("P040"), this.getNode("P042"), 4);
        this.addEdge(this.getNode("P042"), this.getNode("P043"), 13);
        this.addEdge(this.getNode("P043"), this.getNode("P044"), 10);
        
        //Dirty Connetion added here
        this.addEdge(this.getNode("P027"), this.getNode("P024"), 17);
        
        // First floor:
        
        this.addEdge(this.getNode("P100"), this.getNode("P101"), 2.5);
        this.addEdge(this.getNode("P101"), this.getNode("P102"), 9);
        this.addEdge(this.getNode("P102"), this.getNode("P103"), 5);
        this.addEdge(this.getNode("P103"), this.getNode("P104"), 8);
        this.addEdge(this.getNode("P104"), this.getNode("P105"), 3.5);
        this.addEdge(this.getNode("P105"), this.getNode("P106"), 3);
        this.addEdge(this.getNode("P105"), this.getNode("P107"), 21);
        this.addEdge(this.getNode("P107"), this.getNode("P108"), 3);
        this.addEdge(this.getNode("P107"), this.getNode("P112"), 7);
        this.addEdge(this.getNode("P108"), this.getNode("P109"), 16);
        this.addEdge(this.getNode("P108"), this.getNode("P111"), 7);
        this.addEdge(this.getNode("P109"), this.getNode("P110"), 6);
        this.addEdge(this.getNode("P111"), this.getNode("P112"), 3);
        this.addEdge(this.getNode("P112"), this.getNode("P113"), 4.5);
        this.addEdge(this.getNode("P113"), this.getNode("P114"), 7);
        this.addEdge(this.getNode("P113"), this.getNode("P120"), 8.5);
        this.addEdge(this.getNode("P114"), this.getNode("P115"), 10);
        this.addEdge(this.getNode("P115"), this.getNode("P116"), 8.5);
        this.addEdge(this.getNode("P116"), this.getNode("P117"), 3.5);
        this.addEdge(this.getNode("P117"), this.getNode("P118"), 5);
        this.addEdge(this.getNode("P118"), this.getNode("P119"), 3.5);
        this.addEdge(this.getNode("P120"), this.getNode("P121"), 8.5);
        this.addEdge(this.getNode("P121"), this.getNode("P122"), 4);
        this.addEdge(this.getNode("P122"), this.getNode("P123"), 8);
        this.addEdge(this.getNode("P123"), this.getNode("P124"), 11.5);
        this.addEdge(this.getNode("P123"), this.getNode("P127"), 8);
        this.addEdge(this.getNode("P124"), this.getNode("P125"), 11);
        this.addEdge(this.getNode("P125"), this.getNode("P126"), 7);
        this.addEdge(this.getNode("P127"), this.getNode("P128"), 13);
        this.addEdge(this.getNode("P128"), this.getNode("P130"), 3.5);
        this.addEdge(this.getNode("P128"), this.getNode("P137"), 11.5);
        this.addEdge(this.getNode("P130"), this.getNode("P131"), 5);
        this.addEdge(this.getNode("P131"), this.getNode("P132"), 3.5);
        this.addEdge(this.getNode("P132"), this.getNode("P133"), 9);
        this.addEdge(this.getNode("P133"), this.getNode("P134"), 5);
        this.addEdge(this.getNode("P134"), this.getNode("P135"), 2.5);
        this.addEdge(this.getNode("P136"), this.getNode("P137"), 7.5);
        this.addEdge(this.getNode("P137"), this.getNode("P138"), 9);
        this.addEdge(this.getNode("P138"), this.getNode("P139"), 8.5);
        this.addEdge(this.getNode("P139"), this.getNode("P140"), 3);
        this.addEdge(this.getNode("P139"), this.getNode("P141"), 15);
        this.addEdge(this.getNode("P141"), this.getNode("P142"), 5);
        
        // Second floor:
        
        this.addEdge(this.getNode("P200"), this.getNode("P201"), 5);
        this.addEdge(this.getNode("P200"), this.getNode("P247"), 3.5);
        this.addEdge(this.getNode("P201"), this.getNode("P202"), 3.5);
        this.addEdge(this.getNode("P202"), this.getNode("P203"), 8.5);
        this.addEdge(this.getNode("P203"), this.getNode("P204"), 5);
        this.addEdge(this.getNode("P204"), this.getNode("P205"), 3);
        this.addEdge(this.getNode("P205"), this.getNode("P206"), 3.5);
        this.addEdge(this.getNode("P205"), this.getNode("P208"), 8.5);
        this.addEdge(this.getNode("P206"), this.getNode("P207"), 5);
        this.addEdge(this.getNode("P208"), this.getNode("P209"), 11.5);
        this.addEdge(this.getNode("P209"), this.getNode("P210"), 3);
        this.addEdge(this.getNode("P209"), this.getNode("P215"), 7);
        this.addEdge(this.getNode("P210"), this.getNode("P211"), 8.5);
        this.addEdge(this.getNode("P210"), this.getNode("P214"), 7);
        this.addEdge(this.getNode("P211"), this.getNode("P212"), 8);
        this.addEdge(this.getNode("P212"), this.getNode("P213"), 8);
        this.addEdge(this.getNode("P214"), this.getNode("P215"), 3);
        this.addEdge(this.getNode("P215"), this.getNode("P216"), 4);
        this.addEdge(this.getNode("P216"), this.getNode("P217"), 6.5);
        this.addEdge(this.getNode("P216"), this.getNode("P223"), 8.5);
        this.addEdge(this.getNode("P217"), this.getNode("P218"), 9.5);
        this.addEdge(this.getNode("P218"), this.getNode("P219"), 8);
        this.addEdge(this.getNode("P219"), this.getNode("P220"), 3.5);
        this.addEdge(this.getNode("P220"), this.getNode("P221"), 5);
        this.addEdge(this.getNode("P221"), this.getNode("P222"), 3.5);
        this.addEdge(this.getNode("P223"), this.getNode("P225"), 9);
        this.addEdge(this.getNode("P225"), this.getNode("P227"), 9);
        this.addEdge(this.getNode("P227"), this.getNode("P228"), 8);
        this.addEdge(this.getNode("P228"), this.getNode("P229"), 12);
        this.addEdge(this.getNode("P228"), this.getNode("P232"), 7.5);
        this.addEdge(this.getNode("P229"), this.getNode("P230"), 10.5);
        this.addEdge(this.getNode("P230"), this.getNode("P231"), 6.5);
        this.addEdge(this.getNode("P232"), this.getNode("P233"), 12);
        this.addEdge(this.getNode("P233"), this.getNode("P234"), 11);
        this.addEdge(this.getNode("P233"), this.getNode("P241"), 3);
        this.addEdge(this.getNode("P234"), this.getNode("P235"), 9);
        this.addEdge(this.getNode("P234"), this.getNode("P238"), 7);
        this.addEdge(this.getNode("P235"), this.getNode("P236"), 8);
        this.addEdge(this.getNode("P235"), this.getNode("P248"), 3);
        this.addEdge(this.getNode("P236"), this.getNode("P237"), 3.5);
        this.addEdge(this.getNode("P237"), this.getNode("P239"), 8.5);
        this.addEdge(this.getNode("P239"), this.getNode("P240"), 9);
        this.addEdge(this.getNode("P241"), this.getNode("P242"), 5);
        this.addEdge(this.getNode("P242"), this.getNode("P243"), 8.5);
        this.addEdge(this.getNode("P243"), this.getNode("P244"), 3);
        this.addEdge(this.getNode("P244"), this.getNode("P246"), 8.5);
        
        // Third floor:
        
        this.addEdge(this.getNode("P300"), this.getNode("P301"), 0.5);
        this.addEdge(this.getNode("P300"), this.getNode("P302"), 5);
        this.addEdge(this.getNode("P302"), this.getNode("P303"), 3.5);
        this.addEdge(this.getNode("P303"), this.getNode("P304"), 5);
        this.addEdge(this.getNode("P304"), this.getNode("P305"), 0.5);
        this.addEdge(this.getNode("P305"), this.getNode("P306"), 4);
        this.addEdge(this.getNode("P306"), this.getNode("P307"), 2);
        this.addEdge(this.getNode("P307"), this.getNode("P308"), 4);
        this.addEdge(this.getNode("P308"), this.getNode("P309"), 2);
        this.addEdge(this.getNode("P309"), this.getNode("P310"), 2);
        this.addEdge(this.getNode("P310"), this.getNode("P311"), 1.5);
        this.addEdge(this.getNode("P311"), this.getNode("P312"), 4);
        this.addEdge(this.getNode("P312"), this.getNode("P313"), 1);
        this.addEdge(this.getNode("P312"), this.getNode("P314"), 3);
        
            // Waypoints to rooms:
            
        // Basement:
        // TODO(if needed): change senseless distances
        this.addEdge(this.getNode("P-100"), this.getNode("Saftbar"), 0.1);
        this.addEdge(this.getNode("P-101"), this.getNode("Kunstraum 53"), 0.1);
        this.addEdge(this.getNode("P-102"), this.getNode("Kunstraum 59"), 0.1);
        this.addEdge(this.getNode("P-102"), this.getNode("Technikraum 62"), 0.1);
        // dirty connection
        this.addEdge(this.getNode("Saftbar"), this.getNode("Spielekeller"), 0.11);
        
        // Ground floor:
        
        this.addEdge(this.getNode("P007"), this.getNode("Bibliothek"), 0.1);
        this.addEdge(this.getNode("P005"), this.getNode("Foyer"), 13);
        this.addEdge(this.getNode("P007"), this.getNode("Foyer"), 7);
        this.addEdge(this.getNode("P008"), this.getNode("Foyer"), 15);
        this.addEdge(this.getNode("P026"), this.getNode("Foyer"), 19);
        this.addEdge(this.getNode("P002"), this.getNode("WC Herren"), 0.1);
        this.addEdge(this.getNode("P003"), this.getNode("WC Damen"), 0.1);
        this.addEdge(this.getNode("P003"), this.getNode("Mensa"), 0.1);
        this.addEdge(this.getNode("P010"), this.getNode("Raum 38"), 0.1);
        this.addEdge(this.getNode("P011"), this.getNode("Raum 39"), 0.1);
        this.addEdge(this.getNode("P012"), this.getNode("Raum 42"), 0.1);
        this.addEdge(this.getNode("P017"), this.getNode("Werkraum 8"), 0.1);
        this.addEdge(this.getNode("P018"), this.getNode("Werkraum 14"), 0.1);
        this.addEdge(this.getNode("P019"), this.getNode("Werkraum 15"), 0.1);
        this.addEdge(this.getNode("P020"), this.getNode("Werkraum 17"), 0.1);
        this.addEdge(this.getNode("P021"), this.getNode("Werkraum 20"), 0.1);
        this.addEdge(this.getNode("P022"), this.getNode("Werkraum 21"), 0.1);
        this.addEdge(this.getNode("P023"), this.getNode("Werkraum 24"), 0.1);
        this.addEdge(this.getNode("P022"), this.getNode("Werkraum 27"), 0.1);
        this.addEdge(this.getNode("P016"), this.getNode("Werkraum 32"), 0.1);
        this.addEdge(this.getNode("P028"), this.getNode("Raum 63"), 0.1);
        this.addEdge(this.getNode("P029"), this.getNode("Raum 64"), 0.1);
        this.addEdge(this.getNode("P030"), this.getNode("Raum 65"), 0.1);
        this.addEdge(this.getNode("P032"), this.getNode("Raum 66"), 0.1);
        this.addEdge(this.getNode("P033"), this.getNode("Raum 67"), 0.1);
        this.addEdge(this.getNode("P033"), this.getNode("Raum 70"), 0.1);
        this.addEdge(this.getNode("P032"), this.getNode("Raum 71"), 0.1);
        this.addEdge(this.getNode("P030"), this.getNode("Raum 72"), 0.1);
        this.addEdge(this.getNode("P029"), this.getNode("Raum 73"), 0.1);
        this.addEdge(this.getNode("P039"), this.getNode("Raum 87"), 0.1);
        this.addEdge(this.getNode("P042"), this.getNode("Raum 88"), 0.1);
        this.addEdge(this.getNode("P043"), this.getNode("Raum 89"), 0.1);
        this.addEdge(this.getNode("P043"), this.getNode("Raum 90"), 0.1);
        this.addEdge(this.getNode("P044"), this.getNode("Raum 92"), 0.1);
        this.addEdge(this.getNode("P044"), this.getNode("Raum 93"), 0.1);
        this.addEdge(this.getNode("P041"), this.getNode("Raum 94"), 0.1);
        this.addEdge(this.getNode("P041"), this.getNode("Raum 95"), 0.1);
        this.addEdge(this.getNode("P041"), this.getNode("Raum 96"), 0.1);
        this.addEdge(this.getNode("P037"), this.getNode("Raum 97"), 0.1);
        
        // First floor:
        
        this.addEdge(this.getNode("P110"), this.getNode("Lehrerzimmer"), 0.1);
        this.addEdge(this.getNode("P110"), this.getNode("Büro der Schulleitung/Sekretariat"), 0.1);
        this.addEdge(this.getNode("P109"), this.getNode("Büro der stellv. Schulleitung"), 0.1);
        this.addEdge(this.getNode("P106"), this.getNode("Raum 104"), 0.1);
        this.addEdge(this.getNode("P104"), this.getNode("Raum 106"), 0.1);
        this.addEdge(this.getNode("P103"), this.getNode("Raum 107"), 0.1);
        this.addEdge(this.getNode("P102"), this.getNode("Raum 108"), 0.1);
        this.addEdge(this.getNode("P101"), this.getNode("Raum 109"), 0.1);
        this.addEdge(this.getNode("P100"), this.getNode("Raum 110"), 0.1);
        this.addEdge(this.getNode("P114"), this.getNode("Raum 132"), 0.1);
        this.addEdge(this.getNode("P115"), this.getNode("Raum 134"), 0.1);
        this.addEdge(this.getNode("P116"), this.getNode("Raum 135"), 0.1);
        this.addEdge(this.getNode("P117"), this.getNode("Raum 136"), 0.1);
        this.addEdge(this.getNode("P119"), this.getNode("Raum 137"), 0.1);
        this.addEdge(this.getNode("P119"), this.getNode("Raum 141"), 0.1);
        this.addEdge(this.getNode("P118"), this.getNode("Raum 142"), 0.1);
        this.addEdge(this.getNode("P120"), this.getNode("Raum 144"), 0.1);
        this.addEdge(this.getNode("P121"), this.getNode("Raum 145"), 0.1);
        this.addEdge(this.getNode("P125"), this.getNode("Raum 150"), 0.1);
        this.addEdge(this.getNode("P126"), this.getNode("Raum 151"), 0.1);
        this.addEdge(this.getNode("P126"), this.getNode("Raum 154"), 0.1);
        this.addEdge(this.getNode("P125"), this.getNode("Raum 155"), 0.1);
        this.addEdge(this.getNode("P124"), this.getNode("Raum 156"), 0.1);
        this.addEdge(this.getNode("P130"), this.getNode("Raum 166"), 0.1);
        this.addEdge(this.getNode("P131"), this.getNode("Raum 167"), 0.1);
        this.addEdge(this.getNode("P132"), this.getNode("Raum 168"), 0.1);
        this.addEdge(this.getNode("P133"), this.getNode("Raum 169"), 0.1);
        this.addEdge(this.getNode("P134"), this.getNode("Raum 170"), 0.1);
        this.addEdge(this.getNode("P135"), this.getNode("Raum 171"), 0.1);
        this.addEdge(this.getNode("P135"), this.getNode("Raum 174"), 0.1);
        this.addEdge(this.getNode("P133"), this.getNode("Raum 175"), 0.1);
        this.addEdge(this.getNode("P141"), this.getNode("Raum 184"), 0.1);
        this.addEdge(this.getNode("P142"), this.getNode("Raum 185"), 0.1);
        this.addEdge(this.getNode("P142"), this.getNode("Raum 188"), 0.1);
        this.addEdge(this.getNode("P141"), this.getNode("Raum 189"), 0.1);
        this.addEdge(this.getNode("P140"), this.getNode("Raum 190"), 0.1);
        this.addEdge(this.getNode("P138"), this.getNode("Raum 191"), 0.1);
        this.addEdge(this.getNode("P138"), this.getNode("Raum 192/193"), 0.1);
        
        // Second floor:
        
        this.addEdge(this.getNode("P209"), this.getNode("Raum 200"), 0.1);
        this.addEdge(this.getNode("P208"), this.getNode("Raum 203/204"), 0.1);
        this.addEdge(this.getNode("P206"), this.getNode("Raum 205"), 0.1);
        this.addEdge(this.getNode("P207"), this.getNode("Raum 206"), 0.1);
        this.addEdge(this.getNode("P204"), this.getNode("Raum 208"), 0.1);
        this.addEdge(this.getNode("P203"), this.getNode("Raum 209"), 0.1);
        this.addEdge(this.getNode("P202"), this.getNode("Raum 210"), 0.1);
        this.addEdge(this.getNode("P200"), this.getNode("Raum 212"), 0.1);
        this.addEdge(this.getNode("P247"), this.getNode("Raum 213"), 0.1);
        this.addEdge(this.getNode("P247"), this.getNode("Raum 215"), 0.1);
        this.addEdge(this.getNode("P201"), this.getNode("Raum 216"), 0.1);
        this.addEdge(this.getNode("P212"), this.getNode("Raum 217"), 0.1);
        this.addEdge(this.getNode("P213"), this.getNode("Raum 218"), 0.1);
        this.addEdge(this.getNode("P213"), this.getNode("Raum 219"), 0.1);
        this.addEdge(this.getNode("P213"), this.getNode("Raum 220"), 0.1);
        this.addEdge(this.getNode("P212"), this.getNode("Raum 222"), 0.1);
        this.addEdge(this.getNode("P211"), this.getNode("Raum 223"), 0.1);
        this.addEdge(this.getNode("P217"), this.getNode("Raum 227"), 0.1);
        this.addEdge(this.getNode("P218"), this.getNode("Raum 228"), 0.1);
        this.addEdge(this.getNode("P219"), this.getNode("Raum 229"), 0.1);
        this.addEdge(this.getNode("P220"), this.getNode("Raum 230"), 0.1);
        this.addEdge(this.getNode("P222"), this.getNode("Raum 231"), 0.1);
        this.addEdge(this.getNode("P222"), this.getNode("Raum 235"), 0.1);
        this.addEdge(this.getNode("P221"), this.getNode("Raum 236"), 0.1);
        this.addEdge(this.getNode("P223"), this.getNode("Raum 237"), 0.1);
        this.addEdge(this.getNode("P225"), this.getNode("Raum 238"), 0.1);
        this.addEdge(this.getNode("P223"), this.getNode("Raum 240"), 0.1);
        this.addEdge(this.getNode("P225"), this.getNode("Raum 241"), 0.1);
        this.addEdge(this.getNode("P227"), this.getNode("Raum 242"), 0.1);
        this.addEdge(this.getNode("P230"), this.getNode("Raum 250"), 0.1);
        this.addEdge(this.getNode("P231"), this.getNode("Raum 251"), 0.1);
        this.addEdge(this.getNode("P231"), this.getNode("Raum 253"), 0.1);
        this.addEdge(this.getNode("P230"), this.getNode("Raum 254"), 0.1);
        this.addEdge(this.getNode("P229"), this.getNode("Raum 255"), 0.1);
        this.addEdge(this.getNode("P241"), this.getNode("Raum 262"), 0.1);
        this.addEdge(this.getNode("P242"), this.getNode("Raum 263"), 0.1);
        this.addEdge(this.getNode("P243"), this.getNode("Raum 264"), 0.1);
        this.addEdge(this.getNode("P244"), this.getNode("Raum 266"), 0.1);
        this.addEdge(this.getNode("P246"), this.getNode("Raum 267"), 0.1);
        this.addEdge(this.getNode("P246"), this.getNode("Raum 269"), 0.1);
        this.addEdge(this.getNode("P244"), this.getNode("Raum 270"), 0.1);
        this.addEdge(this.getNode("P239"), this.getNode("Raum 283"), 0.1);
        this.addEdge(this.getNode("P240"), this.getNode("Raum 284"), 0.1);
        this.addEdge(this.getNode("P240"), this.getNode("Raum 286"), 0.1);
        this.addEdge(this.getNode("P239"), this.getNode("Raum 288"), 0.1);
        this.addEdge(this.getNode("P237"), this.getNode("Raum 289"), 0.1);
        this.addEdge(this.getNode("P236"), this.getNode("Raum 290"), 0.1);
        this.addEdge(this.getNode("P248"), this.getNode("Raum 293"), 0.1);
        
        // Third floor:
        
        this.addEdge(this.getNode("P301"), this.getNode("Raum 302"), 0.1);
        this.addEdge(this.getNode("P301"), this.getNode("Raum 303"), 0.1);
        this.addEdge(this.getNode("P305"), this.getNode("Raum 304"), 0.1);
        this.addEdge(this.getNode("P307"), this.getNode("Raum 305"), 0.1);
        this.addEdge(this.getNode("P309"), this.getNode("Raum 306"), 0.1);
        this.addEdge(this.getNode("P311"), this.getNode("Raum 307"), 0.1);
        this.addEdge(this.getNode("P313"), this.getNode("Raum 308"), 0.1);
        this.addEdge(this.getNode("P314"), this.getNode("Raum 309"), 0.1);
        this.addEdge(this.getNode("P310"), this.getNode("Raum 310"), 0.1);
        this.addEdge(this.getNode("P308"), this.getNode("Raum 311"), 0.1);
        this.addEdge(this.getNode("P306"), this.getNode("Raum 312"), 0.1);
        this.addEdge(this.getNode("P304"), this.getNode("Raum 313"), 0.1);
        this.addEdge(this.getNode("P303"), this.getNode("Raum 314"), 0.1);
    }
}
