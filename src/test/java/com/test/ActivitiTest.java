package com.test;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.List;

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

    /**
     * 部署流程
     */
    @Test
    public void deployProcess() {

        ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("bpmn/holiday.bpmn")
                .addClasspathResource("img/holiday.png")
                .name("请假申请单流程")
                .deploy();

        System.out.println("流程实例ID" + deployment.getId());
        System.out.println("流程部署名称" + deployment.getName());
    }

    /**
     * 启动一个流程实例
     */
    @Test
    public void startProcessInstance() {

        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance holiday = runtimeService.startProcessInstanceByKey("holiday");

        System.out.println("流程定义ID：" + holiday.getProcessDefinitionName());
        System.out.println("流程实例ID：" + holiday.getId());
        System.out.println("当前活动ID：" + holiday.getActivityId());

    }

    /**
     * 查询待办任务
     */
    @Test
    public void findPersonalTask() {

        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        List<Task> tasks = taskService.createTaskQuery()
                .processDefinitionKey("holiday")
                .taskAssignee("zhangsan")
                .list();
        for (Task task : tasks) {
            System.out.println("流程实例ID：" + task.getProcessInstanceId());
            System.out.println("任务ID：" +  task.getId());
            System.out.println("任务名称：" + task.getName());
            System.out.println("办理人：" + task.getAssignee());
        }
    }

    /**
     * 办理任务
     */
    @Test
    public void completeTask() {

        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        String taskId = "5005";
        taskService.complete(taskId);
        System.out.println("完成任务：" + taskId);//执行完后，发现ru_task表中，该条任务消失，流转到了下一节点。act_hi_taskinst中保存了所有
    }
}
