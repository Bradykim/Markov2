import java.util.*;

public class EfficientMarkov extends BaseMarkov {
	private Map<String,ArrayList<String>> myMap;
	
	public EfficientMarkov(){
		this(3);
	}

	public EfficientMarkov(int order) {
		super(order);
		myMap = new HashMap<>();
	}

	@Override
	public void setTraining(String text)
	{
		super.setTraining(text);
		myMap.clear();
		String[] str = myText.split("");
		for(int i = 0; i<str.length -1 ;i++)
		{
			String key = myText.substring(i,i+myOrder);

			if(i+ myOrder >= myText.length())
			{
				myMap.putIfAbsent(key,new ArrayList<>());
				myMap.get(key).add(PSEUDO_EOS);
				break;
			}
			if(!myMap.containsKey(key))
			{
				myMap.putIfAbsent(key,new ArrayList<>());
				myMap.get(key).add(myText.substring(i+myOrder,i+myOrder+1));
			}

		}


	}
	@Override
	public ArrayList<String> getFollows(String key)
	{
		if(!myMap.containsKey(key))
		{
			throw new NoSuchElementException(key+" not in map");
		}
		return myMap.get(key);
	}
}	
