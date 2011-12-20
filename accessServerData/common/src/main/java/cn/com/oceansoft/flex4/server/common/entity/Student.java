package cn.com.oceansoft.flex4.server.common.entity;

import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Past;
import java.util.Date;

/**
 * Date: 11-12-12
 * Time: 下午1:21
 * Description: 学生实体类
 */
@Entity
@Table(name = "tbl_domain_student")
public class Student extends BaseEntity{

    private String name; //姓名
    private String identityCard; //身份证号
    private Integer gender;  //性别
    private Date birthDay; //生日
    private String studentNumber; //学号
    private String picture; //照片
    private String nativePlace; //籍贯
    private String politicalLandscape; //政治面貌

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", identityCard='" + identityCard + '\'' +
                ", gender=" + gender +
                ", birthDay=" + birthDay +
                ", studentNumber='" + studentNumber + '\'' +
                ", picture='" + picture + '\'' +
                ", nativePlace='" + nativePlace + '\'' +
                ", politicalLandscape='" + politicalLandscape + '\'' +
                '}';
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(length = 18 , unique = true)
    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    @Column(nullable = false , length = 1)
    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past
    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    @Column(nullable = false , unique = true)
    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getPoliticalLandscape() {
        return politicalLandscape;
    }

    public void setPoliticalLandscape(String politicalLandscape) {
        this.politicalLandscape = politicalLandscape;
    }
}
