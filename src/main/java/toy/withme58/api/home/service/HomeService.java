package toy.withme58.api.home.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.withme58.api.common.error.ErrorCode;
import toy.withme58.api.common.exception.ApiException;
import toy.withme58.api.home.dto.response.MemberFriendDto;
import toy.withme58.api.home.dto.response.SendQuestionDto;
import toy.withme58.db.answer.AnswerEntity;
import toy.withme58.db.answer.AnswerRepository;
import toy.withme58.db.member.MemberEntity;
import toy.withme58.db.member.MemberRepository;
import toy.withme58.db.memberfriend.MemberFriendRepository;
import toy.withme58.db.memberfriend.enums.MemberFriendStatus;
import toy.withme58.db.memberquestion.MemberQuestionEntity;
import toy.withme58.db.memberquestion.MemberQuestionRepository;
import toy.withme58.db.question.QuestionEntity;
import toy.withme58.db.question.QuestionRepository;

import java.time.LocalDate;
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

    @Transactional
    public String findQuestion(Long memberId) {

        LocalDate now = LocalDate.now();
        if (memberQuestionRepository.findFirstByCreatedAtAndMemberId(now, memberId).isPresent()) { //현재 시간이 db에 존재하면
            MemberQuestionEntity memberQuestion = memberQuestionRepository.findFirstByCreatedAtAndMemberId(now, memberId)
                    .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
            return memberQuestion.getQuestion().getTitle();
        } else {

            Random random = new Random();

            List<Long> idxList = IntStream.rangeClosed(1, 10)
                    .filter(i -> memberQuestionRepository.findFirstByMemberIdAndQuestionId(memberId, (long) i).isEmpty())
                    .mapToObj(Long::valueOf)
                    .toList();

            int randomIdx = random.nextInt(idxList.size());
            Long questionId = idxList.get(randomIdx);

            saveData(memberId, questionId);

            return questionRepository.findById(questionId).get().getTitle();
        }
    }

    @Transactional
    public void saveData(Long memberId, Long questionId) {
        MemberEntity member = memberRepository.findById(memberId).orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
        QuestionEntity question = questionRepository.findById(questionId).orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
        LocalDate createAt = LocalDate.now();

        MemberQuestionEntity memberQuestionEntity = new MemberQuestionEntity(createAt, member, question);
        memberQuestionRepository.save(memberQuestionEntity);
    }

    @Transactional
    public List<MemberFriendDto> findMemberFriendEntity(Long memberId) {
        return memberFriendRepository.findAllByFriendId(memberId).stream()
                .filter(memberFriendEntity -> memberFriendEntity.getStatus() == MemberFriendStatus.REGISTERED)
                .map(memberFriendEntity -> new MemberFriendDto(
                        memberFriendEntity.getMember().getId(), memberFriendEntity.getMember().getName()
                ))
                .toList();
    }

    @Transactional
    public Long findReceiverIdByFriendName(String friendName) {
        MemberEntity member = memberRepository.findByName(friendName)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
        return member.getId();
    }

    @Transactional
    public Long findSenderId(Long memberId) {
        MemberEntity member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
        return member.getId();
    }

    @Transactional
    public void saveQuestion(AnswerEntity answer) {
        answerRepository.save(answer);
    }

    @Transactional
    public SendQuestionDto makeSendQuestion(Long senderId, Long receiverId, Long questionId) {
        QuestionEntity question = questionRepository.findById(questionId)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));

        return new SendQuestionDto(question, LocalDateTime.now(), receiverId, senderId);
    }
}
