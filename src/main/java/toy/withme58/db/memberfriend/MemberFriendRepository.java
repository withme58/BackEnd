package toy.withme58.db.memberfriend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import toy.withme58.db.member.MemberEntity;

import java.util.List;
import java.util.Optional;

public interface MemberFriendRepository extends JpaRepository<MemberFriendEntity,Long> {

    //생성 조회 삭제

    //조회
    //memberId를 받으면 해당하는 친구list 를 보여준다
    //select * from member_friend where memberId = ? order by id desc

    List<MemberFriendEntity> findAllByMemberIdOrderByIdDesc(Long memberId);

    //memberId 와 특정 frinedId 를 받으면 해당 개체 전달
    //select * from member_friend where memberId = ? friendId = ? order by id desc

    Optional<MemberFriendEntity> findFirstByMemberIdAndFriendIdOrderByIdDesc(Long memberId, Long friendId);


    //삭제
    //memberId 와 특정 friendId 를 받으면 해당 개체를 삭제해주세요
    @Modifying
    @Transactional
    @Query("DELETE FROM MemberFriendEntity m where m.memberId = :memberId and m.friendId = :friendId")
    void deleteByMemberIdAndFriendId(@Param("memberId") Long memberId , @Param("friendId") Long friendId);

}
