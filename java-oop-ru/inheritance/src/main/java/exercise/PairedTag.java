package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
class PairedTag extends Tag {
    String body;
    List<Tag> children;

    public PairedTag(String name, Map<String, String> property, String body, List<Tag> children) {
        super(name, property);
        this.body = body;
        this.children = children;
    }

    @Override
    public String toString() {

        String pattern = "%s%s</%s>";

        String child = children.stream()
                .map(e -> e.toString().trim())
                .collect(Collectors.joining(""));

        return String.format(pattern, super.toString(), body + child, name);
    }
}
// END
