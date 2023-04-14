package exercise;

import java.lang.reflect.Proxy;

import exercise.calculator.CalculatorImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// BEGIN
@Component
public class CustomBeanPostProcessor implements BeanPostProcessor {

    public static final Logger LOGGER = LoggerFactory.getLogger(CustomBeanPostProcessor.class);
    private HashMap<String, Class> beansWithAnnotation = new HashMap<>();
    private HashMap<String, String> loggingLevels = new HashMap<>();

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        if (bean.getClass().isAnnotationPresent(Inspect.class)) {
            String loggingLevel = bean.getClass().getAnnotation(Inspect.class).level();
            beansWithAnnotation.put(beanName, bean.getClass());
            loggingLevels.put(beanName, loggingLevel);
        }

        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if (!beansWithAnnotation.containsKey(beanName)) {
            return bean;
        }

        return Proxy.newProxyInstance(
                beansWithAnnotation.get(beanName).getClassLoader(),
                beansWithAnnotation.get(beanName).getInterfaces(),
     // Лямбда - обработчик вызова
     // В качестве аргумента принимает сам объект прокси, метод, к которому происходит обращение
     // и массив аргументов, переданных при вызове


                (proxy, method, args) -> {
                    String logMessage = "Was called method: %s() with arguments: %s"
                            .formatted(method.getName(),Arrays.toString(args));

                    switch (loggingLevels.get(beanName)) {
                        case "info" -> LOGGER.info(logMessage);
                        case "debug" -> LOGGER.debug(logMessage);
                    }

                    return method.invoke(bean, args);

     }
 );
    }
}
// END
