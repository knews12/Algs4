import java.lang.Math;

/**
 * Auto Generated Java Class.
 */
public class Board 
{
    private Point blankPos;
    private int[][] blocks;
    
    // Construct a new Baord from a N-by-N array of blocks
    // where blocks[i][j] = block in row i, column j
    public Board(int[][] blocks) 
    { 
        this.blocks = blocks;
        
        // Find the position of the blank block
        for (int row = 0; row < dimension(); row++)
        {
            for (int col = 0; col < dimension(); col++)
            {
                if (blocks[row][col] == 0)
                {
                    blankPos = new Point(row, col);
                    return;
                }
            }
        }
    }
    
    // Board dimension N
    public int dimension()
    {
        return blocks[0].length;
    }
    
    // Number of blocks out of place
    public int hamming()
    {
        int hamming = 0;
        
        for (int row = 0; row < dimension(); row++)
        {
            for (int col = 0; col < dimension(); col++)
            {
                int expectedBlockValue = (row + 1) + col * dimension();
                
                if (blocks[row][col] != expectedBlockValue && blocks[row][col] != 0)
                    hamming++;
            }
        }
        
        return hamming;
    }
    
    // Sum of Manhattan distances between blocks and goal
    public int manhattan()
    {
        int manhattan = 0;
        
        for (int row = 0; row < dimension(); row++)
        {
            for (int col = 0; col < dimension(); col++)
            {
                if (blocks[row][col] != 0)
                {
                    manhattan += calcManhattan(row, col);
                }
            }
        }
        
        return manhattan;
    }
    
    // Is this Board the goal Board?
    public boolean isGoal()
    {
        //System.out.println("Goal:");
        
        for (int row = 0; row < dimension(); row++)
        {
            for (int col = 0; col < dimension(); col++)
            {
                int expectedBlockValue = (col + 1) + row * dimension();
                
                if ( row == dimension()-1 && col == dimension()-1 ) 
                    expectedBlockValue = 0;
                
                //System.out.print(expectedBlockValue + " ");
                
                if ( blocks[row][col] != expectedBlockValue)
                {
                    //System.out.println();
                    return false;
                }
            }
            
            //System.out.print("\n");
        }
        
        return true;
    }
    
    // A Board obtained by exchanging two adjacent blocks in the same row
    public Board twin()
    {
        int[][] copyOfBlocks = copyOfBlocks();
        Board twin = new Board(copyOfBlocks);
        
        // Swap two adjacent non blank blocks in the same row
        for (int row = 0; row < dimension(); row++)
        {
            for (int col = 0; col < dimension(); col++)
            {
                if ( col == dimension() - 1 )
                    continue;
                
                if ( copyOfBlocks[row][col] != 0 && copyOfBlocks[row][col+1] != 0)
                {
                    twin.swap(new Point(row, col), new Point(row, col+1));
                    return twin;
                }
            }
        }
        
        return null;
    }
    
    // Does this Board equal y?
    public boolean equals(Object y)
    {
        Board by = (Board)y;
        
        for (int row = 0; row < dimension(); row++)
        {
            for (int col = 0; col < dimension(); col++)
            {
                if (blocks[row][col] != by.blocks[row][col])
                    return false;
            }
        }
        
        return true;
    }
    
    // All neighboring Boards
    public Iterable<Board> neighbors()
    {
        Bag<Board> neighbors = new Bag<Board>();
        
        // Top
        if (blankPos.row > 0)
        {
            Board topBoard = new Board(copyOfBlocks());
            topBoard.swap(new Point(blankPos.row, blankPos.col), new Point(blankPos.row - 1, blankPos.col));
            neighbors.add(topBoard);
        }
        
        // Botton
        if (blankPos.row < dimension() - 1)
        {
            Board bottomBoard = new Board(copyOfBlocks());
            bottomBoard.swap(new Point(blankPos.row, blankPos.col), new Point(blankPos.row + 1, blankPos.col));
            neighbors.add(bottomBoard);
        }
        
        // Right
        if (blankPos.col < dimension() - 1)
        {
            Board rightBoard = new Board(copyOfBlocks());
            rightBoard.swap(new Point(blankPos.row, blankPos.col), new Point(blankPos.row, blankPos.col + 1));
            neighbors.add(rightBoard);
        }
        
        // Left
        if (blankPos.col > 0)
        {
            Board leftBoard = new Board(copyOfBlocks());
            leftBoard.swap(new Point(blankPos.row, blankPos.col), new Point(blankPos.row, blankPos.col - 1));
            neighbors.add(leftBoard);
        }
        
        return neighbors;
    }
    
    // String representation of the Board
    public String toString()
    {
        String board = "";
        
        for (int row = 0; row < dimension(); row++)
        {
            for (int col = 0; col < dimension(); col++)
            {
                board += blocks[row][col] + " ";
            }
            board += "\n";
        }
        
        // += "(blank at " + blankPos.row + ", " + blankPos.col + ")";
        
        return board;
    }
    
    
    private int calcManhattan(int row, int col)
    {
        int value = blocks[row][col];
        
        int expectedCol = value % dimension() - 1;
        if (expectedCol < 0) expectedCol = dimension() - 1;
        
        int expectedRow = Math.max((value - expectedCol - 1) / dimension(), 0);
        
        int xOffset = Math.abs(expectedRow - row);
        int yOffset = Math.abs(expectedCol - col);
        
        int manhattan = xOffset + yOffset;
        
        return manhattan;
    }
    
    private void swap(Point b1, Point b2)
    {
        int temp = blocks[b1.row][b1.col];
        blocks[b1.row][b1.col] = blocks[b2.row][b2.col];
        blocks[b2.row][b2.col] = temp;
        
        // Update blank position
        if ( blocks[b1.row][b1.col] == 0 )
        {
            blankPos = new Point(b1.row, b1.col);
        }
        else if ( blocks[b2.row][b2.col] == 0 )
        {
            blankPos = new Point(b2.row, b2.col);
        }
    }
    
    private int[][] copyOfBlocks()
    {
        int[][] copy = new int[dimension()][dimension()];
        
        for (int row = 0; row < dimension(); row++)
        {
            for (int col = 0; col < dimension(); col++)
            {
                copy[row][col] = blocks[row][col];
            }
        }
        
        return copy;
    }
    
    private class Point
    {
        public int row;
        public int col;
        
        public Point(int r, int c)
        {
            row = r;
            col = c;
        }
        
        public String toString()
        {
            return "(" + row + ", " + col+ ")";
        }
    }
    
    public static void main(String[] args)
    {
        int[][] blocks = {
            {1,2,3},
            {4,5,6},
            {7,8,0}
        };
        
        Board b = new Board(blocks);
        //b.isGoal();
        //System.out.println(b.isGoal());
        
        //System.out.println(b.dimension());
        System.out.println(b.toString());
        
        //System.out.println( b.calcManhattan(0,0) );
        //System.out.println( b.calcManhattan(0,1) );
        
        for (Board n : b.neighbors())
        {
            System.out.println(n.toString());
            System.out.println();
        }
        
        Board twin = b.twin();
        
        System.out.println("\n TWIN: \n" + twin.toString());
    }
}
