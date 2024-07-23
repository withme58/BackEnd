package toy.withme58.db.memberfriend;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberFriendRepository extends JpaRepository<MemberFriendEntity,Long> {
    List<MemberFriendEntity> findAllByFriendId(Long memberId);
}
