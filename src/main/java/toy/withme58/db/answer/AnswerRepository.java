package toy.withme58.db.answer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {

    //생성 조회 삭제

    //memberId와 receiverId가 같은 것을 전체 보여주기
    //select * from answer where receiverId = ? order by id desc
    @Query("select a from AnswerEntity a join fetch a.question aq " +
            "where a.senderId = :senderId and a.content is not null " +
            "order by a.id desc")
    List<AnswerEntity> findAllBySenderIdOrderByIdDesc(@Param("senderId")Long receiverId);

    //answerId를 받은경우 상세 조회
    //select * from answer where answerId = ? order by id desc
    @Query("select a from AnswerEntity a join fetch a.question " +
            "Where a.id = :id and a.content is not null " +
            "Order by a.id desc")
    Optional<AnswerEntity> findFirstByIdAndContentIsNotNullOrderByIdDesc(Long id);

    @Query("select a from AnswerEntity a join fetch a.question " +
            "Where a.receiverId = :receiverId ")
    List<AnswerEntity> findAllByReceiverId(Long receiverId);

    @Query("select a from AnswerEntity a where a.senderId = :senderId and a.content is not null")
    List<AnswerEntity> findBySenderIdAndContentIsNotNull(@Param("senderId") Long senderId);

    @Query("select a from AnswerEntity a where a.receiverId = :receiverId and a.content is not null")
    List<AnswerEntity> findByReceiverIdAndContentIsNotNull(@Param("receiverId") Long senderId);

    @Query("select count(a) from AnswerEntity a where a.receiverId = :receiverId and a.content is not null")
    Long countByReceiverIdAndContentIsNotNull(@Param("receiverId") Long receiverId);

    @Query("select a from AnswerEntity a where a.senderId = :senderId And DATE(a.createdAt) = :date Order By a.id desc")
    Optional<AnswerEntity> findFirstBySenderIdAndCreatedAtOrderByIdDesc(@Param("senderId")Long senderId,@Param("date") LocalDate date);
}
