package ait.cohort46.accounting.service;

import ait.cohort46.accounting.dao.UserAccountRepository;
import ait.cohort46.accounting.dto.RolesDto;
import ait.cohort46.accounting.dto.UserDto;
import ait.cohort46.accounting.dto.UserEditDto;
import ait.cohort46.accounting.dto.UserRegisterDto;
import ait.cohort46.accounting.dto.exception.UserExistsException;
import ait.cohort46.accounting.dto.exception.UserNotFoundException;
import ait.cohort46.accounting.model.UserAccount;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userAccountRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto register(UserRegisterDto userRegisterDto) {
        if (userAccountRepository.existsById(userRegisterDto.getLogin())) {
            throw new UserExistsException();
        }
        UserAccount userAccount = modelMapper.map(userRegisterDto, UserAccount.class);
        String password = passwordEncoder.encode(userRegisterDto.getPassword());
        userAccount.setPassword(password);
        userAccountRepository.save(userAccount);
        return modelMapper.map(userAccount, UserDto.class);
    }

    @Override
    public UserDto getUser(String login) {
        UserAccount userAccount = userAccountRepository.findById(login).orElseThrow(UserNotFoundException::new);
        return modelMapper.map(userAccount, UserDto.class);
    }

    @Override
    public UserDto removeUser(String login) {
        UserAccount userAccount = userAccountRepository.findById(login).orElseThrow(UserNotFoundException::new);
        userAccountRepository.delete(userAccount);
        return modelMapper.map(userAccount, UserDto.class);
    }

    @Override
    public UserDto updateUser(String login, UserEditDto userEditDto) {
        UserAccount userAccount = userAccountRepository.findById(login).orElseThrow(UserNotFoundException::new);
        if (userEditDto.getFirstName() != null) {
            userAccount.setFirstName(userEditDto.getFirstName());
        }
        if (userEditDto.getLastName() != null) {
            userAccount.setLastName(userEditDto.getLastName());
        }
        userAccountRepository.save(userAccount);
        return modelMapper.map(userAccount, UserDto.class);
    }

    @Override
    public RolesDto changeRolesList(String login, String role, boolean isAddRole) {
        UserAccount userAccount = userAccountRepository.findById(login).orElseThrow(UserNotFoundException::new);
        boolean res;
        if (isAddRole) {
            res = userAccount.addRole(role);
        } else {
            res = userAccount.removeRole(role);
        }
        if(res) {
            userAccountRepository.save(userAccount);
        }
        return modelMapper.map(userAccount, RolesDto.class);
    }

    @Override
    public void changePassword(String login, String newPassword) {
        UserAccount userAccount = userAccountRepository.findById(login).orElseThrow(UserNotFoundException::new);
        String password = passwordEncoder.encode(newPassword);
        userAccount.setPassword(password);
        userAccountRepository.save(userAccount);
    }
}