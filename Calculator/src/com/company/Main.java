package com.company;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = null;
    private static BufferedReader bufferedReader = null;
    private static BufferedWriter bufferedWriter = null;
    private static ArrayList<String> fileName = new ArrayList<>();

    public static void main(String[] args) {

            boolean flag = true;

            while (flag) {
                scanner = new Scanner(System.in);
                prikazy();
                String order = scanner.next();
                scanner.nextLine();

                switch (order) {

                    case "4":
                        flag = false;
                        System.out.println("Now exiting");
                        break;


                    case "2":
                        //reset fileName
                        fileName = new ArrayList<>();
                        System.out.println("showing you names of all files");
                        showListofFile();
                        break;

                    case "3":
                        System.out.println("Now execute file, choose number");
                        int value = scanner.nextInt();
                        String yourFile = fileName.get(value);
                        Calculator kalkulacka = new Calculator("C:\\Users\\karol\\IdeaProjects\\Calculator\\src\\" + yourFile);
                        break;

                    case "1":
                        prikazy();
                        break;
                }
            }
        }

        public static void prikazy() {

            System.out.println("Press 1 to show options");
            System.out.println("Press 2 show list of file");
            System.out.println("Press 3 execute choose number ");
            System.out.println("Press 4 to exit");
            System.out.println(" ");
            System.out.println("Please choose what you want to do");


        }

        public static void showListofFile() {

            try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\karol\\IdeaProjects\\Calculator\\src\\Allfile.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    //  System.out.println(line);
                    fileName.add(line);
                }
            } catch (Exception e) {
                System.out.println("Problem with reading allFile");
            }
            for (int i = 0; i < fileName.size(); i++) {
                System.out.println("File name : " + fileName.get(i) + " position :" + i);
            }


    }
}
