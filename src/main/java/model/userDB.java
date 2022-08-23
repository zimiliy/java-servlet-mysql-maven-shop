package model;

public class userDB {
    private Integer UID;
    private String USER;
    private String PASS;
    private String EMAIL;
    private String ADDRESS;

    public userDB(Integer UID, String USER, String PASS, String EMAIL, String ADDRESS) {
        this.UID = UID;
        this.USER = USER;
        this.PASS = PASS;
        this.EMAIL = EMAIL;
        this.ADDRESS = ADDRESS;
    }

    public userDB(String USER, String PASS) {
        this.USER = USER;
        this.PASS = PASS;
    }

    public Integer getUID() {
        return UID;
    }

    public void setUID(Integer UID) {
        this.UID = UID;
    }

    public String getUSER() {
        return USER;
    }

    public void setUSER(String USER) {
        this.USER = USER;
    }

    public String getPASS() {
        return PASS;
    }

    public void setPASS(String PASS) {
        this.PASS = PASS;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getADDRESS() {
        return ADDRESS;
    }

    public void setADDRESS(String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }
}