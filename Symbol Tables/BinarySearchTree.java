public class BinarySearchTree<Key extends Comparable<Key>, Value>
{
    private Node root;
    
    private class Node
    {
        private Key key;
        private Value value;
        private Node left, right;
        
        public Node(Key key, Value value)
        {
            this.key = key;
            this.value = value;
        }
    }
    
    public BinarySearchTree()
    {
        root = null;
    }
    
    public void put(Key key, Value value)
    {
        root = put(root, key, value);
    }
    
    private Node put(Node x, Key k, Value v)
    {
        if ( x == null ) return new Node(k, v);
        
        int cmp = k.compareTo(x.key);
        
        if ( cmp < 0 )
        {
            x.left = put(x.left, k, v);
        }
        else if ( cmp > 0 )
        {
            x.right = put(x.right, k, v);
        }
        else if ( cmp == 0 ) 
        {
            x.value = v;
        }
        
        return x;
    }
    
    
    public Value get(Key key)
    {
        Node index = root;
        
        while (index != null)
        {
            int cmp = key.compareTo(index.key);
            
            // Key is greater than index key, 
            // so lets look at right side of the tree
            if (cmp > 0)
            {
                index = index.right;
            }
            // Key is less than index key,
            // so lets look at the left side of the tree
            else if (cmp < 0)
            {
                index = index.left;
            }
            // Key is the same as index key
            // we found it!
            else if (cmp == 0)
            {
                return index.value;
            }
        }
        
        // Key does not exist in tree
        return null;
    }
    
    public void delete(Key key)
    {
        root = delete(root, key);
    }
    
    private Node delete(Node x, Key key)
    {
        if ( x == null ) return null;
        
        int cmp = key.compareTo(x.key);
        
        if ( cmp < 0 ) x.left = delete(x.left, key);
        else if ( cmp > 0 ) x.right = delete(x.right, key);
        else if ( cmp == 0 )
        {
            // we found the node to delete
            
            if ( x.right == null ) return x.left; // no right child
            
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right); // replace with successor
            x.left =  t.left;
        }
        
        return x;
    }
    
    public Node deleteMin(Node x)
    {
        return null;
    }
    
    public Node min(Node x)
    {
        return null;
    }
    
    public Iterable<Key> iterator()
    {
        return null;
    }
    
    public static void main(String[] args)
    {
        BinarySearchTree<String, String> bst = 
            new BinarySearchTree<String, String>();
        
        bst.put("a", "apple");
        bst.put("c", "carrot");
        bst.put("b", "banana");
        
        System.out.println(bst.get("c"));
        System.out.println(bst.get("b"));
        System.out.println(bst.get("a"));
    }
}