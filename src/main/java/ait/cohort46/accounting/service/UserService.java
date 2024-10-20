package ait.cohort46.accounting.service;

import ait.cohort46.accounting.dto.UserChangeRoleDto;
import ait.cohort46.accounting.dto.UserDto;
import ait.cohort46.accounting.dto.UserRegisterDto;
import ait.cohort46.accounting.dto.UserUpdateDto;

public interface UserService {
    UserDto registryUser(UserRegisterDto userRegisterDto);

    UserDto loginUser();

    UserDto deleteUser(String login);

    UserDto updateUser(String login, UserUpdateDto user);

    UserChangeRoleDto addRoleUser(String login, String role);

    UserChangeRoleDto removeRoleUser(String login, String role);

    void changePassword();

    UserDto getUser(String login);

}
