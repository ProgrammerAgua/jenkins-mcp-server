[English](README.md) | [中文](README.zh-CN.md)
# Jenkins API MCP Server

这是一个基于 Spring AI 的 Jenkins Rest API 调用的 MCP Server 服务，提供了对 Jenkins 任务的常用操作接口。

## 功能特性

- 任务管理：创建、删除、启用/禁用、重命名任务
- 任务构建：触发构建、带参数构建、停止构建
- 任务信息：获取任务信息、构建信息、构建日志
- 任务配置：获取和更新任务配置

## 技术栈

- Spring Boot 3.3.6
- Jenkins REST API Client
- Spring AI MCP Server

## 快速开始

### 环境要求

- JDK 17+
- Maven 3.6+
- Jenkins 服务器（需启用「远程访问API」权限）

### 构建项目

克隆存储库并进入项目目录:

```bash
git clone [repository-url]
cd jenkins-mcp-server
```

在运行项目之前，需要先使用 Maven 打包项目：

```bash
mvn clean package
```

### mcp.json配置
```yaml
{
  "mcpServers": {
    "jenkins-mcp": {
      "command": "java",
      "args": [
        "-jar",
        "{你的路径}\\mcp-jenkins-server-0.0.1-SNAPSHOT.jar"
      ],
      "env": {
        "JENKINS_API_SERVER_URI": "jenkins-uri",
        "JENKINS_API_USERNAME": "username",
        "JENKINS_API_TOKEN": "password/token" 
      }
    }
  }
}
```
## API 文档
### 任务管理
- createJob : 创建新的 Jenkins 任务
- deleteJob : 删除现有的 Jenkins 任务
- enableJob : 启用被禁用的 Jenkins 任务
- disableJob : 禁用启用的 Jenkins 任务
- renameJob : 重命名现有的 Jenkins 任务
### 任务构建
- buildJob : 触发 Jenkins 任务构建
- buildJobWithParams : 带参数触发构建
- killJob : 停止正在运行的构建
### 任务信息
- getJobInfo : 获取任务的详细信息
- getBuildInfo : 获取特定构建的信息
- getBuildLog : 获取构建的控制台输出
- getLastBuildNumber : 获取最后一次构建的编号
- getLastBuildTimestamp : 获取最后一次构建的时间戳
### 任务配置
- getJobConfig : 获取任务的配置 XML
- updateJobConfig : 更新任务的配置