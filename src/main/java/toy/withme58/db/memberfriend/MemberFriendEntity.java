package toy.withme58.db.memberfriend;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import toy.withme58.db.friend.FriendEntity;
import toy.withme58.db.member.MemberEntity;

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

    @Column(nullable = false)
    private LocalDateTime createdAt;



}
