package business.service.impl;

import business.config.IOFile;
import business.entity.Cart;
import business.entity.CartItem;
import business.entity.Stock;
import business.service.ICartService;
import business.utils.IdGenerator;
import business.utils.InputData;
import presentation.web.Home;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static presentation.shop.StockManagement.getTitle;

public class CartsService implements ICartService {
    private static  final TreeMap<Integer, List<CartItem>> carts =
                          IOFile.getInstance().readFromFileCart(
                                  IOFile.getInstance().getCART_PATH()
                          );
    private static volatile CartsService cartsService = null;

    private CartsService(){
    }

    public static CartsService getInstance() {
        if (cartsService == null) {
            // To make thread safe
            synchronized (ShoesService.class) {
                // check again as multiple threads
                // can reach above step
                if (cartsService == null)
                    cartsService = new CartsService();
            }
        }
        return cartsService;
    }

    @Override
    public TreeMap<Integer, List<CartItem>> findAll() {
        return carts;
    }

    @Override
    public CartItem getCartItemInCartsList(List<CartItem> list, String findValue) {
        if (!list.isEmpty()) {
            for (CartItem item : list) {
                if(item.getProductId().equals(findValue) || getTitle(item.getProductId()).contains(findValue)) {
                    return item;
                }
            }
        }
        return null;
    }

    @Override
    public List<CartItem> getCartsByAccountId(int accountId) {
        if(!carts.isEmpty()) {
            for (Map.Entry<Integer, List<CartItem>> entry : carts.entrySet()) {
                if (entry.getKey() == accountId) {
                    return entry.getValue();
                }
            }
        }
        return null;
    }

    @Override
    public boolean isExistById(List<CartItem> list, String title) {
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                if(getTitle(list.get(i).getProductId()).equalsIgnoreCase(title)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void removeItemByTitle(List<CartItem> list, String title) {
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                if(getTitle(list.get(i).getProductId()).equalsIgnoreCase(title)){
                    list.remove(i);
                }
            }
        }
    }

    @Override
    public CartItem create(Stock product) {
        CartItem cartItem = new CartItem();
        /**Id*/
        cartItem.setId(IdGenerator.generateUniqueId());
        /**Id*/
        cartItem.setProductId(product.getProductId());
        /**Size*/
        cartItem.setSize(InputData.size());
        /**Quantity*/
        cartItem.setQuantity(InputData.quantity());
        /**Price*/
        cartItem.setPrice(product.getPrice()*1.2);

        return cartItem;
    }

    @Override
    public void save() {
        IOFile.getInstance().writeToFileCarts(IOFile.getInstance().getCART_PATH(), carts);
    }
}
