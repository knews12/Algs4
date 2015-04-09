class NBody
{
    private static final double G = 6.67e-11;
    private static double radius = 2.50e+11;
    private static double T = 157788000.0;
    private static double dt = 25000.0;
    private static int numberOfParticles = 5;
    
    public static void main(String[] args)
    {   
        // Init
        //T = Integer.parseInt(args[0]);
        //dt = Integer.parseInt(args[1]);
        
        numberOfParticles = StdIn.readInt();
        radius = StdIn.readDouble();
        
        System.out.println(numberOfParticles);
        System.out.println(radius);
        
        //StdDraw.setXscale(-1*radius, radius);
        //StdDraw.setYscale(-1*radius, radius);
        
//        Particle[] particles = new Particle[numberOfParticles];
        
//        for (int i = 0; i < particles.length; i++)
//        {
//            String line = StdIn.readLine();
//            System.out.println(line);
//            String[] params = line.split(",");
//            
////            particles[i] = new Particle();
////            Particle p = particles[i];
////            p.r.x = Double.parseDouble(params[0]);
////            p.r.y = Double.parseDouble(params[1]);
////            p.v.x = Double.parseDouble(params[2]);
////            p.v.y = Double.parseDouble(params[3]);
////            p.m = Double.parseDouble(params[4]);
//        }
        
//        particles[0].r.x = 1.4925e+11;
//        particles[0].r.y = -1.0467e+10;
//        particles[0].v.x = 2.0872e+03;
//        particles[0].v.y = 2.9723e+04;
//        particles[0].m = 5.9740e+24;
//        
//        particles[1].r.x = -1.1055e+11;
//        particles[1].r.y = -1.9868e+11;
//        particles[1].v.x = 2.1060e+04;
//        particles[1].v.y = -1.1827e+04;
//        particles[1].m = 6.4190e+23;
//        
//        particles[2].r.x = -1.1708e+10;
//        particles[2].r.y = -5.7384e+10;
//        particles[2].v.x = 4.6276e+04;
//        particles[2].v.y = -9.9541e+03;
//        particles[2].m = 3.3020e+23;
//        
//        particles[3].r.x = 2.1709e+05;
//        particles[3].r.y = 3.0029e+07;
//        particles[3].v.x = 4.5087e-02;
//        particles[3].v.y = 5.1823e-02;
//        particles[3].m = 2.9890e+31;
//        
//        particles[4].r.x = 6.9283e+10;
//        particles[4].r.y = 8.2658e+10;
//        particles[4].v.x = -2.6894e+04;
//        particles[4].v.y = 2.2585e+04;
//        particles[4].m = 4.8690e+24;
        
        // Run
//        for (double t = 0.0; t < T; t += dt)
//        {
//            update(particles, dt);
//            draw(particles, t);
//        }
    }
    
    private static void update(Particle[] particles, double dt)
    {
        for (int i = 0; i < particles.length; i++)
        {
            Particle a = particles[i];
            
            // Calculate force
            Vector force = new Vector(0.0, 0.0);
            for (int j = 0; j < particles.length; j++)
            {
                if (i == j) continue;
                
                Particle b = particles[j];
                
                Vector a_adjR = new Vector(a.r.x + radius, a.r.y + radius);
                Vector b_adjR = new Vector(b.r.x + radius, b.r.y + radius);
                
                Vector dr = new Vector();
                dr.x = a_adjR.x - b_adjR.x;
                dr.y = a_adjR.y - b_adjR.y;
                
                double distance = Math.sqrt(dr.x*dr.x + dr.y*dr.y);
                
                if (distance > 0.0)
                {
                    double f = (G * a.m * b.m) / (distance * distance);
                    
                    double direction = (dr.x > 0) ? -1 : 1;
                    force.x += f * Math.cos(dr.x / distance) * direction;
                    
                    direction = (dr.y > 0) ? -1 : 1;
                    force.y += f * Math.cos(dr.y / distance) * direction;
                }
            }
            
           //force.x = force.y = 0.0;
            
            // Calculate acceleration
            Vector accel = new Vector(force.x / a.m, force.y / a.m);
            
            // Calculate Velocity
            a.v.x = a.v.x + accel.x * dt;
            a.v.y = a.v.y + accel.y * dt;
            
            // Calculate Position
            a.r.x = a.r.x + a.v.x * dt;
            a.r.y = a.r.y + a.v.y * dt;
        }
    }
    
    private static void draw(Particle[] particles, double t)
    {
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        
        for (int i = 0; i < particles.length; i++)
        {
            Particle p = particles[i];
            StdDraw.filledCircle(p.r.x, p.r.y, radius / 50.0);
        }
        
        StdDraw.text(-1 * radius * 0.9, radius, "t: " + t);
        StdDraw.show(10);
    }
}

class Particle
{
    public double m;
    public Vector r,v;
    public String image;
    
    public Particle()
    {
        m = 0.1;
        r = new Vector(0.0, 0.0);
        v = new Vector(0.0, 0.0);
        image = "";
    }
}

class Vector
{
    public double x,y;
    
    public Vector() 
    {}
    
    public Vector(double X, double Y)
    {
        x = X;
        y = Y;
    }
}