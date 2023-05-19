package cinema;

import java.util.Scanner;

public class Cinema {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seatsInEachRow = scanner.nextInt();
        char[][] seats = createSeatingArrangement(rows, seatsInEachRow);

        String menu = """
                1. Show the seats
                2. Buy a ticket
                3. Statistics
                0. Exit
                """;
        int userInput;
        int rowNumber = 0;
        int seatNumber = 0;

        do {
            System.out.println("\n" + menu);
            userInput = scanner.nextInt();

            switch (userInput) {
                case 1 -> showTheSeats(seats);
                case 2 -> buyATicket(seats);
                case 3 -> statistics(seats, rowNumber);
                case 0 -> {
                    return;
                }
            }
        } while (true);
    }

    public static char[][] createSeatingArrangement(int rows, int seatsInEachRow) {
        char[][] seats = new char[rows][seatsInEachRow];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seatsInEachRow; j++) {
                seats[i][j] = 'S';  // Initialize all seats as available
            }
        }
        return seats;
    }

    public static void showTheSeats(char[][] seats) {
        int rows = seats.length;
        int seatsInEachRow = seats[0].length;

        System.out.println("\nCinema:");
        System.out.print("  ");
        for (int i = 1; i <= seatsInEachRow; i++) {
            System.out.print(i + " ");
        }
        for (int a = 0; a < rows; a++) {
            System.out.print("\n" + (a + 1) + " ");
            for (int j = 0; j < seatsInEachRow; j++) {
                System.out.print(seats[a][j] + " ");
            }
        }
        System.out.println();
    }

    public static void buyATicket(char[][] seats) {
        Scanner scanner = new Scanner(System.in);
        int rows = seats.length;
        int seatsInEachRow = seats[0].length;
        boolean validTicket = false;
        while (!validTicket) {
            System.out.println("\n\nEnter a row number:");
            int rowNumber = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int seatNumber = scanner.nextInt();

            if (rowNumber < 1 || rowNumber > rows || seatNumber < 1 || seatNumber > seatsInEachRow) {
                System.out.println("Wrong input!");
            } else if (seats[rowNumber - 1][seatNumber - 1] == 'B') {
                System.out.println("That ticket has already been purchased!");
            } else {
                validTicket = true;
                seats[rowNumber - 1][seatNumber - 1] = 'B';  // Mark the chosen seat as taken
            }

            int ticket, ticket1, income;
            int frontHalf = rows / 2;
            int backHalf = rows - frontHalf;
            if (rows * seatsInEachRow > 60) {
                ticket = 10;
                ticket1 = 8;
                income = (frontHalf * ticket + backHalf * ticket1) * seatsInEachRow;
            } else {
                ticket = 10;
                income = rows * seatsInEachRow * ticket;
            }
            System.out.println("Total income: " + income);
            if (rows * seatsInEachRow > 60 && rowNumber > frontHalf) {
                System.out.println("Ticket price: $8");
            } else {
                System.out.println("Ticket price: $10");
            }
            showTheSeats(seats);
        }
    }


    public static void statistics(char[][] seats, int rowNumber) {
        int rows = seats.length;
        int seatsInEachRow = seats[0].length;
        int counterB = 0;
        int currentIncome = 0;

        int ticket = 10, ticket1 = 8, income;
        int frontHalf = rows / 2;
        int backHalf = rows - frontHalf;

        if (rows * seatsInEachRow > 60) {
            income = (frontHalf * ticket + backHalf * ticket1) * seatsInEachRow;
        } else {
            income = rows * seatsInEachRow * ticket;
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seatsInEachRow; j++) {
                if (seats[i][j] == 'B') {
                    counterB++;
                    if (rows * seatsInEachRow > 60 && i < frontHalf) {
                        currentIncome += ticket;
                    } else if (rows * seatsInEachRow > 60 && i >= frontHalf) {
                        currentIncome += ticket1;
                    } else {
                        currentIncome += ticket;
                    }
                }
            }
        }
        float percentage = (float) (100 * counterB) / (rows * seatsInEachRow);
        System.out.printf("""
            Number of purchased tickets: %d
            Percentage: %.2f%%
            Current income: $%d
            Total income: $%d
            """, counterB, percentage, currentIncome, income);
    }
}




