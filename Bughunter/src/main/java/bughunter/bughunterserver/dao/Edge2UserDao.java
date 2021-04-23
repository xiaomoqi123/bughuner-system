package bughunter.bughunterserver.dao;

import bughunter.bughunterserver.model.entity.Edge2User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * @author sean
 * @date 2019-03-11.
 */
@Transactional
public interface Edge2UserDao extends CrudRepository<Edge2User, Long> {

    Edge2User findByUserIdAndEdgeId(Integer userId, Long id);

}
