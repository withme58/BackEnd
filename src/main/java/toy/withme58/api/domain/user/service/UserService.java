package toy.withme58.api.domain.user.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toy.withme58.api.common.error.ErrorCode;
import toy.withme58.api.common.error.UserErrorCode;
import toy.withme58.api.common.exception.ApiException;
import toy.withme58.db.user.UserEntity;
import toy.withme58.db.user.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserEntity register(UserEntity userEntity){
        return Optional.ofNullable(userEntity)
                .map(it->{
                return userRepository.save(userEntity);
                }).orElseThrow(()->new ApiException(ErrorCode.NULL_POINT,"userEntity Null"));
    }


    public UserEntity login(String email, String password) {
        var userEntity = getUserWithThrow(email,password);
        return userEntity;
    }

    public UserEntity getUserWithThrow(
            String email,
            String password
    ){
        return userRepository.findFirstByEmailAndPasswordOrderByIdDesc(
                email,
                password
        ).orElseThrow(()->new ApiException(UserErrorCode.USER_NOT_FOUND));
    }

    public UserEntity getUserWithThrow(
            Long userId
    ){
        return userRepository.findFirstByIdOrderByIdDesc(userId)
                .orElseThrow(()-> new ApiException(UserErrorCode.USER_NOT_FOUND));
    }
}
