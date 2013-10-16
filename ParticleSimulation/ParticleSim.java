public class ParticleSim
{
    final double BORDER = 0.05;
    private Particle[] particles;
    private MinPQ pq;
    private double time = 0.0;
    
    public static void main(String[] args)
    {
        final int numParticles = 20;
        
         // init the particles
        Particle[] parts = new Particle[numParticles];
        for(int i = 0; i < numParticles; i++)
        {
            parts[i] = new Particle(
                             StdRandom.uniform(0.2, 0.8), 
                             StdRandom.uniform(0.2, 0.8), 
                             StdRandom.uniform(-0.1, 0.1), 
                             StdRandom.uniform(-0.1, 0.1),
                             StdRandom.uniform(0.01, 0.1),
                             StdRandom.uniform(0.01, 0.1)
                            );
        }
        
        ParticleSim sim = new ParticleSim(parts);
        sim.simulate();
    }
    
    public ParticleSim(Particle[] particles)
    {
        this.particles = particles;
    }
    
    private void predict(Particle a)
    {
        if ( a == null ) return;
        
        for(int i=0; i < particles.length; i++)
        {
            double dt = a.timeToHit(particles[i]);
            pq.insert(new Event(time + dt, a, particles[i]));
        }
        
        pq.insert( new Event(time + a.timeToHitVerticalWall(), a , null) );
        pq.insert( new Event(time + a.timeToHitHorizontalWall(), null , a) );
    }
    
    public void simulate()
    {
        pq = new MinPQ<Event>();
        
        for(int i=0; i < particles.length; i++) 
            predict(particles[i]);
        pq.insert( new Event(0.0, null, null) );
        
        while ( !pq.isEmpty() )
        {
            Event event = (Event)pq.delMin();
            if ( !event.isValid() ) continue;
            
            Particle a = event.a;
            Particle b = event.b;
            
            for(int i=0; i < particles.length; i++)
            {
                particles[i].move(event.time - time);
            }
                
            time = event.time;
            
            if ( a != null && b != null ) a.bounceOff(b);
            else if ( a != null && b == null ) a.bounceOffVerticalWall();
            else if ( a == null && b != null ) b.bounceOffHorizontalWall();
            else if ( a == null && b == null )
            {
                redraw();
                pq.insert( new Event(time + 0.1, null, null) );
            }
            
            predict(a);
            predict(b);
        }
    }
    
    private void redraw()
    {
        StdDraw.clear();
        
        for(Particle p : particles)
        {
            p.draw();
        }
            
        StdDraw.show(33); // needed for better animation
    }
}