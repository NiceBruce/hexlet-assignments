package exercise;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Value;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.IOException;


// BEGIN
@Value
// END
class Car {
    int id;
    String brand;
    String model;
    String color;
    User owner;

    // BEGIN
    public static String serialize(Car car) throws JsonProcessingException {
        ObjectMapper dataMapper = new ObjectMapper();
        return dataMapper.writeValueAsString(car);
    }

    public static Car unserialize(String dataJson) throws IOException {
        ObjectMapper dataMapper = new ObjectMapper();
        return dataMapper.readValue(dataJson, Car.class);
    }
    // END
}
