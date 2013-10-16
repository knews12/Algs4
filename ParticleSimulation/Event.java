public class Event implements Comparable<Event>
{
    double INFINITY = 99.0;
    
    public double time;
    public Particle a;
    public Particle b;
    private int countA, countB;
    
    public Event(double time, Particle A, Particle B)
    {
        this.time = time;
        a = A;
        b = B;
        
        if ( a != null ) countA = a.count;
        if ( b != null ) countB = b.count;
    }
    
    public int compareTo(Event that)
    {
        return (int)(1000 * this.time - 1000 * that.time);
    }
    
    public boolean isValid()
    {
        if ( time == INFINITY ) return false;
        else if ( a == null && b == null ) return true;
        else if ( a == null && b != null ) return b.count == countB; 
        else if ( a != null && b == null ) return a.count == countA;
        else return (a.count == countA && b.count == countB);
    }
    
    public static void main(String[] args)
    {
        Particle a = new Particle(0.0, 0.0, 1.0, 0.0, 1.0, 1.0);
        Particle b = new Particle(0.0, 0.0, 1.0, 1.0, 1.0, 1.0);
        Event e1 = new Event(1.0, a, b);
        Event e2 = new Event(1.1, a , null);
        
        System.out.println(e1.isValid());
        System.out.println(e2.isValid());
        System.out.println(e2.time);
        
        a.bounceOff(b);
        
        System.out.println(e1.isValid());
        System.out.println(e2.isValid());
    }
}
