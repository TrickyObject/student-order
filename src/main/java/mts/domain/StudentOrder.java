package mts.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StudentOrder {

    private long studentOrderId;
    private LocalDateTime studentOrderDate;
    private StudentOrderStatus studentOrderStatus;
    private Adult husband;
    private Adult wife;
    private List<Child> children;
    private String MarriageCertId;
    private LocalDate MarriageDate;
    private RegisterOffice MarriageOffice;

    public LocalDateTime getStudentOrderDate() {
        return studentOrderDate;
    }

    public void setStudentOrderDate(LocalDateTime studentOrderDate) {
        this.studentOrderDate = studentOrderDate;
    }

    public StudentOrderStatus getStudentOrderStatus() {
        return studentOrderStatus;
    }

    public void setStudentOrderStatus(StudentOrderStatus studentOrderStatus) {
        this.studentOrderStatus = studentOrderStatus;
    }

    public void addChild(Child child) {
        if(children == null) {
            children = new ArrayList<>();
        }
        children.add(child);

    }

    public LocalDate getMarriageDate() {
        return MarriageDate;
    }

    public void setMarriageDate(LocalDate marriageDate) {
        MarriageDate = marriageDate;
    }

    public RegisterOffice getMarriageOffice() {
        return MarriageOffice;
    }

    public void setMarriageOffice(RegisterOffice marriageOffice) {
        MarriageOffice = marriageOffice;
    }

    public String getMarriageCertId() {
        return MarriageCertId;
    }

    public void setMarriageCertId(String marriageCertId) {
        MarriageCertId = marriageCertId;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    public long getStudentOrderId() {
        return studentOrderId;
    }

    public void setStudentOrderId(long studentOrderId) {
        this.studentOrderId = studentOrderId;
    }

    public Adult getHusband() {
        return husband;
    }

    public void setHusband(Adult husband) {
        this.husband = husband;
    }

    public Adult getWife() {
        return wife;
    }

    public void setWife(Adult wife) {
        this.wife = wife;
    }

    public List<Child> getChild() {
        return children;
    }

    @Override
    public String toString() {
        return "StudentOrder{" +
                "studentOrderId=" + studentOrderId +
                ", studentOrderDate=" + studentOrderDate +
                ", studentOrderStatus=" + studentOrderStatus +
                ", husband=" + husband +
                ", wife=" + wife +
                ", children=" + children +
                ", MarriageCertId='" + MarriageCertId + '\'' +
                ", MarriageDate=" + MarriageDate +
                ", MarriageOffice=" + MarriageOffice +
                '}';
    }
}

