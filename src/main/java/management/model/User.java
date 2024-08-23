package management.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class User {
    private String firstName;
    private String lastName;
    private String email;
    private List<String> roles;
    private List<String> phones;
}
