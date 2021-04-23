package bughunter.bughunterserver.model.repository;


import bughunter.bughunterserver.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findFirstUserByEmail(String email);


}
