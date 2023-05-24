package exercise;

import java.util.concurrent.CompletableFuture;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

class App {

    // BEGIN
    public static CompletableFuture<String> unionFiles(String source_path1,
                                                       String source_path2,
                                                       String joinPath) {

        CompletableFuture<String> future_1 = CompletableFuture.supplyAsync(() -> {
            try {
                return readFile(source_path1);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        CompletableFuture<String> future_2 = CompletableFuture.supplyAsync(() -> {
            try {
                return readFile(source_path2);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        CompletableFuture<String> future_res = future_1.thenCombine(future_2, (str1, str2) -> {
            String result = str1 + str2;
            System.out.println("result" + result);
            try {
                writeFile(joinPath, result);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return result;
        }).exceptionally(ex -> {
            System.out.println("NoSuchFileException");
            return null;
        });
        System.out.println("fures " + future_res);
        return future_res;
    }

    public static String readFile(String filePath) throws Exception {
        Path path = Paths.get(filePath).toAbsolutePath().normalize();
        System.out.println(path.toString());
        if (!Files.exists(path)) {
            throw new Exception("File " + filePath + " does not exist");
        }

        return Files.readString(path);
    }

    public static void writeFile(String filePath, String content) throws Exception {
        Path path = Paths.get(filePath).toAbsolutePath().normalize();

        Files.writeString(path, content);
    }
    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        CompletableFuture<String> res = App.unionFiles(
                "src\\main\\resources\\file1.txt",
                "src\\main\\resources\\file2.txt",
                "src\\main\\resources\\dest.txt");
        System.out.println(res.get());
        // END
    }
}

