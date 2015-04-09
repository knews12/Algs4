class RedBlackBST<Key extends Comparable<Key>, Value>
{
    private static final class Color
    {
        private static final boolean RED = true;
        private static final boolean BLACK = false;
    }
        
    private class Node
    {
        private Key key;
        private Value value;
        private boolean color;
        private Node left, right;
        
        public Node(Key k, Value v, boolean c)
        {
            key = k;
            v = value;
            c = color;
        }
    }
    
    public static void main(String[] args)
    {
    }
}