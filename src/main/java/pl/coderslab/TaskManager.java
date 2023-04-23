package pl.coderslab;

import org.apache.commons.lang3.ArrayUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class TaskManager {

    static final String FILE_NAME = "tasks.csv";

    static String[][] tasks;

    public static void main(String[] args) throws FileNotFoundException {

        boolean isLoopWorking = true;
        tasks = tasks();

        Scanner scanner = new Scanner(System.in);

        while (isLoopWorking) {


            menu();

            switch (scanner.nextLine()) {
                case "add" -> addTask();
                case "remove" -> remove();
                case "list" -> list();
                case "exit" -> {
                    isLoopWorking = false;
                    exit();
                }
                default -> System.out.println("Please select a correct option.");
            }

        }
    }


    public static void menu() {

        String[] lines = new String[5];

        lines[0] = ConsoleColors.BLUE + "Please select an option";
        lines[1] = ConsoleColors.RESET + "add";
        lines[2] = "remove";
        lines[3] = "list";
        lines[4] = "exit";
        System.out.println();
        for (String line : lines) {

            System.out.println(line);

        }
    }

    public static String[][] tasks () throws FileNotFoundException {

        File tasksFile = new File(FILE_NAME);

        int fileRow = 0;
        int fileColumn = 3;
        Scanner scanner = new Scanner(tasksFile);

        while (scanner.hasNextLine()) {

            scanner.nextLine();
            fileRow++;


        }

        String[][] tasksList = new String[fileRow][fileColumn];

        Scanner scanner2 = new Scanner(tasksFile);

        for (int i = 0; i < tasksList.length; i++) {

            String line = scanner2.nextLine();
            String[] smallArr = line.split(",");

            for (int a = 0; a < tasksList[i].length; a++) {
                tasksList[i][a] = smallArr[a];
                //System.out.println(tasksList[i][a]);
            }
        }

        return tasksList;

    }


    private static void addTask () {


        Scanner scanner = new Scanner(System.in);

        System.out.println("add\nPlease add task description");
        String description = scanner.nextLine();

        System.out.println("Please add task due date");
        String date = scanner.nextLine();

        System.out.println("Is your task important?: true/false");
        String isImportant = scanner.nextLine();

        tasks = Arrays.copyOf(tasks,tasks.length + 1);
        tasks[tasks.length-1] = new String[3];
        tasks[tasks.length-1][0] = description;
        tasks[tasks.length-1][1] = date;
        tasks[tasks.length-1][2] = isImportant;
    }

    private static void list() {

        System.out.println("list");
        int extra = 0;

        for (int i = 0; i < tasks.length; i++) {
            System.out.print(extra + " : ");
            for (int a = 0; a < tasks[i].length; a++) {
                System.out.print(tasks[i][a]+" ");
            }
            extra++;
            System.out.println();
        }
    }

    private static void remove() {

        boolean isLoopOn = true;
        Scanner scanner = new Scanner(System.in);
        System.out.println("remove\nPlease select number to remove.");
        while (isLoopOn) {

                String indexS = scanner.nextLine();
                if (indexS.matches("\\d+")) {
                    int index = Integer.parseInt(indexS);
                    if (index >= tasks.length || index < 0) {
                        System.out.println("Incorrect argument passed. Please give number between: 0 - " + (tasks.length - 1));
                    } else {
                        tasks = ArrayUtils.remove(tasks, index);
                        System.out.println("Value was successfully deleted.");
                        isLoopOn = false;
                    }
                } else {
                    System.out.println("Incorrect argument passed. Please give number between: 0 - " + (tasks.length - 1));
                }


        }
    }

    private static void exit() {
        Path path = Paths.get(FILE_NAME);

        File file = new File(String.valueOf(path));

        try (PrintWriter printWriter = new PrintWriter(file)) {

            for (int i = 0; i < tasks.length; i++) {
                for (int a = 0; a < 3; a++) {
                    if (a == 2){
                        printWriter.print(tasks[i][a]);
                    } else {
                        printWriter.print(tasks[i][a] + ",");
                    }

                }
                printWriter.println();
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println(ConsoleColors.RED+ "bye, bye");

    }

}


