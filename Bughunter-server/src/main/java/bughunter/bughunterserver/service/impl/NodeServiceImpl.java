package bughunter.bughunterserver.service.impl;

import bughunter.bughunterserver.dao.NodeDao;
import bughunter.bughunterserver.model.entity.Node;
import bughunter.bughunterserver.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sean
 * @date 2019-01-23.
 */
@Service
public class NodeServiceImpl implements NodeService {

    @Autowired
    private NodeDao nodeDao;

    @Override
    public Node save(Node node) {
        return nodeDao.save(node);
    }

    @Override
    public Node findByWindow(String window) {
        return nodeDao.findByWindow(window);
    }
}
