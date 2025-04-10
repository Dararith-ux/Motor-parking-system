import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Checkin {
    public static void main(String[] args) {
        processCheckin();
    }
    //checkinMain() is the method that process all the program flow and displayed in the main method.
    public static void processCheckin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=========================================");
        System.out.println("Welcome to the Check-in System");

        int id = getID(scanner);
        String[] userInfo = getUserInfo(id);

        // Exit immediately if ID is not found
        if (userInfo == null) {
            System.out.println("Access Denied! ID not found in the registration list.");
            return;  // Exit the method
        }

        Date currentDate = new Date();
        System.out.println("=========================================");
        System.out.println("Check-in successfully!!!!!!!");
        System.out.println("Name: " + userInfo[0]);
        System.out.println("ID: " + userInfo[1]);
        System.out.println("Plate Number: " + userInfo[2]);

        // Call the write-to-file method
        writeToCheckinFile(userInfo[0], Integer.parseInt(userInfo[1]), userInfo[2], currentDate);
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
        //using bufferedreader to read the register.txt file.
        try (BufferedReader reader = new BufferedReader(new FileReader("register.txt"))) {
            String line;
            //when the line has not come to an end during the loop
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length >= 3) {
                    try {
                        int validID = Integer.parseInt(parts[1].trim());
                        if (id == validID) {
                            // if the id exist, return all the part include name, id, and platenumber into the getUserinfo array.
                            return parts;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Cannot convert ID to number");
                        continue;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: register.txt not found!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // if ID not found, return null to the array.
        return null;
    }
    //method writeToCheckinFile is used to write the valid info when input id is matched into the chekc_in.txt
    public static void writeToCheckinFile(String name, int id, String plateNumber, Date currentDate) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("check_in.txt", true))) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy, HH:mm");
            writer.write(name + ", " + id + ", " + plateNumber + ", " + formatter.format(currentDate) + "\n");
            writer.close();
            System.out.println("Check-in saved successfully!");
            System.out.println("=========================================");
        } catch (IOException e) {
            System.out.println("Error: check_in.txt not found!");
            e.printStackTrace();
        }
    }
}