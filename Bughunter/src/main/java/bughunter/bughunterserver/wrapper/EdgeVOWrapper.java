package bughunter.bughunterserver.wrapper;

import bughunter.bughunterserver.constants.ConverterMessage;
import bughunter.bughunterserver.model.entity.Edge;
import bughunter.bughunterserver.vo.EdgeVO;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * @author sean
 * @date 2019-01-23.
 */
@Service
public class EdgeVOWrapper extends BaseWrapper<EdgeVO, Edge> {

    @Override
    public EdgeVO wrap(Edge edge) {
        EdgeVO edgeVO = new EdgeVO();
        edgeVO.setId(edge.getId());
        edgeVO.setSourceNode(edge.getSourceNode());
        edgeVO.setTargetNode(edge.getTargetNode());
        edgeVO.setEventHandlers(ConverterMessage.convertMessage(edge.getEventHandlers()));
        edgeVO.setDataType(edge.getDataType());
        edgeVO.setAppKey(edge.getAppKey());
        edgeVO.setEventType(edge.getEventType());
        edgeVO.setNumber(edge.getNumber());
        edgeVO.setCreateTime(edge.getCreateTime().toString());
        edgeVO.setAssistTime(edge.getAssistTime());
        edgeVO.setMessage(edge.getMessage());
        edgeVO.setImageUrl(edge.getImageUrl());
        edgeVO.setPath(edge.getPath());
        return edgeVO;
    }

    @Override
    public Edge unwrap(EdgeVO data) {
        Edge edge = new Edge();
        edge.setSourceNode(data.getSourceNode());
        edge.setTargetNode(data.getTargetNode());
        edge.setEventHandlers(data.getEventHandlers());
        edge.setEventType(data.getEventType());
        edge.setDataType(data.getDataType());
        edge.setAppKey(data.getAppKey());
        edge.setNumber(data.getNumber());
        edge.setCreateTime(Timestamp.valueOf(data.getCreateTime()));
        edge.setAssistTime(data.getAssistTime());
        edge.setMessage(data.getMessage());
        edge.setImageUrl(data.getImageUrl());
        return edge;
    }
}
