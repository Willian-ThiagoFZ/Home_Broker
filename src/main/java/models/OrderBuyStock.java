
package models;


public class OrderBuyStock {
    
    private Integer id, account_id, quantity;
    private String stock;
    private double buy_price, sold_price, total_amount_invest, total_amount_returns;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Integer account_id) {
        this.account_id = account_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String Stock) {
        this.stock = Stock;
    }

    public double getBuy_price() {
        return buy_price;
    }

    public void setBuy_price(double buy_price) {
        this.buy_price = buy_price;
    }

    public double getSold_price() {
        return sold_price;
    }

    public void setSold_price(double sold_price) {
        this.sold_price = sold_price;
    }

    public double getTotal_amount_invest() {
        return total_amount_invest;
    }

    public void setTotal_amount_invest(double total_amount_invest) {
        this.total_amount_invest = total_amount_invest;
    }

    public double getTotal_amount_returns() {
        return total_amount_returns;
    }

    public void setTotal_amount_returns(double total_amount_returns) {
        this.total_amount_returns = total_amount_returns;
    }
    
}
