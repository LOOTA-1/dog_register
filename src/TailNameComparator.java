import java.util.Comparator;

public class TailNameComparator implements Comparator<Dog> {

	public int compare(Dog firstDog, Dog secondDog) {
		if (firstDog.getTailLength() > secondDog.getTailLength()) {
			return 1;
		}
		if (firstDog.getTailLength() < secondDog.getTailLength()) {
			return -1;
		}
		if (firstDog.getName().equals(secondDog.getName())) {
			return 0;
		}
		int charIndex = 0;

		
		do {
			char firstChar = firstDog.getName().replaceAll(" ", "").charAt(charIndex);
			char secondChar = secondDog.getName().replaceAll(" ", "").charAt(charIndex);
			
			if(((int) firstChar != (int) secondChar))
				return (int) firstChar > (int) secondChar ? 1 : -1;
			
			charIndex++;
		} while (charIndex < firstDog.getName().length());
		return 0;
	}

}

