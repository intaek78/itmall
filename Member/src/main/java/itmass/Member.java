package itmass;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Member_table")
public class Member {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String memId;
    private String phoneNum;
    private String addr;
    private String password;

    @PostPersist
    public void onPostPersist(){
        MemChanged memChanged = new MemChanged();
        BeanUtils.copyProperties(this, memChanged);
        memChanged.publishAfterCommit();


    }

    @PrePersist
    public void onPrePersist(){
        SignedIn signedIn = new SignedIn();
        BeanUtils.copyProperties(this, signedIn);
        signedIn.publishAfterCommit();


        SignedOut signedOut = new SignedOut();
        BeanUtils.copyProperties(this, signedOut);
        signedOut.publishAfterCommit();


    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId;
    }
    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }




}
