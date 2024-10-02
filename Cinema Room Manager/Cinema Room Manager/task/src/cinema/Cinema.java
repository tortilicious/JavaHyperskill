package cinema;
import java.text.DecimalFormat;
import java.util.Scanner;


public class Cinema {
    public static char[][] roomCreator(int rows, int seats) {
        char[][] matrix = new char[rows][seats];
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < seats; ++j) {
                matrix[i][j] = 'S';
            }
        }
        return matrix;
    }

    public static void screenPrinter(char[][] array) {
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int i = 1; i <= array[0].length; ++i) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < array.length; ++i) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < array[i].length; ++j) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int seatPicker(char[][] array) {

        Scanner read = new Scanner(System.in);
        int clientRowNumber;
        int clientSeatNumber;
        int ticketPrice;
        int roomSize = array.length * array[0].length;

        while (true){
            System.out.println("Enter a row number:");
            clientRowNumber = read.nextInt();

            System.out.println("Enter a seat number in that row:");
            clientSeatNumber = read.nextInt();

            if(clientRowNumber >= 1 && clientRowNumber<= array.length && clientSeatNumber >=1 && clientSeatNumber <= array[0].length){
                if (array [clientRowNumber - 1][clientSeatNumber - 1] == 'B') {
                    System.out.println("That ticket has already been purchased!");
                } else {
                    if (roomSize <= 60) {
                        ticketPrice = 10;
                    } else if (clientRowNumber <= array.length / 2) {
                        ticketPrice = 10;
                    } else {
                        ticketPrice = 8;
                    }
                    array[clientRowNumber - 1][clientSeatNumber - 1] = 'B';
                    break;
                }
            } else {
                System.out.println("Wrong input!");
            }
        }


        return ticketPrice;
    }


    public static String showMenu() {

        return "1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit";
    }


    public static void showStatistics(char[][] array, int soldTickets, int income){

        int roomSize = array.length * array[0].length;
        int potentialIncome;


        if (roomSize <= 60){
            potentialIncome = roomSize*10;
        } else {
            potentialIncome = array.length/2 * array[0].length * 10 + (array.length - array.length/2) * array[0].length * 8;
        }

        double purchasedTicketsRatio = (double) soldTickets/roomSize * 100;


        System.out.println("Number of purchased tickets: " + soldTickets);
        System.out.printf("Percentage: %.2f%% %n", purchasedTicketsRatio);
        System.out.println("Current income: " + "$" + income);
        System.out.println("Total income: " + "$" + potentialIncome);
    }


    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);

        int soldTickets = 0;
        int roomSize;
        int income = 0;
        boolean programIsWorking = true;

        System.out.println("Enter the number of rows:");
        int rows = read.nextInt();

        System.out.println("Enter the number of seats in each row:");
        int seats = read.nextInt();
        roomSize = rows * seats;

//      Creamos nuestra sala de cine con los datos proporcionados
        char[][] theaterRoom = roomCreator(rows, seats);

        String menu = showMenu();

        boolean working = true;

        while (working) {
            System.out.print(menu);
            System.out.println();
            int option = read.nextInt();

            switch (option) {
                case 1:
//      Mostramos en consola el estado de la sala.
                    screenPrinter(theaterRoom);
                    System.out.println();
                    break;

                case 2:
//      Calculamos precio entrada y actualizamos estado de la sala.
                    int ticketPrice = seatPicker(theaterRoom);
                    System.out.println("Ticket price: " + "$" + ticketPrice);
                    ++soldTickets;
                    income += ticketPrice;
                    break;

                case 3:
                    showStatistics(theaterRoom, soldTickets, income);
                    break;

                case 0:
                    working = false;
                    break;

                default:
                    working = false;
                    break;

            }


        }
    }
}