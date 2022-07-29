## Abstract

&emsp; Crowdsourced testing is an emerging trend in mobile application testing. The openness of crowdsourced testing provides a promising way to conduct large-scale and user-oriented testing scenarios on various mobile devices, while it also brings a problem, i.e., crowdworkers with different levels of testing skills severely threaten crowdsourced testing quality. Many approaches (e.g., incentive mechanism, crowdworker/test task recommendation, and test report optimization) are proposed to improve crowdsourced testing. However, these approaches do not fundamentally improve the ability of crowdworkers. In essence, the low-quality crowdsourced testing is caused by crowdworkers who are unfamiliar with the App Under Test (AUT) and do not know which part of the AUT should be tested. 
<br>
&emsp;To address this problem, we propose a testing assistance approach, which leverages Android automated testing (i.e., dynamic and static analysis) to improve crowdsourced testing. Our approach constructs an Annotated Window Transition Graph (AWTG) model for the AUT by merging dynamic and static analysis results. To assist crowdworkers in testing the AUT, our approach relies on the AWTG model to implement a testing assistance pipeline that provides the test task extraction, test task recommendation, and test task guidance for crowdworkers during crowdsourced testing.The experimental results on real-world AUTs quantitatively demonstrate that our approach can effectively and efficiently assist crowdsourced testing. Besides, the qualitative results from a user study confirm the usefulness of our approach.

## Setup
The following is required to set up our tool:
+ Java version 1.8+

+ Android API level 19+

+ at least 1GB hard driver and 8GB memory

+ Window operation: Mac OS 10+ or Ububntu 18.04 64-bit or Windows 10 64-bit

## Usage step
Our tool consists of the sever and client ends. The sever end is responsible for constructing AWTG model, test task extraction, recommendation, and guidance. The client end is responsible for showing the assistance information to crowdworkers.

+ Step 1: Using dynamic and static analysis tool to analysis the AUT. The dynamic analysis tool is from [MoocTest](http://www.mooctest.net). If it is required to dynamically analyze the AUT, please contact 1683245057@qq.com. The static analysis tool is from [GATOR](http://web.cse.ohio-state.edu/presto/software/gator/). 

+ Step 2: Taking dynamic and static analysis results as input to Bughunter-sever.

+ Step 3: Embedding the cilent ends to the source code of AUT (CloudReader-client). 

+ Step 4: Recompiling CloudReader-client as a new APK and assigning it to crowdworkers.

+ Step 5: Crowdworkers test the recompiled AUT and submit test reports to the server end.



## Example
As an example of CloudReader app, this repository includes the following information.

+ Bughunter: the server of our tool.

+ CloudReader-client: the apk embedded with our tool. The source code of CloudReader is from https://github.com/youlookwhat/CloudReader.git.

+ usage: the assistance information for crowdworkers in CloudReader-client. It mainly contains the operation process of test tasks with abnormal and uncovered windows transitions.

## Other information

+ crowdworker_distribution.xlsx: the crowdworker distribution information for all all AUTs.

+ user_study_result.xlsx: the feedback of crowdworkers via the questionnaire.
