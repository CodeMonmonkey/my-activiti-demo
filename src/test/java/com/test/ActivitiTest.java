package com.test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;

/**
 * 测试activiti所需要的25张表
 */
public class ActivitiTest {

    @Test
    public void testGenTable() {
        //创建ProcessEngineConfiguration对象
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");

        //创建ProcessEngine对象
        ProcessEngine processEngine = configuration.buildProcessEngine();

        //输出
        System.out.println(processEngine);
    }
}
