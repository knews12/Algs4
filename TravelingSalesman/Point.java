public class Point
{
    public double x;
    public double y;
    
    Point(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    
    public String toString()
    {
        return String.valueOf(x) + ", " + String.valueOf(y);
    }
    
    public void draw()
    {
        StdDraw.setPenRadius(StdDraw.getPenRadius() * 5.0);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.point(x, y);
        StdDraw.setPenRadius(StdDraw.getPenRadius() / 5.0);
    }
    
    public void drawTo(Point b)
    {
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.line(x, y, b.x, b.y);
    }
    
    public double distance(Point b)
    {
        double base = Math.abs(x - b.x);
        double height = Math.abs(y - b.y);
        return Math.sqrt(base*base + height*height);
    }
}