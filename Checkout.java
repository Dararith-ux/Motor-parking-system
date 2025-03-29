import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Checkout {
    public static void main(String[] args) {
        checkoutMain();
    }
    public static void checkoutMain() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=========================================");
        System.out.println("Welcome to the Check-out System");
        int id = getID(scanner);
        String[] userInfo = getUserInfo(id);
        while (userInfo == null) {
            System.out.println("Access Denied! ID not found.");
            System.out.print("Please input ID again: ");
            id = getID(scanner);
            userInfo = getUserInfo(id);
        }
        Date currentDate = new Date();
        System.out.println("=========================================");
        System.out.println("Check-out successfully!!!!!!!");
        System.out.println("Name: " + userInfo[0]);
        System.out.println("ID: " + userInfo[1]);
        System.out.println("Plate Number: " + userInfo[2]);
        System.out.println("Check-In date & time: " + userInfo[3] + ", " + userInfo[4] );
        writeToFile(userInfo[0], Integer.parseInt(userInfo[1]), userInfo[2], currentDate);
    }
    public static int getID(Scanner scanner) {
        int id;
        System.out.print("Please enter your ID: ");
        while (true) {
            if (scanner.hasNextInt()) {
                id = scanner.nextInt();
                if (id > 0) {
                    scanner.nextLine();
                    return id;
                }
            } else {
                scanner.next();
            }
            System.out.print("Please enter your ID as a Numerical Positive Value: ");
        }
    }
    public static String[] getUserInfo(int id) {
        String[] latestCheckin = null;
        try (BufferedReader reader = new BufferedReader(new FileReader("check_in.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length >= 3) {
                    try {
                        int validID = Integer.parseInt(parts[1].trim());
                        if (id == validID) {
                            latestCheckin = parts;
                        }
                    } catch (NumberFormatException e) {
                        continue;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: check_in.txt not found!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return latestCheckin;
    }
    public static void writeToFile(String name, int id, String licensePlate, Date currentDate) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("check_out.txt", true))) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy, HH:mm");
            writer.write(name + ", " + id + ", " + licensePlate + ", " + formatter.format(currentDate) + "\n");
            System.out.println("Check-out saved successfully!");
            System.out.println("=========================================");
        } catch (IOException e) {
            System.out.println("Error: check_out.txt not found!");
            e.printStackTrace();
        }
    }
}