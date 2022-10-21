package pl.edu.pjwstk.mpr.observer;

public class User implements MailObserver {

    private String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public void newsletter(String mail) {
        System.out.println(name + " got mail: " + mail);
    }
}