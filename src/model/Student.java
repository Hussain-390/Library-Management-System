package model;

/**
 * Model representing a student in the library system.
 */
public class Student {
    private String studentId;
    private String name;
    private String branch;
    private int year;
    private String phone;
    private String email;

    public Student(String studentId, String name, String branch, int year, String phone, String email) {
        this.studentId = studentId;
        this.name = name;
        this.branch = branch;
        this.year = year;
        this.phone = phone;
        this.email = email;
    }

    // Getters and Setters
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student [studentId=" + studentId + ", name=" + name + ", branch=" + branch + 
               ", year=" + year + ", phone=" + phone + ", email=" + email + "]";
    }
}
