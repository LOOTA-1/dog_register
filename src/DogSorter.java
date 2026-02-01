import java.util.Comparator;

public class DogSorter {

	private DogSorter() {

	}

	public static void sort(SortingAlgorithm algoritm, Comparator<Dog> compareFunction, Dog... hundar) {
		switch (algoritm) {
		case BUBBLE_SORT:
			bubbleSort(hundar, compareFunction);
			break;
		case MERGE_SORT:
			insertionSort(hundar, compareFunction);
			break;

		}
	}

	private static void bubbleSort(Dog[] dogs, Comparator<Dog> compareFunction) {
		
		Dog place;				
		int firstDog, secondDog;
        boolean swapped;
        for (firstDog = 0; firstDog < dogs.length - 1; firstDog++) {
            swapped = false;
            for (secondDog = 0; secondDog < dogs.length - firstDog - 1; secondDog++) {
                if (compareFunction.compare(dogs[secondDog], dogs[secondDog + 1]) > 0) {
                                       
                	place = dogs[secondDog];
                    dogs[secondDog] = dogs[secondDog + 1];
                    dogs[secondDog + 1] = place;
                    swapped = true;
                }
            }          
            if (swapped == false)
                break;
        }
		
	}

	private static void insertionSort(Dog[] dogs, Comparator<Dog> compareFunction)
	
	{
		for (int firstDog = 1; firstDog < dogs.length; ++firstDog) {
			Dog temp = dogs[firstDog];
			int secondDog = firstDog - 1;

			while (secondDog >= 0 && compareFunction.compare(dogs[secondDog], temp) > 0) {
				dogs[secondDog + 1] = dogs[secondDog];
				secondDog--;
			}
			dogs[secondDog + 1] = temp;
		}
	}
}
