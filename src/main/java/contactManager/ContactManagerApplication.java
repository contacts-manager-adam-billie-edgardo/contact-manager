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
        contactList = readListFromFile(contactsFile);

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
        System.out.print("Enter a choice: ");
        int choice = input.getInt(1,contactList.size());
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
        input.getString();
        do {
            Contact contact = new Contact();
            System.out.print("Enter contact's first name: ");
            contact.setFName(input.getString());
            System.out.print("Enter contact's last name: ");
            contact.setLName(input.getString());
            contact.setPhoneNum(contact.formattedPhoneNum(input.getString(10, "Enter contact's phone number: ")));

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

            System.out.println("Press: \n 1. To View Contacts \n 2. To Add A New Contact \n " +
                    "3. To Search A Contact \n 4. Delete An Existing Contact \n 5. To Exit Program"  );



    }
    
    private static void formatListAndPrintToConsole(ArrayList<Contact> contacts) {
        int namePadding = getLongestNameSize(contacts);
        int phonePadding = 10;
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
