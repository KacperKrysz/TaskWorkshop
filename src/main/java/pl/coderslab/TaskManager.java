package pl.coderslab;

import pl.coderslab.ConsoleColors;

public class TaskManager {

    public static void main(String[] args) {
        menu();

    }


    public static void menu() {

        String[] lines = new String[5];

        lines[0] = ConsoleColors.BLUE + "Please select an option";
        lines[1] = ConsoleColors.RESET + "add";
        lines[2] = "remove";
        lines[3] = "list";
        lines[4] = "exit";

        for (String line : lines) {

            System.out.println(line);

        }
    }

}