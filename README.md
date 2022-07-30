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

+ Step 1: Using dynamic and static analysis tool to analysis the AUT. The dynamic analysis tool is from [MoocTest](http://www.mooctest.net). If it is required to dynamically analyze the AUT, please contact 1683245057@qq.com. The static analysis tool is from [GATOR](http://web.cse.ohio-state.edu/presto/software/gator/). 

+ Step 2: Taking dynamic and static analysis results as input to Bughunter-sever.

+ Step 3: In the source code of AUT, adding the permissions to AndroidManifest.xml and adding the configuration statement to each activity class and each base class.

&emsp; &emsp; Permissions:

&emsp; &emsp; <img width="562" alt="image" src="https://user-images.githubusercontent.com/18481003/181909465-daad8017-3077-41a7-a253-9ce9c83b5d59.png">


&emsp; &emsp; Configuration statement:

&emsp; &emsp; <img width="700" alt="image" src="https://user-images.githubusercontent.com/18481003/181909712-874f0956-b6e3-4f3a-84ea-b645813ab582.png"> 


+ Step 4: Recompiling AUT as a new APK and assigning it to crowdworkers.

+ Step 5: Crowdworkers test the recompiled AUT and submit test reports to Bughunter-sever.



## Example
As an example of CloudReader app, this repository includes the following information.

+ Bughunter: the server end.

+ CloudReader-client: the client end, which is installed on the Android device. The source code of CloudReader is from https://github.com/youlookwhat/CloudReader.git.

+ usage: the assistance information for crowdworkers in CloudReader-client. It mainly contains the operation process of test tasks with abnormal and uncovered window transitions.

## Other information

+ crowdworker_distribution.xlsx: the crowdworker distribution information for all all AUTs.

+ user_study_result.xlsx: the feedback of crowdworkers via the questionnaire.
