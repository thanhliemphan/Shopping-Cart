package mvc.service;

import mvc.entity.CartEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class CartService {
    Map<Integer, CartEntity> cartEntityMap = new HashMap<>();
    public void addItem(CartEntity cartItem){
        CartEntity cartEntity = cartEntityMap.get(cartItem.getProduct().getProductId());
        if (cartEntity==null){
            cartEntityMap.put(cartItem.getProduct().getProductId(),cartItem);
        } else {
            cartEntity.setQuantity(cartEntity.getQuantity()+1);
        }
    }
    public void removeItem(int id){
        cartEntityMap.remove(id);
    }
    public Collection<CartEntity> getAllItem(){
        return cartEntityMap.values();
    }
}
