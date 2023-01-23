package exercise;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

// BEGIN
class App {
    public static Car extract(Path path) {
        String jsonContent = "";

        try {
            jsonContent = Files.readString(path);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            return Car.unserialize(jsonContent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void save(Path path, Car car) throws JsonProcessingException {
        String jsonContent = Car.serialize(car);

        try {
            Files.writeString(path, jsonContent, StandardOpenOption.WRITE);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
// END
