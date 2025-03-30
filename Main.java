import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //object instantiation from the local class within the same file directory . we use this to combine all the written program into one executable file to enhance readability and reduce code complexity
        Register reg = new Register();
        Checkin cin = new Checkin();
        Checkout cout = new Checkout();

        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    while (true) {
                        reg.registerMain();
                        System.out.print("\nDo you want to continue registering? (1=Yes, 0=No): ");
                        int continueChoice = scanner.nextInt();
                        scanner.nextLine();
                        if (continueChoice != 1) {
                            break;  //if choice is 1, meaning that the user quit and the case 1 is broken
                        }
                    }
                    break;
                case 2:
                    while (true) {
                        cin.checkinMain();
                        System.out.print("\nDo you want to continue checking in? (1=Yes, 0=No): ");
                        int continueChoice = scanner.nextInt();
                        scanner.nextLine();
                        if (continueChoice != 1) {
                            break;  //if choice is 1, meaning that the user quit and the case 2 is broken.
                        }
                    }
                    break;
                case 3:
                    while (true) {
                        cout.checkoutMain();
                        System.out.print("\nDo you want to continue checking out? (1=Yes, 0=No): ");
                        int continueChoice = scanner.nextInt();
                        scanner.nextLine();
                        if (continueChoice != 1) {
                            break; //if choice is 1, meaning that the user quit and the case 3 is broken.
                        }
                    }
                    break;
                case 4:
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
        System.out.println("1. Register");
        System.out.println("2. Check-In");
        System.out.println("3. Check-Out");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }
}