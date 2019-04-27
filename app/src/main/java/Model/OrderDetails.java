package Model;

public class OrderDetails {
    String docKey;
    String orderKey;
    String productName;
    String productDesc;
    String productQuantityOpted;
    String productDiscountPrice;
    String productSeller;
    String productImageUrl1;
    String purchasedDate;
    String address;
    String trackingId;
    String orderId;
    String delivery;
    String status;
    String emailId;
    String returnProductStatus;
    String returnRequest;
    String orderApproved;
    String orderPacked;
    String transId;



    public OrderDetails() {
    }


    public OrderDetails(String orderKey, String productName, String productDesc
            , String productQuantityOpted, String productDiscountPrice, String productSeller,
                        String productImageUrl1, String purchasedDate, String address, String trackingId,
                        String orderId, String delivery, String status, String emailId) {
        this.orderKey = orderKey;
        this.productName = productName;
        this.productDesc = productDesc;
        this.productQuantityOpted = productQuantityOpted;
        this.productDiscountPrice = productDiscountPrice;
        this.productSeller = productSeller;
        this.productImageUrl1 = productImageUrl1;
        this.purchasedDate = purchasedDate;
        this.address = address;
        this.trackingId = trackingId;
        this.orderId = orderId;
        this.delivery = delivery;
        this.status = status;
        this.emailId = emailId;
    }

    public String getReturnProductStatus() {
        return returnProductStatus;
    }

    public void setReturnProductStatus(String returnProductStatus) {
        this.returnProductStatus = returnProductStatus;
    }

    public String getReturnRequest() {
        return returnRequest;
    }

    public void setReturnRequest(String returnRequest) {
        this.returnRequest = returnRequest;
    }

    public String getOrderApproved() {
        return orderApproved;
    }

    public void setOrderApproved(String orderApproved) {
        this.orderApproved = orderApproved;
    }

    public String getOrderPacked() {
        return orderPacked;
    }

    public void setOrderPacked(String orderPacked) {
        this.orderPacked = orderPacked;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getDocKey() {
        return docKey;
    }

    public void setDocKey(String docKey) {
        this.docKey = docKey;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getOrderKey() {
        return orderKey;
    }

    public void setOrderKey(String orderKey) {
        this.orderKey = orderKey;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductQuantityOpted() {
        return productQuantityOpted;
    }

    public void setProductQuantityOpted(String productQuantityOpted) {
        this.productQuantityOpted = productQuantityOpted;
    }

    public String getProductDiscountPrice() {
        return productDiscountPrice;
    }

    public void setProductDiscountPrice(String productDiscountPrice) {
        this.productDiscountPrice = productDiscountPrice;
    }

    public String getProductSeller() {
        return productSeller;
    }

    public void setProductSeller(String productSeller) {
        this.productSeller = productSeller;
    }

    public String getProductImageUrl1() {
        return productImageUrl1;
    }

    public void setProductImageUrl1(String productImageUrl1) {
        this.productImageUrl1 = productImageUrl1;
    }

    public String getPurchasedDate() {
        return purchasedDate;
    }

    public void setPurchasedDate(String purchasedDate) {
        this.purchasedDate = purchasedDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId=orderId;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
