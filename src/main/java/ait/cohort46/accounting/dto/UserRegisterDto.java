package ait.cohort46.accounting.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegisterDto {
    private String login;
    private String password;
    private String firstName;
    private String lastName;
}
