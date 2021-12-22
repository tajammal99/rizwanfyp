package com.example.rizwan.fyp.Admin;

public class SubmittedProposalModel
{
    private String projectId,projectName,leaderRegNo,memRegNo,superVisor,fileLink;

    public SubmittedProposalModel()
    {

    }

    public SubmittedProposalModel(String projectId, String projectName, String leaderRegNo, String memRegNo, String superVisor, String fileLink) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.leaderRegNo = leaderRegNo;
        this.memRegNo = memRegNo;
        this.superVisor = superVisor;
        this.fileLink = fileLink;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getLeaderRegNo() {
        return leaderRegNo;
    }

    public void setLeaderRegNo(String leaderRegNo) {
        this.leaderRegNo = leaderRegNo;
    }

    public String getMemRegNo() {
        return memRegNo;
    }

    public void setMemRegNo(String memRegNo) {
        this.memRegNo = memRegNo;
    }

    public String getSuperVisor() {
        return superVisor;
    }

    public void setSuperVisor(String superVisor) {
        this.superVisor = superVisor;
    }

    public String getFileLink() {
        return fileLink;
    }

    public void setFileLink(String fileLink) {
        this.fileLink = fileLink;
    }
}
