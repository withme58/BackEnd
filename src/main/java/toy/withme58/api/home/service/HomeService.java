package toy.withme58.api.home.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toy.withme58.db.member.MemberEntity;
import toy.withme58.db.member.MemberRepository;
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

    public String findQuestion(Long memberId) {

        Random random = new Random();

        List<Long> idxList = IntStream.rangeClosed(1, 10)
                .filter(i -> memberQuestionRepository.findByMemberIdAndQuestionId(memberId, (long) i).isEmpty())
                .mapToObj(Long::valueOf)
                .toList();

        int randomIdx = random.nextInt(idxList.size());
        Long questionId = idxList.get(randomIdx);

        MemberEntity member = memberRepository.findById(memberId).get();
        QuestionEntity question = questionRepository.findById(questionId).get();
        LocalDateTime createAt = LocalDateTime.now();

        MemberQuestionEntity memberQuestionEntity = new MemberQuestionEntity(createAt, member, question);
        memberQuestionRepository.save(memberQuestionEntity);
        
        return null;
    }
}
