package bughunter.bughunterserver.model.repository;

import bughunter.bughunterserver.model.entity.AppMember;
import bughunter.bughunterserver.model.entity.AppMemberKeys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppMemberRepository extends JpaRepository<AppMember, AppMemberKeys> {

    List<AppMember> findAllByUserId(int userId);

    List<AppMember> findAllByAppKey(String appKey);

    Integer countAllByUserIdAndType(int userId, String type);

}
