package bughunter.bughunterserver.model.repository;

import bughunter.bughunterserver.model.entity.OldBugBaseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OldBugRepository extends JpaRepository<OldBugBaseInfo, Integer> {


    List<OldBugBaseInfo> findAllByAppKeyAndBugId(String appKey, String BugId);

}
