import java.util.Scanner;

/*
input = 8
output =
1 1 1 1 1 1 1 2
3 2 2 2 2 2 2 2
3 3 3 3 3 3 3 4
5 4 4 4 4 4 4 4
5 5 5 5 5 5 5 6
7 6 6 6 6 6 6 6
7 7 7 7 7 7 7 8

input = 3

1 1 2
3 3 2

 */
public class NumberPattern {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows: ");
        int rows = 3;//scanner.nextInt();


        printPattern(rows);
    }

    static void printPattern(int n) {

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {

                if (i % 2 == 0) {
                    if (j == 1) {
                        System.out.print(i + 1);
                    } else
                        System.out.print(i);
                } else {
                    if (j == 5) {
                        System.out.print(i + 1);
                        break;
                    } else {
                        System.out.print(i);
                    }
                }
            }
            System.out.println();
        }
    }

}