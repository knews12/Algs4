/**
 * Auto Generated Java Class.
 */
import java.util.*;
import java.io.File;

public class Dictionary 
{
    private Hashtable<String, String> hashTable;
    
    public Dictionary(String dictionaryFile)
    {
        hashTable = new Hashtable<String, String>(); 
        loadDictionary(dictionaryFile);
    }
    
    public boolean isWord(String word)
    {
        return hashTable.containsKey(word.toUpperCase());
    }
    
    private void loadDictionary(String dictionaryFile)
    {
        File file = new File(dictionaryFile);
        
        
        if (file != null)
        {
            In in = new In(file);
            String[] words = in.readAllStrings();
            
            for(int i = 0; i < words.length; i++)
            {
                //System.out.println(words[i]);
                hashTable.put(words[i], words[i]);
            }
            
            in.close();
        }
        else
        {
            System.out.println("dictionary file does not exist");
        }
    }
    
    public static void main(String[] args)
    {
        Dictionary d = new Dictionary("dictionary.txt");
        
        System.out.println(d.isWord("aba"));
        System.out.println(d.isWord("x343"));
    }
    
    private String[] words = {""};
}

