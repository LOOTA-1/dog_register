import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class OwnerCollection {

	private ArrayList<Owner> ownersList = new ArrayList<Owner>();

	public OwnerCollection(Owner... owners) {

		for (int i = 0; i < owners.length; i++) {
			this.ownersList.add(owners[i]);
		}

	}

	private boolean safeCheck(Owner owner) {
		if (owner == null) {
			return false;
		}
		return true;
	}

	private boolean ownerExists(Owner owner) {
		if (this.ownersList.indexOf(owner) == -1) {
			return false;
		}
		return true;
	}

	private int searchOwner(Owner owner) {
		int position = this.ownersList.indexOf(owner);
		return position;
	}

	private int searchOwnerString(String name) {
		int counter = 0;
		for (Owner owner : this.ownersList) {
			if (owner.getName().equals(name.toUpperCase())) {
				return counter;
			}
			counter++;
		}

		return -1;
	}

	public boolean addOwner(Owner owner) {

		if (!safeCheck(owner) || ownerExists(owner)) {
			return false;
		}
		
		this.ownersList.add(owner);
		return true;

	}

	public boolean removeOwner(String owner) {

		int temp = searchOwnerString(owner);
		if (temp == -1) {
			return false;
		}
		this.ownersList.remove(temp);
		return true;
	}

	public boolean removeOwner(Owner owner) {
		if (!safeCheck(owner)) {
			return false;
		}
		if (!ownerExists(owner)) {
			return false;
		}
		this.ownersList.remove(searchOwner(owner));
		return true;
	}

	public boolean containsOwner(String owner) {

		if (searchOwnerString(owner) == -1) {

			return false;
		}

		return true;
	}

	public boolean containsOwner(Owner owner) {

		if (!safeCheck(owner)) {
			return false;
		}
		if (!ownerExists(owner)) {
			return false;
		}

		return true;

	}

	public Owner getOwner(String owner) {
		int temp = searchOwnerString(owner);
		if (temp == -1) {

			return null;
		}
		Owner ownertemp = this.ownersList.get(temp);
		
		return ownertemp;

	}

	public ArrayList<Owner> getAllOwners() {
		
		sortOwners();

		return this.ownersList;
	}

	public int size() {
		int intReturn = this.ownersList.size();
		return intReturn;
	}

	public void sortOwners() {

		Collections.sort(this.ownersList, new Comparator<Owner>() {

			@Override
			public int compare(Owner firstOwner, Owner secondOwner) {
				return firstOwner.getName().compareTo(secondOwner.getName());
			}

		});

	}
}
