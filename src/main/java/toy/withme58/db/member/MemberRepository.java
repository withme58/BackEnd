package toy.withme58.db.member;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.withme58.db.member.enums.MemberStatus;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity,Long> {

    //select * from user where email =? and password = ? and status = ? order by id desc limit 1
    Optional<MemberEntity> findFirstByEmailAndPasswordAndStatusOrderByIdDesc(String email, String password, MemberStatus status);

    //select * from user where id = ? and status = ? order by id desc limit 1
    Optional<MemberEntity> findFirstByIdAndStatusOrderByIdDesc(Long userId,MemberStatus status);

    //이름으로 조회
    Optional<MemberEntity> findFirstByNameOrderByIdDesc(String name);

    //이메일로 조회
    Optional<MemberEntity> findFirstByEmailOrderByIdDesc(String email);
}
