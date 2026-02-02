public class Owner {   
    private static final int MAX_DOG_AMOUNT = 7;
    
	private String name;
	private Dog[] dogs = new Dog[MAX_DOG_AMOUNT];

	public Owner(String name, Dog... dogs) {
		this.name = name.toUpperCase();
		for (int i = 0; i < dogs.length; i++) {
			addDog(dogs[i]);

		}
	}

	public String getName() {
		return name;
	}

	public String toString() {

		Dog[] dogs = getDogs();

		if (dogs == null) {
			return name + " owns no dogs";
		}

		String returnString = " ";
		String addString;
		for (int i = 0; dogs.length > i; i++) {
			if (dogs[i] == null) {
			
				continue;
			}
			addString = dogs[i].toString();
			returnString = returnString.concat(" ").concat(addString);
			returnString = returnString.concat("\n \t");
		}

		return "\n" + name + " dogs (" + returnString + ") ";

	}

	public Dog getDog(String name) {
		for (int i = 0; dogs.length > i; i++) {
			if (dogs[i] != null && dogs[i].getName().equals(name.toUpperCase()))
				return dogs[i];
		}
		return null;

	}

	public Dog[] getDogs() {
		if (!ownsAnyDog()) {
			return new Dog[0];
		}
		int nullCounter = 0;
		for (int i = 0; i < dogs.length; i++) {
			if (this.dogs[i] != null) {
				nullCounter++;
			}
		}
		int writeIndex = 0;
		Dog[] dogsCopy = new Dog[nullCounter];
		for (int i = 0; i < dogs.length; i++) {
			if (dogs[i] != null) {
				dogsCopy[writeIndex] = dogs[i];
				writeIndex++;
			}
		}
		DogSorter.sort(SortingAlgorithm.MERGE_SORT, new NameComparator(), dogsCopy);
		return dogsCopy;

	}

	public boolean addDog(Dog dog) {
		if (dog == null) {
			
			return false;
		}
		if (this.ownsMaxDogs()) {
			
			return false;
		}
		if (this.ownsDog(dog.getName())) {
			
			return false;
		}

		for (int i = 0; i < dogs.length; i++) {
			if (this.dogs[i] == null) {
				if (this != dog.getOwner()) {
					dog.setOwner(this);
				}				
			
			this.dogs[i] = dog;
						
			return true;

			}
		}

		return false;
	}

	public boolean removeDog(String name) {
		for (int i = 0; i < dogs.length; i++) {
			if (this.dogs[i] != null && dogs[i].getName().equals(name.toUpperCase())) {
				return removeDog(dogs[i]);

			}
		}
		return false;
	}

	public boolean removeDog(Dog dog) {
		
		for (int i = 0; i < dogs.length; i++) {
			if (this.dogs[i] != null && dogs[i].equals(dog)) {
				dogs[i] = null;
				if (dog.getOwner() != null && this == dog.getOwner()) {
					dog.setOwner(null);

				}

				return true;
			}
		}
		return false;
	}

	public boolean ownsAnyDog() {
		
		for (int i = 0; i < dogs.length; i++) {
			if (dogs[i] != null) {

				return true;
			}
		}
		return false;
	}

	public boolean ownsMaxDogs() {

		for (int i = 0; i < dogs.length; i++) {
			if (dogs[i] == null) {
				return false;
			}
		}
		return true;
	}

	public boolean ownsDog(String name) {
		for (int i = 0; i < dogs.length; i++) {
			if (dogs[i] != null && dogs[i].getName().equals(name.toUpperCase())) {

				return true;
			}
		}
		return false;
	}

	public boolean ownsDog(Dog dog) {
		for (int i = 0; i < dogs.length; i++) {
			if (dogs[i] == dog) {

				return true;
			}
		}
		return false;
	}

}
