public class ScrabbleGame
{
    private Board board;
    private Hand hand;
    private WordFinder wfinder;
    private ScrabbleGraphics graphics;
    
    public static void main(String[] args)
    {
        Board board = new Board(15);
        ScrabbleGame game = new ScrabbleGame(board);
        game.setHand(new Hand("abc"));
        game.addWordToBoard("hello world", 0, 0, BoardDirection.Right);
        game.addWordToBoard("scrabble", 10, 10, BoardDirection.Down);
        game.addWordToBoard("newswanger", 2, 12, BoardDirection.Down);
    }
    
    public ScrabbleGame(Board board)
    {
        this.board = board;
        hand = new Hand("");
        graphics = new ScrabbleGraphics();
        drawBoard();
    }
    
    public void setHand(Hand hand)
    {
        this.hand = hand;
    }
    
    public void addWordToBoard(String word, int x, int y, BoardDirection direction)
    {
        board.addWord(word, x, y, direction);
        drawBoard();
    }
    
    private void drawBoard()
    {
        graphics.drawBoard(board);
    }
}