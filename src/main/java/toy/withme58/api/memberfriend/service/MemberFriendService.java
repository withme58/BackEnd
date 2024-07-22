package toy.withme58.api.memberfriend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toy.withme58.api.common.error.ErrorCode;
import toy.withme58.api.common.exception.ApiException;
import toy.withme58.db.memberfriend.MemberFriendEntity;
import toy.withme58.db.memberfriend.MemberFriendRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberFriendService {
    //저장 조회 삭제

    private MemberFriendRepository memberFriendRepository;

    //***********저장**********

    public MemberFriendEntity create(
            MemberFriendEntity memberFriendEntity
    ){
        return Optional.ofNullable(memberFriendEntity)
                .map(it->{
                    it.setCreatedAt(LocalDateTime.now());
                    return memberFriendRepository.save(it);
                })
                .orElseThrow(()-> new ApiException(ErrorCode.NULL_POINT));
    }


    //*************조회***********

    //memberId 받을 시 모든 friendId List 로 넘겨주기

    public List<MemberFriendEntity> searchListByMemberId(Long memberId){
        return memberFriendRepository.findAllByMemberIdOrderByIdDesc(memberId);
    }

    //memberId 와 friendId 받을시 특정 entry 전달하기

    public MemberFriendEntity searchOne(Long memberId , Long friendId){
        var memberFriendEntity = memberFriendRepository.findFirstByMemberIdAndFriendIdOrderByIdDesc(memberId,friendId);
        return memberFriendEntity.orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));

    }

    //********삭제************

    public void deleteOne(Long memberId, Long friendId){
        memberFriendRepository.deleteByMemberIdAndFriendId(memberId,friendId);
    }

}
