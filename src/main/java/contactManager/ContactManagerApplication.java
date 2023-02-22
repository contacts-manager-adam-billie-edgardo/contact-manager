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
        //search contacts method
    }

    private static void addContact() {
        //add contacts method
        input.getString();
        do {
            Contact contact = new Contact();
            System.out.print("Enter contact's first name: ");
            contact.setFName(input.getString());
            System.out.print("Enter contact's last name: ");
            contact.setLName(input.getString());
            System.out.print("Enter contact's phone number: ");
            // use getPhoneNumber to validate a 10-digit number from the user
            System.out.print("Enter contact's email: ");
            contact.setEmail(input.getString());
            contactList.add(contact);
            System.out.printf("Added contact: %s %s%n", contact.getFName(), contact.getLName());
        } while (input.yesNo("Add another contact? Y/N"));
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
