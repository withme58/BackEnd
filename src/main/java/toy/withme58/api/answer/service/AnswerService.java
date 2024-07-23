package toy.withme58.api.answer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toy.withme58.api.common.error.ErrorCode;
import toy.withme58.api.common.exception.ApiException;
import toy.withme58.db.answer.AnswerEntity;
import toy.withme58.db.answer.AnswerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;

    //생성 조회 삭제


    //****생성********



    //*****조회******
    //전체조회
    public List<AnswerEntity> getAllListByReceiverId(Long receiverId){

        return answerRepository.findAllByReceiverIdOrderByDesc(receiverId);
    }

    //단일조회
    public AnswerEntity getOneByReceiverIdAndQuestionId(Long receiverId, Long questionId){

        return answerRepository.findFirstByReceiverIdAndQuestionIdOrderByDesc(receiverId,questionId)
                .orElseThrow(()->new ApiException(ErrorCode.NULL_POINT));
    }
}
