package bughunter.bughunterserver.wrapper;

import bughunter.bughunterserver.model.entity.Node;
import bughunter.bughunterserver.vo.NodeVO;
import org.springframework.stereotype.Service;

/**
 * @author sean
 * @date 2019-01-23.
 */
@Service
public class NodeVOWrapper extends BaseWrapper<NodeVO, Node> {

    @Override
    public NodeVO wrap(Node node) {
        NodeVO nodeVO = new NodeVO();
        nodeVO.setId(node.getId());
        nodeVO.setWindow(node.getWindow());
        nodeVO.setAppKey(node.getAppKey());
        nodeVO.setType(node.getType());
        return nodeVO;
    }

    @Override
    public Node unwrap(NodeVO data) {
        Node node = new Node();
        node.setAppKey(data.getAppKey());
        node.setWindow(data.getWindow());
        node.setType(data.getType());
        return node;
    }
}
