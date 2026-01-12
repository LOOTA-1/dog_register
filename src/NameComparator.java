import java.util.Comparator;

public class NameComparator implements Comparator<Dog> {

	public int compare(Dog firstDog, Dog secondDog) {
		
		if(firstDog == null) {
			return -1;
		}
		if(secondDog == null) {
			return 1;
		}
		return firstDog.getName().compareTo(secondDog.getName());
		
	}

}