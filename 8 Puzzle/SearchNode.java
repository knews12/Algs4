import java.util.Comparator;

/**
 * Auto Generated Java Class.
 */
public class SearchNode implements Comparator<SearchNode>, Comparable
{
    public Board board;
    public SearchNode parent;
    public int moves;
    
    public SearchNode(Board board)
    {
        this.board = board;
        this.moves = 0;
        this.parent = null;
    }
    
    public SearchNode(Board board, SearchNode parent)
    {
        this.board = board;
        this.moves = parent.moves + 1;
        this.parent = parent;
    }
    
    public int priority()
    {
        return board.manhattan() + moves;
    }
    
    public int compare(SearchNode a, SearchNode b)
    {
        if ( a.priority() < b.priority() )
            return -1;
        if ( a.priority() > b.priority() )
            return 1;
        else
            return 0;
    }
    
    public int compareTo(Object y)
    {
        return compare(this, (SearchNode)y);
    }
    
    public boolean equals(Object b)
    {
        return this.priority() == ((SearchNode)b).priority();
    }
}
