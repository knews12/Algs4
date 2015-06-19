public class NearestTour 
{
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
            
            tour.insertNearest(p);
            
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
