package util;

import java.util.Scanner;

public class Input {
    private Scanner scanner;

    public Input(){
        scanner = new Scanner(System.in);
    }

    public String getString(){
        String input = scanner.nextLine();
        return input;
    }

    public boolean yesNo(){
        String input = scanner.nextLine();
        return input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes");
    }

    public boolean yesNo(String prompt){
        System.out.println(prompt);
        String input = scanner.nextLine();
        return input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes");
    }

    public int getInt(){
        int input = 0;

        while(true){
            try{
                input = scanner.nextInt();
                break;
            }catch(Exception e){
                System.out.print("Not an integer. Please reenter: ");
                scanner.nextLine();
            }
        }
        return input;
    }
    public int getInt(int min, int max){
        int input = 0;

        while(true){
            try{
                input = scanner.nextInt();
                break;
            }catch(Exception e){
                System.out.print("Not an integer. Please reenter: ");
                scanner.nextLine();
            }
        }
        if(!inRange(input,min,max)){
            System.out.print("Not in range. Please reenter: ");
            input = getInt(min,max);
        }
        return input;
    }
    public int getInt(String prompt){
        System.out.print(prompt);
        return getInt();
    }
    public int getInt(int min, int max, String prompt){
        System.out.println(prompt);
        return getInt(min, max);
    }
    public boolean inRange(int num, int min, int max){
        return num <= max && num >= min;
    }
    public boolean inRange(double num, double min, double max){
        return num <= max && num >= min;
    }
    public double getDouble(){
        double input = 0;

        while(true){
            try{
                input = scanner.nextInt();
                break;
            }catch(Exception e){
                System.out.print("Not a double. Please reenter: ");
                scanner.nextLine();
            }
        }
        return input;
    }
    public double getDouble(String prompt){
        System.out.println(prompt);
        return getDouble();
    }
    public double getDouble(double min, double max){
        double input = 0;

        while(true){
            try{
                input = scanner.nextInt();
                break;
            }catch(Exception e){
                System.out.print("Not a double. Please reenter: ");
                scanner.nextLine();
            }
        }
        while(!inRange(input,min,max)){
            System.out.print("Not in range. Please reenter: ");
            input = getDouble(min,max);
        }
        return input;
    }
}
