package toy.withme58.api.answer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toy.withme58.api.common.error.ErrorCode;
import toy.withme58.api.common.exception.ApiException;
import toy.withme58.db.answer.AnswerEntity;
import toy.withme58.db.answer.AnswerRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;

    //생성 조회 삭제


    //****생성********



    //*****조회******
    //전체조회


    public List<AnswerEntity> getAllListBySenderId(Long senderId){

        return answerRepository.findAllBySenderIdOrderByIdDesc(senderId);
    }

    //단일조회
    public AnswerEntity getOneBySenderIdAndQuestionId(Long answerId){

        return answerRepository.findFirstByIdAndContentIsNotNullOrderByIdDesc(answerId)
                .orElseThrow(()->new ApiException(ErrorCode.NULL_POINT));
    }

    public List<AnswerEntity> getAllListByReceiverIdAndIsNotNull(Long receiverId){

        return answerRepository.findByReceiverIdAndContentIsNotNull(receiverId);
    }

    public List<AnswerEntity> getAllListBySenderIdAndIsNotNull(Long senderId){
        return answerRepository.findBySenderIdAndContentIsNotNull(senderId);
    }

    public Long getCountByReceiverId(Long receiverId){
        return answerRepository.countByReceiverIdAndContentIsNotNull(receiverId);
    }

    public Optional<AnswerEntity> getOneBySenderIdAndCreatedAt(Long senderId, LocalDate date){

        return answerRepository.findFirstBySenderIdAndCreatedAtOrderByIdDesc(senderId,date);
    }
}
