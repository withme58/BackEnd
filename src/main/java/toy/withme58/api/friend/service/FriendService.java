package toy.withme58.api.friend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toy.withme58.api.common.error.ErrorCode;
import toy.withme58.api.common.exception.ApiException;
import toy.withme58.db.friend.FriendEntity;
import toy.withme58.db.friend.FriendRepository;
import toy.withme58.db.friend.enums.FriendStatus;
import toy.withme58.db.member.MemberEntity;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;


    //생성 조회 삭제

    //*******생성**********
    //todo member business 에 friend 등록 추가하기
    public void register(FriendEntity friendEntity){
         friendRepository.save(friendEntity);
    }

    //********조회***********
    //friend Id 를 받으면 친구 정보를 보여줘요
    public FriendEntity searchOne(Long friendId){
        var friendEntity = friendRepository.findFirstByFriendIdAndStatusOrderByIdDesc(friendId, FriendStatus.REGISTERED);
        return friendEntity.orElseThrow(()-> new ApiException(ErrorCode.NULL_POINT, "해당 유저는 탈퇴했거나 없습니다"));
    }



}
