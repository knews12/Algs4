public class Particle
{
    double INFINITY = 99.0;
    
    public double px, py;  // position
    private double vx, vy;  // velocity
    private final double r; // radius
    private final double m; // mass
    public int count;      // number of collisions 
    
    public Particle(double posx, double posy, double velx, double vely, double radius, double mass)
    {
        px = posx;
        py = posy;
        vx = velx;
        vy = vely;
        r = radius;
        m = mass;
    }
    
    public void move(double dt)
    {
        //if ( px > 1.0 || px < 0.0 ) vx = -vx;
        //if ( py > 1.0 || py < 0.0 ) vy = -vy;
        
        px = px + vx*dt;
        py = py + vy*dt;
    }
    
    public void draw()
    {
        StdDraw.filledCircle(px, py, r);
    }
    
    // predict
    
    public double timeToHit(Particle target) 
    {
        if ( this == target ) return INFINITY;
        
        double dpx = this.px - target.px, dpy = this.py - target.py;
        double dvx = this.vx - target.vx, dvy = this.vy - target.vy;
        double dvdp = dpx*dvx + dpy*dvy;
        
        if ( dvdp > 0 ) return INFINITY;
        
        double dvdv = dvx*dvx + dvy*dvy;
        double dpdp = dpx*dpx + dpy*dpy;
        
        double sigma = this.r + target.r;
        
        double d = (dvdp*dvdp) - dvdv * (dpdp - sigma*sigma);
        
        if ( d < 0 ) return INFINITY;
        
        return -(dvdp + Math.sqrt(d)) / dvdv;
    }
    
    public double timeToHitVerticalWall() 
    {
        if ( vx == 0.0 ) return INFINITY;
        
        double wall, velocity;
        if (vx > 0.0)
        {
            wall = 1.0;
            velocity =  vx;
        }
        else 
        {
            wall = 0.0;
            velocity = vx * -1.0;
        }
        
        double distance = (wall > px) ? wall-px : px-wall;
        
        return distance / velocity;
    }
    
    public double timeToHitHorizontalWall() 
    {
        if ( vy == 0.0 ) return INFINITY;
        
        double wall, velocity;
        if (vy > 0.0)
        {
            wall = 1.0;
            velocity =  vy;
        }
        else 
        {
            wall = 0.0;
            velocity = vy * -1.0;
        }
        
        double distance = (wall > py) ? wall-py : py-wall;
        
        return distance / velocity;
    }
    
    
    // resolve
    
    public void bounceOff(Particle target) 
    {
        double dpx = target.px - this.px, dpy = target.py - this.py;
        double dvx = target.vx - this.vx, dvy = target.vy - this.vy;
        double dvdp = dpx*dvx + dpy*dvy;
        double dist = this.r + target.r;
        
        double J = (2 * this.m * target.m * dvdp ) / ((this.m + target.m) * dist);
        double Jx = J * dpx / dist;
        double Jy = J * dpy / dist;
        
        this.vx += Jx / this.m;
        this.vy += Jy / this.m;
        target.vx -= Jx / target.m;
        target.vy -= Jy / target.m;
        
        this.count++;
        target.count++;
    }
    
    public void bounceOffVerticalWall() 
    {
        vx = -vx;
        count++;
    }
    
    public void bounceOffHorizontalWall() 
    {
        vy = -vy;
        count++;
    }
    
    public static void main(String[] args)
    {
        Particle a = new Particle(0.0, 0.5, 0.0, -0.1, 0.01, 1.0);
        Particle b = new Particle(0.0, 0.0, 0.0, 0.1, 0.01, 1.0);
        
        double timeToHit = a.timeToHit(b);
        double timeAToHitVWall = a.timeToHitVerticalWall();
        double timeAToHitHWall = a.timeToHitHorizontalWall();
        
        System.out.println(timeToHit);
        //System.out.println(timeAToHitVWall);
        //System.out.println(timeAToHitHWall);
        
        a.move(timeToHit);
        b.move(timeToHit);
        
        a.bounceOff(b);
        
        System.out.println(a.vy);
        System.out.println(b.vy);
    }
}