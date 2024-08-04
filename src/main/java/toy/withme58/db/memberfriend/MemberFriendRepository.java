package toy.withme58.db.memberfriend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import toy.withme58.db.memberfriend.enums.MemberFriendStatus;

import java.util.List;
import java.util.Optional;

public interface MemberFriendRepository extends JpaRepository<MemberFriendEntity, Long> {



    @Query("select mf from MemberFriendEntity mf Join Fetch mf.friend Join Fetch mf.member Where mf.friend.id = :friendId")
    List<MemberFriendEntity> findAllByFriendId(@Param("friendId")Long memberId);
    //생성 조회 삭제

    //조회
    //memberId를 받으면 상태에 따라 친구list 를 보여준다
    //select * from member_friend where memberId = ? and status = ? order by id desc

    List<MemberFriendEntity> findAllByMemberIdAndStatusOrderByIdDesc(Long memberId, MemberFriendStatus status);

    //memberId 와 특정 frinedId 를 받으면 해당 개체 전달 상태에 따라 다름
    //select * from member_friend where memberId = ? and friendId = ? and status = ?order by id desc limit 1

    @Query("SELECT mf FROM MemberFriendEntity mf " +
            "JOIN FETCH mf.friend " +
            "JOIN FETCH mf.member " +
            "WHERE mf.member.id = :memberId " +
            "AND mf.friend.id = :friendId " +
            "AND mf.status = :status " +
            "ORDER BY mf.id DESC")
    Optional<MemberFriendEntity> findFirstByMemberIdAndFriendIdAndStatusOrderByIdDesc(
            @Param("memberId") Long memberId,
            @Param("friendId") Long friendId,
            @Param("status") MemberFriendStatus status);

}
