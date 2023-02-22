package contactManager;
import util.Input;

import java.util.ArrayList;
import java.util.Scanner;
public class ContactManagerApplication {
    private static final Input input = new Input();
    private static ArrayList<Contact> contactList = new ArrayList<>();
    public static void main(String[] args) {

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
        Input in = new Input();
        System.out.println("Which contact would you like to delete?");
        for (int i = 0; i < contactList.size(); i++) {
            System.out.println((i+1)+". "+contactList.get(i).getFName()+" "+contactList.get(i).getLName());
        }
        System.out.print("Enter a choice: ");
        int choice = in.getInt(1,contactList.size());
        contactList.remove(choice-1);
    }

    private static void searchContactByName() {
        String fName = null;
        String lName = null;

        System.out.print("Type contact first name to view" );
        fName = input.getString();

        System.out.println("Type contact last name to view");
        lName = input.getString();

        for (Contact contact : contactList) {
            if (fName.equals(contact.getFName())) {
                System.out.println("Contacts with " + fName);
            } else
                if (lName.equals(contact.getLName())) {
                    System.out.println("Contacts with " + lName);
            } else {
                    System.out.println("Contact dose not exist, yet!");
                }
        }
    }

    private static void addContact() {
        //add contacts method
    }

    private static void showContacts() {
        if (!contactList.isEmpty()) {
            for (int i = 0; i < contactList.size(); i++)
                System.out.println(contactList);
        } else {
            System.out.println("Your contact list is empty!! Shame!!");
        }
    }

    public static void writeMenu(){

            System.out.println("Press: \n 1. To View Contacts \n 2. To Add A New Contact \n " +
                    "3. To Search A Contact \n 4. Delete An Existing Contact \n 5. To Exit Program"  );



    }
}
