package toy.withme58.api.member.converter;


import toy.withme58.api.common.annotation.Converter;
import toy.withme58.api.common.error.ErrorCode;
import toy.withme58.api.common.exception.ApiException;
import toy.withme58.api.member.dto.request.MemberRegisterRequest;
import toy.withme58.api.member.dto.response.MemberResponse;
import toy.withme58.db.member.MemberEntity;

import java.util.Optional;

@Converter
public class MemberConverter {

    public MemberEntity toMemberEntity(MemberRegisterRequest request){
        return Optional.ofNullable(request)
                .map(it->{
                    return MemberEntity.builder()
                            .name(request.getName())
                            .email(request.getEmail())
                            .password(request.getPassword())
                            .build();
                })
                .orElseThrow(()-> new ApiException(ErrorCode.NULL_POINT,"[MemberConverter-toMemberEntity()] request null"));

    }


    public MemberResponse toMemberResponse(MemberEntity memberEntity, Long giveAnswerCount){
        return Optional.ofNullable(memberEntity)
                .map(it->{
                    return MemberResponse.builder()
                            .id(memberEntity.getId())
                            .name(memberEntity.getName())
                            .email(memberEntity.getEmail())
                            .createdAt(memberEntity.getCreatedAt())
                            .status(memberEntity.getStatus())
                            .giveAnswerCount(giveAnswerCount.intValue())
                            .build();

                })
                .orElseThrow(()->new ApiException(ErrorCode.NULL_POINT,"[MemberConverter-toUserResponse()] MemberEntity null "));
    }

    public MemberResponse toMemberResponse(MemberEntity memberEntity){
        return Optional.ofNullable(memberEntity)
                .map(it->{
                    return MemberResponse.builder()
                            .id(memberEntity.getId())
                            .name(memberEntity.getName())
                            .email(memberEntity.getEmail())
                            .createdAt(memberEntity.getCreatedAt())
                            .status(memberEntity.getStatus())
                            .build();

                })
                .orElseThrow(()->new ApiException(ErrorCode.NULL_POINT,"[MemberConverter-toUserResponse()] MemberEntity null "));
    }
}
