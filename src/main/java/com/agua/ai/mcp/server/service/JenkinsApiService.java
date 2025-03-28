package com.agua.ai.mcp.server.service;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.agua.ai.mcp.server.util.JenkinsTemplate;
import com.google.gson.reflect.TypeToken;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class JenkinsApiService {

    private final JenkinsTemplate jenkinsTemplate;

    @Tool(description = "获取任务列表")
    public String getJobList(@ToolParam(description = "可选的文件夹路径") String optionalFolderPath) {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(jenkinsTemplate.getJobList(optionalFolderPath));
    }

    @Tool(description = "获取任务信息")
    public String getJobInfo(@ToolParam(description = "可选的文件夹路径") String optionalFolderPath,
                             @ToolParam(description = "任务名称") String jobName) {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(jenkinsTemplate.getJobInfo(optionalFolderPath, jobName));
    }

    @Tool(description = "使用 XML 文件创建任务")
    public String createJob(@ToolParam(description = "可选的文件夹路径") String optionalFolderPath,
                            @ToolParam(description = "任务名称") String jobName,
                            @ToolParam(description = "任务的配置 XML") String configXML) {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(jenkinsTemplate.createJob(optionalFolderPath, jobName, configXML));
    }

    @Tool(description = "删除任务")
    public String deleteJob(@ToolParam(description = "可选的文件夹路径") String optionalFolderPath,
                            @ToolParam(description = "任务名称") String jobName) {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(jenkinsTemplate.deleteJob(optionalFolderPath, jobName));
    }

    @Tool(description = "启用任务")
    public String enableJob(@ToolParam(description = "可选的文件夹路径") String optionalFolderPath,
                            @ToolParam(description = "任务名称") String jobName) {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(jenkinsTemplate.enableJob(optionalFolderPath, jobName));
    }

    @Tool(description = "禁用任务")
    public String disableJob(@ToolParam(description = "可选的文件夹路径") String optionalFolderPath,
                             @ToolParam(description = "任务名称") String jobName) {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(jenkinsTemplate.disableJob(optionalFolderPath, jobName));
    }

    @Tool(description = "获取任务配置文件内容")
    public String getJobConfig(@ToolParam(description = "可选的文件夹路径") String optionalFolderPath,
                               @ToolParam(description = "任务名称") String jobName) {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(jenkinsTemplate.getJobConfig(optionalFolderPath, jobName));
    }

    @Tool(description = "更新任务配置文件内容")
    public String updateJobConfig(@ToolParam(description = "可选的文件夹路径") String optionalFolderPath,
                                  @ToolParam(description = "任务名称") String jobName,
                                  @ToolParam(description = "新的配置 XML") String configXML) {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(jenkinsTemplate.updateJobConfig(optionalFolderPath, jobName, configXML));
    }

    @Tool(description = "构建任务")
    public String buildJob(@ToolParam(description = "可选的文件夹路径") String optionalFolderPath,
                           @ToolParam(description = "任务名称") String jobName) {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(jenkinsTemplate.buildJob(optionalFolderPath, jobName));
    }

    @Tool(description = "构建带参数的任务")
    public String buildJobWithParams(@ToolParam(description = "可选的文件夹路径") String optionalFolderPath,
                                     @ToolParam(description = "任务名称") String jobName,
                                     @Schema(description = "参数列表（格式：Map<String, List<String>>）") String properties) {
        Gson gson = new GsonBuilder().create();
        Map<String, List<String>> paramsMap = gson.fromJson(properties, new TypeToken<Map<String, List<String>>>(){}.getType());
        return gson.toJson(jenkinsTemplate.buildJobWithParams(optionalFolderPath, jobName, paramsMap));
    }

    @Tool(description = "获取任务上次构建序号")
    public String getLastBuildNumber(@ToolParam(description = "可选的文件夹路径") String optionalFolderPath,
                                     @ToolParam(description = "任务名称") String jobName) {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(jenkinsTemplate.getLastBuildNumber(optionalFolderPath, jobName));
    }

    @Tool(description = "获取任务上次构建时间戳")
    public String getLastBuildTimestamp(@ToolParam(description = "可选的文件夹路径") String optionalFolderPath,
                                        @ToolParam(description = "任务名称") String jobName) {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(jenkinsTemplate.getLastBuildTimestamp(optionalFolderPath, jobName));
    }

    @Tool(description = "获取构建信息")
    public String getBuildInfo(@ToolParam(description = "可选的文件夹路径") String optionalFolderPath,
                               @ToolParam(description = "任务名称") String jobName,
                               @ToolParam(description = "构建编号（必须是整数）") String buildNumber,
                               @ToolParam(description = "是否返回变更历史（boolean类型）") String changeSetFlag) {
        Gson gson = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                return Boolean.parseBoolean(changeSetFlag) && "changeSet".equals(f.getName());
            }
            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        }).create();
        return gson.toJson(jenkinsTemplate.getBuildInfo(optionalFolderPath, jobName, Integer.parseInt(buildNumber)));
    }

    @Tool(description = "获取构建控制台输出内容")
    public String getBuildLog(@ToolParam(description = "可选的文件夹路径") String optionalFolderPath,
                              @ToolParam(description = "任务名称") String jobName,
                              @ToolParam(description = "构建编号（必须是整数）") String buildNumber,
                              @ToolParam(description = "开始位置（必须是整数）") String start) {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(jenkinsTemplate.getBuildLog(optionalFolderPath, jobName, Integer.parseInt(buildNumber), Integer.parseInt(start)));
    }

    @Tool(description = "重命名任务")
    public String renameJob(@ToolParam(description = "可选的文件夹路径") String optionalFolderPath,
                            @ToolParam(description = "当前任务名称") String currentJobName,
                            @ToolParam(description = "新任务名称") String newJobName) {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(jenkinsTemplate.renameJob(optionalFolderPath, currentJobName, newJobName));
    }

    @Tool(description = "停止任务（必须二次确认）")
    public String killJob(@ToolParam(description = "可选的文件夹路径") String optionalFolderPath,
                          @ToolParam(description = "任务名称") String jobName,
                          @ToolParam(description = "构建编号（必须是整数）") String buildNumber) {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(jenkinsTemplate.killJob(optionalFolderPath, jobName, Integer.parseInt(buildNumber)));
    }

    @Tool(description = "查看执行日志")
    public String progressiveTextJob(@ToolParam(description = "可选的文件夹路径") String optionalFolderPath,
                          @ToolParam(description = "任务名称") String jobName,
                          @ToolParam(description = "开始位置（必须是整数）") String start) {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(jenkinsTemplate.progressiveTextJob(optionalFolderPath, jobName, Integer.parseInt(start)));
    }
}
