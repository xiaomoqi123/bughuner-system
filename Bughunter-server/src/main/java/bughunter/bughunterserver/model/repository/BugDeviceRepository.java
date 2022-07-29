package bughunter.bughunterserver.model.repository;

import bughunter.bughunterserver.model.entity.BugDeviceInfo;
import bughunter.bughunterserver.model.entity.BugInfoKeys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BugDeviceRepository extends JpaRepository<BugDeviceInfo, BugInfoKeys> {


    @Query(value = "select distinct b.system_version,b.device_brand,b.providers_name,b.system_model from bug_device_info b join app_bug_info a using(app_key,bug_id) where a.app_key=?1 and a.app_version=?2 and a.priority=?3", nativeQuery = true)
    List findDeviceByByAppKeyAndAppVersion(String appKey, String appVersion, String priority);

}
