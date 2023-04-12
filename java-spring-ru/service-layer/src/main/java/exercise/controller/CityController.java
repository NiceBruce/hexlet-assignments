package exercise.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import exercise.CityNotFoundException;
import exercise.model.City;
import exercise.repository.CityRepository;
import exercise.service.WeatherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private WeatherService weatherService;

    // BEGIN
    @GetMapping(path = "cities/{id}")
    public Map<String, String> getCityWether(@PathVariable long id) {
        City city = cityRepository.findById(id).orElseThrow(() -> new CityNotFoundException("User not found"));

        try {
            return weatherService.getWether(city.getName());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
//    @GetMapping(path = "search")
//    public List<City> getCityes() {
//        return cityRepository.findAllByOrderByNameAsc();
//    }
    @GetMapping(path = "search")
    public List<Map<String, String>> getCityes(String name) {

        List<Map<String, String>> res = null;

        if (name == null) {
            res = cityRepository.findAllByOrderByNameAsc()
                    .stream()
                    .map(city -> {
                        Map<String, String> cityWeather;
                        try {
                            cityWeather = weatherService.getWether(city.getName());
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }
                        return Map.of("temperature", cityWeather.get("temperature"),
                                "name", cityWeather.get("name"));
                    }).collect(Collectors.toList());
        } else {
            res = cityRepository.findByNameContainsIgnoreCase(name)
                    .stream()
                    .map(city -> {
                        Map<String, String> cityWeather;
                        try {
                            cityWeather = weatherService.getWether(city.getName());
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }
                        return Map.of("temperature", cityWeather.get("temperature"),
                                "name", cityWeather.get("name"));
                    }).collect(Collectors.toList());
        }

        return res;
    }

    // END
}

