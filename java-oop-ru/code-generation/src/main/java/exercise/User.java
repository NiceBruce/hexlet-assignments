package exercise;

import lombok.*;

// BEGIN
//@Getter
//@Setter
//@RequiredArgsConstructor
@Value
// END
class User {
    int id;
    String firstName;
    String lastName;
    int age;
}
