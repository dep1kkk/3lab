package ru.tgbot.tgbot.service;

import ru.tgbot.tgbot.exceptions.UsernameAlreadyExistsException;
import ru.tgbot.tgbot.model.User;
import ru.tgbot.tgbot.model.UserAuthority;
import ru.tgbot.tgbot.model.UserRole;
import ru.tgbot.tgbot.repository.UserRepository;
import ru.tgbot.tgbot.repository.UserRolesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceInterface, UserDetailsService {

    private final UserRolesRepository userRolesRepository;
    private final UserRepository userRepository;

    @Override
    public void registration(String username, String password) {
        if (userRepository.findByUsername(username).isEmpty()){
            User user = userRepository.save(
                    new User()
                            .setId(null)
                            .setUsername(username)
                            .setPassword(password)
                            .setLocked(false)
                            .setExpired(false)
                            .setEnabled(true));
            userRolesRepository.save(new UserRole(null, UserAuthority.USER, user));
        }
        else{
            throw new UsernameAlreadyExistsException();
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
