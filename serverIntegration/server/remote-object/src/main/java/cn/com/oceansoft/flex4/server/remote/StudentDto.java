package cn.com.oceansoft.flex4.server.remote;


/**
 * Description: Student data transfer object
 */
public class StudentDto {

    private String id; // id
    private String name; //姓名
    private String studentNumber; //学号
    private Integer gender;  //性别

    public StudentDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

}
