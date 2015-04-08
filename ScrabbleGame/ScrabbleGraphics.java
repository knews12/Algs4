import java.awt.Color;
import java.awt.event.KeyEvent;

public class ScrabbleGraphics
{
    private static final char NULL_CHAR = '\u0000';
    private static double tileSize = 3.3;
    private static Point2D selectedTile;
    
    public ScrabbleGraphics()
    {
        StdDraw.setScale(0.0, 100.0);
    }
    
    public static void drawBoard(Board board)
    {
        Tile[][] tiles = board.getTiles();
        double ratio = (100.0/15.0);
        
        for(int row = 0; row < 15; row++)
        {
            for(int col = 0; col < 15; col++)
            {
                double x = row * ratio + tileSize;
                double y = col * ratio + tileSize;
                drawTile(col+1, row+1, tiles[row][col].getLetter(), StdDraw.BOOK_RED);
            }
        }
    }
    
    private static void drawTile(int x, int y, char character, Color bg)
    {
        double tileXLoc = convertTileLocXToCenterLoc(x);
        double tileYLoc = convertTileLocYToCenterLoc(y);
        
        StdDraw.setPenColor(bg);
        StdDraw.filledRectangle(tileXLoc, tileYLoc, tileSize, tileSize);
        
        if (character != NULL_CHAR)
        {
            StdDraw.setPenColor(StdDraw.GRAY);
            StdDraw.filledRectangle(tileXLoc, tileYLoc, tileSize - 0.5, tileSize - 0.5);
            
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.text(tileXLoc, tileYLoc, Character.toString(character));
        }
    }
    
    private static int convertToTileLoc(double location)
    {
        int val = 0;
        val = (int)( location / (100.0/15.0));
        val++;
        
        if (val > 15) val = 15;
        if (val < 1) val = 1;
        
        return val;
    }
    
    private static double convertTileLocXToCenterLoc(int tileLoc)
    {
        return (100.0 / 15.0) * (tileLoc - 0.5);
    }
    
    private static double convertTileLocYToCenterLoc(int tileLoc)
    {
        return 100 - ((100.0 / 15.0) * (tileLoc - 0.5));
    }
    
    private static void listenForMousePressed(IHandleMouseEvent handler)
    {
        while(true)
        {
            if(StdDraw.mousePressed())
            {
                handler.handle(StdDraw.mouseX(), StdDraw.mouseY());
            }
            
            try 
            {
                Thread.sleep(100);
            } 
            catch(InterruptedException ex) 
            {
                Thread.currentThread().interrupt();
            }
            
        }
    }
    
    private static void listenForKeyPressed(IHandleKeyPressedEvent handler)
    {
        while(true)
        {
            int key = KeyEvent.VK_A;
            if(StdDraw.isKeyPressed(key) == true)
            {
                handler.handle(key);
            }
            
            try 
            {
                Thread.sleep(100);
            } 
            catch(InterruptedException ex) 
            {
                Thread.currentThread().interrupt();
            }
            
        }
    }
    
    public static void main(String args[])
    {
        StdDraw.setScale(0.0, 100.0);
        
        Board b = new Board(15);
        b.addWord("hello world", 0, 0, BoardDirection.Right);
        
        drawBoard(b);
        
        selectedTile = new Point2D(0.0, 0.0);
        
//        listenForKeyPressed(new IHandleKeyPressedEvent(){
//            public void handle(int key)
//            {
//                StdOut.println("key: " + key);
//            }
//        });
        
        IHandleMouseEvent handler = new IHandleMouseEvent(){
            public void handle(double x, double y)
            {
                StdOut.println(x + ", " + y);
                
                int tileX = convertToTileLoc(x);
                int tileY = convertToTileLoc(y);
                
                StdOut.println(tileX + ", " + tileY);
                
                drawTile((int)selectedTile.x(), (int)selectedTile.y(), '*', StdDraw.YELLOW);
                
                selectedTile = new Point2D((double)tileX, (double)tileY);
                
                //StdDraw.setPenColor(StdDraw.YELLOW);
                //StdDraw.filledRectangle( convertTileLocToCenterLoc(tileX), convertTileLocToCenterLoc(tileY), tileSize, tileSize );
            }
        };
        
        listenForMousePressed(handler);
    }
}