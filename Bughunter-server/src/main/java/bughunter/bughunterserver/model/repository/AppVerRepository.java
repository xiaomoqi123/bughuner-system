package bughunter.bughunterserver.model.repository;

import bughunter.bughunterserver.model.entity.AppVersion;
import bughunter.bughunterserver.model.entity.AppVersionKeys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppVerRepository extends JpaRepository<AppVersion, AppVersionKeys> {

    List<AppVersion> findAllByAppKey(String appKey);


    Integer countAllByAppKey(String appKey);


}
