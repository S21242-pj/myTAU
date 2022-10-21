//package pl.edu.pjwstk.mpr.observer;
//
//import org.junit.Test;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//
//public class ObserverMainTest {
//
//    @Test
//    public void should_create_class_User() {
//        //assume
//        var user = new User("Szymon");
//
//        //assert
//        assertThat(user).isNotNull();
//    }
//
//    @Test
//    public void should_create_class_Blog() {
//        //assume
//        var blog = new Blog();
//
//        //assert
//        assertThat(blog).isNotNull();
//    }
//
//    @Test
//    public void should_user_subscribe_blog(){
//        //assume
//        var blog = new Blog();
//
//        //assert
//        try {
//            blog.subscribe(new User("Szymon"));
//        } catch (Exception e) {
//            assertThatThrownBy(() -> {
//                throw e;
//            }).isInstanceOf(IllegalArgumentException.class)
//                    .hasMessage("*error about the problem*");
//        }
//    }
//
//    @Test
//    public void should_start_observing_and_sending_new_emails() {
//        //assume
//        var blog = new Blog();
//
//        //assert
//        try {
//            blog.subscribe(new User("Patrycja"));
//        } catch (Exception e) {
//            assertThatThrownBy(() -> {
//                throw e;
//            }).isInstanceOf(IllegalArgumentException.class)
//                    .hasMessage("*some error explain about what is going wrong*");
//        }
//    }
//
//    @Test
//    public void should_throw_when_user_not_specified() {
//        //assume
//        var blog = new Blog();
//
//        // assert
//        try {
//            blog.subscribe(new User(""));
//        } catch (Exception e) {
//            assertThatThrownBy(() -> {
//                throw e;
//            }).isInstanceOf(IllegalArgumentException.class)
//                    .hasMessage("No file specified.");
//        }
//    }
//
//    @Test
//    public void should_user_got_mail(){
//        //assume
//        var blog = new Blog();
//
//        //act
//        blog.subscribe(new User("Patrycja"));
//
//        //assert
//        blog.startWork();
//    }
//
//    @Test
//    public void should_start_without_users(){
//        //assume
//        var blog = new Blog();
//
//        //assert
//        blog.startWork();
//    }
//
//    @Test
//    public void should_start_with_many_users(){
//        //assume
//        var blog = new Blog();
//
//        //act
//        blog.subscribe(new User("UserOneWantsNewsletter"));
//        blog.subscribe(new User("UserTwoWantsNewsletter"));
//        blog.subscribe(new User("UserThreeWantsNewsletter"));
//        blog.subscribe(new User("UserFourWantsNewsletter"));
//        blog.subscribe(new User("UserFiveWantsNewsletter"));
//        blog.subscribe(new User("UserSixWantsNewsletter"));
//        blog.subscribe(new User("UserSevenWantsNewsletter"));
//        blog.subscribe(new User("UserEightWantsNewsletter"));
//        blog.subscribe(new User("UserNineWantsNewsletter"));
//        blog.subscribe(new User("UserTenWantsNewsletter"));
//
//        //assert
//        blog.startWork();
//    }
//
//
//
//
//
//}
