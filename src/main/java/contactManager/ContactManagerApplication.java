package contactManager;
import java.util.Scanner;
public class ContactManagerApplication {
    public static void main(String[] args) {

    Scanner input = new Scanner(System.in);

        boolean exitList = false;

        while(!exitList) {

            //print menu to prompt user to choose item from list
            writeMenu();
            int userOptions = Integer.parseInt(input.nextLine());
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

            System.out.println("Press ");
    }
}
