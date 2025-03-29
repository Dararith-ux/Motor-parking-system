import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Checkin {
    public static void main(String[] args) {
        checkinMain();
    }

    public static void checkinMain() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=========================================");
        System.out.println("Welcome to the Check-in System");

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
        System.out.println("Check-in successfully!!!!!!!");
        System.out.println("Name: " + userInfo[0]);
        System.out.println("ID: " + userInfo[1]);
        System.out.println("Plate Number: " + userInfo[2]);

        writetocheckinFile(userInfo[0], Integer.parseInt(userInfo[1]), userInfo[2], currentDate);
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
        try (BufferedReader reader = new BufferedReader(new FileReader("register.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length >= 3) {
                    try {
                        int validID = Integer.parseInt(parts[1].trim());
                        if (id == validID) {
                            return parts;
                        }
                    } catch (NumberFormatException e) {
                        continue;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: register.txt not found!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writetocheckinFile(String name, int id, String licensePlate, Date currentDate) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("check_in.txt", true))) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy, HH:mm");
            writer.write(name + ", " + id + ", " + licensePlate + ", " + formatter.format(currentDate) + "\n");
            writer.close();
            System.out.println("Check-in saved successfully!");
            System.out.println("=========================================");
        } catch (IOException e) {
            System.out.println("Error: check_in.txt not found!");
            e.printStackTrace();
        }
    }
}