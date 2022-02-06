package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Calculator {

    private String path;
    private Scanner scanner = null;
    private BufferedReader bf = null;
    private ArrayList<String> words = new ArrayList<>();
    private ArrayList<Double> numbers = new ArrayList<>();

    public Calculator(String path) {
        this.path = path;
        openFile(path);
        read();
    }

    public BufferedReader openFile(String cesta) {

        try {
            bf = new BufferedReader(new FileReader(cesta));
            System.out.println("Open file succsefull");
            return bf;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            scanner.close();
            System.out.println("Wrong Path");
            return null;
        }

    }

    public void read() {
        try {
            scanner = new Scanner(bf);
            boolean pravda = true;
            while (scanner.hasNextLine() && pravda) {
                try {
                    String slovo = scanner.next();
                    if (slovo.equals("apply")) {
                        pravda = false;
                    }
                    words.add(slovo);
                    String cislo = scanner.next();
                    double cisielko = Double.parseDouble(cislo);
                    numbers.add(cisielko);
                } catch (NoSuchElementException e) {
                    System.out.println("Problem with instuction or number while try read");
                    throw e;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Problem " + e.getMessage());
        }
//only if we have good file with right values only then we start calculation
        if (checkIaN(words, numbers) == true && LenghtEqual(words, numbers) == true) {
            System.out.println(calculation());
        } else {
            System.out.println("Inccorect values in files");
        }

    }
//Chcecking if ArrayList<String> have only string if not we have a file with incorrect values
//Chcecking if ArrayList<Double> have only numbers if not we have a file with inccorect values
    public boolean checkIaN(ArrayList<String> arrayList, ArrayList<Double> cisla) {
        for (String s : arrayList) {
            if (s.contains("[0-9]")){
                return false;
            }
        }
        for (Double in : cisla) {
            if (!(in instanceof Double)) {
                return false;
            }
        }
        return true;
    }

    public double calculation() {
        if(!chcekingApply(words)){
            System.out.println("Warning file doesnt have apply");
        } else {
            String slovo = words.get(words.size() - 1);
            double vypocet = numbers.get(numbers.size() - 1);
            StringBuilder str = new StringBuilder(slovo);
            StringBuilder preklad = new StringBuilder();
            preklad.append(numbers.get(numbers.size() - 1));
            if (words.get(0).equals("apply")) {
                System.out.println("result with out calculation " + vypocet);
            }
            for (int i = 0; i < words.size() - 1; i++) {

                if (chceckDivisor(numbers)==true){

                } else {
                    break;
                }
                preklad.append(" ");
                str.append(" ");
                str.append(words.get(i));
                switch (words.get(i)) {

                    case "add":
                        vypocet = vypocet + numbers.get(i);
                        preklad.append(" + ");
                        preklad.append(numbers.get(i));
                        break;

                    case "multiply":
                        vypocet = vypocet * numbers.get(i);
                        preklad.append(" * ");
                        preklad.append(numbers.get(i));
                        break;

                    case "divide":
                        try {
                            if (numbers.get(i) == 0) {
                                return -1;
                            }
                            vypocet = vypocet / numbers.get(i);
                            vypocet = Math.floor(vypocet * 100) / 100;
                            preklad.append(" / ");
                            preklad.append(numbers.get(i));
                        } catch (ArithmeticException e) {
                            System.out.println("Warning divide with zero");
                            throw e;
                        }
                        break;

                    case "substract":
                        vypocet = vypocet - numbers.get(i);
                        preklad.append(" - ");
                        preklad.append(numbers.get(i));
                        break;

                    case "square":
                        //square(cisla.get(i));
                        if(numbers.get(i)==0){
                            preklad.append(" ^ ");
                            preklad.append(numbers.get(i));
                            vypocet = 1;
                        } else {
                            preklad.append(" ^ ");
                            preklad.append(numbers.get(i));
                            vypocet = Math.pow(vypocet,numbers.get(i));
                            break;
                        }
                }
            }
            //instruction all what we use
            System.out.println(str);
            //translate to add = + ...
            System.out.println(preklad + " = " + vypocet);
            //final result
            System.out.println("calculation = " + vypocet);
            return vypocet;
        }
        return -3;
    }

    public boolean chceckDivisor(ArrayList<Double> cisla) {
        for (Double in : cisla) {
            if (in instanceof Double) {
                // System.out.println("v poradecku");
            } else {
                return false;//delenie niecim inym ako cislom
            }
        }
        return true;
    }

    public boolean chcekingApply(ArrayList<String> arrayList){
        int pom = arrayList.indexOf("apply");
        if(pom >= 0){
            return true;
        } else {
            return false;
        }

    }

    public Scanner getScanner(){
        if(scanner != null){
            return scanner;
        }
        else {
            return null;
        }
    }

    public boolean LenghtEqual(ArrayList<String> arrayList, ArrayList<Double> cisla) {
        if (getArrayLenghtWord() != getArrayLenghtNumber()) {
            return false;
        }
        return true;
    }

    public int getArrayLenghtWord() {
        return words.size();
    }

    public int getArrayLenghtNumber() {
        return numbers.size();
    }

    public ArrayList<String> getArrayWord() {
        return words;
    }

    public ArrayList<Double> getArrayNumbers() {
        return numbers;
    }
}
