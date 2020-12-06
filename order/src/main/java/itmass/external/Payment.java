package itmass.external;

public class Payment {

    private Long id;
    private Long orderId;
    private String payMth;
    private String payStatus;

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
