package contactManager;

public class Contact {
    private String fName;
    private String lName;
    private String phoneNum;
    private String email;

    public Contact(){

    }
    public Contact(String fName,String lName,String phoneNum,String email){
        this.fName = fName;
        this.lName = lName;
        this.phoneNum = phoneNum;
        this.email = email;
    }

    public String formattedPhoneNum(long phoneNumber){
        String formattedPhoneNum = phoneNumber+"";

        StringBuilder str = new StringBuilder();
        str
                .append("("+formattedPhoneNum.substring(0,3)+")")
                .append(formattedPhoneNum.substring(3,6)+"-")
                .append(formattedPhoneNum.substring(6,10));
        return str.toString();
    }

    //accessors

    public String getfName() {
        return fName;
    }

    @Override
    public String toString(){
        return String.format("First Name: %s, Last Name: %s, Phone Number: %s, Email: %s",this.fName, this.lName,this.phoneNum,this.email);
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
