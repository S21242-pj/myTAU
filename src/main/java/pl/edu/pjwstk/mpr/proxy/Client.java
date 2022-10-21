package pl.edu.pjwstk.mpr.proxy;

public class Client implements User {

    public String getUserLogin() {
        return "UserLogin";
    }

    public String getUserPassword() {
        return "UserPassword";
    }

    public void someOperations() {
        if(getUserLogin().equals("UserLogin") && getUserPassword().equals("UserPassword")){
            Directory d = new Directory();
            d.someOperations();
        } else {
            System.out.println("Access denied");
        }
    }


}
