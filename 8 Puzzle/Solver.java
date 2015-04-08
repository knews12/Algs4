public class Solver
{
    private SearchNode mainCurrentNode;
    private boolean solvable = true;
    
    public Solver(Board initial)
    {
        MinPQ<SearchNode> mainPQ = new MinPQ<SearchNode>();
        SearchNode sn = new SearchNode(initial);
        mainPQ.insert(sn);
        
        MinPQ<SearchNode> altPQ = new MinPQ<SearchNode>();
        SearchNode altSN = new SearchNode(initial.twin());
        altPQ.insert(altSN);
        SearchNode altCurrentNode = null;
        
        while (true)
        {
            mainCurrentNode = mainPQ.delMin();
            altCurrentNode = altPQ.delMin();
            
            //System.out.println(currentNode.board.toString());
            
            if ( mainCurrentNode.board.isGoal() )
                break;
            
            if ( altCurrentNode.board.isGoal() )
            {
                solvable = false;
                break;
            }
            
            // main
            for (Board neighbor : mainCurrentNode.board.neighbors())
            {
                if ( mainCurrentNode.parent != null && neighbor.equals(mainCurrentNode.parent.board) )
                        continue;
                SearchNode child = new SearchNode(neighbor, mainCurrentNode);
                mainPQ.insert(child);
            }
            
            // alt
            for (Board neighbor : altCurrentNode.board.neighbors())
            {
                if ( altCurrentNode.parent != null && neighbor.equals(altCurrentNode.parent.board) )
                        continue;
                SearchNode child = new SearchNode(neighbor, altCurrentNode);
                altPQ.insert(child);
            }
        }
    }
    
    public boolean isSolvable()
    {
        return solvable;
    }
    
    public int moves()
    {
        return mainCurrentNode.moves;
    }
    
    public Iterable<Board> solution()
    {
        Bag<Board> boards = new Bag<Board>();
        
        SearchNode node = mainCurrentNode;
        
        while (node.parent != null)
        {
            boards.add(node.board);
            node = node.parent;
        }
        
        return boards;
    }
    
    
    public static void main(String[] args)
    {
        System.out.println("Starting");
        
        Stopwatch sw = new Stopwatch();
        
        int[][][] blocks = {
            { {1,2,3}, {4,5,6}, {7,8,0} },
            { {0,1,3}, {4,2,5}, {7,8,6} },
            { {0,8,7}, {6,5,4}, {3,2,1} },
            { {4,6,1}, {8,5,2}, {7,3,0} },
            { {6,5,4}, {3,1,2}, {0,8,7} },
            { {4,5,6}, {2,1,3}, {7,8,0} },
            { {8,5,6}, {7,4,1}, {2,0,3} },
            { {2,4,5}, {3,6,7}, {0,1,8} },
        };
        
        for (int i = 0; i < blocks.length; i++)
        {
            Board board = new Board(blocks[i]);
            
            //System.out.println(board.twin().toString());
            
            System.out.println(board.toString());
            
            Solver solver = new Solver(board);
            
            if (solver.isSolvable())
                System.out.println("It took " + solver.moves() + " moves\n");
            else
                System.out.println("This board is not solvable\n");
        }
        
        
        //for (Board b : solver.solution())
        //{
        //    System.out.println(b.toString());
        //    System.out.println();
        //}
        
        System.out.println("\n Elapsed time: " + sw.elapsedTime() + " seconds");
    }
}