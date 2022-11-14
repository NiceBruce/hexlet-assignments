package exercise;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

// BEGIN
class AppTest {
    @Test
    void testApp() {
        String[][] image = {
                {"*", "*", "*", "*"},
                {"*", " ", " ", "*"},
                {"*", " ", " ", "*"},
                {"*", "*", "*", "*"},
        };

        String[][] actual = App.enlargeArrayImage(image);

        String[][] expected = {
                {"*", "*", "*", "*","*", "*", "*", "*"},
                {"*", "*", "*", "*","*", "*", "*", "*"},
                {"*", "*", " ", " ", " ", " ", "*", "*"},
                {"*", "*", " ", " ", " ", " ", "*", "*"},
                {"*", "*", " ", " ", " ", " ", "*", "*"},
                {"*", "*", " ", " ", " ", " ", "*", "*"},
                {"*", "*", "*", "*","*", "*", "*", "*"},
                {"*", "*", "*", "*","*", "*", "*", "*"},
        };
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testAppEmpty() {
        String[][] image = {};

        String[][] actual = App.enlargeArrayImage(image);
        String[][] expected = {};

        assertThat(actual).isEqualTo(expected);
    }


    @Test
    void testAppWitnOneElement() {
        String[][] image = {
                {"*"},
        };

        String[][] actual = App.enlargeArrayImage(image);

        String[][] expected = {
                {"*", "*"},
                {"*", "*"},

        };
        assertThat(actual).isEqualTo(expected);
    }
}
// END
