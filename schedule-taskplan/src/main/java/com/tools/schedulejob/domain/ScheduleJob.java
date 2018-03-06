package com.tools.schedulejob.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="t_schedulejob")
public class ScheduleJob  implements Serializable { 

	/**
	 * 
	 */
	private static final long serialVersionUID = -2262702994907995628L;

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
    private String jobId;//任务id  
	
	@Column(nullable = false)
    private String jobName;//任务名称  
	
	@Column(nullable = false)
    private String jobGroup;//任务分组  
	
	@Column(nullable = false)
    private int jobStatus;//任务状态 0禁用 1启用 2删除  
	
	@Column(nullable = false)
    private String cronExpression;//任务运行时间表达式  
	
	@Column(nullable = false)
    private String jobDesc;//任务描述
    
    public String getJobId() {  
        return jobId;  
    }  
    public void setJobId(String jobId) {  
        this.jobId = jobId;  
    }  
    public String getJobName() {  
        return jobName;  
    }  
    public void setJobName(String jobName) {  
        this.jobName = jobName;  
    }  
    public String getJobGroup() {  
        return jobGroup;  
    }  
    public void setJobGroup(String jobGroup) {  
        this.jobGroup = jobGroup;  
    }  
      
    public int getJobStatus() {  
        return jobStatus;  
    }  
    public void setJobStatus(int jobStatus) {  
        this.jobStatus = jobStatus;  
    }  
    public String getCronExpression() {  
        return cronExpression;  
    }  
    public void setCronExpression(String cronExpression) {  
        this.cronExpression = cronExpression;  
    }  
    public String getJobDesc() {  
        return jobDesc;  
    }  
    public void setJobDesc(String jobDesc) {  
        this.jobDesc = jobDesc;  
    }  
}  