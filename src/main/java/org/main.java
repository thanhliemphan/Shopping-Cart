package org;

import mvc.configuration.SpringConfig;
import mvc.repository.OrderDetailsRepository;
import mvc.repository.OrdersRepository;
import mvc.repository.ProductRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class main {
    static ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    static OrdersRepository ordersRepository = (OrdersRepository) context.getBean("ordersRepository");
    static OrderDetailsRepository orderDetailsRepository = (OrderDetailsRepository) context.getBean("orderDetailsRepository");
    static ProductRepository productRepository = (ProductRepository) context.getBean("productRepository");
    public static void main(String[] args) {

    }
}
