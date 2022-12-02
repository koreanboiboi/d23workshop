package workshop3.workshop23.models;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class OrderDetails {
    
    private Integer orderId;
    private DateTime orderDate;
    private Integer customerId;
    private Double discounted_price;
    private Double cost_price;

    public Integer getOrderId() {
        return orderId;
    }
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    public DateTime getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(DateTime orderDate) {
        this.orderDate = orderDate;
    }
    public Integer getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    public Double getDiscountedPrice() {
        return discounted_price;
    }
    public void setDiscountedPrice(Double discounted_price) {
        this.discounted_price = discounted_price;
    }
    public Double getCostPrice() {
        return cost_price;
    }
    public void setCostPrice(Double cost_price) {
        this.cost_price = cost_price;
    }

    public static OrderDetails create(SqlRowSet rs) {
        OrderDetails od = new OrderDetails();
        od.setOrderId(rs.getInt("order_id"));
        od.setOrderDate(new DateTime(
                DateTimeFormat.forPattern("dd/MM/yyyy")
                        .parseDateTime(rs.getString("order_date"))));
        od.setCustomerId(rs.getInt("customer_id"));
        od.setDiscountedPrice(rs.getDouble("discounted_price"));
        od.setCostPrice(rs.getDouble("cost_price"));
        return od;
    }

    public JsonObject toJSON() {
        return Json.createObjectBuilder()
                .add("order_id", getOrderId())
                .add("order_date", getOrderDate() != null ? getOrderDate().toString() : "")
                .add("customer_id", getCustomerId())
                .add("discounted_price", getDiscountedPrice().toString())
                .add("cost_price", getCostPrice().toString())
                .build();
    }
}
