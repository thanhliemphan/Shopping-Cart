package mvc.entity;

import javax.persistence.Entity;

public class CartEntity {
//    private Integer productId;
//    private String productName;
//    private  String productDescription;
//    private int unitPrice;
    private ProductEntity product;
    private int quantity;

//    public int getProductId() {
//        return productId;
//    }
//
//    public void setProductId(int productId) {
//        this.productId = productId;
//    }
//
//    public String getProductName() {
//        return productName;
//    }
//
//    public void setProductName(String productName) {
//        this.productName = productName;
//    }
//
//    public String getProductDescription() {
//        return productDescription;
//    }
//
//    public void setProductDescription(String productDescription) {
//        this.productDescription = productDescription;
//    }
//
//    public int getUnitPrice() {
//        return unitPrice;
//    }
//
//    public void setUnitPrice(int unitPrice) {
//        this.unitPrice = unitPrice;
//    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
