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

		for (int j = 0; j < dogs.length - 1; j++) {
			for (int i = j + 1; i < dogs.length; i++) {
				if (compareFunction.compare(dogs[j], dogs[i]) > 0)

				{
					place = dogs[j];
					dogs[j] = dogs[i];
					dogs[i] = place;
				}
			}
			
		}
	}

	private static void insertionSort(Dog[] dogs, Comparator<Dog> compareFunction)
	
	{
		for (int i = 1; i < dogs.length; ++i) {
			Dog temp = dogs[i];
			int j = i - 1;

			while (j >= 0 && compareFunction.compare(dogs[j], temp) > 0) {
				dogs[j + 1] = dogs[j];
				j--;
			}
			dogs[j + 1] = temp;
		}
	}
}
