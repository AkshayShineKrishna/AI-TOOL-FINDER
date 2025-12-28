package com.trumio.task.aitools.models;

import java.util.ArrayList;
import java.util.List;

public class ToolsResponse {
    private List<AITool> tools;
    private String result;

    public ToolsResponse(List<AITool> tools) {
        this.tools = tools;
        setResult("success");
    }

    public ToolsResponse(String result) {
        this.result = result;
        setTools(new ArrayList<>());
    }

    public List<AITool> getTools() {
        return tools;
    }

    public void setTools(List<AITool> tools) {
        this.tools = tools;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
