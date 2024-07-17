package toy.withme58.db.member;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import toy.withme58.db.member.enums.MemberStatus;

import java.time.LocalDateTime;

@Entity
@Table(name ="member")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length =50, nullable =false)
    @Email
    private String email;

    @Column(length =50, nullable =false)
    private String password;

    @Column(length =50, nullable =false)
    private String name;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(columnDefinition = "varchar(50)", nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberStatus status;

}
