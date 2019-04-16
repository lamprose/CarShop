package com.carshop.server.service.impl;

import com.carshop.server.dao.CartMapper;
import com.carshop.server.domain.Cars;
import com.carshop.server.domain.ShoppingCart;
import com.carshop.server.domain.User;
import com.carshop.server.utils.Enum;
import com.carshop.server.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartMapper cartMapper;

    @Override
    public Map<String, Object> getCartInfo(Map<String, String> params) {

        String id = params.get("id");
        Map<String, Object> data=new HashMap<String, Object>();
        data.put("code",Enum.Code.COMMON.getValue());
        List<ShoppingCart> cartList = cartMapper.selectAllCartByUserId(id);
        data.put("datas",cartList);
        return data;
    }

    @Override
    public Map<String, Object> editCart(Map<String, String> params) {
        Integer amount = Integer.parseInt(params.get("amount"));
        String id = params.get("id");
        Map<String,Object> data = new HashMap<>();
        data.put("code",Enum.Code.COMMON.getValue());
        try{
            cartMapper.updateAmountByUserId(amount,id);
            data.put("datas","success");
        }
        catch (Exception e){
            System.out.println(e);
            data.put("datas","fail");
        }
        return data;
    }

    @Override
    public Map<String, Object> addCart(Map<String, String> params) {

        String id = params.get("id");
        String carId = params.get("carId");
        Integer amount = Integer.parseInt(params.get("amount"));

        User user = new User();
        user.setId(id);
        Cars car = new Cars();
        car.setCarId(carId);
        ShoppingCart cart = new ShoppingCart();
        cart.setUser(user);
        cart.setCar(car);
        cart.setAmount(amount);

        Map<String,Object> data = new HashMap<>();
        data.put("code",Enum.Code.COMMON.getValue());
        ShoppingCart cartTemp = cartMapper.selectOneByUserIdAndCarId(id,carId);
        try{
            if(cartTemp!=null){
                amount = amount + cartTemp.getAmount();
                cartMapper.updateAmountByUserId(amount,id);
            }
            else{
                cartMapper.insertOneByShoppingCart(cart);
            }
            data.put("datas","success");
        }
        catch (Exception e){
            System.out.println(e);
            data.put("datas","fail");
        }
        return data;
    }

    @Override
    public Map<String, Object> removeCart(List<Map<String, String>> params) {
        Map<String,Object> data = new HashMap<>();
        data.put("code",Enum.Code.COMMON.getValue());
        try{
            cartMapper.deleteCartsByIds(params);
            data.put("datas","success");
        }
        catch (Exception e){
            System.out.println(e);
            data.put("datas","fail");
        }
        return data;
    }
}
