
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
				System.out.println("null found in getdogs");
				continue;
			}
			addString = dogs[i].toString();
			returnString = returnString.concat(" ").concat(addString);
			returnString = returnString.concat("\n \t");
		}

		return "\n" + DogRegister.formatName(name) + " dogs (" + returnString + ") ";
	}

	public Dog getDog(String name) {
		for (int i = 0; dogs.length > i; i++) {
			if (dogs[i] != null && dogs[i].getName().equals(name.toUpperCase()))
				return dogs[i];
		}
		
		return null;
	}

	public int size() {
		int i = 0;
		for (; i < dogs.length; i++) {
			if (this.dogs[i] == null) break;
		}
		return i + 1;
	}
	
	public Dog[] getDogs() {
		
		if (!ownsAnyDog()) {
			return new Dog[0];
		}
		
		int current_length = size();
		Dog[] dogs_copy = new Dog[current_length];
		for (int i = 0; i < current_length; i++) {
				dogs_copy[i] = dogs[i];
		}
		
		DogSorter.sort(SortingAlgorithm.MERGE_SORT, new NameComparator(), dogs_copy);
		return dogs_copy;
	}

	public boolean addDog(Dog dog) {
		if (dog == null) {
			System.out.print("null dog");
			return false;
		} else if (this.ownsMaxDogs()) {
			System.out.print("max dog");
			return false;
		} else if (this.ownsDog(dog.getName())) {
			System.out.print("owed dog");
			return false;
		}

		int length = size();
		for (int i = 0; i < length; i++) {
				if (this != dog.getOwner()) {
					dog.setOwner(this);
				}
				
				this.dogs[i] = dog;
				return true;
		}

		return false;
	}

	private boolean weOwn(Dog dog) {
		return dog.getOwner() != null && this == dog.getOwner();
	}
	
	public boolean removeDog(String name) {
		
		name = name.toUpperCase();
		
		int length = size();
		for (int i = 0; i < length; i++) {
			if (dogs[i].getName().equals(name)) {
				if(weOwn(dogs[i]))
					dogs[i].removeOwner();
				removeDog(i);
			}
		}
		return false;
	}

	private void removeDog(int index) {
	
		int offset = 0;
		while(index + offset + 1 < MAX_DOG_AMOUNT && dogs[index + offset + 1] != null) {
			dogs[index + offset] = dogs[index + offset + 1];
		}
		
		dogs[index + offset] = null;
	}
	
	public boolean removeDog(Dog dog) {
		for (int i = 0; i < dogs.length; i++) {
			if (this.dogs[i] == null) break;
			
			if (dogs[i].equals(dog)) {
				if (weOwn(dog)) dog.removeOwner();
				removeDog(i);
				return true;
			}
		}
		return false;
	}

	public boolean ownsAnyDog() {
		return dogs[0] != null;
	}

	public boolean ownsMaxDogs() {
		return dogs[MAX_DOG_AMOUNT - 1] != null;
	}

	public boolean ownsDog(String name) {
		int length = size();
		for (int i = 0; i < length; i++) {
			if (dogs[i].getName().equals(name.toUpperCase())) {
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
