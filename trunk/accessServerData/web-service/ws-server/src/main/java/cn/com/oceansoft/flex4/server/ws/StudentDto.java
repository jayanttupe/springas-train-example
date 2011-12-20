package cn.com.oceansoft.flex4.server.ws;

import javax.xml.bind.annotation.XmlType;

/**
 * Created by IntelliJ IDEA.
 * User: Hu Jing Ling
 * Date: 11-12-15
 * Time: 下午2:20
 * Description:
 */
@XmlType(name = "Student", propOrder = {"id", "name", "studentNumber", "gender","identityCard"})
public class StudentDto {

    private String id; // id
    private String name; //姓名
    private String studentNumber; //学号
    private Integer gender;  //性别
    private String identityCard; //身份证号

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

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }
}
