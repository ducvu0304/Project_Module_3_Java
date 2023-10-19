package business.service.impl;

import business.config.IOFile;
import business.entity.Stock;
import business.entity.enum_type.Size;
import business.service.IStockService;
import business.utils.InputData;
import business.utils.ScannerUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class StockService implements IStockService {
    private static List<Stock> productList = IOFile.getInstance().readFromFile(IOFile.getInstance().getSTOCK_PATH());
    private static volatile StockService stockService =  null;


    private StockService(){}

    public static StockService getInstance() {
        if (stockService == null) {
            // To make thread safe
            synchronized (ShoesService.class) {
                // check again as multiple threads
                // can reach above step
                if (stockService == null)
                    stockService = new StockService();
            }
        }
        return stockService;
    }

    @Override
    public List<Stock> getAllProduct() {
        return productList;
    }

    @Override
    public void importProduct(String productId, Size size) {
        Stock stock = new Stock();
        /**Product id*/
        stock.setProductId(productId);
        /**Size*/
        stock.setSize(size);
        /**Price*/
        stock.setPrice(InputData.price());
        /**Quantity*/
        stock.setQuantity(InputData.quantity());
        /**Status*/
        stock.setStatus(true);
        /**Import date*/
        stock.setImportDate(LocalDateTime.now());
        /**Update date*/
        stock.setUpdateDate(LocalDateTime.now());
        /**Add into product list*/
        productList.add(stock);

        System.out.printf("Importing product \"%s\" successfully!%n", productId);
    }

    @Override
    public List<Stock> findProductById(String productId) {
        return productList.stream().filter(item -> item.getProductId().equals(productId)).collect(Collectors.toList());
    }

    @Override
    public Stock findItemByProductIdAndSize(String productId, Size size) {
        if (!productList.isEmpty()) {
            for (Stock item: productList) {
                if(item.getProductId().equals(productId) && item.getSize().equals(size)) {
                    return item;
                }
            }
        }
        return null;
    }

    @Override
    public void save() {
        IOFile.getInstance().writeToFile(IOFile.getInstance().getSTOCK_PATH(), productList);
    }
}
