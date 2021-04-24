### Abstract
&emsp; Crowdsourced testing is an emerging trend in the mobile application testing paradigm. Along with conducting large-scale user-oriented testing scenarios in diverse mobile devices, 
crowdsourced testing also brings the problem of crowdworkers with different testing skills, which significantly slows down the quality of crowdsourced testing. 
Despite massive approaches to assisting crowdsourced testing, there is still a lack of assistance for crowdworkers. 
That is, the existing approaches lack a way to guide crowdworkers to test the App Under Test (AUT) during crowdsourced testing.
<br>
&emsp; This paper proposes a test assistance mechanism to assist crowdsourced testing. Such a test assistance mechanism leverages Android automated testing (i.e., dynamic analysis and static analysis) to assist crowdworkers in getting familiar with and understanding the AUT, and relies on the domain knowledge of crowdworkers to discover bugs of the AUT during crowdsourced testing. Our approach combines dynamic and static analysis to construct an Annotated Window Transition Graph (AWTG) model of the AUT. Based on the AWTG model, our approach automatically extracts test tasks for crowdworkers, recommends these test tasks to crowdworkers, and further guides crowdworkers to complete these test tasks. We conduct evaluations on ten real-world Android apps, and the experimental results demonstrate that our approach can effectively and efficiently assist crowdworkers in testing the AUT during crowdsourced testing. Moreover, the results from a user study indicate that our approach is useful to assist crowdsourced testing.


### Detailed information
As an example of Yunyue app, this repository includes the following information.

+ Bughunter: the server of our tool.

+ Yunyue: the apk embedded with our tool.

+ Yunyue-master: the source code combined Yunyue and our tool, the source code of Yunyue is from https://github.com/youlookwhat/CloudReader.git.

+ sql: there are the node and edge information, and a part of test reports submitted by crowdworkers about Yunyue.

+ usage: as an example of Yunyue, this file fold includes the operation process of test tasks with abnormal and uncovered windows transitions.

+ crowdworker distribution: as for all AUTs, there is the crowdworker distribution information.

+ results: the experimental results related to all research questions.

