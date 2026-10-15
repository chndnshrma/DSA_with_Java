import java.math.BigInteger;
import java.util.Scanner;

public class PascalsTriangle {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the number of rows: ");
		int rows = scanner.nextInt();

		if (rows < 0) {
			System.out.println("Number of rows cannot be negative.");
			scanner.close();
			return;
		}

		for (int row = 0; row < rows; row++) {
			BigInteger value = BigInteger.ONE;
			for (int column = 0; column <= row; column++) {
				if (column > 0) {
					System.out.print(" ");
				}
				System.out.print(value);
				value = value.multiply(BigInteger.valueOf(row - column))
							 .divide(BigInteger.valueOf(column + 1));
			}
			System.out.println();
		}

		scanner.close();
	}
}
