package contactManager;
import util.Input;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
public class ContactManagerApplication {
    private static final Input input = new Input();
    private static ArrayList<Contact> contactList = new ArrayList<>();

    public static void main(String[] args) {

        System.out.println(System.getProperty("user.dir"));
        Path contactsDir = Paths.get( "src", "main", "java","contactManager","assets");
        Path contactsFile = Paths.get( "src", "main", "java","contactManager","assets", "contacts.txt");

        try {
            Files.createDirectory(contactsDir);
        } catch(FileAlreadyExistsException e) {
            System.out.println("Dir exists");
        } catch (IOException e) {
            System.out.println("createDirectory exception: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            Files.createFile(contactsFile);
        } catch(FileAlreadyExistsException e) {
            System.out.println("File exists");
        } catch (IOException e) {
            System.out.println("createFile exception: " + e.getMessage());
            e.printStackTrace();
        }
        contactList = writeListFromFile(contactsFile);

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
                    break;
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
        System.out.println("Which contact would you like to delete?");
        for (int i = 0; i < contactList.size(); i++) {
            System.out.println((i+1)+". "+contactList.get(i).getFName()+" "+contactList.get(i).getLName());
        }
        System.out.print("Enter a choice: ");
        int choice = input.getInt(1,contactList.size());
        contactList.remove(choice-1);
    }

    private static void searchContactByName() {
        String fName = null;
        String lName = null;

        boolean foundMatch = false;

        System.out.println("Type contact first name to view" );
        input.getString();
        fName = input.getString();

        System.out.println("Type contact last name to view");
        lName = input.getString();

        for (int i = 0; i < contactList.size(); i++) {

            if (fName.equalsIgnoreCase(contactList.get(i).getFName()) && lName.equalsIgnoreCase(contactList.get(i).getLName())) {
                System.out.println(contactList.get(i).toString());
                foundMatch = true;
            }
        }
        if(!foundMatch) {
            System.out.println("Contact does not exist, yet!");
        }
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
    public static ArrayList<Contact> writeListFromFile(Path contactsFile){
        ArrayList<Contact> contacts = new ArrayList<>();
        String str;

        try{
            BufferedReader reader = new BufferedReader(new FileReader(contactsFile.toFile()));
            while((str = reader.readLine()) != null){
                Contact contact = writeContactFromString(str);
                contacts.add(contact);
            }

        }catch(IOException e){
            e.printStackTrace();
        }
        return contacts;
    }
    public static Contact writeContactFromString(String str){
        String[] attr = str.split(",");
        Contact contact = new Contact();
        contact.setFName(attr[0]);
        contact.setLName(attr[1]);
        contact.setPhoneNum(attr[2]);
        contact.setEmail(attr[3]);

        return contact;
    }
}
