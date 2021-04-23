package bughunter.bughunterserver.model.repository;


import bughunter.bughunterserver.model.entity.AppBaseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppBaseRepository extends JpaRepository<AppBaseInfo, String> {


}
