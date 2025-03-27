import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class checkout {
    public static void main(String[] args) {
        checkoutMain();
    }
    public static void checkoutMain(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Check-out System");
        int id;
        String[] userInfo = null;
        while (userInfo == null) {
            id = getID(scanner);
            userInfo = getUserInfo(id);

            if (userInfo == null) {
                System.out.println("Access Denied! ID not found.");
                System.out.println("Please input ID again.");
            }
        }

        Date currentdate = new Date();
        scanner.close();

        System.out.println("Check-out successfully!!!!!!!");
        System.out.println("Name: " + userInfo[0]);
        System.out.println("ID: " + userInfo[1]);
        System.out.println("Plate Number: " + userInfo[2]);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("check_out.txt", true))) {
            writer.write(userInfo[0] + ", " + userInfo[1] + ", " + userInfo[2] + ", " + getDate(currentdate) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        String [] latestCheckin = null;
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
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: register.txt not found!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return latestCheckin;
    }
    public static String getDate(Date currentdate) {
        currentdate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy, HH:mm");
        return formatter.format(currentdate);
    }
}
