package com.example.rizwan.fyp.Evaluator;

public class EvaluatorMod {
    private String panelID,panelMemberName,panelMemberNameEmailID,panelMemberName2,panelMemberName2EmailID,
            panelMemberName3,panelMemberName3EmailID,password;

    public EvaluatorMod()
    {

    }

    public EvaluatorMod(String panelID, String panelMemberName, String panelMemberNameEmailID, String panelMemberName2, String panelMemberName2EmailID, String panelMemberName3, String panelMemberName3EmailID, String password) {
        this.panelID = panelID;
        this.panelMemberName = panelMemberName;
        this.panelMemberNameEmailID = panelMemberNameEmailID;
        this.panelMemberName2 = panelMemberName2;
        this.panelMemberName2EmailID = panelMemberName2EmailID;
        this.panelMemberName3 = panelMemberName3;
        this.panelMemberName3EmailID = panelMemberName3EmailID;
        this.password = password;
    }

    public String getPanelID() {
        return panelID;
    }

    public void setPanelID(String panelID) {
        this.panelID = panelID;
    }

    public String getPanelMemberName() {
        return panelMemberName;
    }

    public void setPanelMemberName(String panelMemberName) {
        this.panelMemberName = panelMemberName;
    }

    public String getPanelMemberNameEmailID() {
        return panelMemberNameEmailID;
    }

    public void setPanelMemberNameEmailID(String panelMemberNameEmailID) {
        this.panelMemberNameEmailID = panelMemberNameEmailID;
    }

    public String getPanelMemberName2() {
        return panelMemberName2;
    }

    public void setPanelMemberName2(String panelMemberName2) {
        this.panelMemberName2 = panelMemberName2;
    }

    public String getPanelMemberName2EmailID() {
        return panelMemberName2EmailID;
    }

    public void setPanelMemberName2EmailID(String panelMemberName2EmailID) {
        this.panelMemberName2EmailID = panelMemberName2EmailID;
    }

    public String getPanelMemberName3() {
        return panelMemberName3;
    }

    public void setPanelMemberName3(String panelMemberName3) {
        this.panelMemberName3 = panelMemberName3;
    }

    public String getPanelMemberName3EmailID() {
        return panelMemberName3EmailID;
    }

    public void setPanelMemberName3EmailID(String panelMemberName3EmailID) {
        this.panelMemberName3EmailID = panelMemberName3EmailID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
