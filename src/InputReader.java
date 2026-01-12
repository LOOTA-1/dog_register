import java.util.Scanner;

public class InputReader {

    private static boolean exist; 
	private Scanner scan;
	
	
	public InputReader() {
		this(new Scanner(System.in));
	}
	
	public InputReader(Scanner scanner) {
		
		if (exist) {
			throw new IllegalStateException();		
		}
		exist = true;
		this.scan = scanner;
		
	}

	public int readInt(String text) {
		int temp;
		while (true) {
			System.out.println(text + "?>");

			temp = this.scan.nextInt();
			this.scan.nextLine();
			if (temp >= 0) {
				break;
			} else {
				System.out.println("Error: must not be negative try again");
			}
		}
		return temp;
	}

	public double readDouble(String text) {
		double temp;
		while (true) {
			System.out.println(text + "?>");

			temp = this.scan.nextDouble();
			this.scan.nextLine();
			if (temp >= 0) {
				break;
			} else {
				System.out.println("Error: must not be negative try again");
			}
		}
		return temp;

	}

	public String readString(String text) {
		while (true) {
			if (text == null || text.trim().equals("")) {
				System.out.println("empty input ERROR");
			} else {
				break;
			}
		}
		System.out.println(text + "?>");

		return this.scan.nextLine().trim();
	}
}
