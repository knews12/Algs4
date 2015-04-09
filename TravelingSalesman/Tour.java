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
        
        Node index = first;
        double minDist = 0.0;
        Node minNode = index;
        while(index != null) {
            if(index == next) continue;
            
            insertNode(next, index);
            double dist = distance();
            
            if (dist < minDist || minDist == 0.0) {
                minNode = index;
                minDist = dist;
            }
            //removeNode(next);
            
            index = index.next;
        }
        
        removeNode(next);
        
        if (minNode != null) {
            insertNode(next, minNode);
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
        if (n.next != null) {
            Node orphan = n.next;
            Node index = first;
            while(index != null) {
                if (index.next == null) break;
                index = index.next;
            }
            index.next = orphan;
            n.next = null;
        }
    }
    
    private void insertNode(Node middle, Node start)
    {
        if (middle.next != null) {
            Node orphan = middle.next;
            Node index = first;
            while(index != null) {
                if (index.next == null) break;
                index = index.next;
            }
            index.next = orphan;
        }
        
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
            
            //StdDraw.clear();
            //tour.draw();
            //StdDraw.show(50);
        }
        
        tour.draw();
        StdDraw.show(0);
        
        System.out.println("Distance = " + String.valueOf(tour.distance()));
        tour.draw();
    }
}