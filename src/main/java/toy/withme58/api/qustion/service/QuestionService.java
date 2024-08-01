package toy.withme58.api.qustion.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toy.withme58.api.common.error.ErrorCode;
import toy.withme58.api.common.exception.ApiException;
import toy.withme58.db.answer.AnswerEntity;
import toy.withme58.db.answer.AnswerRepository;
import toy.withme58.db.answer.enums.AnswerStatus;
import toy.withme58.db.member.MemberRepository;
import toy.withme58.db.question.QuestionEntity;
import toy.withme58.db.question.QuestionRepository;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final MemberRepository memberRepository;

    public List<QuestionEntity> findAll() {
        return questionRepository.findAll();
    }

    public List<AnswerEntity> findQuestionsByReceiverId(Long receiverId) { //이떄 답변이 미등록 된 것만 가져온다.
        return answerRepository.findAllByReceiverId(receiverId).stream()
                .filter(e -> e.getStatus() == AnswerStatus.UNREGISTERED)
                .toList();
    }

    public QuestionEntity findQuestionById(Long questionId){
        return questionRepository.findFirstByIdAndStatusOrderById(questionId,AnswerStatus.REGISTERED)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    public String findQuestionTitle(Long questionId) {
        return questionRepository.findById(questionId)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT)).getTitle();
    }

    public String findFriendNameBySenderId(Long senderId) {
        return memberRepository.findById(senderId)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT)).getName();
    }

    public void updateAnswer(Long answerId, String answerContent) {
        AnswerEntity answer = answerRepository.findById(answerId)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
        answer.setAnsweredAt(LocalDateTime.now());
        answer.setContent(answerContent);
        answer.setStatus(AnswerStatus.REGISTERED);
        answerRepository.save(answer);
    }

    public AnswerEntity findAnswerById(Long answerId) {
        return answerRepository.findById(answerId).orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }
}
