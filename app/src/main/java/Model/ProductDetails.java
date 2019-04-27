package Model;

public class ProductDetails {

    String productKey;
    String productName;
    String prroductCategory;
    String productDesc;
    String productOriginalPrice;
    String productDiscountPrice;
    String productQuantityAvailable;
    String productRating;
    String productReviews;
    String productPinCode;
    String productSeller;
    String productImageUrl1;
    String productImageUrl2;
    String productImageUrl3;
    String productImageUrl4;
    String qtyDesc;
    String deal;


    public ProductDetails() {
    }

    public ProductDetails(String productName, String prroductCategory
            , String productDesc, String productOriginalPrice, String productDiscountPrice
            , String productQuantityAvailable
            , String productPinCode
            , String productSeller, String productImageUrl1, String productImageUrl2) {
        this.productName = productName;
        this.prroductCategory = prroductCategory;
        this.productDesc = productDesc;
        this.productOriginalPrice = productOriginalPrice;
        this.productDiscountPrice = productDiscountPrice;
        this.productQuantityAvailable = productQuantityAvailable;

        this.productPinCode = productPinCode;
        this.productSeller = productSeller;
        this.productImageUrl1 = productImageUrl1;
        this.productImageUrl2 = productImageUrl2;
    }

    public ProductDetails(String productName, String prroductCategory,
                          String productDesc, String productOriginalPrice,
                          String productDiscountPrice, String productQuantityAvailable,
                          String productPinCode, String productSeller,
                          String productImageUrl1
            , String productImageUrl2, String productImageUrl3, String productImageUrl4,String qtyDesc) {
        this.productName = productName;
        this.prroductCategory = prroductCategory;
        this.productDesc = productDesc;
        this.productOriginalPrice = productOriginalPrice;
        this.productDiscountPrice = productDiscountPrice;
        this.productQuantityAvailable = productQuantityAvailable;
        this.productPinCode = productPinCode;
        this.productSeller = productSeller;
        this.productImageUrl1 = productImageUrl1;
        this.productImageUrl2 = productImageUrl2;
        this.productImageUrl3 = productImageUrl3;
        this.productImageUrl4 = productImageUrl4;
        this.qtyDesc=qtyDesc;
    }
    public ProductDetails(String productName,
                          String productDesc, String productOriginalPrice,
                          String productDiscountPrice, String productQuantityAvailable,
                          String productPinCode, String productSeller,
                          String productImageUrl1
            , String productImageUrl2, String productImageUrl3, String productImageUrl4
            ,String qtyDesc,String prroductCategory,String deal) {
        this.productName = productName;
        this.productDesc = productDesc;
        this.productOriginalPrice = productOriginalPrice;
        this.productDiscountPrice = productDiscountPrice;
        this.productQuantityAvailable = productQuantityAvailable;
        this.productPinCode = productPinCode;
        this.productSeller = productSeller;
        this.productImageUrl1 = productImageUrl1;
        this.productImageUrl2 = productImageUrl2;
        this.productImageUrl3 = productImageUrl3;
        this.productImageUrl4 = productImageUrl4;
        this.qtyDesc=qtyDesc;
        this.prroductCategory=prroductCategory;
        this.deal=deal;
    }
    public ProductDetails(String productName,
                          String productDesc, String productOriginalPrice,
                          String productDiscountPrice, String productQuantityAvailable,
                          String productPinCode, String productSeller,
                          String productImageUrl1
            ,String qtyDesc,String prroductCategory,String deal) {
        this.productName = productName;
        this.productDesc = productDesc;
        this.productOriginalPrice = productOriginalPrice;
        this.productDiscountPrice = productDiscountPrice;
        this.productQuantityAvailable = productQuantityAvailable;
        this.productPinCode = productPinCode;
        this.productSeller = productSeller;
        this.productImageUrl1 = productImageUrl1;
        this.qtyDesc=qtyDesc;
        this.prroductCategory=prroductCategory;
        this.deal=deal;
    }


    public String getProductKey() {
        return productKey;
    }

    public void setProductKey(String productKey) {
        this.productKey = productKey;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPrroductCategory() {
        return prroductCategory;
    }

    public void setPrroductCategory(String prroductCategory) {
        this.prroductCategory = prroductCategory;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductOriginalPrice() {
        return productOriginalPrice;
    }

    public void setProductOriginalPrice(String productOriginalPrice) {
        this.productOriginalPrice = productOriginalPrice;
    }

    public String getProductDiscountPrice() {
        return productDiscountPrice;
    }

    public void setProductDiscountPrice(String productDiscountPrice) {
        this.productDiscountPrice = productDiscountPrice;
    }

    public String getProductRating() {
        return productRating;
    }

    public void setProductRating(String productRating) {
        this.productRating = productRating;
    }

    public String getProductReviews() {
        return productReviews;
    }

    public void setProductReviews(String productReviews) {
        this.productReviews = productReviews;
    }

    public String getProductPinCode() {
        return productPinCode;
    }

    public void setProductPinCode(String productPinCode) {
        this.productPinCode = productPinCode;
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

    public String getProductImageUrl2() {
        return productImageUrl2;
    }

    public void setProductImageUrl2(String productImageUrl2) {
        this.productImageUrl2 = productImageUrl2;
    }


    public String getProductQuantityAvailable() {
        return productQuantityAvailable;
    }

    public void setProductQuantityAvailable(String productQuantityAvailable) {
        this.productQuantityAvailable = productQuantityAvailable;
    }


    public String getProductImageUrl3() {
        return productImageUrl3;
    }

    public void setProductImageUrl3(String productImageUrl3) {
        this.productImageUrl3 = productImageUrl3;
    }

    public String getProductImageUrl4() {
        return productImageUrl4;
    }

    public void setProductImageUrl4(String productImageUrl4) {
        this.productImageUrl4 = productImageUrl4;
    }

    public String getQtyDesc() {
        return qtyDesc;
    }

    public void setQtyDesc(String qtyDesc) {
        this.qtyDesc = qtyDesc;
    }

    public String getDeal() {
        return deal;
    }

    public void setDeal(String deal) {
        this.deal = deal;
    }
}
