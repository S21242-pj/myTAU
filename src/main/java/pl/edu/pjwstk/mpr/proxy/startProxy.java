package pl.edu.pjwstk.mpr.proxy;

public class startProxy {

    public static void main(String[] args) {
        System.out.println("Design pattern: Proxy" );

        System.out.println("\nWithout proxy");
        User client = new Client();
        System.out.println("login: " + client.getUserLogin() + ", pass: " + client.getUserPassword());
        client.someOperations();

        System.out.println("\nWith proxy");
        User proxyClient = new ProxyClient();
        System.out.println("login: " + proxyClient.getUserLogin() + ", pass: " + proxyClient.getUserPassword());
        proxyClient.someOperations();

    }
}
