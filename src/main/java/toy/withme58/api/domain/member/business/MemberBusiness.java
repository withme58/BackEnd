package toy.withme58.api.domain.member.business;


import lombok.RequiredArgsConstructor;
import toy.withme58.api.common.annotation.Business;
import toy.withme58.api.common.error.MemberErrorCode;
import toy.withme58.api.common.exception.ApiException;
import toy.withme58.api.domain.token.business.TokenBusiness;
import toy.withme58.api.domain.token.controller.model.TokenResponse;
import toy.withme58.api.domain.member.model.MemberLoginRequest;
import toy.withme58.api.domain.member.model.MemberRegisterRequest;
import toy.withme58.api.domain.member.model.MemberResponse;
import toy.withme58.api.domain.member.converter.MemberConverter;
import toy.withme58.api.domain.member.service.MemberService;

@Business
@RequiredArgsConstructor
public class MemberBusiness {

    private final MemberService memberService;
    private final MemberConverter memberConverter;

    private final TokenBusiness tokenBusiness;


    /*
    * 1. request -> entity  로 변환
    * 2. entity -> service를 호출하여 저장
    * 3. 저장된 entity를 userResponse 로 변환 후 리턴
    * */

    public MemberResponse register(MemberRegisterRequest memberRegisterRequest){

        validateDuplicate(memberRegisterRequest);

        var entity = memberConverter.toMemberEntity(memberRegisterRequest);

        var userEntity = memberService.register(entity);

        var response = memberConverter.toMemberResponse(userEntity);

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

        var entity = memberService.getMemberWithThrow(userId);
        var response = memberConverter.toMemberResponse(entity);
        return response;
    }

    public void validateDuplicate(MemberRegisterRequest request){

        var memberByNameEntity = memberService.getMemberByNameWithThrow(request.getName());

        var memberByEmailEntity = memberService.getMemberByEmailWithThrow(request.getEmail());



        if(memberByNameEntity!=null){
            throw new ApiException(MemberErrorCode.Member_Name_Duplicate);
        }
        if(memberByEmailEntity!=null){
            throw new ApiException(MemberErrorCode.Member_Email_Duplicate);
        }
    }
}
