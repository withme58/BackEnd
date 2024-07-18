package toy.withme58.api.domain.member.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toy.withme58.api.common.error.ErrorCode;
import toy.withme58.api.common.error.MemberErrorCode;
import toy.withme58.api.common.exception.ApiException;
import toy.withme58.db.member.MemberEntity;
import toy.withme58.db.member.MemberRepository;
import toy.withme58.db.member.enums.MemberStatus;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberEntity register(MemberEntity memberEntity){
        return Optional.ofNullable(memberEntity)
                .map(it->{


                    it.setCreatedAt(LocalDateTime.now());
                    it.setStatus(MemberStatus.REGISTERED);
                return memberRepository.save(it);
                }).orElseThrow(()->new ApiException(ErrorCode.NULL_POINT,"memberEntity Null"));
    }


    public MemberEntity login(String email, String password) {
        var memberEntity = getMemberWithThrow(email,password);
        return memberEntity;
    }

    public MemberEntity getMemberWithThrow(
            String email,
            String password
    ){
        return memberRepository.findFirstByEmailAndPasswordAndStatusOrderByIdDesc(
                email,
                password,
                MemberStatus.REGISTERED
        ).orElseThrow(()->new ApiException(MemberErrorCode.Member_Not_Found));
    }

    public MemberEntity getMemberWithThrow(
            Long memberId
    ){
        return memberRepository.findFirstByIdAndStatusOrderByIdDesc(memberId, MemberStatus.REGISTERED)
                .orElseThrow(()-> new ApiException(MemberErrorCode.Member_Not_Found));
    }


    //아이디 중복 체크 찾는 용도
    public Optional<MemberEntity> getMemberByNameWithThrow(
            String name
    ){
        return memberRepository.findFirstByNameOrderByIdDesc(name);

    }

    public Optional<MemberEntity> getMemberByEmailWithThrow(
            String email
    ){
        return memberRepository.findFirstByEmailOrderByIdDesc(email);

    }
}
