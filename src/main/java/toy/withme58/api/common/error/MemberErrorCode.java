package toy.withme58.api.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements ErrorCodeIfs{

    Member_Not_Found(200,200,"사용자를 찾을 수 없음"),
    Member_Name_Duplicate(200,200,"해당 닉네임은 중복"),
    Member_Email_Duplicate(200,200,"해당 이메일은 존재"),
    Member_Friend_Duplicate(200,200,"다른 친구이름 작성하세요")
    ;


    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;
}
