package exercise;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// BEGIN
class App {
    public static String[][] enlargeArrayImage(String[][] image) {

        int lengthOfImage = (image.length != 0) ? image.length * 2 : 0;
        String [][] res = new String[lengthOfImage][];
        int count = 0;

        for (int i = 1; i < res.length; i+=2) {
            res[i] = new String[image[count].length * 2];

            for (int j = 0; j < image.length; j++) {

                res[i][j+j] = image[count][j];
                res[i][j+j+1] = image[count][j];
            }

            res[i - 1] = Arrays.copyOf(res[i], res[i].length);
            count++;
        }

        return res;
    }

    public static void main(String[] args) {

        String[][] image = {
                {"*", "*", "*", "*"},
                {"*", " ", " ", "*"},
                {"*", " ", " ", "*"},
                {"*", "*", "*", "*"},
        };

//        String[][] res = Arrays.stream(image).map(String[] :: clone).toArray(String[][] :: new);

//        Arrays.stream(image).flatMap(x -> Arrays.stream(x)).forEach(x -> System.out.println(x));
//        Arrays.stream(image).flatMap(x -> Arrays.stream(x)).forEach(System.out::print);

//        for (String[] e : res) {
//            System.out.println(e);
//        }
        String[][] res = App.enlargeArrayImage(image);
        Arrays.stream(image).map(i -> i[3] + i[3]).forEach(System.out::print);
        System.out.println();
        Arrays.stream(res).flatMap(Arrays::stream).forEach(System.out::print);
//        System.out.println(Arrays.deepToString(res));
    }
}
// END
