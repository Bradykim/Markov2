import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class EfficientWordMarkov extends BaseWordMarkov
{
    private Map<WordGram, ArrayList<String>> myMap;

    /**
     * Default constructor has order 3
     */
    public EfficientWordMarkov(){
        this(3);
    }


    /**
     * Construct an EfficientWordMarkov object with the given order that is passed though the super.
     * Also sets myMap to an empty map.
     * @param order size of this markov generator
     */
    public EfficientWordMarkov(int order) {
        super(order);
        myMap = new HashMap<>();
    }

    /**
     * Generating keys of WordGrams whose length are based on the value of myOrder. Also these keys
     * are stored in the map and have potential values with them.
     * @param text is the String that needs to be evaluated for keys.
     */
    @Override
    public void setTraining(String text)
    {
        super.setTraining(text);
        myMap.clear();
        for(int i = 0; i<myWords.length - myOrder+1;i++)
        {
            WordGram key = new WordGram(myWords,i,myOrder);
            if(!myMap.containsKey(key))
            {
                myMap.put(key,new ArrayList<>());

            }
            if(i+ myOrder>=myWords.length)
            {
                myMap.get(key).add(PSEUDO_EOS);
                break;
            }
            myMap.get(key).add(myWords[i+myOrder]);



        }


    }

    /**
     * Return the value at the given key if there is one.
     * @param key is the key that's value we are finding.
     * @return the value of the String key.
     */
    @Override
    public ArrayList<String> getFollows(WordGram key)
    {
        if(!myMap.containsKey(key))
        {
            throw new NoSuchElementException(key+" not in map");
        }
        return myMap.get(key);
    }
}
