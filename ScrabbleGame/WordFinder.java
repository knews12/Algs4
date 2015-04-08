/**
 * Auto Generated Java Class.
 */
import java.util.*;

public class WordFinder 
{    
    private Board board;
    private Hand hand;
    
    public WordFinder(Board board, Hand hand)
    {
        this.board = board;
        this.hand = hand;
    }
    
    public String highestValueWord(String word)
    {
        Bag<String> possibleWords = possibleWords(word);
        String highestValueWord = highestValue(possibleWords);
        return highestValueWord;
    }
    
    private Bag<String> possibleWords(String letters)
    {
        Bag<String> words = new Bag<String>();
        Dictionary dictionary = new Dictionary("dictionary.txt");
        Bag<String> letterPermutations = permute("", letters, new Bag<String>());
        Iterator<String> it = letterPermutations.iterator();
        
        while(it.hasNext())
        {
            String w = it.next();
            if ( dictionary.isWord(w) )
            {
                words.add(w);
            }
        }
        
        return words;
    }
    
    private Bag<String> permute(String soFar, String left, Bag<String> permutations)
    {
        if (left.length() < 1)
        {
            permutations.add(soFar);
            return permutations;
        }
        
        for(int i = 0; i < left.length(); i++)
        {
            String next = soFar + left.charAt(i);
            String remaining = left.substring(0, i) + left.substring(i + 1, left.length());
            if (remaining.length() > 0 ) permutations.add(next);
            permutations = permute(next, remaining, permutations);
        }
        
        return permutations;
    }
    
    private String highestValue(Bag<String> words)
    {
        Iterator<String> it = words.iterator();
        
        int highestValue = 0;
        String highestValueWord = "";
        while (it.hasNext())
        {
            String nextWord = it.next();
            int wordValue = WordValue.value(nextWord);
            if ( wordValue > highestValue)
            {
                highestValue = wordValue;
                highestValueWord = nextWord;
            }
        }
        
        return highestValueWord;
    }
    
    public static void main(String[] args)
    {
        WordFinder wf = new WordFinder(null, new Hand("abc"));
        
        while ( StdIn.hasNextLine() )
        {
            String letters = StdIn.readString();
            
            Stopwatch stopWatch = new Stopwatch();
            
            //Bag<String> bag = wf.permute("", "abcd", new Bag<String>());
            Bag<String> bag = wf.possibleWords(letters);
        
            Iterator<String> it = bag.iterator();
        
            int c = 0;
            while(it.hasNext())
            {
                System.out.println(it.next());
                c++;
            }
        
            System.out.println(c);
        
            String highestValueword = wf.highestValueWord(letters);
            System.out.println("HIGHEST VALUE");
            System.out.println("word: " + highestValueword);
            System.out.println("value: " + WordValue.value(highestValueword));
            System.out.println(stopWatch.elapsedTime());
        }
    }   
}
