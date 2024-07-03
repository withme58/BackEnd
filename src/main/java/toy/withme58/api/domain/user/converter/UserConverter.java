package toy.withme58.api.domain.user.converter;

import toy.withMe58.api.common.annotation.Converter;
import toy.withMe58.api.common.error.ErrorCode;
import toy.withMe58.api.common.exception.ApiException;
import toy.withMe58.api.domain.user.controller.model.UserRegisterRequest;
import toy.withMe58.api.domain.user.controller.model.UserResponse;
import toy.withMe58.db.user.UserEntity;

import java.util.Optional;

@Converter
public class UserConverter {

    public UserEntity toUserEntity(UserRegisterRequest request){
        return Optional.ofNullable(request)
                .map(it->{
                    return UserEntity.builder()
                            .nickName(request.getNickName())
                            .email(request.getEmail())
                            .password(request.getPassword())
                            .build();
                })
                .orElseThrow(()-> new ApiException(ErrorCode.NULL_POINT,"[UserConverter-toUserEntity()] request null"));

    }

    public UserResponse toUserResponse(UserEntity userEntity){
        return Optional.ofNullable(userEntity)
                .map(it->{
                    return UserResponse.builder()
                            .id(userEntity.getId())
                            .nickName(userEntity.getNickName())
                            .email(userEntity.getEmail())
                            .build();

                })
                .orElseThrow(()->new ApiException(ErrorCode.NULL_POINT,"[UserConverter-toUserResponse()] UserEntity null "));
    }
}
