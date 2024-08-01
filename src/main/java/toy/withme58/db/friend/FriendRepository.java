package toy.withme58.db.friend;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.withme58.db.friend.enums.FriendStatus;

import java.util.Optional;

public interface FriendRepository extends JpaRepository<FriendEntity,Long> {

    //생성 조회 삭제

    //friendId를 받을경우 해당 엔트리 보여주기
    //select * from friend where friendId = ? status = ? order by id desc limit 1
    Optional<FriendEntity> findFirstByIdAndStatusOrderByIdDesc(Long friendId, FriendStatus status);

    //이름으로 찾기
    Optional<FriendEntity> findFirstByNameAndStatusOrderByIdDesc(String name, FriendStatus status);


}
