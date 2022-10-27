package exercise;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AppTest {

    private static final List<Integer> ELEMENTS = Arrays.asList(1,2,3,4,5,6);


    @Test
    void testTake1() {
        // BEGIN

        int count = 7;
        List<Integer> expected = new ArrayList<>();
        expected.addAll(0, ELEMENTS);

        List<Integer> actual = App.take(ELEMENTS, count);

        assertThat(actual).isEqualTo(expected);
        // END
    }

    @Test
    void testTake2() {
        // BEGIN

        int count = 2;
        List<Integer> expected = new ArrayList<>(ELEMENTS.subList(0, count));

        List<Integer> actual = App.take(ELEMENTS, count);

        assertThat(actual).isEqualTo(expected);
        // END
    }

    @Test
    void testTake3() {
        // BEGIN

        int count = 0;

        List<Integer> actual = App.take(ELEMENTS, count);

        assertThat(actual).isEmpty();
        // END
    }
}
