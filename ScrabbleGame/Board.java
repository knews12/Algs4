/**
 * Auto Generated Java Class.
 */
public class Board 
{
    private Tile[][] tiles;
    private int size;
    
    public Board(int size)
    {
        tiles = new Tile[size][size];
        this.size = size;
        clearBoard();
    }
    
    public void addWord(String word, int x, int y, BoardDirection direction)
    {
        // check if valid to add word
        if ( x < 0 || x >= size || 
             y < 0 || y >= size )
        {
            return;
        }
        
        if (direction == BoardDirection.Down)
        {
            for(int i = 0; i < word.length(); i++)
            {
                if (x + i >= size) break;
                tiles[x + i][y].setLetter(word.charAt(i));
            }
        }
        else if (direction == BoardDirection.Right)
        {
            for(int i = 0; i < word.length(); i++)
            {
                if (y + i >= size) break;
                tiles[x][y + i].setLetter(word.charAt(i));
            }
        }
    }
    
    public Tile[][] getTiles()
    {
        return tiles;
    }
    
    public void print()
    {
        for(int i = 0; i < tiles[0].length; i++)
        {
            for(int j = 0; j < tiles[0].length; j++)
            {
                System.out.print(tiles[i][j].getLetter() + " ");
            }
            
            System.out.println();
        }
    }
    
    private void clearBoard()
    {
        for(int i = 0; i < tiles[0].length; i++)
        {
            for(int j = 0; j < tiles[0].length; j++)
            {
                tiles[i][j] = new Tile(0, '_');
            }
        }
    }
    
    public static void main(String[] args)
    {
        Board b = new Board(5);
        b.print();
        
        b.addWord("abc", 0, 0, BoardDirection.Down);
        System.out.println();
        b.print();
        
        b.addWord("def", 0, 0, BoardDirection.Right);
        System.out.println();
        b.print();
        
        b.addWord("ghijklm", 0, 0, BoardDirection.Down);
        System.out.println();
        b.print();
        
        b.addWord("nopqrstuv", 0, 0, BoardDirection.Right);
        System.out.println();
        b.print();
        
        b.addWord("w", 5, 5, BoardDirection.Down);
        System.out.println();
        b.print();
        
        b.addWord("xyz", 4, 4, BoardDirection.Down);
        System.out.println();
        b.print();
        
        b.addWord("123", 3, 4, BoardDirection.Right);
        System.out.println();
        b.print();
    }
}