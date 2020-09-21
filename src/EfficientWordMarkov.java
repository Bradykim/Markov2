import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class EfficientWordMarkov extends BaseWordMarkov
{
    private Map<WordGram, ArrayList<String>> myMap;

    public EfficientWordMarkov(){
        this(3);
    }

    public EfficientWordMarkov(int order) {
        super(order);
        myMap = new HashMap<>();
    }

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
                myMap.put(key,new ArrayList<>());
                myMap.get(key).add(PSEUDO_EOS);
                break;
            }
            myMap.get(key).add(myWords[i+myOrder+1]);



        }


    }
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
