package itmass;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Payment_table")
public class Payment {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long orderId;
    private String payMth;
    private String payStatus;

    @PostPersist
    public void onPostPersist(){
        PayCompleted payCompleted = new PayCompleted();
        BeanUtils.copyProperties(this, payCompleted);
        payCompleted.publishAfterCommit();
    }

    @PostUpdate
    public void onPostUpdate(){
        PayCompleted payCompleted = new PayCompleted();
        BeanUtils.copyProperties(this, payCompleted);
        payCompleted.publishAfterCommit();
    }


//    @PostUpdate
//    public void onPostUpdateCancel(){
//        PayCancelled payCancelled = new PayCancelled();
//        BeanUtils.copyProperties(this, payCancelled);
//        payCancelled.publishAfterCommit();
//    }

    @PreRemove
    public void onPreRemove(){
        PayCancelled payCancelled = new PayCancelled();
        BeanUtils.copyProperties(this, payCancelled);
        payCancelled.publishAfterCommit();


    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public String getPayMth() {
        return payMth;
    }

    public void setPayMth(String payMth) {
        this.payMth = payMth;
    }
    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }




}
