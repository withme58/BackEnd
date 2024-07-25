package toy.withme58.api.home.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toy.withme58.api.common.error.ErrorCode;
import toy.withme58.api.common.exception.ApiException;
import toy.withme58.api.home.converter.HomeConverter;
import toy.withme58.api.home.dto.response.MemberFriendDto;
import toy.withme58.api.home.dto.response.SendQuestionDto;
import toy.withme58.db.answer.AnswerEntity;
import toy.withme58.db.answer.AnswerRepository;
import toy.withme58.db.answer.enums.AnswerStatus;
import toy.withme58.db.member.MemberEntity;
import toy.withme58.db.member.MemberRepository;
import toy.withme58.db.memberfriend.MemberFriendRepository;
import toy.withme58.db.memberfriend.enums.MemberFriendStatus;
import toy.withme58.db.memberquestion.MemberQuestionEntity;
import toy.withme58.db.memberquestion.MemberQuestionRepository;
import toy.withme58.db.question.QuestionEntity;
import toy.withme58.db.question.QuestionRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Service
public class HomeService {

    private final MemberQuestionRepository memberQuestionRepository;
    private final MemberRepository memberRepository;
    private final QuestionRepository questionRepository;
    private final MemberFriendRepository memberFriendRepository;
    private final AnswerRepository answerRepository;
    private final HomeConverter homeConverter;

    public String findQuestion(Long memberId) {
        Random random = new Random();

        List<Long> idxList = IntStream.rangeClosed(1, 10)
                .filter(i -> memberQuestionRepository.findByMemberIdAndQuestionId(memberId, (long) i).isEmpty())
                .mapToObj(Long::valueOf)
                .toList();

        int randomIdx = random.nextInt(idxList.size());
        Long questionId = idxList.get(randomIdx);

        saveData(memberId, questionId);

        return questionRepository.findById(questionId).get().getTitle();
    }

    private void saveData(Long memberId, Long questionId) {
        MemberEntity member = memberRepository.findById(memberId).orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
        QuestionEntity question = questionRepository.findById(questionId).orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
        LocalDateTime createAt = LocalDateTime.now();

        MemberQuestionEntity memberQuestionEntity = new MemberQuestionEntity(createAt, member, question);
        memberQuestionRepository.save(memberQuestionEntity);
    }

    public List<MemberFriendDto> findMemberFriendEntity(Long memberId) {
        return memberFriendRepository.findAllByFriendId(memberId).stream()
                .filter(memberFriendEntity -> memberFriendEntity.getStatus() == MemberFriendStatus.REGISTERED)
                .map(memberFriendEntity -> new MemberFriendDto(
                        memberFriendEntity.getMember().getId(), memberFriendEntity.getMember().getName()
                ))
                .toList();
    }

    public Long findReceiverIdByFriendName(String friendName) {
        MemberEntity member = memberRepository.findByName(friendName)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
        return member.getId();
    }

    public Long findSenderId(Long memberId) {
        MemberEntity member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
        return member.getId();
    }

    public void saveQuestion(AnswerEntity answer) {
        answerRepository.save(answer);
    }

    public SendQuestionDto makeSendQuestion(Long senderId, Long receiverId, Long questionId) {
        QuestionEntity question = questionRepository.findById(questionId)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));

        return new SendQuestionDto(question, LocalDateTime.now(), receiverId, senderId);
    }
}
