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
		
		int shortestNameLength;
		
		if (firstDog.getName().length() > secondDog.getName().length()) {
			shortestNameLength = secondDog.getName().length();
		}else {
			shortestNameLength = firstDog.getName().length();
		}
		
		int charIndex = 0;
		
		while (shortestNameLength > charIndex) {

			char firstChar = firstDog.getName().replaceAll(" ", "").charAt(charIndex);
			char secondChar = secondDog.getName().replaceAll(" ", "").charAt(charIndex);

			if ((int) firstChar == (int) secondChar) {
				charIndex++;
			} else if ((int) firstChar > (int) secondChar) {
				return 1;
			} else
				return -1;

		}
		

		if(firstDog.getName().length() > secondDog.getName().length()) {
			return -1;
		}
		return 1;
	}

}

