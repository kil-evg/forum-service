package ait.cohort46.accounting.service;

import ait.cohort46.accounting.dao.UserRepository;
import ait.cohort46.accounting.dto.UserChangeRoleDto;
import ait.cohort46.accounting.dto.UserDto;
import ait.cohort46.accounting.dto.UserRegisterDto;
import ait.cohort46.accounting.dto.UserUpdateDto;
import ait.cohort46.accounting.model.User;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import ait.cohort46.accounting.dto.exceptions.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, CommandLineRunner {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDto registryUser(UserRegisterDto userRegisterDto) {
        if (userRepository.existsById(userRegisterDto.getLogin())) {
            throw new UserAlreadyExistsException();
        }
        User user = modelMapper.map(userRegisterDto, User.class);
        user.addRole("USER");
        String password = BCrypt.hashpw(userRegisterDto.getPassword(), BCrypt.gensalt());
        user.setPassword(password);
        userRepository.save(user);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto loginUser() {
        return null;
    }

    @Override
    public UserDto deleteUser(String login) {
        User user = userRepository.findById(login).orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto updateUser(String login, UserUpdateDto userData) {
        User user = userRepository.findById(login).orElseThrow(UserNotFoundException::new);
        if (userData.getFirstName() != null) {
            user.setFirstName(userData.getFirstName());
        }
        if (userData.getLastName() != null) {
            user.setLastName(userData.getLastName());
        }
        userRepository.save(user);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserChangeRoleDto addRoleUser(String login, String role) {
        User user = userRepository.findById(login).orElseThrow(UserNotFoundException::new);
        user.addRole(role.toUpperCase());
        userRepository.save(user);
        return modelMapper.map(user, UserChangeRoleDto.class);
    }

    @Override
    public UserChangeRoleDto removeRoleUser(String login, String role) {
        User user = userRepository.findById(login).orElseThrow(UserNotFoundException::new);
        user.removeRole(role.toUpperCase());
        userRepository.save(user);
        return modelMapper.map(user, UserChangeRoleDto.class);
    }

    @Override
    public void changePassword(String login, String newPassword) {
        User user = userRepository.findById(login).orElseThrow(UserNotFoundException::new);
        String password = BCrypt.hashpw(newPassword, BCrypt.gensalt());
        user.setPassword(password);
        userRepository.save(user);
    }

    @Override
    public UserDto getUser(String login) {
        User user = userRepository.findById(login).orElseThrow(UserNotFoundException::new);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public void run(String... args) throws Exception {
        if (!userRepository.existsById("admin")) {
            String password = BCrypt.hashpw("admin", BCrypt.gensalt());
            User admin = new User("admin", "", "", password);
            admin.addRole("MODERATOR");
            admin.addRole("ADMINISTRATOR");
            userRepository.save(admin);
        }
    }
}
