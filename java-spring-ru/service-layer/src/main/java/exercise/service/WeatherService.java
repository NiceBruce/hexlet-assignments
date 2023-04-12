package exercise.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import exercise.HttpClient;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import exercise.CityNotFoundException;
import exercise.repository.CityRepository;
import exercise.model.City;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class WeatherService {

    @Autowired
    CityRepository cityRepository;

    // Клиент
    HttpClient client;

    // При создании класса сервиса клиент передаётся снаружи
    // В теории это позволит заменить клиент без изменения самого сервиса
    WeatherService(HttpClient client) {
        this.client = client;
    }

    // BEGIN
//    public City getCity(String name) throws CityNotFoundException{
//        City city = null;
//
//        try {
//            city = cityRepository.findByName(name);
//        } catch (CityNotFoundException e) {
//            new CityNotFoundException("City not found");
//        }
//
//        return city;
//    }

    public Map<String, String> getWether(String name) throws CityNotFoundException, JsonProcessingException {

        City city = null;

        try {
            city = cityRepository.findByName(name);
        } catch (CityNotFoundException e) {
            new CityNotFoundException("City not found");
        }

        String jsonString = client.get("http://weather/api/v2/cities/" + city.getName());
        Map<String, String> cityWeather = new ObjectMapper().readValue(jsonString, Map.class);  ;
        return cityWeather;
    }
    // END
}
