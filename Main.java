import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.regex.*;

public class Main {
    private static final String REGISTER_FILE = "Registered_bikes.txt";
    private static final String CHECKIN_FILE = "Checkin_log.txt";
    private static final String CHECKOUT_FILE = "Checkout_log.txt";
    private static final String LICENSE_PLATE_PATTERN = "2[A-Z]{2}-\\\\d{4}";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose an option: \n1. Register Motorbike \n2. Check-in \n3. Check-out");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                registerMotorbike(scanner);
                break;
            case 2:
                checkIn(scanner);
                break;
            case 3:
                checkOut(scanner);
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    private static void registerMotorbike(Scanner scanner) {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter AUPP ID: ");
        String auppId = scanner.nextLine();
        System.out.print("Enter license plate (format 2XX-1234): ");
        String licensePlate = scanner.nextLine();

        if (!Pattern.matches(LICENSE_PLATE_PATTERN, licensePlate)) {
            System.out.println("Invalid license plate format!");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(REGISTER_FILE, true))) {
            writer.write(name + "," + auppId + "," + licensePlate);
            writer.newLine();
            System.out.println("Registration successful!");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    private static void checkIn(Scanner scanner) {
        System.out.print("Enter AUPP ID for check-in: ");
        String auppId = scanner.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CHECKIN_FILE, true))) {
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            writer.write(auppId + "," + timeStamp);
            writer.newLine();
            System.out.println("Check-in successful!");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    private static void checkOut(Scanner scanner) {
        System.out.print("Enter AUPP ID for check-out: ");
        String auppId = scanner.nextLine();

        List<String> checkIns = new ArrayList<>();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(CHECKIN_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                checkIns.add(line);
                if (line.startsWith(auppId + ",")) {
                    found = true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading check-in file: " + e.getMessage());
            return;
        }

        if (!found) {
            System.out.println("User has not checked in.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CHECKOUT_FILE, true))) {
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            writer.write(auppId + "," + timeStamp);
            writer.newLine();
            System.out.println("Check-out successful!");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
