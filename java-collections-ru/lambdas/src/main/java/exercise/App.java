package exercise;

import java.util.Arrays;
import java.util.stream.Stream;

// BEGIN
class App {
    public static String[][] enlargeArrayImage(String[][] image) {

//        int lengthOfImage = (image.length != 0) ? image.length * 2 : 0;
//        String [][] res = new String[lengthOfImage][];
//        int count = 0;
//
//        for (int i = 1; i < res.length; i+=2) {
//            res[i] = new String[image[count].length * 2];
//
//            for (int j = 0; j < image.length; j++) {
//
//                res[i][j+j] = image[count][j];
//                res[i][j+j+1] = image[count][j];
//            }
//
//            res[i - 1] = Arrays.copyOf(res[i], res[i].length);
//            count++;
//        }

        return Arrays.stream(image)
                .map(innerStr -> {
                    return Arrays.stream(innerStr)
                            .flatMap(element -> Stream.of(element,element)).toArray(String[] :: new);
                })
                .flatMap(outerStr -> Stream.of(outerStr,outerStr))
                .toArray(String[][] :: new);
    }
}
// END
