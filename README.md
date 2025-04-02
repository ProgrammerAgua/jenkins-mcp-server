English | [中文](README.zh-CN.md)
# Jenkins API MCP Server

This is a MCP Server service based on Spring AI for Jenkins Rest API calls, providing common operation interfaces for Jenkins jobs.

## Features

- Job Management: Create, delete, enable/disable, rename jobs
- Job Building: Trigger builds, build with parameters, stop builds
- Job Information: Get job info, build info, build logs
- Job Configuration: Get and update job configurations

## Technology Stack

- Spring Boot 3.3.6
- Jenkins REST API Client
- Spring AI MCP Server

## Quick Start

### Requirements

- JDK 17+
- Maven 3.6+
- Jenkins server (with "Remote Access API" enabled)

### Build Project

Clone the repository and navigate to the project directory:

```bash
git clone [repository-url]
cd jenkins-mcp-server
```

Before running the project, you need to package it using Maven:

```bash
mvn package
```
After a successful build, a file named `mcp-jenkins-server-0.0.1-SNAPSHOT.jar` will be generated in the `/target` directory. Use the full path to this file in your `mcp.json` configuration:

```bash
{your_path}\\mcp-jenkins-server-0.0.1-SNAPSHOT.jar
 ```

### mcp.json Configuration
```yaml
{
  "mcpServers": {
    "jenkins-mcp": {
      "command": "java",
      "args": [
        "-jar",
        "{your_path}\\mcp-jenkins-server-0.0.1-SNAPSHOT.jar"
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

## API Documentation
### Job Management
- createJob : Create a new Jenkins job
- deleteJob : Delete an existing Jenkins job
- enableJob : Enable a disabled Jenkins job
- disableJob : Disable an enabled Jenkins job
- renameJob : Rename an existing Jenkins job
### Job Building
- buildJob : Trigger a build for a Jenkins job
- buildJobWithParams : Trigger a build with parameters
- killJob : Stop a running build
### Job Information
- getJobInfo : Get detailed information about a job
- getBuildInfo : Get information about a specific build
- getBuildLog : Get the console output of a build
- getLastBuildNumber : Get the last build number
- getLastBuildTimestamp : Get the timestamp of the last build
### Job Configuration
- getJobConfig : Get the configuration XML of a job
- updateJobConfig : Update the configuration of a job