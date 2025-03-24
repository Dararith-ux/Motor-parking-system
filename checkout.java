import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class checkout {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Welcome to the Checkout System");
        int id = getID(scanner);
        Date currentdate = new Date();
        scanner.close();
        String[] userInfo = getUserInfo(id);
        if (userInfo != null) {
            System.out.println("Access Granted!");
            System.out.println("Name: " + userInfo[0]);
            System.out.println("ID: " + userInfo[1]);
            System.out.println("License Plate: " + userInfo[2]);
            System.out.println("Check-In date&time: " + userInfo[3]+", "+userInfo[4]);
            BufferedWriter writer = new BufferedWriter(new FileWriter("check_out.txt", true));
            writer.write(userInfo[0] + ", " + userInfo[1] + ", " + userInfo[2] + ", " +getDate(currentdate)+"\n");
            writer.close();
        } else {
            System.out.println("Access Denied! ID not found in today's check-in.");
        }
    }

    public static int getID(Scanner scanner) {
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        return id;
    }

    public static String[] getUserInfo(int id) {
        try (BufferedReader reader = new BufferedReader(new FileReader("check_in.txt"))) {
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
    public static String getDate(Date currentdate) {
        currentdate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy, HH:mm");
        return formatter.format(currentdate);
    }{

    }
}
