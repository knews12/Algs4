public class Tour
{
    private Node first;
    private Node current;
    
    Tour()
    {
        
    }
    
    Tour(Point a, Point b, Point c, Point d)
    {
        insertNearest(a);
        insertNearest(b);
        insertNearest(c);
        insertNearest(d);
    }
    
    public void show()
    {
        Node index = first;
        while(index != null) {
            System.out.println(index.p.toString());
            index = index.next;
        }
    }
    
    public void draw()
    {
        Node index = first;
        while(index != null) {
            if(index.next != null) {
                index.p.drawTo(index.next.p);
            } 
            else {
                index.p.drawTo(first.p);
            }
            
            index.p.draw();
            index = index.next;
        }
        
        StdDraw.text(200, 0, "Total Distance = " + String.valueOf(distance()));
        StdDraw.show(0);
    }
    
    public double distance()
    {
        double distance = 0.0;
        Node index = first;
        Node last = index;
        while(index != null) {
            if (index.next != null) {
                distance += index.p.distance(index.next.p);
            }
            
            if (index.next != null) last = index.next;
            
            index = index.next;
        }
        
        distance += first.p.distance(last.p);
        
        return distance;
    }
    
    public void insertSmallest(Point p)
    {
        Node next = new Node(p);
        
        if (first == null){
            first = next;
            return;
        }
        
        Node index = first;
        double minDist = 0.0;
        Node smallest = index;
        double dist = distance();
        while(index != null) {
            if(index != next) {
                double distToNext = index.p.distance(next.p);
                double distFromNext = 0.0;
                if (index.next != null) 
                    distFromNext = next.p.distance(index.next.p);
                
                double additionalDist = dist + distToNext + distFromNext;
                double newDist = dist + additionalDist;
                if (newDist < minDist || minDist == 0.0) {
                    smallest = index;
                    minDist = newDist;
                }
            }
            
            index = index.next;
        }
        
        if (smallest != null) {
            insertNode(next, smallest);
        }
    }
    
    public void insertNearest(Point p)
    {
        Node next = new Node(p);
        
        Node index = first;
        double closestDist = 0.0;
        Node closestNode = index;
        
        while(index != null) {
            double dist = index.p.distance(next.p);
            if (dist < closestDist || closestDist == 0.0) {
                closestNode = index;
                closestDist = dist;
            }
            
            index = index.next;
        }
        
        if (closestNode != null) {
            insertNode(next, closestNode);
        }
        else {
            first = next;
        }
    }
    
    private void removeNode(Node n)
    {
        // n is first
        if (n == first && n.next != null) {
            first = n.next;
            return; // nothing else to due
        }
        
        
        Node before = null;
        Node index = first;
        while(index != null) {
            if (index.next == n) {
                before = index;
                break;
            }
            index = index.next;
        }
        
        Node after = n.next;
        if (before != null) before.next = after;
    }
    
    private void insertNode(Node middle, Node start)
    {
        Node end = start.next;
        start.next = middle;
        middle.next = end;
    }
    
    private class Node
    {
        private Point p;
        private Node next;
        
        Node(Point p)
        {
            this.p = p;
            this.next = null;
        }
    }
    
    public static void main(String[] args)
    {
        int w = StdIn.readInt();
        int h = StdIn.readInt();
        StdDraw.setCanvasSize(w, h);
        StdDraw.setXscale(0, w);
        StdDraw.setYscale(0, h);

        // turn on animation mode
        StdDraw.show(0);
        
        Tour tour = new Tour();
        
        while(!StdIn.isEmpty())
        {
            double x = StdIn.readDouble();
            double y = StdIn.readDouble();
            Point p = new Point(x, y);
            
            tour.insertSmallest(p);
            
            StdDraw.clear();
            tour.draw();
            StdDraw.show(50);
        }
        
        tour.draw();
        StdDraw.show(0);
        
        System.out.println("Distance = " + String.valueOf(tour.distance()));
        tour.draw();
    }
}