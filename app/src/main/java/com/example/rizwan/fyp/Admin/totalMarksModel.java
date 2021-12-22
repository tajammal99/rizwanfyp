package com.example.rizwan.fyp.Admin;

public class totalMarksModel {
    private String GlName,MemName,Remarks;
    private int glTotalMarks,memTotalMarks;

    public totalMarksModel()
    {

    }

    public totalMarksModel(String glName, String memName, String remarks, int glTotalMarks, int memTotalMarks) {
        GlName = glName;
        MemName = memName;
        Remarks = remarks;
        this.glTotalMarks = glTotalMarks;
        this.memTotalMarks = memTotalMarks;
    }

    public String getGlName() {
        return GlName;
    }

    public void setGlName(String glName) {
        GlName = glName;
    }

    public String getMemName() {
        return MemName;
    }

    public void setMemName(String memName) {
        MemName = memName;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }

    public int getGlTotalMarks() {
        return glTotalMarks;
    }

    public void setGlTotalMarks(int glTotalMarks) {
        this.glTotalMarks = glTotalMarks;
    }

    public int getMemTotalMarks() {
        return memTotalMarks;
    }

    public void setMemTotalMarks(int memTotalMarks) {
        this.memTotalMarks = memTotalMarks;
    }
}
