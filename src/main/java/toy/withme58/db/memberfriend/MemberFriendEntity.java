package toy.withme58.db.memberfriend;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import toy.withme58.db.friend.FriendEntity;
import toy.withme58.db.member.MemberEntity;
import toy.withme58.db.memberfriend.enums.MemberFriendStatus;

import java.time.LocalDateTime;

@Entity
@Table(name ="member_friend")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberFriendEntity {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "friend_id")
    private FriendEntity friend;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "member_id")
    private MemberEntity member;


    private LocalDateTime createdAt;

    @Column(columnDefinition = "varchar(50)" , nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberFriendStatus status;




    public void makeMember(MemberEntity member){
        this.member = member;
        member.getMemberFriendList().add(this);
    }

    public void makeFriend(FriendEntity friend){
        this.friend = friend;
        friend.getMemberFriendList().add(this);
    }

}
