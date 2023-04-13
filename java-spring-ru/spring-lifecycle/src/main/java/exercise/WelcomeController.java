package exercise;

import exercise.daytimes.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// BEGIN
@RestController
public class WelcomeController {

    MyApplicationConfig myConf = new MyApplicationConfig();

    @Autowired
    Meal meal;

    @Autowired
    Daytime daytime;

    @GetMapping("/daytime")
    public String getBonAppetit() {
        Daytime currentTime = myConf.getTimeOfDay();
        return  "It is %s now. Enjoy your %s".formatted(currentTime.getName(),
                meal.getMealForDaytime(currentTime.getName()));
    }
}
// END
