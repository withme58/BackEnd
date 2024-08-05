package toy.withme58.db.memberquestion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;

public interface MemberQuestionRepository extends JpaRepository<MemberQuestionEntity, Long> {

    @Query("select mq from MemberQuestionEntity mq join fetch mq.member m join fetch mq.question q " +
            "Where m.id = :memberId and q.id = :questionId")
    Optional<MemberQuestionEntity> findFirstByMemberIdAndQuestionId(Long memberId, Long questionId);

    Optional<MemberQuestionEntity> findFirstByCreatedAtAndMemberId(LocalDate now, Long memberId);
}
