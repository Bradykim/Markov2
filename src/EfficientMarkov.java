import java.util.*;

public class EfficientMarkov extends BaseMarkov {
	private Map<String,ArrayList<String>> myMap;

	/**
	 * Default constructor has order 3
	 */
	public EfficientMarkov(){
		this(3);
	}


	/**
	 * Construct an EfficientMarkov object with the given order that is passed though the super.
	 * Also sets myMap to an empty map.
	 * @param order size of this markov generator
	 */
	public EfficientMarkov(int order) {
		super(order);
		myMap = new HashMap<>();
	}


	/**
	 * Generating keys of Strings whose length are based on the value of myOrder. Also these keys
	 * are stored in the map and have potential values with them.
	 * @param text is the String that needs to be evaluated for keys.
	 */
	@Override
	public void setTraining(String text)
	{
		super.setTraining(text);
		myMap.clear();
		for(int i = 0; i<myText.length() -myOrder+1 ;i++)
		{
			String key = myText.substring(i,i+myOrder);
			if(!myMap.containsKey(key))
			{
				myMap.put(key,new ArrayList<>());
			}
			if(i+ myOrder >= myText.length())
			{
				myMap.get(key).add(PSEUDO_EOS);
				break;
			}


			myMap.get(key).add(myText.substring(i+myOrder,i+myOrder+1));


		}


	}

	/**
	 * Return the value at the given key if there is one.
	 * @param key is the key that's value we are finding.
	 * @return the value of the String key.
	 */
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
