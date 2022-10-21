package pl.edu.pjwstk.mpr.observer;

public class ObserverMain {
    public static void main(String[] args) {
        Blog blog = new Blog();
        blog.subscribe(new User("Szymon"));
        blog.subscribe(new User("Patrycja"));
        blog.subscribe(new User("Bożydar"));
        blog.subscribe(new User("asdsadsad"));
        blog.startWork();
    }
}
