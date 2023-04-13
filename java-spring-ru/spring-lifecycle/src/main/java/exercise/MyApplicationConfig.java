package exercise;

import java.time.LocalDateTime;

import org.apache.tomcat.jni.Local;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import exercise.daytimes.Daytime;
import exercise.daytimes.Morning;
import exercise.daytimes.Day;
import exercise.daytimes.Evening;
import exercise.daytimes.Night;

// BEGIN
@Configuration
public class MyApplicationConfig {

    public static int getCurrentHour() {
        return LocalDateTime.now().getHour();
    }
    @Bean
    public Daytime getTimeOfDay() {
        int currentHour = getCurrentHour();
        System.out.println(currentHour);

        if (currentHour >= 6 && currentHour < 12) {
            return new Morning();
        } else if (currentHour >= 12 && currentHour < 18) {
            return new Day();
        } else if (currentHour >= 18 && currentHour < 23) {
            return new Evening();
        } else {
            return new Night();
        }
    }
}
// END
