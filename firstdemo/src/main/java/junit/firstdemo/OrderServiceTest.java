package junit.firstdemo;

import org.junit.Test;
import static org.junit.Assert.*;

public class OrderServiceTest {

    // Static inner class: Order
    public static class Order {
        private int id;
        private String productName;
        private double price;

        public Order(int id, String productName, double price) {
            this.id = id;
            this.productName = productName;
            this.price = price;
        }

        public int getId() {
            return id;
        }

        public String getProductName() {
            return productName;
        }

        public double getPrice() {
            return price;
        }
    }

    // Static inner class: OrderService
    public static class OrderService {
        public Order createOrder(int id, String productName, double price) {
            return new Order(id, productName, price);
        }
    }

    @Test
    public void testOrderCreation() {
        OrderService service = new OrderService();
        Order order = service.createOrder(1, "Laptop", 50000.0);

        assertEquals(1, order.getId());
        assertEquals("Laptop", order.getProductName());
        assertEquals(50000.0, order.getPrice(), 0.001);
    }
}
