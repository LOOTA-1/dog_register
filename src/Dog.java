public class Dog {
	private static final double DACHSHUND_TAIL_LENGTH = 3.7;
	private static final String DACHSHUND_BREED_ALT_SE = "TAX";//dont know
	private static final String DACHSHUND_BREED = "DACHSHUND";

	private String name;
	private String breed;
	private int age;
	private int weight;
	private double tailSize;
	private Owner owner;

	public Dog(String name, String breed, int age, int weight) {
		this(name, breed, age, weight, null);
	}

	public Dog(String name, String breed, int age, int weight, Owner owner) {
		if (name == null || breed == null) {
			throw new NullPointerException();
		}
		if (name.isEmpty() || breed.isEmpty() || age < 0 || weight < 0) {
			throw new IllegalArgumentException();
		}

		this.name = name.toUpperCase();
		this.breed = breed.toUpperCase();
		this.weight = weight;
		this.age = age;
		setTailSize(this.breed);
		this.owner = owner;

	}
	

	private void setTailSize(String breed) {
		if (breed.equals(DACHSHUND_BREED_ALT_SE) || breed.equals(DACHSHUND_BREED)) {
			this.tailSize = DACHSHUND_TAIL_LENGTH;
		} else {
			this.tailSize = (age * weight) / 10.0;

		}		

	}

	public int updateAge(int years) {
		if (years >= 0) {
			if ((age + years) < 0) {
				this.age = Integer.MAX_VALUE;
			} else {
				this.age = age + years;
			}

		}
		setTailSize(this.breed);
		return age;
	}

	public String getName() {
		return name;
	}

	public String getBreed() {
		return breed;
	}

	public int getAge() {
		return age;
	}

	public int getWeight() {
		return weight;
	}

	public double getTailLength() {
		return tailSize;
	}

	public Owner getOwner() {
		return owner;
	}

	public void removeOwner() {
		this.owner = null;
	}

	public void increaseTime() {				
		this.age++;
	}

	public boolean setOwner(Owner owner) {
		
		if (owner == null) {
			this.owner.removeDog(this);
			removeOwner();

			return true;

		}
		if (owner.equals(this.owner)) {
			return false;
		} else {
			if (this.owner != null) {
				this.owner.removeDog(this);
			}
			this.owner = owner;
			owner.addDog(this);
			return true;
		}

	}

	public String toString() {
		
		String returnString = name +" "+ breed+" "+ age+" "+ weight+" "+tailSize;
		if(owner != null) {
			returnString = returnString + owner.getName();
		}
		return returnString;
	}
}
