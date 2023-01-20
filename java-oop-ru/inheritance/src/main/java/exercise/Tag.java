package exercise;

import java.util.LinkedHashMap;
import java.util.stream.Collectors;
import java.util.Map;

// BEGIN
class Tag {
    public String name;
    private Map<String, String> property;

    public Tag(String name, Map<String, String> property) {
        this.name = name;
        this.property = property;
    }

    public String toString() {
        String pattern = "<" + name;

        String res = (property.size() == 0) ? "" : " " + property.entrySet().stream()
                .map(e -> {
                    return String.format("%s=\"%s\"", e.getKey(), e.getValue());
                }).collect(Collectors.joining(" "));

        return pattern + res + ">";
    }
}


// END
