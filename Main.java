import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        register reg = new register();
        checkin cin = new checkin();
        checkout cout = new checkout();

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
                            break;
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
                            break;
                        }
                    }
                    break;
                case 3:
                    while (true) {
                        cout.checkoutMain();

                        System.out.print("\nDo you want to continue checking out? (1=Yes, 0=No): ");
                        int continueChoice = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        if (continueChoice != 1) {
                            break;
                        }
                    }
                    break;
                case 4:
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n===== PARKING MANAGEMENT SYSTEM =====");
        System.out.println("1. Register");
        System.out.println("2. Check-In");
        System.out.println("3. Check-Out");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }
}