package toy.withme58.api.qustion.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toy.withme58.api.common.error.ErrorCode;
import toy.withme58.api.common.exception.ApiException;
import toy.withme58.db.answer.AnswerEntity;
import toy.withme58.db.answer.AnswerRepository;
import toy.withme58.db.member.MemberRepository;
import toy.withme58.db.question.QuestionEntity;
import toy.withme58.db.question.QuestionRepository;

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

    public List<AnswerEntity> findQuestionsByReceiverId(Long receiverId) {
        return answerRepository.findAllByReceiverId(receiverId);
    }

    public String findQuestionTitle(Long questionId) {
        return questionRepository.findById(questionId)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT)).getTitle();
    }

    public String findFriendNameBySenderId(Long senderId) {
        return memberRepository.findById(senderId)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT)).getName();
    }
}
