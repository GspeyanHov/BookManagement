package books.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    private int Id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private UserRole userRole;
    private String profilePic;
}
