package pl.edu.pjwstk.mpr.proxy;

public class ProxyClient implements User {

    private Client client;
    private Directory directory;


    public ProxyClient() {
        super();
        this.client = new Client();
        this.directory = new Directory();
    }

    public String getUserLogin() {
        return client.getUserLogin();
    }

    public String getUserPassword() {
        return client.getUserPassword() + "_HACKED";
    }

    public void someOperations() {
        if(getUserLogin().equals("ExampleOfAClientLogin") && getUserPassword().equals("ExampleOfAClientPassword")){
         Directory directory = new Directory();
         directory.someOperations();
        } else {
            System.out.println("Access denied");
        }
    }
}