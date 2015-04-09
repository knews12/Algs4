import javazoom.jl.player.StdPlayer;
import java.awt.event.KeyEvent;

public class Player
{
    static double amplitude = 0.5;
    
    public static void main(String[] args)
    {
        StdPlayer.open("felten.mp3");
        
        Wave[] waves = new Wave[10];
        for (int i = 0; i < waves.length; i++)
        {
            waves[i] = new Wave(StdPlayer.getLeftChannel(), StdPlayer.getRightChannel());
        }
        
        int playIndex = 0;
        int echoIndex = 1;
        StdDraw.show(0);
        while (!StdPlayer.isEmpty())
        {
            int deltaAnimationDelay = 0;
            if (StdDraw.isKeyPressed(KeyEvent.VK_UP)) deltaAnimationDelay = 1;
            if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN)) deltaAnimationDelay = -1;
            
            Wave w = waves[playIndex];//.plus(waves[echoIndex]);
            w.play();
            w.draw(deltaAnimationDelay);
                
            echoIndex++;
            if (echoIndex >= waves.length) echoIndex = 0;
            
            playIndex = echoIndex - 1;
            if (playIndex < 0) playIndex = waves.length - 1;
                
            waves[playIndex] = new Wave(StdPlayer.getLeftChannel(), StdPlayer.getRightChannel());
        }
        
        //StdPlayer.open();
        //playFurElise();
        //playStairwayToHeaven();
        
        StdPlayer.close();
        //System.exit(0);
    }
    
    private static void playFurElise()
    {
        // Setup the notes - multiply freq by 2 to raise an octave
        Wave E  = new Wave(659.25 * 2, 0.3, amplitude);
        Wave DS = new Wave(622.25 * 2, 0.3, amplitude);
        Wave B  = new Wave(493.88 * 2, 0.3, amplitude);
        Wave D  = new Wave(587.33 * 2, 0.3, amplitude);
        Wave C  = new Wave(523.25 * 2, 0.3, amplitude);
        Wave A  = new Wave(440.00 * 2, 0.9, amplitude);

        // e, d#, e, d#, e, b, d, c, a
        E.play();
        DS.play(); 
        E.play(); 
        DS.play(); DS.draw(); StdDraw.clear(StdDraw.WHITE);
        E.play(); E.draw(); StdDraw.clear(StdDraw.WHITE);
        B.play(); B.draw(); StdDraw.clear(StdDraw.WHITE);
        D.play(); D.draw(); StdDraw.clear(StdDraw.WHITE);
        C.play(); C.draw(); StdDraw.clear(StdDraw.WHITE);
        A.play(); A.draw();
    }
    
    private static void playStairwayToHeaven()
    {
        // Create the notes
        // A "delay", i.e., a wave without sound
        Wave delay = new Wave(0.0, 0.4, amplitude);

        Wave E3   = new Wave(659.26 / 4, 0.4, amplitude);
        Wave B4   = new Wave(493.88 / 2, 0.4, amplitude);
        Wave C4   = new Wave(523.25 / 2, 0.4, amplitude);
        Wave E4   = new Wave(659.26 / 2, 0.4, amplitude);
        Wave Gb4  = new Wave(739.99 / 2, 0.4, amplitude);
        Wave G4   = new Wave(783.99 / 2, 0.4, amplitude);
        Wave Gs4  = new Wave(830.61 / 2, 0.4, amplitude);
        Wave A5   = new Wave(440.00 / 1, 0.4, amplitude);
        Wave B5   = new Wave(493.88 / 1, 0.4, amplitude);
        Wave C5   = new Wave(523.25 / 1, 0.4, amplitude);
        Wave C5x2 = new Wave(523.25 / 1, 0.8, amplitude);
        Wave D5   = new Wave(587.33 / 1, 0.4, amplitude);
        Wave E5   = new Wave(659.26 / 1, 0.4, amplitude);
        Wave Gb5  = new Wave(739.99 / 1, 0.4, amplitude);
        Wave A6   = new Wave(440.00 * 2, 0.4, amplitude);
        Wave B6   = new Wave(493.88 * 2, 0.4, amplitude);
        Wave C6   = new Wave(523.25 * 2, 0.4, amplitude);

        // create the combo-notes
        Wave GsB = B6.plus(Gs4);
        Wave GC  = G4.plus(C6);
        Wave GbG = Gb4.plus(Gb5);
        Wave EE  = E4.plus(E5);
        Wave GB  = G4.plus(B5);
        Wave AC  = A5.plus(C5);
        Wave AClong = new Wave(440.0, .8, amplitude).plus(new Wave(523.3, .8, amplitude));

        // play twice
        for (int i = 0; i < 2; i++) {
            A5.play();
            C5.play();
            E5.play();
            A6.play();

            GsB.play();
            E5.play();
            C5.play();
            B6.play();

            GC.play();
            E5.play();
            C5.play();
            C6.play();

            GbG.play();
            D5.play();
            A5.play();
            Gb5.play();

            EE.play();
            C5.play();
            A5.play();
            C5x2.play();

            E5.play();
            C5.play();
            A5.play();

            GB.play();
            AC.play();
            AClong.play();

            delay.play();
            E3.play();
            C4.play();
            B4.play();
        }
    }
}