
public class Dog {
	private static final double TAX_TAIL_LENGTH = 3.7;
	private static final String TAX_BREED = "TAX";
	private static final String DACHSHOUND_BREED = "DACHSHOUND";
	private static final double TAIL_SCALE_FACTOR = 10.0;
	
	private String name;
	private String breed;
	private int age;
	private int weight;
	private double tailSize;
	private Owner owner;

	public Dog(String name, String breed, int age, int weight) {
		if (name == null || breed == null) {
			throw new NullPointerException();
		}
		if (name.isEmpty() || breed.isEmpty()|| age < 0 || weight < 0) {
			throw new IllegalArgumentException();
		}
		
		this.name = name.toUpperCase();
		this.breed = breed.toUpperCase();
		this.weight = weight;
		this.age = age;
		setTailSize(this.breed);
	}

	public Dog(String name, String breed, int age, int weight, Owner owner) {
		this(name, breed, age, weight);
		this.owner = owner;
	}


	private void setTailSize(String breed) {
		if (breed.equals(TAX_BREED) || breed.equals(DACHSHOUND_BREED)) {
			this.tailSize = TAX_TAIL_LENGTH;
		} else {
			this.tailSize = (age * weight) / TAIL_SCALE_FACTOR;
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
		//name bad so junit work
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
		}
		
		if (this.owner != null) {
			this.owner.removeDog(this);
		}
		
		this.owner = owner;
		owner.addDog(this);
		return true;
	}

	public String toString() {
		
		String returnString = DogRegister.formatName(name) +" "+ breed+" "+ age+" "+ weight+" "+tailSize;
		if(owner != null) {
			returnString = returnString + owner.getName();
		}
		return returnString;
	}
}
