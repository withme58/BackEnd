package toy.withme58.api.memberfriend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toy.withme58.api.common.error.ErrorCode;
import toy.withme58.api.common.exception.ApiException;
import toy.withme58.db.memberfriend.MemberFriendEntity;
import toy.withme58.db.memberfriend.MemberFriendRepository;
import toy.withme58.db.memberfriend.enums.MemberFriendStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberFriendService {
    //저장 조회 삭제

    private final MemberFriendRepository memberFriendRepository;

    //***********저장**********

    public MemberFriendEntity create(
            MemberFriendEntity memberFriendEntity
    ){
        return Optional.ofNullable(memberFriendEntity)
                .map(it->{
                    it.setCreatedAt(LocalDateTime.now());

                     it.setStatus(MemberFriendStatus.WAITING);

                    return memberFriendRepository.save(it);
                })
                .orElseThrow(()-> new ApiException(ErrorCode.NULL_POINT));
    }

    public MemberFriendEntity swapCreate(
            MemberFriendEntity memberFriendEntity
    ){
        return Optional.ofNullable(memberFriendEntity)
                .map(it->{

                    it.setRegisteredAt(LocalDateTime.now());
                    it.setStatus(MemberFriendStatus.REGISTERED);
                    return memberFriendRepository.save(it);
                })
                .orElseThrow(()-> new ApiException(ErrorCode.NULL_POINT));
    }

    //*************조회***********

    //memberId 와 friendId 받을시 특정 entry 전달하기
    //단 status 가 REGISTERED 인경우
    public MemberFriendEntity searchOneRegistered(Long memberId , Long friendId){
        //todo 주객이 전도된 상황이라 memberId와 friendId를 바꿔서 넣어야 된다. 어떻게 해결해야 될까?

        var memberFriendEntity = memberFriendRepository
                .findFirstByMemberIdAndFriendIdAndStatusOrderByIdDesc(friendId,memberId,MemberFriendStatus.REGISTERED);

        return memberFriendEntity.orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));

    }
    //단 status 가 WAITING 인경우
    public MemberFriendEntity searchOneWaiting(Long memberId , Long friendId){

        var memberFriendEntity = memberFriendRepository
                .findFirstByMemberIdAndFriendIdAndStatusOrderByIdDesc(friendId,memberId,MemberFriendStatus.WAITING);

        return memberFriendEntity.orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }


    //******상태변경하기********
    public MemberFriendEntity setStatus(MemberFriendEntity entity, MemberFriendStatus status){
        entity.setStatus(status);
        return memberFriendRepository.save(entity);
    }

    public MemberFriendEntity statusRegistered(MemberFriendEntity entity){
        entity.setRegisteredAt(LocalDateTime.now());
        return setStatus(entity, MemberFriendStatus.REGISTERED);
    }

    public MemberFriendEntity statusUnRegistered(MemberFriendEntity entity){
        entity.setRegisteredAt(LocalDateTime.now());
        return setStatus(entity, MemberFriendStatus.UNREGISTERED);
    }

    public MemberFriendEntity statusDeleted(MemberFriendEntity entity){
        entity.setRegisteredAt(LocalDateTime.now());
        return setStatus(entity, MemberFriendStatus.DELETED);
    }

}
