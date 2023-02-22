package contactManager;
import util.Input;

import java.util.Scanner;
public class ContactManagerApplication {

    public static void main(String[] args) {

    Input input = new Input();

        boolean exitList = false;

        while(!exitList) {

            //print menu to prompt user to choose item from list
            writeMenu();
            int userOptions = input.getInt(1, 5);
            switch (userOptions) {
                case 1:
                    showContacts();
                    break;
                case 2:
                    addContact();
                    break;
                case 3:
                    searchContactByName();
                case 4:
                    deleteExistingContact();
                    break;
                case 5:
                    exitList = true;
                    break;
            }
        }
    }

    private static void deleteExistingContact() {
        //deleting contacts method
    }

    private static void searchContactByName() {
        //search contacts method
    }

    private static void addContact() {
        //add contacts method
    }

    private static void showContacts() {
        //show contacts
    }

    public static void writeMenu(){

            System.out.println("Press: \n 1. To View Contacts \n 2. To Add A New Contact \n " +
                    "3. To Search A Contact \n 4. Delete An Existing Contact \n 5. To Exit Program"  );



    }
}
