package ait.cohort46.accounting.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@EqualsAndHashCode(of = "login")
@NoArgsConstructor
@Document(collection = "users")
public class User {
    @Id
    private String login;
    @Setter
    private String firstName;
    @Setter
    private String lastName;
    private Set<String> roles = new HashSet<>();
    private String password;

    public boolean addRole(String role) {
        return roles.add(role);
    }

    public boolean removeRole(String role) {
        return roles.remove(role);
    }
}
