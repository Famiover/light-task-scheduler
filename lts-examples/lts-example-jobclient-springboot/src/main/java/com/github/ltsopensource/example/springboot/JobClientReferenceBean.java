package com.github.ltsopensource.example.springboot;

import com.github.ltsopensource.core.domain.Job;
import com.github.ltsopensource.jobclient.JobClient;
import com.github.ltsopensource.jobclient.domain.Response;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Robert HG (254963746@qq.com) on 4/9/16.
 */
@Component
public class JobClientReferenceBean implements InitializingBean {

    /**
     * 自己的业务类,就可以这样引用了
     */
    @Autowired
    private JobClient jobClient;

    @Override
    public void afterPropertiesSet() {
        // 这里模拟提交任务
        Job job = new Job();
        job.setTaskId("task001");
        job.setParam("shopId", "100");
        job.setTaskTrackerNodeGroup("test_trade_TaskTracker");
        job.setNeedFeedback(true);
        // 当任务队列中存在这个任务的时候，是否替换更新
        job.setReplaceOnExist(true);
        job.setCronExpression("0 0/1 * * * ?");
        Response response = jobClient.submitJob(job);
        System.out.println(response);
    }
}
