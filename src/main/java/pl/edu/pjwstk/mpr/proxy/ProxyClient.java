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
        return client.getUserPassword() /*+ "_H@CK3D_P@55w0rd"*/;
    }

    public void someOperations() {
        if(getUserLogin().equals("UserLogin") && getUserPassword().equals("UserPassword")){
         Directory directory = new Directory();
         directory.someOperations();
        } else {
            System.out.println("Access denied");
        }
    }
}