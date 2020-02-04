package com.demo.controller;

import com.demo.common.result.BaseResult;
import com.demo.common.result.BootstrapTableResult;
import com.demo.domain.ScheduleJob;
import com.demo.service.ScheduleJobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * ��ʱ������Ʋ�
 * @author admin
 * @date 2017-11-25 18:49
 */

@Controller
@RequestMapping(value = "/scheduleJob")
public class ScheduleJobController {
    public static Logger log = LoggerFactory.getLogger(ScheduleJobController.class);

    @Resource
    private ScheduleJobService scheduleJobService;

    /**
     * ��ѯ���еĶ�ʱ��������ҳ�����ʱ��ʾ�������
     * @param pageSize ÿҳ��ʾ����
     * @param pageNumber ҳ��
     * @return BootstrapTableResult
     */
    @RequestMapping(value = "/listAllJob", method = RequestMethod.POST)
    @ResponseBody
    public BootstrapTableResult listAllJob(int pageSize, int pageNumber) {
        BootstrapTableResult bootstrapTableResult = scheduleJobService.listAllJob(pageSize, pageNumber);
        return bootstrapTableResult;
    }

    /**
     * ��ͣ��ʱ����
     * @param jobId
     * @return BaseResult
     */
    @RequestMapping(value = "/pauseJob", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult pauseJob(int jobId) {
        scheduleJobService.pauseJob(jobId);
        return new BaseResult(1, "success", "��ʱ������ͣ�ɹ�");
    }

    /**
     * �ָ���ʱ����
     * @param jobId
     * @return BaseResult
     */
    @RequestMapping(value="/resumeJob",method = RequestMethod.POST)
    @ResponseBody
    public BaseResult resumeJob(int jobId){
        scheduleJobService.resumeJob(jobId);
        return new BaseResult(1, "success", "��ʱ����ָ��ɹ�");
    }

    /**
     * ����ִ�ж�ʱ����
     * @param jobId
     * @return BaseResult
     */
    @RequestMapping(value = "/runOnce",method = RequestMethod.POST)
    @ResponseBody
    public BaseResult runOnce(int jobId){
        scheduleJobService.runOnce(jobId);
        return new BaseResult(1, "success", "����ִ�ж�ʱ����ɹ�");
    }

    /**
     * ����ʱ����ʽ
     * @param id
     * @param cronExpression
     * @return BaseResult
     */
    @RequestMapping(value = "/updateCron",method = RequestMethod.POST)
    @ResponseBody
    public BaseResult updateCron(int id,String cronExpression){
        scheduleJobService.updateCron(id,cronExpression);
        return new BaseResult(1, "success", "����ʱ����ʽ�ɹ�");
    }

    /**
     * ��Ӷ�ʱ����
     * @param scheduleJob
     * @return
     */
    @RequestMapping(value = "/addScheduleJob",method = RequestMethod.POST)
    @ResponseBody
    public BaseResult addScheduleJob(ScheduleJob scheduleJob){
        try {
            scheduleJobService.addScheduleJob(scheduleJob);
        }catch (Exception e){
            return new BaseResult(0, "default", "��Ӷ�ʱ����ʧ��");
        }

        return new BaseResult(1, "success", "��Ӷ�ʱ����ɹ�");
    }

}
