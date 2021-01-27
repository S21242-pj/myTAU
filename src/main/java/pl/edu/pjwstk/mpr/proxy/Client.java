package pl.edu.pjwstk.mpr.proxy;

public class Client implements User {

    public String getUserLogin() {
        return "ExampleOfAClientLogin";
    }

    public String getUserPassword() {
        return "ExampleOfAClientPassword";
    }

    public void someOperations() {
        if(getUserLogin().equals("ExampleOfAClientLogin") && getUserPassword().equals("ExampleOfAClientPassword")){
            Directory d = new Directory();
            d.someOperations();
        } else {
            System.out.println("Access denied");
        }
    }


}
