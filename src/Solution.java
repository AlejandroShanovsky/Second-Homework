
import java.util.Scanner;

interface Writeable {
    void write();
}

public class Solution {
    private static String[] choseColorMarker = {"1.RED", "2.GREEN", "3.YELLOW", "4.BLUE", "5.MAGENTA", "6.CYAN", "7.WHITE", "8.BLACK", "9.BRIGHT"};

    public static void main(String[] args) {
        start();
    }

    private static void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Chose color of marker");
        printMenu();
        int k = scanner.nextInt();
        String color = "";
        switch (k) {
            case 1:
                color = "RED";
                break;
            case 2:
                color = "GREEN";
                break;
            case 3:
                color = "YELLOW";
                break;
            case 4:
                color = "BLUE";
                break;
            case 5:
                color = "MAGENTA";
                break;
            case 6:
                color = "CYAN";
                break;
            case 7:
                color = "WHITE";
                break;
            case 8:
                color = "BLACK";
                break;
            case 9:
                color = "BRIGHT";
                break;
        }
        Teacher teacher = new Teacher(new Marker(color));
        teacher.write();
    }

    private static void printMenu() {
        for (String color : choseColorMarker) {
            System.out.println(color);
        }
    }
}

class Marker implements Writeable {
    private String color;
    private int markerCharge = 10000;

    public Marker(String color) {
        this.color = color;
    }

    @Override
    public void write() {
        Scanner scanner = new Scanner(System.in);
        String ourLine = "";
        boolean isTrue = true;
        System.out.println("Write text :");
        while (isTrue) {
            String line = scanner.nextLine();
            int lineLength = line.length();
            if (markerCharge > lineLength) {
                if (line.equals("Stop")) {
                    isTrue = false;
                } else {
                    ourLine += line;
                    markerCharge -= lineLength;
                }
            } else {
                System.out.println("Marker with color - " + color + " is empty!");
                isTrue = false;
            }
        }
        int x = color.equals("RED") ? 31 :
                color.equals("GREEN") ? 32 :
                        color.equals("YELLOW") ? 33 :
                                color.equals("BLUE") ? 34 :
                                        color.equals("MAGENTA") ? 35 :
                                                color.equals("CYAN") ? 36 :
                                                        color.equals("WHITE") ? 37 :
                                                                color.equals("BLACK") ? 30 :
                                                                        color.equals("BRIGHT") ? 1 : 0;
        System.out.println((char) 27 + "[" + x + "m" + "<" + color.toLowerCase() + ">" + ourLine + "<" + color.toLowerCase() + "/>" + (char) 27 + "[0m");
        System.out.println("Charge of this marker " + markerCharge);
    }
}

class Teacher {
    private Marker marker;

    public Teacher(Marker marker) {
        this.marker = marker;
    }

    public void write() {
        marker.write();
    }
}
