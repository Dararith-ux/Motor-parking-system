import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.regex.*;

public class Main {
    public static void main(String[] args) {
        register reg = new register();
        checkin cin = new checkin();
        checkout cout = new checkout();
        reg.registerMain();
    }

}