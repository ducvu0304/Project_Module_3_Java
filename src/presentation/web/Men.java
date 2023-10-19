package presentation.web;

import business.config.IOFile;
import business.entity.Cart;
import business.entity.CartItem;
import business.entity.Stock;
import business.entity.account.Account;
import business.entity.enum_type.GroupProduct;
import business.service.impl.CartsService;
import business.service.impl.StockService;
import business.utils.InputData;
import business.utils.ScannerUtils;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static presentation.shop.StockManagement.*;

public class Men {
    public static void products() throws IOException {
        List<Stock> viewList = getProductOfGroup(GroupProduct.MEN);
        while (true) {
            Account account = IOFile.getInstance().readFromCookie(IOFile.getInstance().getCOOKIE_PATH());
            Optional<List<CartItem>> cartList = Optional.ofNullable(CartsService.getInstance().getCartsByAccountId(account.getId()));

            if (viewList.size() > 0) {
                viewProduct(viewList);
            }

            System.out.println("+-----------------------------------------+\n" +
                    "| 1 | Add to cart                         |\n" +
                    "+---+-------------------------------------+\n" +
                    "| 2 | View Cart                           |\n" +
                    "+---+-------------------------------------+\n" +
                    "| 3 | Sort shoes by price low to high     |\n" +
                    "+---+-------------------------------------+\n" +
                    "| 4 | Sort shoes by price high to low     |\n" +
                    "+---+-------------------------------------+\n" +
                    "| 5 | Find product by title               |\n" +
                    "+---+-------------------------------------+\n" +
                    "| 6 | Remove product                      |\n" +
                    "+---+-------------------------------------+\n" +
                    "| 7 | Return men products                 |\n" +
                    "+---+-------------------------------------+\n" +
                    "| 8 | Exit                                |\n" +
                    "+---+-------------------------------------+");

            int choice = ScannerUtils.inputInteger();

            switch (choice) {
                case 1:
                    System.out.println("Enter product id or title");
                    String findValue = ScannerUtils.inputString();

                    Optional<Stock> product = Optional.ofNullable(findProductByIdOrTitle(findValue));

                    /**Cart list is exist*/
                    if (cartList.isPresent()) {
                        Optional<CartItem> checkItem = Optional.ofNullable(
                                CartsService.getInstance()
                                        .getCartItemInCartsList(cartList.get(), findValue));

                        /**Check product is exist or not yet in cart list*/
                        if (checkItem.isPresent()) {
                            int quantity = InputData.quantity();
                            checkItem.get().setQuantity(checkItem.get().getQuantity() + quantity);
                            CartsService.getInstance().save();
                        } else {
                            CartItem item = CartsService.getInstance().create(product.get());
                            cartList.get().add(item);
                            CartsService.getInstance().save();
                        }

                    }
                    /**Cart list is not exist*/
                    else {
                        CartItem item = CartsService.getInstance().create(product.get());
                        List<CartItem> newCart = new ArrayList<>();
                        newCart.add(item);

                        // Create new cart
                        Cart cart = new Cart();
                        cart.setCartId(account.getId());
                        cart.setAccountId(account.getId());

                        //List<AccountId, List<CartItem>>
                        CartsService.getInstance().findAll().put(cart.getAccountId(), newCart);
                        CartsService.getInstance().save();
                    }

                    break;
                case 2:
                    if (cartList.isPresent()) {
                        if(cartList.get().size() > 0) {
                            viewCart(cartList.get());
                        }else {
                            System.out.println("YOUR CART IS EMPTY !");
                        }
                    } else {
                        System.out.println("YOUR CART IS EMPTY !");
                    }
                    break;
                case 3:
                    viewList.sort(Comparator.comparing(Stock::getPrice));
                    break;
                case 4:
                    viewList.sort(Comparator.comparing(Stock::getPrice).reversed());
                    break;
                case 5:
                    String title = InputData.text("Title").toUpperCase();
                    viewList =  viewList.stream().filter(cartItem -> getTitle(cartItem.getProductId()).contains(title)).collect(Collectors.toList());
                    break;
                case 6:

                    String item = InputData.text("Title");

                    if (cartList.isPresent()) {
                        if (CartsService.getInstance().isExistById(cartList.get(), item)) {
                            System.out.println("Are youre sure \n" +
                                    "1. Yes\n" +
                                    "2. No\n" +
                                    "Default: no");
                            System.out.println("Enter choice");
                            int confirm = ScannerUtils.inputInteger();

                            switch (confirm) {
                                case 1:
                                    CartsService.getInstance().removeItemByTitle(cartList.get(), item);
                                    CartsService.getInstance().save();
                                    System.out.printf("Remove product \"%s\" successfully.%n", item);
                                    break;
                                default:
                                    System.out.printf("The product \"%s\" does not remove. %n", item);
                            }

                        }else {
                            System.err.printf("The product \"%s\" is not exist in carts %n!", item);
                        }
                    } else {
                        System.out.println("YOUR CART IS EMPTY !");
                    }
                    break;
                case 7:
                    viewList = getProductOfGroup(GroupProduct.MEN);
                    break;
                case 8:
                    return;
                default:
                    System.out.printf("The choice \"%d\" is not function %n", choice);
            }
        }
    }

    /**
     * Men product
     */
    private static void tableHeader() {
        System.out.println("+-------------------------------------------------------------------------------------------------+");
        System.out.println("|                                            MEN PRODUCTS                                          |");
        System.out.println("+-------------------------------------------------------------------------------------------------+");
        System.out.printf("| %-15s| %-25s| %-12s| %-20s| %-16s|%n",
                "Product ID", "Title", "Price", "Catalog", "Featured");
        System.out.println("+-------------------------------------------------------------------------------------------------+");
    }

    private static void tableBody(Stock item) {
        System.out.printf("| %-15s| %-25s| %-12s| %-20s| %-16s|%n",
                item.getProductId(),
                getTitle(item.getProductId()),
                item.getPrice() * 1.2,
                getCatalog(item.getProductId()),
                getFeatured(item.getProductId())
        );
        System.out.println("+-------------------------------------------------------------------------------------------------+");
    }

    private static void viewProduct(List<Stock> items) {
        tableHeader();
        for (int i = 0; i < items.size(); i++) {
            tableBody(items.get(i));
        }
    }

    /**
     * Men product
     */
    private static void cartHeader() {
        System.out.println("+-------------------------------------------------------------------------------------------------+");
        System.out.println("|                                                CARTS                                            |");
        System.out.println("+-------------------------------------------------------------------------------------------------+");
        System.out.printf("| %-3s | %-25s| %-15s| %-12s| %-10s| %-20s|%n",
                "STT", "Product", "Catalog", "quantity", "Price", "Sub total");
        System.out.println("+-------------------------------------------------------------------------------------------------+");
    }

    private static void cartBody(int stt, CartItem item) {
        System.out.printf("| %-3d | %-25s| %-15s| %-12s| %-10s| %-20s|%n",
                stt,
                getTitle(item.getProductId()),
                getCatalog(item.getProductId()),
                item.getQuantity(),
                item.getPrice(),
                item.getQuantity() * item.getPrice());
        System.out.println("+-------------------------------------------------------------------------------------------------+");
    }

    private static void viewCart(List<CartItem> items) {
        cartHeader();
        for (int i = 0; i < items.size(); i++) {
            cartBody(i + 1, items.get(i));
        }
    }

    public static List<Stock> getProductOfGroup(GroupProduct groupProduct) {
        List<Stock> viewList = new ArrayList<>();
        TreeMap<String, Stock> map = new TreeMap<>();


        for (Stock item : StockService.getInstance().getAllProduct()) {
            map.put(item.getProductId(), item);
        }

        for (Map.Entry<String, Stock> entry : map.entrySet()) {
            viewList.add(entry.getValue());
        }

        return viewList.stream().filter(item -> getGroup(item.getProductId()).equals(groupProduct)).collect(Collectors.toList());
    }

    public static void findProductByTitle(List<Stock> list, String title) {

    }

    public static Stock findProductByIdOrTitle(String findValue) {
        for (Stock item : getProductOfGroup(GroupProduct.MEN)) {
            if (item.getProductId().equals(findValue) || getTitle(item.getProductId()).contains(findValue)) {
                return item;
            }
        }
        return null;
    }
}
