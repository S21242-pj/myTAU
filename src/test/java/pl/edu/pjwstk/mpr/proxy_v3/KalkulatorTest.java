package pl.edu.pjwstk.mpr.proxy_v3;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class KalkulatorTest {

    @Test
    public void should_create_class_kalkulator(){
        //assume
        var x = new KalkulatorProxy("1111");

        //assert
        assertThat(x).isNotNull();
    }

    @Test
    public void should_enter_to_kalkulator_by_proxy(){
        //assume
        var x = new KalkulatorProxy("1111");

        //assert
        assertThat(x.podaneHaslo).isEqualTo("1111");
    }

    @Test
    public void should_calc_sum_of_two_int() {
        //assume
        var x = new KalkulatorProxy("0000");

        //assert
        assert(x.sumowanie(1,2) == 3);
    }

    @Test
    public void should_throw_error_becouse_wrong_password(){
        //assume
        var x = new KalkulatorProxy("2222");

        assertThatThrownBy(() ->
        x.sumowanie(1,2)).isInstanceOf(RuntimeException.class).hasMessage("Password Error") ;

    }




}