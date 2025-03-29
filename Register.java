import java.io.*;
import java.util.Scanner;

public class Register {
    public static void main(String[] args) {
        registerMain();
    }
    public static void registerMain() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Register System");
        String name = getName(scanner);
        int id = getID(scanner);
        while (idExists(id)) {
            System.out.println("ID already exists! Please enter ID again.");
            id = getID(scanner);
        }
        String platenumber = getPlateNumber(scanner);
        writetoFile(name, id, platenumber);
    }
    public static String getName(Scanner scanner) {
        System.out.print("Please enter your name: ");
        return scanner.nextLine();
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
    public static String getPlateNumber(Scanner scanner) {
        final String platePattern = "2[A-Z]{2}-\\d{4}";
        String platenumber;
        do {
            System.out.print("Please enter your license plate in format 2XX-1234: ");
            platenumber = scanner.nextLine();
        }while(!platenumber.matches(platePattern));
        return platenumber;
    }
    public static boolean idExists(int id) {
        try (BufferedReader reader = new BufferedReader(new FileReader("register.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length >= 2) {
                    try {
                        int existingID = Integer.parseInt(parts[1].trim());
                        if (id == existingID) {
                            return true;
                        }
                    } catch (NumberFormatException e) {
                        continue;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static void writetoFile(String name,int id,String platenumber) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("register.txt", true))){;
            writer.write(name + ", " + id + ", " + platenumber + "\n");
            writer.close();
            System.out.println("Registration saved successfully!");
            System.out.println("=========================================");
        } catch (IOException e) {
            System.out.println("Error: register.txt not found!");
            e.printStackTrace();
        }
    }
}
