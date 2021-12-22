package com.example.rizwan.fyp;

public class GroupModel {
    private String groupID,groupName,groupLeaderName,groupLeaderReg,groupLeaderEmail,groupLeaderMobile,
            memberName, memberReg,memberEmail,program,groupPassword,groupConfirmPassword;

    public GroupModel ()
    {

    }

    public GroupModel(String groupID, String groupName, String groupLeaderName, String groupLeaderReg, String groupLeaderEmail, String groupLeaderMobile, String memberName, String memberReg, String memberEmail, String program, String groupPassword, String groupConfirmPassword) {
        this.groupID = groupID;
        this.groupName = groupName;
        this.groupLeaderName = groupLeaderName;
        this.groupLeaderReg = groupLeaderReg;
        this.groupLeaderEmail = groupLeaderEmail;
        this.groupLeaderMobile = groupLeaderMobile;
        this.memberName = memberName;
        this.memberReg = memberReg;
        this.memberEmail = memberEmail;
        this.program = program;
        this.groupPassword = groupPassword;
        this.groupConfirmPassword = groupConfirmPassword;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupLeaderName() {
        return groupLeaderName;
    }

    public void setGroupLeaderName(String groupLeaderName) {
        this.groupLeaderName = groupLeaderName;
    }

    public String getGroupLeaderReg() {
        return groupLeaderReg;
    }

    public void setGroupLeaderReg(String groupLeaderReg) {
        this.groupLeaderReg = groupLeaderReg;
    }

    public String getGroupLeaderEmail() {
        return groupLeaderEmail;
    }

    public void setGroupLeaderEmail(String groupLeaderEmail) {
        this.groupLeaderEmail = groupLeaderEmail;
    }

    public String getGroupLeaderMobile() {
        return groupLeaderMobile;
    }

    public void setGroupLeaderMobile(String groupLeaderMobile) {
        this.groupLeaderMobile = groupLeaderMobile;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberReg() {
        return memberReg;
    }

    public void setMemberReg(String memberReg) {
        this.memberReg = memberReg;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getGroupPassword() {
        return groupPassword;
    }

    public void setGroupPassword(String groupPassword) {
        this.groupPassword = groupPassword;
    }

    public String getGroupConfirmPassword() {
        return groupConfirmPassword;
    }

    public void setGroupConfirmPassword(String groupConfirmPassword) {
        this.groupConfirmPassword = groupConfirmPassword;
    }
}
