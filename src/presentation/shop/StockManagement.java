package presentation.shop;

import business.entity.Stock;
import business.entity.enum_type.ClothingCatalog;
import business.entity.enum_type.GroupProduct;
import business.entity.enum_type.ShoesCatalog;
import business.entity.enum_type.Size;
import business.service.impl.ClothingService;
import business.service.impl.ShoesService;
import business.service.impl.StockService;
import business.utils.InputData;
import business.utils.ScannerUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static business.utils.DateTimeFormat.SIMPLE_DATE_FORMAT;
import static business.utils.ScannerUtils.inputInteger;

public class StockManagement {
    public static void menu() throws IOException {
        boolean flag = true;
        String productId;
        List<Stock> viewList = StockService.getInstance().getAllProduct();

        while (true) {

            if(!viewList.isEmpty()) {
                Collections.sort(viewList, Comparator.comparing(Stock::getImportDate).reversed());
                viewStockTable(viewList);
            }else {
                System.out.println("Stock is empty");
            }

            functionMenu();

            System.out.println("Enter choice function:");
            int choice = inputInteger();

            switch (choice) {
                case 1:
                    productId = InputData.text("Product Id:");
                    Size size = InputData.size();

                    Optional<Stock> optional =  Optional.ofNullable(StockService.getInstance().findItemByProductIdAndSize(productId, size));

                    if(optional.isPresent()) {
                        tableBody(1, optional.get());

                        System.out.println("Enter quantity: ");
                        int quantity =  ScannerUtils.inputInteger();

                        optional.get().setQuantity(optional.get().getQuantity() + quantity);
                        optional.get().setUpdateDate(LocalDateTime.now());

                        StockService.getInstance().save();

                    }else if(ShoesService.getInstance().isExistById(productId) || ClothingService.getInstance().isExistById(productId)) {
                        StockService.getInstance().importProduct(productId, size);
                        StockService.getInstance().save();
                    }else {
                        System.err.printf("The product \"%s\" is not exist ! %n", productId);
                    }
                    break;
                case 2:
                    ShoesManagement.showListShoes(ShoesService.getInstance().findAll());
                    break;
                case 3:
                    ClothingManagement.showClothingList(ClothingService.getInstance().findAll());
                    break;
                case 4:
                    if(flag) {
                        viewList.sort(Comparator.comparing(Stock::getPrice));
                        flag = false;
                    }else {
                        viewList.sort(Comparator.comparing(Stock::getPrice).reversed());
                        flag = true;
                    }

                    System.out.println("Sorting products by price successfully !");

                    break;
                case 5:
                    System.out.println("Enter product id:");
                    productId = ScannerUtils.inputString();

                    if(!StockService.getInstance().findProductById(productId).isEmpty()) {
                        viewList = StockService.getInstance().findProductById(productId);
                    }else {
                        System.err.printf("The product \"%s\" is not found ! %n", productId);
                    }

                    break;
                case 6:
                    System.out.println( "Filter catalog:\n" +
                                        "1. Shoes\n"        +
                                        "2. Clothing"       );
                    choice = ScannerUtils.inputInteger();
                    switch (choice) {
                        case 1:
                            ShoesCatalog shoesCatalog = InputData.shoesCatalog();
                            viewList = viewList.stream().filter(item -> getCatalog(item.getProductId()).equals(shoesCatalog.toString())).collect(Collectors.toList());
                            break;
                        case 2:
                            ClothingCatalog clothingCatalog = InputData.clothingCatalog();
                            viewList = viewList.stream().filter(item -> getCatalog(item.getProductId()).equals(clothingCatalog.toString())).collect(Collectors.toList());
                            break;
                        default:
                            System.err.printf("No choice \"%d\" ! %n", choice);
                    }
                    break;
                case 7:
                    GroupProduct groupProduct = InputData.groupProduct();
                    viewList = viewList.stream().filter(item -> getGroup(item.getProductId()).equals(groupProduct.toString())).collect(Collectors.toList());
                    break;
                case 8:
                    viewList = StockService.getInstance().getAllProduct();
                    break;
                case 9:
                    return;
                default:
                    System.err.printf("No choice \"%d\"%n", choice);
            }
        }
    }

    private static void functionMenu() {
        System.out.println( "Function: \n" +
                            "1. Import product into stock\n" +
                            "2. View shoes list \n" +
                            "3. View clothing list \n" +
                            "4. Sort products by price\n" +
                            "5. Find products by id  \n" +
                            "6. Filter products by catalog\n" +
                            "7. Filter products by group\n" +
                            "8. Reset filter\n" +
                            "9. Back");
    }

    /**View*/
    private static void tableHeader() {
        System.out.println("+-----+-------------------------------------------------------------------------------------------------------------------------------+");
        System.out.println("|                                                                 STOCK                                                               |");
        System.out.println("+-----+-------------------------------------------------------------------------------------------------------------------------------+");
        System.out.printf( "| %-4s| %-15s| %-15s| %-10s| %-8s| %-10s| %-10s| %-18s| %-12s| %-12s|%n",
                "STT", "Product ID", "Catalog", "Group", "Size", "Price", "Quantity", "Status", "Import date", "Update date");
        System.out.println("+-----+-------------------------------------------------------------------------------------------------------------------------------+");
    }

    private static void tableBody(int stt, Stock item) {
        System.out.printf( "| %-4s| %-15s| %-15s| %-10s| %-8s| %-10s| %-10s| %-18s| %-12s| %-12s|%n",
                            stt, item.getProductId(),
                            getCatalog(item.getProductId()),
                            getGroup(item.getProductId()), item.getSize(),
                            item.getPrice(), item.getQuantity(),
                            item.isStatus() ? "In stock" : "Out of stock",
                            SIMPLE_DATE_FORMAT.format(item.getImportDate()),
                            SIMPLE_DATE_FORMAT.format(item.getUpdateDate()));
        System.out.println("+-----+-------------------------------------------------------------------------------------------------------------------------------+");
    }

    private static void viewStockTable(List<Stock> items) {
        tableHeader();
        for (int i = 0; i < items.size(); i++) {
            tableBody(i+ 1, items.get(i));
        }
    }

    public static String getTitle(String productId) {
        if(productId.startsWith("SH")){
            return ShoesService.getInstance().findById(productId).getTitle();
        }
        return ClothingService.getInstance().findById(productId).getTitle();
    }

    public static String getCatalog(String productId) {
        if(productId.startsWith("SH")){
            return ShoesService.getInstance().findById(productId).getCatalog().toString();
        }
        return ClothingService.getInstance().findById(productId).getCatalog().toString();
    }

    public static GroupProduct getGroup(String productId) {
        if(productId.startsWith("SH")){
            return ShoesService.getInstance().findById(productId).getGroupProduct();
        }
        return ClothingService.getInstance().findById(productId).getGroupProduct();
    }

    public static String getFeatured(String productId) {
        if(productId.startsWith("SH")){
            return ShoesService.getInstance().findById(productId).getFeatured().toString();
        }
        return ClothingService.getInstance().findById(productId).getFeatured().toString();
    }
}

