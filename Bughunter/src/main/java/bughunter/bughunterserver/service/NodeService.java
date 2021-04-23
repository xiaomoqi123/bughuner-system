package bughunter.bughunterserver.service;

import bughunter.bughunterserver.model.entity.Node;

/**
 * @author sean
 * @date 2019-01-23.
 */
public interface NodeService {
    Node save(Node node);

    Node findByWindow(String window);
}
