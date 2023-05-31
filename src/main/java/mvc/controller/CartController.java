package mvc.controller;

import mvc.entity.CartEntity;
import mvc.entity.OrderDetailsEntity;
import mvc.entity.OrdersEntity;
import mvc.entity.ProductEntity;
import mvc.repository.OrderDetailsRepository;
import mvc.repository.OrdersRepository;
import mvc.repository.ProductRepository;
import mvc.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.*;

@Controller
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    OrderDetailsRepository orderDetailsRepository;


    @RequestMapping(value = "/cart")
    public String showCart(Model model){
        model.addAttribute("cartList",cartService.getAllItem());
        return "cart";
    }
    @RequestMapping(value = "add/{productId}")
    public String addItem(@PathVariable("productId") Integer productId){
        ProductEntity product = productRepository.findById(productId).get();
        if (product!=null){
            CartEntity item = new CartEntity();
            item.setProduct(product);
            item.setQuantity(1);
            cartService.addItem(item);
        }
        return "redirect:/cart";
    }
    @RequestMapping(value = "remove/{productId}")
    public String removeItem(@PathVariable("productId") Integer productId){
        cartService.removeItem(productId);
        return "redirect:/cart";
    }

    @RequestMapping(value = "/checkOut",method = RequestMethod.GET)
    public String checkOut(Model model){
        model.addAttribute("order",new OrdersEntity());
        model.addAttribute("action","checkOut");
        return "checkOut";
    }

    @RequestMapping(value = "/checkOut",method = RequestMethod.POST,produces =  "text/plain;charset=UTF-8")
    public String saveCheckOut(@Valid @ModelAttribute("order") OrdersEntity order, BindingResult br, Model model, HttpSession session){
        if(br.hasErrors())
        {
            return "checkOut";
        }
        else
        {
            order.setOrderDate(LocalDate.now());
            ordersRepository.save(order);
            session.setAttribute("order",order);
            for (CartEntity item:cartService.getAllItem()
                 ) {
                OrderDetailsEntity orderDetails = new OrderDetailsEntity();
                orderDetails.setOrdersEntity(order);
                orderDetails.setProductEntity(item.getProduct());
                orderDetails.setQuantity(item.getQuantity());

                orderDetailsRepository.save(orderDetails);
            }
            return "redirect:/myOrder";
        }
    }
    @RequestMapping(value = "/myOrder")
    public String myOrder(Model model){
        List<OrdersEntity> orderList = (List<OrdersEntity>) ordersRepository.findAll();
        model.addAttribute("orderList",orderList);
        return "myOrder";
    }
    @RequestMapping(value = "viewDetail/{order.orderId}")
    public String viewDetail(@PathVariable("order.orderId") int orderId, Model model){
        List<OrderDetailsEntity> orderDetailsList =orderDetailsRepository.findCartDetailsByOrderId(orderId);
        List<CartEntity> cartList = new ArrayList<>();
        for (OrderDetailsEntity orderDetails:orderDetailsList) {
            CartEntity item= new CartEntity();
            item.setProduct(orderDetails.getProductEntity());
            item.setQuantity(orderDetails.getQuantity());
            cartList.add(item);
        }
        model.addAttribute("cartList",cartList);
        return "viewDetails";
    }
}
