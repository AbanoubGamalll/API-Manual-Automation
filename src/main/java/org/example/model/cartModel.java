package org.example.model;

import java.util.LinkedList;
import java.util.List;

public class cartModel {
    private int id;
    private int userId;
    LinkedList<userProduct> products;

    public cartModel(int id, int userId) {
        this.id = id;
        this.userId = userId;
        this.products = new LinkedList<>();
    }

    public void setProducts(LinkedList<userProduct> products) {
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<userProduct> getProducts() {
        return products;
    }

    public void addProduct(int productId, int quantity) {
        products.add(new userProduct(productId, quantity));
    }

    public class userProduct {
        private int productId;
        private int quantity;

        public userProduct(int productId, int quantity) {
            this.productId = productId;
            this.quantity = quantity;
        }

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}
