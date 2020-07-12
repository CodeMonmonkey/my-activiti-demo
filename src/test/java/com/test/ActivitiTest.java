package com.test;

import org.activiti.engine.*;
import org.junit.Test;

/**
 * 创建activiti所需要的25张表
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

    @Test
    public void testActivitiService() {

        //创建ProcessEngineConfiguration对象
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");

        //创建ProcessEngine对象
        ProcessEngine processEngine = configuration.buildProcessEngine();

        //获取服务，共6个。

        HistoryService historyService = processEngine.getHistoryService();

        ManagementService managementService = processEngine.getManagementService();

        RepositoryService repositoryService = processEngine.getRepositoryService();

        RuntimeService runtimeService = processEngine.getRuntimeService();

        DynamicBpmnService dynamicBpmnService = processEngine.getDynamicBpmnService();

        TaskService taskService = processEngine.getTaskService();

    }
}
