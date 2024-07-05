package toy.withme58.db.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length =100, nullable =false)
    private String nickName;

    @Column(length =45, nullable =false)
    private String password;

    @Column(length =100, nullable =false)
    @Email
    private String email;
}
