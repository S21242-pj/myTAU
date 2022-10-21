package pl.edu.pjwstk.mpr.proxy;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class proxyTest {

    @Test
    public void should_create_class_client(){
        //assume
        var client = new Client();

        //assert
        assertThat(client).isNotNull();
    }

    @Test
    public void should_create_class_directory(){
        //assume
        var directory = new Directory();

        //assert
        assertThat(directory).isNotNull();
    }

    @Test
    public void should_create_class_proxyclient(){
        //assume
        var proxyClient = new proxyTest();

        //assert
        assertThat(proxyClient).isNotNull();
    }

    @Test
    public void should_get_access_to_the_directory() {
        //assume
        User proxyClient = new ProxyClient();

        //asser

    }

    @Test
    public void should_not_have_acces_to_the_directory(){

    }

}
