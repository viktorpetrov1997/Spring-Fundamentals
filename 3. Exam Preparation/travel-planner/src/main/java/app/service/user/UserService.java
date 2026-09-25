package app.service.user;

import app.mapper.user.UserMapper;
import app.model.dto.user.UserDto;
import app.model.dto.user.UserLoginRequest;
import app.model.dto.user.UserRegisterRequest;
import app.model.dto.user.UserRole;
import app.model.entity.user.User;
import app.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UserService
{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder)
    {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto login(UserLoginRequest userLoginRequest)
    {
        Optional<User> optionalUser = userRepository.findByEmail(userLoginRequest.getEmail());

        if(optionalUser.isEmpty() || !passwordEncoder.matches(userLoginRequest.getPassword(), optionalUser.get().getPassword()))
        {

            throw new RuntimeException("Email or password mismatch!");
        }

        return UserMapper.toUserDto(optionalUser.get());
    }

    public UserDto register(UserRegisterRequest userRegisterRequest)
    {

        userRepository.findByEmail(userRegisterRequest.getEmail()).ifPresent(user -> {
            //TODO: Create custom exception e.g. UserAlreadyExistsException
            throw new RuntimeException("User with this email already exists!");
        });

        String encodedPassword = passwordEncoder.encode(userRegisterRequest.getPassword());
        userRegisterRequest.setPassword(encodedPassword);

        if(userRegisterRequest.getUserRole() == null)
        {
            userRegisterRequest.setUserRole(UserRole.USER);
        }

        User userEntity = UserMapper.toUserEntity(userRegisterRequest);

        userRepository.save(userEntity);

        return UserMapper.toUserDto(userEntity);
    }

    public List<UserDto> getAllUsers()
    {
        return userRepository.findAll().stream().map(UserMapper::toUserDto).toList();
    }

    public UserDto getUserById(UUID id)
    {
        return userRepository.findById(id).map(UserMapper::toUserDto).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
