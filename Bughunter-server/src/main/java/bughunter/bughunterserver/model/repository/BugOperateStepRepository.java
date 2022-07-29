package bughunter.bughunterserver.model.repository;

import bughunter.bughunterserver.model.entity.BugInfoKeys;
import bughunter.bughunterserver.model.entity.BugOperateStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BugOperateStepRepository extends JpaRepository<BugOperateStep, BugInfoKeys> {


}
