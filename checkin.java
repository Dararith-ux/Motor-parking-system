import java.io.*;
import java.util.Date;
import java.util.Scanner;

public class checkin {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int id = getID(scanner);
        Date currentdate = new Date();
        scanner.close();
        String[] userInfo = getUserInfo(id);
        if (userInfo != null) {
            System.out.println("Access Granted!");
            System.out.println("Name: " + userInfo[0]);
            System.out.println("ID: " + userInfo[1]);
            System.out.println("License Plate: " + userInfo[2]);
            BufferedWriter writer = new BufferedWriter(new FileWriter("check_in.txt", true));
            writer.write(userInfo[0] + ", " + userInfo[1] + ", " + userInfo[2] + ", " +currentdate+"\n");
            writer.close();
        } else {
            System.out.println("Access Denied! ID not found.");
        }
    }

    public static int getID(Scanner scanner) {
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        return id;
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
}