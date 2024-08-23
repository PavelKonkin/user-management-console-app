package management.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    private List<String> roles;
    private List<String> phones;
}
