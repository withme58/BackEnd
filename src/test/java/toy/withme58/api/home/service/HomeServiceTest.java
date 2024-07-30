package toy.withme58.api.home.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import toy.withme58.api.home.converter.HomeConverter;
import toy.withme58.api.qustion.business.QuestionBusiness;
import toy.withme58.api.qustion.dto.QuestionsDto;
import toy.withme58.api.qustion.dto.response.MyQuestionResponse;
import toy.withme58.db.answer.AnswerEntity;
import toy.withme58.db.member.MemberEntity;
import toy.withme58.db.member.MemberRepository;
import toy.withme58.db.member.enums.MemberStatus;
import toy.withme58.db.question.QuestionEntity;
import toy.withme58.db.question.QuestionRepository;
import toy.withme58.db.question.enums.QuestionStatus;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Transactional
@Rollback(value = false)
class HomeServiceTest {

    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    HomeService homeService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    HomeConverter homeConverter;
    @Autowired
    QuestionBusiness questionBusiness;

    @Test
    public void findQuestion() {
        LocalDateTime localDateTime = LocalDateTime.now();
        questionRepository.save(new QuestionEntity("1", "A", QuestionStatus.REGISTERED));
        questionRepository.save(new QuestionEntity("2", "A", QuestionStatus.REGISTERED));
        questionRepository.save(new QuestionEntity("3", "A", QuestionStatus.REGISTERED));
        questionRepository.save(new QuestionEntity("4", "A", QuestionStatus.REGISTERED));
        questionRepository.save(new QuestionEntity("5", "A", QuestionStatus.REGISTERED));
        questionRepository.save(new QuestionEntity("6", "A", QuestionStatus.REGISTERED));
        questionRepository.save(new QuestionEntity("7", "A", QuestionStatus.REGISTERED));
        questionRepository.save(new QuestionEntity("8", "A", QuestionStatus.REGISTERED));
        questionRepository.save(new QuestionEntity("9", "A", QuestionStatus.REGISTERED));
        questionRepository.save(new QuestionEntity("10", "A", QuestionStatus.REGISTERED));

        MemberEntity member1 = MemberEntity.builder().email("1@naver.com")
                .password("1").name("김수민")
                .createdAt(localDateTime).status(MemberStatus.REGISTERED).build();

        memberRepository.save(member1);
        System.out.println("member1 = " + member1.getId());

        for (int i = 1; i <= 5; i++) {
            String question = homeService.findQuestion(1L);
            System.out.println("question = " + question);
        }
    }

    @Test
    public void saveQuestion() {
        LocalDateTime localDateTime = LocalDateTime.now();
        MemberEntity member1 = MemberEntity.builder().email("1@naver.com")
                .password("1").name("김수민")
                .createdAt(localDateTime).status(MemberStatus.REGISTERED).build();
        MemberEntity member2 = MemberEntity.builder().email("2@naver.com")
                .password("2").name("김민교")
                .createdAt(localDateTime).status(MemberStatus.REGISTERED).build();
        memberRepository.save(member1);
        memberRepository.save(member2);

        QuestionEntity question = questionRepository.save(new QuestionEntity("1", "A", QuestionStatus.REGISTERED));

        AnswerEntity answer = homeConverter.sendQuestionAnswer(homeService.makeSendQuestion(1L, 2L, question.getId()));
        homeService.saveQuestion(answer);
    }

    @Test
    public void showMyQuestions() {
        LocalDateTime localDateTime = LocalDateTime.now();
        MemberEntity member1 = MemberEntity.builder().email("1@naver.com")
                .password("1").name("김수민")
                .createdAt(localDateTime).status(MemberStatus.REGISTERED).build();
        MemberEntity member2 = MemberEntity.builder().email("2@naver.com")
                .password("2").name("김민교")
                .createdAt(localDateTime).status(MemberStatus.REGISTERED).build();
        MemberEntity member3 = MemberEntity.builder().email("3@naver.com")
                .password("3").name("이동호")
                .createdAt(localDateTime).status(MemberStatus.REGISTERED).build();
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);

        QuestionEntity question1 = questionRepository.save(new QuestionEntity("1", "A", QuestionStatus.REGISTERED));
        QuestionEntity question2 = questionRepository.save(new QuestionEntity("2", "B", QuestionStatus.REGISTERED));
        QuestionEntity question3 = questionRepository.save(new QuestionEntity("3", "C", QuestionStatus.REGISTERED));

        AnswerEntity answer1 = homeConverter.sendQuestionAnswer(homeService.makeSendQuestion(1L, 2L, question1.getId()));
        AnswerEntity answer2 = homeConverter.sendQuestionAnswer(homeService.makeSendQuestion(2L, 2L, question2.getId()));
        homeService.saveQuestion(answer1);
        homeService.saveQuestion(answer2);

        MyQuestionResponse myQuestionResponse = questionBusiness.myQuestionResponse(2L);
        List<QuestionsDto> question = myQuestionResponse.getQuestion();
        for (QuestionsDto questionsDto : question) {
            System.out.println("questionsDto = " + questionsDto.getQuestionName());
        }
    }
}