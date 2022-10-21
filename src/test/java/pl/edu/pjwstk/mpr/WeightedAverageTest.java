//package pl.edu.pjwstk.mpr;
//
//import org.junit.Test;
//
//import java.io.StringReader;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//
//public class WeightedAverageTest {
//
//    @Test
//    public void should_create_class() {
//        // assume
//        var average = new WeightedAverage();
//
//        // assert
//        assertThat(average).isNotNull();
//    }
//
//    @Test
//    public void should_calculate_simple_average() {
//        // assume
//        var average = new WeightedAverage("average/test_avg_1.txt");
//
//        // act
//        double avg = average.calculate();
//
//        // assert
//        assertThat(avg).isEqualTo(2.0);
//    }
//
//    @Test
//    public void should_throw_when_file_does_not_exist() {
//        // assert
//        assertThatThrownBy(() -> {
//            new WeightedAverage("average/abstraction.txt").calculate();
//        }).isInstanceOf(IllegalArgumentException.class)
//                .hasMessage("File does not exist.");
//    }
//
//    @Test
//    public void should_throw_when_file_not_specifited() {
//
//        // assume = assert
//        try {
//            new WeightedAverage().calculate();
//        } catch (Exception e) {
//            assertThatThrownBy(() -> {
//                throw e;
//            }).isInstanceOf(IllegalArgumentException.class)
//                    .hasMessage("No file specified.");
//        }
//    }
//
//    @Test
//    public void should_throw_when_content_of_a_file_has_wrong_format() {
//        // assume + asserts
//        try {
//            new WeightedAverage("average/test_avg_wrong_content_format.txt").calculate();
//        } catch (Exception e) {
//            assertThatThrownBy(() -> {
//                throw e;
//            }).isInstanceOf(IllegalArgumentException.class)
//                    .hasMessage("Wrong data file format.");
//        }
//
//        try {
//            new WeightedAverage("average/test_avg_wrong_content_format_2.txt").calculate();
//        } catch (Exception e) {
//            assertThatThrownBy(() -> {
//                throw e;
//            }).isInstanceOf(IllegalArgumentException.class)
//                    .hasMessage("The data format in the file is invalid! Check it out!.");
//        }
//    }
//
//    @Test
//    public void should_calculate_average_of_large_file() { // max 1GB
//        // assume
//        var average = new WeightedAverage("average/test_avg_example_large_file.txt");
//
//        // act
//        double avg = average.calculate();
//
//        // assert
//        assertThat(avg).isEqualTo(5.59);
//    }
//
//    @Test
//    public void should_calculate_average_example_1() {
//        // assume
//        var average = new WeightedAverage("average/test_avg_example1.txt");
//
//        // act
//        double avg = average.calculate();
//
//        // assert
//        assertThat(avg).isEqualTo(4.36);
//    }
//
//    @Test
//    public void should_calculate_average_example_2() {
//        // assume
//        var average = new WeightedAverage("average/test_avg_example2.txt");
//
//        // act
//        double avg = average.calculate();
//
//        // assert
//        assertThat(avg).isEqualTo(2);
//    }
//
//    @Test
//    public void should_calculate_average_example_3(){
//        // assume
//        var average = new WeightedAverage("average\\test_avg_example3.txt");
//
//        // act
//        double avg = average.calculate();
//
//        // assert
//        assertThat(avg).isEqualTo(4);
//
//    }
//
//    @Test
//    public void should_remove_trailing_zeros_1() {
//        // assume
//        var average = new WeightedAverage("average\\test_avg_example_zeros_1.txt");
//
//        // act
//        String avg = average.resultAsString();
//
//        // assert
//        assertThat(avg).isEqualTo("1.22");
//    }
//
//
//    @Test
//    public void should_remove_trailing_zeros_2() {
//        // assume
//        var average = new WeightedAverage("average\\test_avg_example_zeros_2.txt");
//
//        // act
//        String avg = average.resultAsString();
//
//        // assert
//        assertThat(avg).isEqualTo("1.2");
//    }
//
//    @Test
//    public void should_remove_trailing_zeros_3() {
//        // assume
//        var average = new WeightedAverage("average\\test_avg_example_zeros_3.txt");
//
//        // act
//        String avg = average.resultAsString();
//
//        // assert
//        assertThat(avg).isEqualTo("1");
//    }
//
//
//}
