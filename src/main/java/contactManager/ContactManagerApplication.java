package contactManager;
import java.util.Scanner;
public class ContactManagerApplication {
    public static void main(String[] args) {

    Scanner input = new Scanner(System.in);

        boolean exitList = false;

        while(!exitList) {

            //print menu to prompt user to choose item from list
            menuList.displayList();
            int userOptions = Integer.parseInt(input.nextLine());
            switch (userOptions) {
                case 1:
                    contact();
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
    public class menuList{
        public static void displayList(){
            System.out.println("menu");
        }
    }
}
