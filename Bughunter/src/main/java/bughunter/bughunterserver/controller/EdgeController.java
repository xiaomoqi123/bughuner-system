package bughunter.bughunterserver.controller;

import bughunter.bughunterserver.dao.Edge2UserDao;
import bughunter.bughunterserver.factory.ResultMessageFactory;
import bughunter.bughunterserver.model.entity.Edge2User;
import bughunter.bughunterserver.model.entity.Edge;
import bughunter.bughunterserver.service.EdgeService;
import bughunter.bughunterserver.service.NodeService;
import bughunter.bughunterserver.vo.EdgeVO;
import bughunter.bughunterserver.vo.ResultMessage;
import bughunter.bughunterserver.wrapper.EdgeVOWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author sean
 * @date 2019-01-26.
 */
@RestController
public class EdgeController {

    @Autowired
    private NodeService nodeService;

    @Autowired
    private EdgeVOWrapper edgeVOWrapper;

    @Autowired
    private EdgeService edgeService;

    @Autowired
    private Edge2UserDao edge2UserDao;

    int flag = 0;
    Timestamp timestamp = Timestamp.valueOf("2019-02-15 16:25:54");
    Integer assist = 885;

    @RequestMapping(value = "/WTG", method = RequestMethod.GET)
    public void getNodeCoverage() throws Exception {
        //插入覆盖的边
//        String coveredPath = "/Users/sean/Desktop/Cheers/action.txt";
//        File covered = new File(coveredPath);
//        if (!covered.exists() || covered.isDirectory())
//            throw new FileNotFoundException();
//        BufferedReader br2 = new BufferedReader(new FileReader(covered));
//        String temp2 = null;
//        StringBuffer sb = new StringBuffer();
//        temp2 = br2.readLine();
//        while (temp2 != null) {
//            /**
//             * 0{"time":"2018-11-29 14:04:56,102
//             * 1type":"CLICK
//             * 2message":"Click Home button because has tried more than 3 times
//             * 3activityBeforeAction":".activity.MainActivity
//             * 4activityAfterAction":".Launcher"}
//             * */
////            Log.info(temp2);
//            String[] strings = temp2.split("\",\"");
//            String time = strings[0];
//            String activityAfterInfo = strings[4];
//            String activityBeforeInfo = strings[3];
//            String messageInfo = strings[2];
//
//            String[] timeInfos = time.split("\":\"");
//            String[] times = timeInfos[1].split(",");
//            String createTime = times[0];
//            String assistTime = times[1];
//
//            String[] stringsAfter = activityAfterInfo.split("\":\"");
//            String s1 = stringsAfter[stringsAfter.length - 1];
//            //.activity.MainActivity
//            //0123456789
//            String activityAfterAction = s1.substring(10, s1.length());
//
//            String[] stringsBefore = activityBeforeInfo.split("\":\"");
//            String s2 = stringsBefore[stringsBefore.length - 1];
//            String activityBeforeAction = s2.substring(10, s2.length());
//
//            String[] stringsMessage = messageInfo.split("\":\"");
//            String s3 = stringsMessage[stringsMessage.length - 1];
//            String message = s3.substring(0, s3.length());
//
//            EdgeVO edgeVO = new EdgeVO();
//            edgeVO.setSourceNode(activityBeforeAction);
//            edgeVO.setTargetNode(activityAfterAction);
//
//            edgeVO.setEventHandlers(message);
//            edgeVO.setEventType("click");
//            edgeVO.setAppKey("JianDou");
//            edgeVO.setNumber(0);
//            edgeVO.setDataType(1);
//            edgeVO.setCreateTime(createTime);
//            edgeVO.setAssistTime(new Integer(assistTime));
//
//            Edge e = edgeVOWrapper.unwrap(edgeVO);
//            edgeService.save(e);
//            if (flag == 0) {
//                timestamp = Timestamp.valueOf(createTime);
//                flag = 1;
//            }
//            temp2 = br2.readLine();
//        }

        String uncoveredPath = "/Users/sean/Desktop/Cheers/graph.txt";
        File uncoveredfile = new File(uncoveredPath);
        if (!uncoveredfile.exists() || uncoveredfile.isDirectory())
            throw new FileNotFoundException();
        BufferedReader br1 = new BufferedReader(new FileReader(uncoveredfile));
        String temp1 = null;
        StringBuffer sb1 = new StringBuffer();
        temp1 = br1.readLine();

        List<EdgeVO> edgeVOs = new ArrayList<>();
        while (temp1 != null) {
            if (temp1.equals("edge:")) {
                EdgeVO edgeVO = new EdgeVO();
                edgeVO.setDataType(0);

                temp1 = br1.readLine();
                edgeVO.setSourceNode(temp1);

                temp1 = br1.readLine();
                edgeVO.setTargetNode(temp1);

                List<Edge> edges = edgeService.getEdgeBySourceNodeAndTargetNode(edgeVO.getSourceNode(), edgeVO.getTargetNode());

                if (edges.size() == 0) {
                    edgeVO.setEventHandlers(br1.readLine());
                    edgeVO.setEventType(br1.readLine());
                    edgeVO.setAppKey("JianDou");
                    edgeVO.setNumber(0);
                    edgeVO.setDataType(0);
                    edgeVO.setCreateTime("2018-02-15 10:25:54");
                    edgeVO.setAssistTime(-1);
                    edgeService.save(edgeVOWrapper.unwrap(edgeVO));
                }
            }
            temp1 = br1.readLine();
        }
    }

//    @RequestMapping

    @RequestMapping(value = "/path/nextHint/{currentWindow}/{nextWindow}/{edgeId}", method = RequestMethod.GET)
    public
    @ResponseBody
    ResultMessage getNextBugHint(@PathVariable String currentWindow,
                                 @PathVariable String nextWindow,
                                 @PathVariable Long edgeId) {
        String[] infos = currentWindow.split("\\.");
        currentWindow = infos[infos.length - 1];
        return ResultMessageFactory.getResultMessage(edgeService.getNextBugHint_3(currentWindow, nextWindow, edgeId));

    }

    @RequestMapping(value = "/edge/{edgeId}/{userId}", method = RequestMethod.GET)
    public
    @ResponseBody
    ResultMessage updateEdgeInfo(HttpServletRequest requgest,
                                 @PathVariable Long edgeId, @PathVariable Integer userId) {
        Edge2User edge2User = new Edge2User();
        edge2User.setEdgeId(edgeId);
        edge2User.setUserId(userId);
        edge2UserDao.save(edge2User);
        return ResultMessageFactory.getResultMessage(edgeService.updateEdge(edgeId));
    }

    @RequestMapping(value = "exception", method = RequestMethod.GET)
    public
    @ResponseBody
    void markException() throws Exception {
        String uncoveredPath = "/Users/sean/Desktop/Cheers/exception.txt";
        File uncoveredfile = new File(uncoveredPath);
        if (!uncoveredfile.exists() || uncoveredfile.isDirectory())
            throw new FileNotFoundException();
        BufferedReader br1 = new BufferedReader(new FileReader(uncoveredfile));
        String temp = null;
        StringBuffer sb = new StringBuffer();
        temp = br1.readLine();

//        while (temp != null) {
//            String[] messages = temp.split("\\|\\|");
//            String message = messages[messages.length - 1];
//            String[] messages2 = message.split("\\.");
//            String createTimeString = messages2[0];
//            String assistTimeString = messages2[1];
//            Integer assistTime = new Integer(assistTimeString);
//
//            Timestamp createTime = Timestamp.valueOf(createTimeString);
//            if (timestamp.before(timestamp) && assistTime >= assist) {
//                break;
//            } else
//                temp = br1.readLine();
//        }
//
//        String test = temp;


        while (temp != null) {
            if (temp.contains("Exception") || temp.contains("Error")) {
                if (!temp.contains("error: null")) {
                    String[] messages = temp.split("\\|\\|");
                    String message = messages[messages.length - 1];
                    String error_message = messages[messages.length - 2];
                    String[] messages2 = message.split("\\.");
                    String createTimeString = messages2[0];
                    String assistTimeString = messages2[1];
                    Integer assistTime = new Integer(assistTimeString);
                    Timestamp createTime = Timestamp.valueOf(createTimeString);

                    List<Edge> edgeList = edgeService.getEdgesByAppKey("JianDou");
                    List<Timestamp> timestamps = edgeList.stream().map(edge -> edge.getCreateTime()).collect(Collectors.toList());
                    timestamps = timestamps.stream().filter(timestamp1 -> timestamp1.before(createTime)).collect(Collectors.toList());
                    timestamps = timestamps.stream().filter(timestamp1 -> timestamp1.after(Timestamp.valueOf("2018-02-15 10:25:54"))).collect(Collectors.toList());
                    Timestamp catchTime = timestamps.get(timestamps.size() - 1);


                    Edge edge = edgeService.getEdgesByCreateTime(catchTime);
                    edge.setDataType(2);
                    edge.setMessage(error_message);
                    edgeService.save(edge);
                }

            }
            temp = br1.readLine();
        }

    }


    @RequestMapping(value = "/path/nextHint/{currentWindow}/{nextWindow}", method = RequestMethod.GET)
    public
    @ResponseBody
    ResultMessage getNextEventHint(@PathVariable String currentWindow,
                                   @PathVariable String nextWindow) {
        String[] infos = currentWindow.split("\\.");
        currentWindow = infos[infos.length - 1];
        return ResultMessageFactory.getResultMessage(edgeService.getNextHint(currentWindow, nextWindow));
    }

}
