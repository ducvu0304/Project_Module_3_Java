package business.service;

import business.entity.CartItem;
import business.entity.Stock;

import java.util.List;
import java.util.TreeMap;

public interface ICartService {

    TreeMap<Integer, List<CartItem>> findAll();
    CartItem getCartItemInCartsList(List<CartItem> list, String findValue);
    List<CartItem> getCartsByAccountId(int accountId);

    boolean isExistById(List<CartItem> items, String id);
    void removeItemByTitle(List<CartItem> list, String title);
    CartItem create(Stock product);
    void save();
}
