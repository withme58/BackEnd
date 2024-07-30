package toy.withme58.db.answer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {

    //생성 조회 삭제

    //memberId와 receiverId가 같은 것을 전체 보여주기
    //select * from answer where receiverId = ? order by id desc
    List<AnswerEntity> findAllByReceiverIdOrderByIdDesc(Long receiverId);

    //memberId와 questionId 를 받은경우 상세 조회
    //select * from answer where receiverId = ? and questionId = ? order by id desc
    Optional<AnswerEntity> findFirstByReceiverIdAndQuestionIdOrderByIdDesc(Long receiverId, Long questionId);

    List<AnswerEntity> findAllByReceiverId(Long receiverId);
}
