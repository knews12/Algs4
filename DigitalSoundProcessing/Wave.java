import javazoom.jl.player.StdPlayer;

public class Wave 
{
    private static final int SampleRate = 44100;
    private double[] left, right;
    private static int animationDelay = 13;
    
    public Wave(double hz, double seconds, double amplitude)
    {
        left = new double[(int)(seconds * SampleRate)];
        right = left;
        
        for (int i = 0; i < left.length; i++)
        {
            double sample = amplitude * Math.sin( 2*Math.PI * hz * i/SampleRate);
            
            left[i] = inSampleRange(sample);
        }
    }
    
    public Wave(double[] left, double[] right)
    {
        this.left = left;
        this.right = right;
    }
    
    public Wave plus(Wave b)
    {
        double[] l, r;
        l = new double[left.length];
        r = l;
        
        for (int i = 0; i < left.length; i++)
        {
            double sample = left[i] + b.left[i];
            l[i] = inSampleRange(sample);
        }
        
        Wave combined = new Wave(l, r);
        
        return combined;
    }
    
    public void play()
    {
        StdPlayer.playWave(left, right);
    }
    
    public void draw()
    {
        draw(0);
    }
    
    public void draw(int delta)
    {
        StdDraw.clear();
        
        for (int i = 0; i < left.length-1; i++)
        {
            // Left Audio - Top of Window
            StdDraw.setPenColor(StdDraw.RED);
            //StdDraw.point((float)i / (float)left.length, (left[i] * 0.5 + 1) / 2 + 0.25);
            StdDraw.line((float)i / (float)left.length, (left[i] * 0.5 + 1) / 2 + 0.25, (float)(i+1) / (float)left.length, (left[i+1] * 0.5 + 1) / 2 + 0.25);
            
            // Right Audio - Bottom of Window
            StdDraw.setPenColor(StdDraw.BLUE);
            //StdDraw.point((float)i / (float)left.length, (right[i] * 0.5 + 1) / 2 - 0.25);
            StdDraw.line((float)i / (float)left.length, (right[i] * 0.5 + 1) / 2 - 0.25, (float)(i+1) / (float)left.length, (right[i+1] * 0.5 + 1) / 2 - 0.25);
        }
        
        animationDelay += delta;
        if(animationDelay < 0) animationDelay = 0;
        
        StdDraw.setFont();
        StdDraw.text(0.5, 0.5, "Delay: " + Integer.toString(animationDelay));
        
        StdDraw.show(animationDelay);
    }
    
    private double inSampleRange(double sample)
    {
        if (sample < -1.0) sample = -1.0;
        else if (sample > 1.0) sample = 1.0;
        
        return sample;
    }
    
    public static void main(String[] args)
    {
        Wave w = new Wave(10.0, 0.1, 0.8);
        w.draw();
    }
}
