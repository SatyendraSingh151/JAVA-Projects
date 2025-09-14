package Project_Advance_JAVA;



public class Student {
    private int sid;
    private String sname;
    private String semail;

    public Student() {}

    public Student(int sid, String sname, String semail) {
        this.sid = sid;
        this.sname = sname;
        this.semail = semail;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSemail() {
        return semail;
    }

    public void setSemail(String semail) {
        this.semail = semail;
    }
}

