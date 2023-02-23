package contactManager;
import util.Input;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

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
            //System.out.println("Dir exists");
        } catch (IOException e) {
            System.out.println("createDirectory exception: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            Files.createFile(contactsFile);
        } catch(FileAlreadyExistsException e) {
            //System.out.println("File exists");
        } catch (IOException e) {
            System.out.println("createFile exception: " + e.getMessage());
            e.printStackTrace();
        }
        contactList = readListFromFile(contactsFile);

        boolean exitList = false;

        while(!exitList) {

            //print menu to prompt user to choose item from list
            writeMenu();
            int userOptions = input.getInt(1, 6);
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
                case 5 :
                    editContact();
                    break;
                case 6:
                    writeListToFile (contactsFile, contactList);
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
        System.out.print("Enter a choice (Enter 0 to cancel): ");
        int choice = input.getInt(0,contactList.size());
        if(choice == 0){
            return;
        }
        contactList.remove(choice-1);
    }
    private static void editContact() {
        System.out.println("Which contact would you like to edit?");
        for (int i = 0; i < contactList.size(); i++) {
            System.out.println((i+1)+". "+contactList.get(i).getFName()+" "+contactList.get(i).getLName());
        }
        System.out.print("Enter a choice (Enter 0 to cancel): ");
        int choice = input.getInt(0,contactList.size());
        if(choice == 0){
            return;
        }
        choice -= 1; //change choice to match contactList index number
        System.out.println(contactList.get(choice).toString());
        System.out.print("""
            What would you like to edit?
            1. First name
            2. Last name
            3. Phone number
            4. Email
            """);

        switch(input.getInt(1,4)){
            case 1 :
                input.getString();
                System.out.print("Enter a new first name: ");
                contactList.get(choice).setFName(input.getString());
                break;
            case 2 :
                input.getString();
                System.out.print("Enter a new last name: ");
                contactList.get(choice).setLName(input.getString());
                break;
            case 3 :
                input.getString();
                contactList.get(choice).setPhoneNum(contactList.get(choice).formattedPhoneNum(input.getNumericString(10, "Enter a new phone number: ")));
                break;
            case 4 :
                input.getString();
                System.out.print("Enter a new email: ");
                contactList.get(choice).setEmail(input.getString());
                break;
        }
        System.out.printf("""
        Contact changed...
        New data: %s
        """,contactList.get(choice).toString());
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

        for (Contact contact : contactList) {

            if (fName.equalsIgnoreCase(contact.getFName()) && lName.equalsIgnoreCase(contact.getLName())) {
                System.out.println(contact.toString());
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
            contact.setPhoneNum(contact.formattedPhoneNum(input.getNumericString(10, "Enter contact's phone number: ")));

            System.out.print("Enter contact's email: ");
            contact.setEmail(input.getString());
            contactList.add(contact);
            System.out.printf("Added contact: %s %s%n", contact.getFName(), contact.getLName());
        } while (input.yesNo("Add another contact? Y/N"));
    }

    private static void showContacts() {
        if (!contactList.isEmpty()) {
            formatListAndPrintToConsole(contactList);

        } else {
            System.out.println("Your contact list is empty!! Shame!!");
        }
    }

    public static void writeMenu(){

        System.out.print("""
            1. View all contacts
            2. Add a new contact
            3. Search a contact
            4. Delete an existing contact
            5. Edit a contact
            6. Exit program
            
            Enter a selection:\s
            """);
    }
    
    private static void formatListAndPrintToConsole(ArrayList<Contact> contacts) {
        int namePadding = getLongestNameSize(contacts);
        int phonePadding = 13;
        int emailPadding = getLongestEmailSize(contacts);
        System.out.println("_".repeat(namePadding + emailPadding + phonePadding + 10));
        System.out.printf("| %-" + namePadding + "s | %-" + phonePadding + "s | %-" + emailPadding + "s |%n", "Name", "Phone", "email");
        System.out.println("-".repeat(namePadding + emailPadding + phonePadding + 10));
        for (Contact contact : contacts) {
            System.out.printf("| %-" + namePadding + "s | %-10s | %-" + emailPadding + "s |%n",
                    contact.getFullName(),
                    contact.getPhoneNum(),
                    contact.getEmail()
            );
        }
    }

    private static int getLongestEmailSize(ArrayList<Contact> contacts) {
        int longestStringSize = "email".length();
        for (Contact contact : contacts) {
            if (contact.getEmail().length() > longestStringSize) {
                longestStringSize = contact.getEmail().length();
            }
        }
        return longestStringSize;
    }

    private static int getLongestNameSize(ArrayList<Contact> contacts) {
        int longestStringSize = "Name".length();
        for (Contact contact : contacts) {
            if (contact.getFullName().length() > longestStringSize) {
                longestStringSize = contact.getFullName().length();
            }
        }
        return longestStringSize;
    }
    

    public static ArrayList<Contact> readListFromFile(Path contactsFile){

        ArrayList<Contact> contacts = new ArrayList<>();
        String str;

        try{
            BufferedReader reader = new BufferedReader(new FileReader(contactsFile.toFile()));
            while((str = reader.readLine()) != null){
                Contact contact = createContactFromString(str);
                contacts.add(contact);
            }

        }catch(IOException e){
            e.printStackTrace();
        }
        return contacts;
    }
    public static Contact createContactFromString(String str){
        String[] attr = str.split(",");
        Contact contact = new Contact();
        contact.setFName(attr[0]);
        contact.setLName(attr[1]);
        contact.setPhoneNum(attr[2]);
        contact.setEmail(attr[3]);

        return contact;
    }

    public static void writeListToFile (Path file, ArrayList<Contact> list){
        ArrayList<String> contactStrings = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            contactStrings.add(createStringForFile(list.get(i)));
        }

        try {
            Files.write(file, contactStrings);
        } catch (IOException e) {
            System.out.println("file write exception: " + e.getMessage());
        }
    }
    public static String createStringForFile (Contact contact){
        //First Name: Jim, Last Name: Jones, Phone Number: (210)555-1234, Email: JJones@web.com
        String toFile;
        return toFile = contact.getFName()+","+contact.getLName()+","+contact.getPhoneNum()+","+contact.getEmail();
    }
}
