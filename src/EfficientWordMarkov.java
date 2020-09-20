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
        for(int i = 0; i<myWords.length-1;i++)
        {
            WordGram wor = new WordGram(myWords,i,myOrder);
            myMap.putIfAbsent(wor,new ArrayList<>());
            myMap.get(wor).add(myWords[myOrder+1]);
        }
        myMap.putIfAbsent(new WordGram(myWords, myWords.length-1,myWords.length),new ArrayList<>());

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
