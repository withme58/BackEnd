package toy.withme58.api.member.business;


import lombok.RequiredArgsConstructor;
import toy.withme58.api.answer.service.AnswerService;
import toy.withme58.api.common.annotation.Business;
import toy.withme58.api.common.error.ErrorCode;
import toy.withme58.api.common.error.MemberErrorCode;
import toy.withme58.api.common.exception.ApiException;
import toy.withme58.api.common.token.business.TokenBusiness;
import toy.withme58.api.common.token.controller.model.TokenResponse;
import toy.withme58.api.friend.business.FriendBusiness;
import toy.withme58.api.member.dto.request.MemberLoginRequest;
import toy.withme58.api.member.dto.request.MemberRegisterRequest;
import toy.withme58.api.member.dto.response.MemberResponse;
import toy.withme58.api.member.converter.MemberConverter;
import toy.withme58.api.member.service.MemberService;

@Business
@RequiredArgsConstructor
public class MemberBusiness {

    private final MemberService memberService;
    private final MemberConverter memberConverter;

    private final TokenBusiness tokenBusiness;

   private final FriendBusiness friendBusiness;

    private final AnswerService answerService;
    /*
    * 1. request -> entity  로 변환
    * 2. entity -> service를 호출하여 저장
    * 3. 저장된 entity를 userResponse 로 변환 후 리턴
    * */

    public MemberResponse register(MemberRegisterRequest memberRegisterRequest){

        validateDuplicate(memberRegisterRequest);

        var entity = memberConverter.toMemberEntity(memberRegisterRequest);

        var memberEntity = memberService.register(entity);

        friendBusiness.registerFriend(memberEntity);


        var response = memberConverter.toMemberResponse(memberEntity);

        return response;
    }

    public TokenResponse login(MemberLoginRequest request) {
        var userEntity = memberService.login(request.getEmail(),request.getPassword());

        //토큰 발행

        var tokenResponse = tokenBusiness.issueToken(userEntity);
        return tokenResponse;
    }

    //userid -> service 에서 정보 가져와
    // entity - > response 변환
    public MemberResponse me(Long userId) {

        var entity = memberService.getMember(userId);

        var response = memberConverter.toMemberResponse(entity);
        return response;
    }

    public void validateDuplicate(MemberRegisterRequest request){

        var memberByNameEntity = memberService.getMemberByNameWithThrow(request.getName());

        var memberByEmailEntity = memberService.getMemberByEmailWithThrow(request.getEmail());

        // TODO 멤버 이름이나 이메일이 같으면 예외를 발생시키는데
        // TODO 예외를 발생시키는 경우 대처도 백엔드가 해야 되나?
        if(memberByNameEntity.isPresent()){ // 이름이 이미 존재하면 예외 발생
            throw new ApiException(MemberErrorCode.Member_Name_Duplicate);
        }
        if(memberByEmailEntity.isPresent()){ // 이메일이 이미 존재하면 예외 발생
            throw new ApiException(MemberErrorCode.Member_Email_Duplicate);
        }
    }
}
