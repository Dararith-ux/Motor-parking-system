import java.io.*;
import java.util.Scanner;

public class register {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            String name = getName(scanner);
            int id = getID(scanner);

            while (idExists(id)) {
                System.out.println("ID already exists! Please enter ID again.");
                id = getID(scanner);
            }

            String licensePlate = getLicensePlate(scanner);
            BufferedWriter writer = new BufferedWriter(new FileWriter("register.txt", true));
            writer.write(name + ", " + id + ", " + licensePlate + "\n");
            writer.close();


            System.out.println("✅ Registration saved successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public static String getLicensePlate(Scanner scanner) {
        System.out.print("Please enter your license plate: ");
        return scanner.nextLine();
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
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
