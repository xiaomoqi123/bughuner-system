package bughunter.bughunterserver.model.repository;

import bughunter.bughunterserver.model.entity.BugConsoleLog;
import bughunter.bughunterserver.model.entity.BugInfoKeys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BugConsoleLogRepository extends JpaRepository<BugConsoleLog, BugInfoKeys> {


}
