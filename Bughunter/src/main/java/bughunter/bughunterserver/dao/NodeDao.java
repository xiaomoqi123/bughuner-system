package bughunter.bughunterserver.dao;

import bughunter.bughunterserver.model.entity.Node;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author sean
 * @date 2019-01-23.
 */
@Transactional
public interface NodeDao extends CrudRepository<Node, Long> {

    Node findByWindow(String window);

    List<Node> findByAppKey(String appKey);
}
