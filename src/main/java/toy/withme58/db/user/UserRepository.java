package toy.withme58.db.user;

import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

    //select * from user where email =? password = ? order by id desc limit 1
    Optional<UserEntity> findFirstByEmailAndPasswordOrderByIdDesc(String email, String password);

    //select * from user where id = ? order by id desc limit 1
    Optional<UserEntity> findFirstByIdOrderByIdDesc(Long userId);

}
