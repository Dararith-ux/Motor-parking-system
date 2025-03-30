import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Checkout {
    public static void main(String[] args) {
        checkoutMain();
    }
    //checkoutMain() is the method that process all the program flow and displayed in the main method.
    public static void checkoutMain() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=========================================");
        System.out.println("Welcome to the Check-out System");

        int id = getID(scanner);
        String[] userInfo = getUserInfo(id);
        // when ID not found in the file, it process the loop again and again until the return value of userInfo array is not null (name, id, platenumber)
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
    //get input ID when it is numerical value & it is positive
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
    //array method that contain all the part of user information n the register.txt file if id is matched.
    public static String[] getUserInfo(int id) {
        //create this array to contain all the part that match the condition, ensuring that the match ID is the latest one (the most bottom in the file) preventing taking the wrong or outdated checkin information if a student is registered multiple times.
        String[] latestCheckin = null;
        //using bufferedreader to read the checkin.txt file.
        try (BufferedReader reader = new BufferedReader(new FileReader("check_in.txt"))) {
            String line;
            //when the line has not come to an end during the loop
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length >= 3) {
                    try {
                        int validID = Integer.parseInt(parts[1].trim());
                        if (id == validID) {
                            latestCheckin = parts; // return all the part of the student's latest matched ID
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Cannot convert ID to number");
                        continue;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: check_in.txt not found!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // return null if ID is unmatched or parts if it matched.
        return latestCheckin;
    }
    //method writetocheckinFile is used to write the valid info when input id is matched into the checkout.txt
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