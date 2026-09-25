package app.mapper.user;

import app.model.dto.user.UserDto;
import app.model.dto.user.UserRegisterRequest;
import app.model.entity.user.User;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserMapper
{
    public static User toEntity(UserDto userDto)
    {
        return User.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .role(userDto.getRole())
                .email(userDto.getEmail())
                .build();
    }

    public static User toUserEntity(UserRegisterRequest userRegisterRequest)
    {
        if(userRegisterRequest == null)
        {
            return null;
        }

        return User.builder()
                .username(userRegisterRequest.getUsername())
                .password(userRegisterRequest.getPassword())
                .email(userRegisterRequest.getEmail())
                .role(userRegisterRequest.getUserRole())
                .build();
    }

    public static UserDto toUserDto(User user)
    {
        if(user == null)
        {
            return null;
        }

        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
