package com.agua.ai.mcp.server.util;

import com.cdancy.jenkins.rest.JenkinsClient;
import com.cdancy.jenkins.rest.domain.common.IntegerResponse;
import com.cdancy.jenkins.rest.domain.common.RequestStatus;
import com.cdancy.jenkins.rest.domain.job.BuildInfo;
import com.cdancy.jenkins.rest.domain.job.JobInfo;
import com.cdancy.jenkins.rest.domain.job.JobList;
import com.cdancy.jenkins.rest.domain.job.ProgressiveText;
import com.cdancy.jenkins.rest.features.JobsApi;
import com.agua.ai.mcp.server.properties.JenkinsProperties;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Jenkins 模板类，用于封装 Jenkins API 的调用
 */
@Component
public class JenkinsTemplate {

    private final JenkinsClient jenkinsClient;

    private JobsApi jobsApi;

    @Autowired
    public JenkinsTemplate(JenkinsProperties jenkinsProperties) {
        this.jenkinsClient = JenkinsClient.builder()
                .endPoint(jenkinsProperties.getServerUri())
                .credentials(jenkinsProperties.getUsername() + ":" + jenkinsProperties.getPassword())
                .build();
    }

    @PostConstruct
    public void init() {
        this.jobsApi = jenkinsClient.api().jobsApi();
    }

    /**
     * 获取任务列表
     *
     * @param optionalFolderPath 可选的文件夹路径
     * @return 任务列表
     */
    public JobList getJobList(String optionalFolderPath) {
        return jobsApi.jobList(optionalFolderPath);
    }

    /**
     * 获取任务信息
     *
     * @param optionalFolderPath 可选的文件夹路径
     * @param jobName 任务名称
     * @return 任务信息
     */
    public JobInfo getJobInfo(String optionalFolderPath, String jobName) {
        return jobsApi.jobInfo(optionalFolderPath, jobName);
    }

    /**
     * 使用 XML 文件创建任务
     *
     * @param optionalFolderPath 可选的文件夹路径
     * @param jobName 任务名称
     * @param configXML 任务的配置 XML
     * @return 请求状态
     */
    public RequestStatus createJob(String optionalFolderPath, String jobName, String configXML) {
        return jobsApi.create(optionalFolderPath, jobName, configXML);
    }

    /**
     * 删除任务
     *
     * @param optionalFolderPath 可选的文件夹路径
     * @param jobName 任务名称
     * @return 请求状态
     */
    public RequestStatus deleteJob(String optionalFolderPath, String jobName) {
        return jobsApi.delete(optionalFolderPath, jobName);
    }

    /**
     * 启用任务
     *
     * @param optionalFolderPath 可选的文件夹路径
     * @param jobName 任务名称
     * @return 是否成功
     */
    public boolean enableJob(String optionalFolderPath, String jobName) {
        return jobsApi.enable(optionalFolderPath, jobName);
    }

    /**
     * 禁用任务
     *
     * @param optionalFolderPath 可选的文件夹路径
     * @param jobName 任务名称
     * @return 是否成功
     */
    public boolean disableJob(String optionalFolderPath, String jobName) {
        return jobsApi.disable(optionalFolderPath, jobName);
    }

    /**
     * 获取任务配置文件内容
     *
     * @param optionalFolderPath 可选的文件夹路径
     * @param jobName 任务名称
     * @return 配置文件内容
     */
    public String getJobConfig(String optionalFolderPath, String jobName) {
        return jobsApi.config(optionalFolderPath, jobName);
    }

    /**
     * 更新任务配置文件内容
     *
     * @param optionalFolderPath 可选的文件夹路径
     * @param jobName 任务名称
     * @param configXML 新的配置 XML
     * @return 是否成功
     */
    public boolean updateJobConfig(String optionalFolderPath, String jobName, String configXML) {
        return jobsApi.config(optionalFolderPath, jobName, configXML);
    }

    /**
     * 构建任务
     *
     * @param optionalFolderPath 可选的文件夹路径
     * @param jobName 任务名称
     * @return 构建响应
     */
    public IntegerResponse buildJob(String optionalFolderPath, String jobName) {
        return jobsApi.build(optionalFolderPath, jobName);
    }

    /**
     * 构建带参数的任务
     *
     * @param optionalFolderPath 可选的文件夹路径
     * @param jobName 任务名称
     * @param properties 参数列表
     * @return 构建响应
     */
    public IntegerResponse buildJobWithParams(String optionalFolderPath, String jobName, Map<String, List<String>> properties) {
        return jobsApi.buildWithParameters(optionalFolderPath, jobName, properties);
    }

    /**
     * 获取任务上次构建序号
     *
     * @param optionalFolderPath 可选的文件夹路径
     * @param jobName 任务名称
     * @return 构建序号
     */
    public Integer getLastBuildNumber(String optionalFolderPath, String jobName) {
        return jobsApi.lastBuildNumber(optionalFolderPath, jobName);
    }

    /**
     * 获取任务上次构建时间戳
     *
     * @param optionalFolderPath 可选的文件夹路径
     * @param jobName 任务名称
     * @return 时间戳
     */
    public String getLastBuildTimestamp(String optionalFolderPath, String jobName) {
        return jobsApi.lastBuildTimestamp(optionalFolderPath, jobName);
    }

    /**
     * 获取构建信息
     *
     * @param optionalFolderPath 可选的文件夹路径
     * @param jobName 任务名称
     * @param buildNumber 构建编号
     * @return 构建信息
     */
    public BuildInfo getBuildInfo(String optionalFolderPath, String jobName, int buildNumber) {
        return jobsApi.buildInfo(optionalFolderPath, jobName, buildNumber);
    }

    /**
     * 获取构建控制台输出内容
     *
     * @param optionalFolderPath 可选的文件夹路径
     * @param jobName 任务名称
     * @param buildNumber 构建编号
     * @param start 开始位置
     * @return 控制台输出内容
     */
    public ProgressiveText getBuildLog(String optionalFolderPath, String jobName, int buildNumber, int start) {
        return jobsApi.progressiveText(optionalFolderPath, jobName, buildNumber, start);
    }

    /**
     * 重命名任务
     *
     * @param optionalFolderPath 可选的文件夹路径
     * @param currentJobName 当前任务名称
     * @param newJobName 新任务名称
     * @return 是否成功
     */
    public boolean renameJob(String optionalFolderPath, String currentJobName, String newJobName) {
        return jobsApi.rename(optionalFolderPath, currentJobName, newJobName);
    }

    /**
     * 停止任务
     *
     * @param optionalFolderPath 可选的文件夹路径
     * @param jobName 任务名称
     * @param buildNumber 构建编号
     * @return 是否成功
     */
    public RequestStatus killJob(String optionalFolderPath, String jobName, int buildNumber) {
        return jobsApi.kill(optionalFolderPath, jobName, buildNumber);
    }

    /**
     * 查看执行日志
     *
     * @param optionalFolderPath 可选的文件夹路径
     * @param jobName 任务名称
     * @param start 开始位置
     * @return 是否成功
     */
    public ProgressiveText progressiveTextJob(String optionalFolderPath, String jobName, int start) {
        return jobsApi.progressiveText(optionalFolderPath, jobName, start);
    }
}