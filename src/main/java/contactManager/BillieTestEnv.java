package contactManager;

import util.Input;

import java.util.ArrayList;
import java.util.Scanner;

public class BillieTestEnv {

    private static ArrayList<Contact> contactList = new ArrayList<>();

    public static void main(String[] args) {
        Contact test = new Contact();
        test.setFName("Jim");
        test.setLName("Jones");
        long phNum = 2105551234;
        test.setPhoneNum(test.formattedPhoneNum(phNum));
        test.setEmail("JJones@web.com");
        contactList.add(test);

        Contact test2 = new Contact();
        test2.setFName("Jill");
        test2.setLName("Jones");
        phNum = 2105552345;
        test2.setPhoneNum(test.formattedPhoneNum(phNum));
        test2.setEmail("JJones@web.com");
        contactList.add(test2);

        Contact test3 = new Contact();
        test3.setFName("Jimmy");
        test3.setLName("Jones");
        phNum = 2105553456;
        test3.setPhoneNum(test.formattedPhoneNum(phNum));
        test3.setEmail("JJones@web.com");
        contactList.add(test3);

        Contact test4 = new Contact();
        test4.setFName("Jiffy");
        test4.setLName("Jones");
        phNum = 2105554567;
        test4.setPhoneNum(test.formattedPhoneNum(phNum));
        test4.setEmail("JJones@web.com");
        contactList.add(test4);

        for (int i = 0; i < contactList.size(); i++) {
            System.out.println(contactList.get(i).toString());
        }

        deleteExistingContact();
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
}
