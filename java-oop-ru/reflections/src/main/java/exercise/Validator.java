package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// BEGIN
class Validator {
    public static List<String> validate(Address address) throws ClassNotFoundException, IllegalAccessException {

        List<String> nullFields = new ArrayList<>();
        Class adr = Class.forName("exercise.Address");
        Field [] fields = adr.getDeclaredFields();

        for (Field fld: fields) {
            fld.setAccessible(true);
            NotNull notNull = fld.getAnnotation(NotNull.class);
            if (notNull != null) {
                if (fld.get(address)==null) {
                    System.out.println(fld.getName());
                    nullFields.add(fld.getName());
                }
            }
        }
        return nullFields;
    }

    public static Map<String, List<String>> advancedValidate(Address address) throws ClassNotFoundException, IllegalAccessException {
        Map<String, List<String>> result = new HashMap<>();
        Class adr = Class.forName("exercise.Address");
        Field [] fields = adr.getDeclaredFields();

        for (Field fld: fields) {
            fld.setAccessible(true);
            NotNull notNull = fld.getAnnotation(NotNull.class);
            MinLength minLength = fld.getAnnotation(MinLength.class);

            if (notNull != null) {
                if (fld.get(address)==null) {
                    result.put(fld.getName(), List.of("can not be null"));
                }

                if (minLength != null) {
                    String str = (String)fld.get(address);
                    if (str.length() < 4 && fld.get(address) != null) {
                        result.put(fld.getName(), List.of("length less than 4"));
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException {
        Address address = new Address("USA", "Texas", null, "7", "2");
        Map<String, List<String>> notValidFields = Validator.advancedValidate(address);
        System.out.println(notValidFields);
    }
}
// END
