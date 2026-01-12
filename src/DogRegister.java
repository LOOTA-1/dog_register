import java.util.ArrayList;
import java.util.Arrays;

public class DogRegister {

	private static final String ADD_OWNER_COMMAND = "ADD OWNER";
	private static final String REMOVE_OWNER_COMMAND = "REMOVE OWNER";
	private static final String ADD_DOG_COMMAND = "ADD DOG";
	private static final String REMOVE_DOG_COMMAND = "REMOVE DOG";
	private static final String CHANGE_OWNER_COMMAND = "CHANGE OWNER";
	private static final String LIST_OWNERS_COMMAND = "LIST OWNERS";
	private static final String LIST_DOGS_COMMAND = "LIST DOGS";
	private static final String INCREASE_AGE_COMMAND = "INCREASE AGE";
	private static final String EXIT_COMMAND = "EXIT";
	private static final String HELP_COMMAND = "HELP";

	private InputReader scan  = new InputReader();
	private OwnerCollection ownerCollection = new OwnerCollection();

	private void start() {
		setUp();
		runCommandLoop();
		shutDown();
	}

	private void setUp() {
		System.out.println("Welcome to the dog sorter.\nType your command.\n");
	}

	private void shutDown() {
		System.out.print("Exiting the program \nGoodbye");

	}

	private void runCommandLoop() {
		String command = " ";
		displayCommands();
		while (!command.equals(EXIT_COMMAND)) {
			command = readCommand();
			command = command.toUpperCase();
			executeCommand(command);
		}

	}
	
	private void displayCommands() {
		System.out.print(
				"Valid Commands \n\n"
				+ ADD_OWNER_COMMAND + "\n"
				+ REMOVE_OWNER_COMMAND + "\n"
				+ ADD_DOG_COMMAND + "\n"
				+ REMOVE_DOG_COMMAND + "\n"
				+ CHANGE_OWNER_COMMAND + "\n" 
				+ LIST_OWNERS_COMMAND	+ "\n" 
				+ LIST_DOGS_COMMAND + "\n" 
				+ INCREASE_AGE_COMMAND + "\n" 
				+ EXIT_COMMAND + "\n\n"
				+ "To bring up this info type " 
				+ HELP_COMMAND + "\n");

	}

	private String readCommand() {

		return scan.readString("Enter command");
	}

	private void executeCommand(String command) {

		switch (command) {
		case ADD_OWNER_COMMAND:

			addOwnerCommand();
			break;
		case REMOVE_OWNER_COMMAND:

			removeOwnerCommand();
			break;
		case ADD_DOG_COMMAND:

			addDogCommand();
			break;
		case REMOVE_DOG_COMMAND:

			removeDogCommand();
			break;
		case CHANGE_OWNER_COMMAND:

			changeOwnerCommand();
			break;
		case LIST_OWNERS_COMMAND:

			listOwnersCommand();
			break;
		case LIST_DOGS_COMMAND:

			listDogsCommand();
			break;
		case INCREASE_AGE_COMMAND:

			increaseAgeCommand();
			break;

		case EXIT_COMMAND:

			// exits the program without Error message
			break;
		case HELP_COMMAND:
			displayCommands();
			break;

		default:
			printErrorMessage("Wrong command, try again \n try " + HELP_COMMAND + "\n");
		}
	}

	public static String formatName(String name) {
		return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
	}

	private void addOwnerCommand() {
		String temp;
		temp = scan.readString("Name of new owner");
		if (ownerCollection.containsOwner(temp)) {
			printErrorMessage("Owner alrady exists");
			return;
		}
		Owner owner = new Owner(temp);
		ownerCollection.addOwner(owner);
		System.out.println(temp + " is in");

	}

	private void removeOwnerCommand() {
		String temp;
		temp = scan.readString("Name of owner to remove");
		if (!ownerCollection.removeOwner(temp)) {
			printErrorMessage("Name of owner does not exist");
			return;
		}
		System.out.println(temp + " removed");

	}

	private void addDogCommand() {
		if (ownerCollection.size() == 0) {
			printErrorMessage("No owners exists");
			return;
		}
		String nameOwner;
		nameOwner = scan.readString("Name of the dogs owner");
		Owner owner = ownerCollection.getOwner(nameOwner);

		if (owner == null) {
			printErrorMessage("This owner does not exist");
			return;
		}
		if (owner.ownsMaxDogs()) {
			printErrorMessage("This owner owns the max amount of dogs");
			return;
		}

		String name = scan.readString("Enter dogs name");
		String race = scan.readString("Enter dogs race");
		int age = scan.readInt("Enter dogs age");
		int wight = scan.readInt("Enter dogs wight");
		Dog dog = new Dog(name, race, age, wight);
		owner.addDog(dog);
		System.out.print(name + " added to " + nameOwner);

	}

	private void removeDogCommand() {
		String temp;
		temp = scan.readString("Name of the dogs owner");
		Owner owner = ownerCollection.getOwner(temp);
		if (owner == null) {
			printErrorMessage("This owner does not exist");
			return;
		}
		if (!owner.ownsAnyDog()) {
			printErrorMessage("This owner does not own a dog");
			return;
		}
		temp = scan.readString("Name of the dog to be removed");
		if (!owner.ownsDog(temp)) {
			printErrorMessage("This dog i not owned by this owner");
			return;
		}
		owner.removeDog(temp);

	}

	private void changeOwnerCommand() {
		String tempDog;
		Dog dog;
		Owner owner = ownerCollection.getOwner(scan.readString("Name of dogs owner"));
		if (owner == null) {
			printErrorMessage("This owner does not exist");
			return;
		}
		if (!owner.ownsAnyDog()) {
			printErrorMessage("This owner does not own a dog");
			return;
		}
		tempDog = scan.readString("Name dog to be moved");

		if (!owner.ownsDog(tempDog)) {
			printErrorMessage("This owner does not own this dog");
			return;
		}

		dog = owner.getDog(tempDog);
		owner = ownerCollection.getOwner(scan.readString("Name of the new owner"));

		if (owner == null) {
			printErrorMessage("This owner does not exist");
			return;
		}
	
		if (!owner.addDog(dog)) {
			return;
		}
	}

	private void listOwnersCommand() {

		if (ownerCollection.size() == 0) {
			printErrorMessage("No owners exists");
			return;
		}

		ArrayList<Owner> owners = ownerCollection.getAllOwners();
		for (int i = 0; owners.size() > i; i++) {

			System.out.print(owners.get(i).toString());

		}
	}

	private Dog[] getAllTheDogs() {
		ArrayList<Dog> dogs = new ArrayList<>();

		if (ownerCollection.size() == 0) {
			printErrorMessage("No owners exists, therefore no dogs are regesterd");
			return null;
		}

		ArrayList<Owner> owners = ownerCollection.getAllOwners();
		for (Owner owner : owners) {
			dogs.addAll(Arrays.asList(owner.getDogs()));
		}
		
		dogs.removeIf((e) -> e == null );
		
		Dog[] sortedDogs = dogs.toArray(new Dog[0]);
		return sortedDogs;
	}

	private void listDogsCommand() {
		Dog[] dogs = getAllTheDogs();
		double minimumTail = scan.readDouble("Minimum tail length");
		if (dogs == null) return;
		if (dogs.length == 0) return;

		TailNameComparator compare = new TailNameComparator();
		DogSorter.sort(SortingAlgorithm.BUBBLE_SORT, compare, dogs);

		if (dogs[dogs.length - 1].getTailLength() < minimumTail) {
			printErrorMessage("No dogs tails over " + minimumTail + " exists");
			return;
		}
		
		for (int i = 0; dogs.length > i; i++) {
			if (dogs[i].getTailLength() < minimumTail) {
				continue;
			}
			System.out.print("Name owner " + formatName(dogs[i].getOwner().getName()) + " Name Dog " + formatName(dogs[i].getName()) + " Age " + dogs[i].getTailLength()+ "Tail Length" + dogs[i].getAge() + "/n");
		}
	}

	private void increaseAgeCommand() {

		Dog[] dogs = getAllTheDogs();
		
		if (dogs == null) {
			return;
		}
		for (int i = 0; dogs.length > i; i++) {
			dogs[i].increaseTime();
		}
	}

	private void printErrorMessage(String msg) {
		System.err.println(msg);
	}

	public static void main(String[] args) {

		new DogRegister().start();

	}

}
