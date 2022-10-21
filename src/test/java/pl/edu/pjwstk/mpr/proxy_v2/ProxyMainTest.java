//package pl.edu.pjwstk.mpr.proxy_v2;
//
//
//import org.junit.Test;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//
//public class ProxyMainTest {
//
//    @Test
//    public void should_create_class_DesignPatternProxyV2() {
//        //assume
//        var designPatternClass = new ProxyMain();
//
//        //assert
//        assertThat(designPatternClass).isNotNull();
//    }
//
//    @Test
//    public void should_create_class_Image() {
//        //assume
//        var imageClass = new Image();
//
//        //assert
//        assertThat(imageClass).isNotNull();
//    }
//
//    @Test
//    public void should_create_class_ProxyImage() {
//        //assume
//        var proxyImage = new Image();
//
//        //assert
//        assertThat(proxyImage).isNotNull();
//    }
//
//    @Test
//    public void should_return_High_Resoultion_Image() {
//        //assume
//        var image = new Image();
//
//        //assert
//        try {
//            image.getImage();
//        } catch (Exception e) {
//            assertThatThrownBy(() -> {
//                throw e;
//            }).isInstanceOf(IllegalArgumentException.class)
//                    .hasMessage("There is no image.");
//        }
//
//    }
//
//    @Test
//    public void should_return_low_resolution_image() {
//        //assume
//        var image = new ProxyImage();
//
//        //assert
//        try {
//            image.getImage();
//        } catch (Exception e) {
//            assertThatThrownBy(() -> {
//                throw e;
//            }).isInstanceOf(IllegalArgumentException.class)
//                    .hasMessage("There is no image.");
//        }
//    }
//}