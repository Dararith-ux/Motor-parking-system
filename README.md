# 🚗 Parking Management System

This is a simple terminal-based Java program for managing a motorbike parking system. The system allows users to register, check in, and check out using a unique ID and plate number. All data is stored in text files.

---

## 📁 Project Structure

The project includes the following Java files:

- `Main.java` — Runs the menu system for Check-In, Check-Out, or Exit.
- `Register.java` — Allows users to register with their name, ID, and plate number.
- `Checkin.java` — Checks in registered users by matching their ID with stored records.
- `Checkout.java` — (Assumed to exist; used for checking out, although not shown here.)

---

## 📝 How to Run the Program

### ✅ Requirements

- Java JDK installed (version 8 or higher).
- A terminal or IDE (like IntelliJ IDEA or Eclipse).

---

### 📦 Steps to Compile and Run

1. **Open Terminal or Command Prompt** in your project folder.

2. **Compile all Java files**:
   ```bash
   javac Main.java Register.java Checkin.java Checkout.java

3. **Run the main program**:
   ```bash
   java Main
   ```

4. To run registration separately:
   ```bash
   java Register
   ```
5. Or You can run it directly in text editor like Intellij, Eclipse, or VS code.
   - First run Register.java to register students into the register.txt
   - Secondly, assuming that students arrive school and we are securities, have to check in there ID. When the ID is input, it should approve access or deny unless their ID is registered or matched with the ID in the register.txt
   - Moving on to the time that the students leave school, they will be asked for their ID. Again, the ID is input and compare with the ID store in the check_in.txt. If the ID exists in check_in.txt meaning that the students come to school and approve check out. The data will be stored in check_out.txt. *Note** : we are not yet implementing a method that prevent the student from checkin or checkout multiple times. We plan to develop it in the future.
   
---

## 🗃️ Data Storage Files

The system uses the following `.txt` files:

- `register.txt` — Stores registered users: `name, id, platenumber`
- `check_in.txt` — Stores check-in logs: `name, id, platenumber, date/time`
- `check_out.txt` — (Assumed file for check-out logs)

---

## 📌 Notes

- Plate numbers must match the format `2XX-1234` (e.g., `2AB-1234`).
- User ID must be a positive number.
- Each ID must be unique in the `register.txt` file and mustnot be repeated

---

## 🧑‍💻 Important

- If `register.txt` doesn't exist, the system will create it automatically.
- Use `BufferedReader` and `BufferedWriter` carefully to avoid file access errors.
- Customize the UI or expand functionality using a GUI (like Java Swing) in the future or web-based GUI (HTML, CSS, Javascript or other framework)

---
