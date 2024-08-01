package toy.withme58.db.friend;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import toy.withme58.db.friend.enums.FriendStatus;
import toy.withme58.db.memberfriend.MemberFriendEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "friend")
public class FriendEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length =50, nullable =false)
    @Email
    private String email;

    @Column(length =50, nullable =false)
    private String name;

    @Column(columnDefinition = "varchar(50)", nullable = false)
    @Enumerated(EnumType.STRING)
    private FriendStatus status;

    @OneToMany(mappedBy = "friend")
    private List<MemberFriendEntity> memberFriendList = List.of();
}
