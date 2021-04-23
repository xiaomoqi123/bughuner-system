package bughunter.bughunterserver.controller;

import bughunter.bughunterserver.model.entity.Node;
import bughunter.bughunterserver.service.NodeService;
import bughunter.bughunterserver.vo.NodeVO;
import bughunter.bughunterserver.wrapper.NodeVOWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

/**
 * @author sean
 * @date 2019-01-23.
 */
@RestController
public class NodeController {

    @Autowired
    private NodeVOWrapper nodeVOWrapper;

    @Autowired
    private NodeService nodeService;

    @RequestMapping(value = "/allNodes", method = RequestMethod.GET)
    public String saveAllNodes() throws IOException {
        String path = "/Users/sean/Desktop/Cheers/allNodes.txt";
        File file = new File(path);
        if (!file.exists() || file.isDirectory())
            throw new FileNotFoundException();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String temp = null;
        StringBuffer sb = new StringBuffer();
        temp = br.readLine();
        while (temp != null) {
            String[] ss = temp.split(":");
            NodeVO nodeVO = new NodeVO();
            nodeVO.setType(ss[0]);
            String window = ss[1];
            if (nodeService.findByWindow(window) == null) {
                nodeVO.setWindow(ss[1]);
                nodeVO.setAppKey("JianDou");
                Node node = nodeVOWrapper.unwrap(nodeVO);
                nodeService.save(node);
                sb.append(temp + " ");
            }

            temp = br.readLine();
        }
        return sb.toString();
    }
}
