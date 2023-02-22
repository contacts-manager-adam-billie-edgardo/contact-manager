package contactManager;

public class BillieTestEnv {
    public static void main(String[] args) {
        Contact test = new Contact();
        test.setFName("Jim");
        test.setLName("Jones");
        long phNum = 2105551234;
        test.setPhoneNum(test.formattedPhoneNum(phNum));
        test.setEmail("JJones@web.com");
        System.out.println(test.getPhoneNum());
        System.out.println(test.toString());
    }
}
