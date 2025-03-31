import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //object instantiation from the local class within the same file directory . we use this to combine all the written program into one executable file to enhance readability and reduce code complexity
        Checkin cin = new Checkin();
        Checkout cout = new Checkout();

        while (true) {
            displayMenu();
            int choice =0;
            //ensuring the input has to be a positive and valid number
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Invalid input! Please enter a valid number.");
                scanner.nextLine();
            }
            switch (choice) {
                case 1:
                    while (true) {
                        cin.checkinMain();
                        System.out.print("\nDo you want to continue checking in? (1=Yes, 0=No): ");
                        int continueChoice = scanner.nextInt();
                        scanner.nextLine();
                        if (continueChoice != 1) {
                            break;  //if choice is 1, meaning that the user quit and the loop is broken.
                        }
                    }
                    break;
                case 2:
                    while (true) {
                        cout.checkoutMain();
                        System.out.print("\nDo you want to continue checking out? (1=Yes, 0=No): ");
                        int continueChoice = scanner.nextInt();
                        scanner.nextLine();
                        if (continueChoice != 1) {
                            break; //if choice is 1, meaning that the user quit and the loop is broken.
                        }
                    }
                    break;
                case 3:
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    return; // ending the program
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    //method that display the menu program
    public static void displayMenu() {
        System.out.println("\n===== PARKING MANAGEMENT SYSTEM =====");
        System.out.println("1. Check-In");
        System.out.println("2. Check-Out");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }
}