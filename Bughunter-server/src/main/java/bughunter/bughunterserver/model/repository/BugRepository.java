package bughunter.bughunterserver.model.repository;

import bughunter.bughunterserver.model.entity.BugBaseInfo;
import bughunter.bughunterserver.model.entity.BugInfoKeys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Collection;
import java.util.List;

@Repository
public interface BugRepository extends JpaRepository<BugBaseInfo, BugInfoKeys> {

    List<BugBaseInfo> findAllByAppKey(String appKey);

    List<BugBaseInfo> findAllByUserId(int userId);

    List<BugBaseInfo> findByAppKeyAndAppVersion(String appKey, String appVersion);

    List<BugBaseInfo> findAllByAppKeyAndCurrent(String appKey, String current);

    List<BugBaseInfo> findByAppKeyAndStatusInAndTypeInAndPriorityIn(String appKey, Collection<String> status, Collection<String> type, Collection<String> priority);

    Integer countAllByUserId(int userId);

    Integer countAllByAppKey(String appKey);

    Integer countAllByAppKeyAndAppVersion(String appKey, String appVersion);

    Integer countAllByAppKeyAndAppVersionAndStatus(String appKey, String appVersion, String status);

    Integer countAllByAppKeyAndAppVersionAndPriority(String appKey, String appVersion, String priority);

    Integer countAllByAppKeyAndAppVersionAndType(String appKey, String appVersion, String type);

    Integer countAllByAppKeyAndStatus(String appKey, String status);

    Integer countAllByAppKeyAndPriority(String appKey, String priority);

    Integer countAllByAppKeyAndType(String appKey, String type);

    @Query(value = "select distinct user_id from app_bug_info b where b.app_key=?1 and b.app_version=?2", nativeQuery = true)
    List findUserByByAppKeyAndAppVersion(String appKey, String appVersion);

    @Query(value = "select distinct user_id from app_bug_info b where b.app_key=?1 and b.app_version=?2 and b.priority =?3", nativeQuery = true)
    List findUserByByAppKeyAndAppVersionAndPriority(String appKey, String appVersion, String priority);

}
