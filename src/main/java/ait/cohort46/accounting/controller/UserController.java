package ait.cohort46.accounting.controller;

import ait.cohort46.accounting.dto.UserChangeRoleDto;
import ait.cohort46.accounting.dto.UserDto;
import ait.cohort46.accounting.dto.UserRegisterDto;
import ait.cohort46.accounting.dto.UserUpdateDto;
import ait.cohort46.accounting.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public UserDto registryUser(@RequestBody UserRegisterDto userRegisterDto) {
        return userService.registryUser(userRegisterDto);
    }

    @PostMapping("/login")
    public UserDto loginUser(Principal principal) {
        return userService.getUser(principal.getName());
    }

    @DeleteMapping("/user/{user}")
    public UserDto deleteUser(@PathVariable String user) {
        return userService.deleteUser(user);
    }

    @PatchMapping("user/{user}")
    public UserDto updateUser(@PathVariable String user, @RequestBody UserUpdateDto data) {
        return userService.updateUser(user, data);
    }

    @PatchMapping("/user/{user}/role/{role}")
    public UserChangeRoleDto addRoleUser(@PathVariable String user, @PathVariable String role) {
        return userService.addRoleUser(user, role);
    }

    @DeleteMapping("/user/{user}/role/{role}")
    public UserChangeRoleDto removeRoleUser(@PathVariable String user, @PathVariable String role) {
        return userService.removeRoleUser(user, role);
    }

    @PatchMapping("/user/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changePassword(Principal principal, @RequestHeader("X-Password") String newPassword) {
        userService.changePassword(principal.getName(), newPassword);
    }

    @GetMapping("/user/{user}")
    public UserDto getUser(@PathVariable String user) {
        return userService.getUser(user);
    }
}
