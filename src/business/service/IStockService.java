package business.service;

import business.entity.Stock;
import business.entity.enum_type.Size;

import java.util.List;

public interface IStockService {
    void importProduct(String productId, Size size);
    List<Stock> getAllProduct();
    List<Stock> findProductById(String productId);
    Stock findItemByProductIdAndSize(String productId, Size size);
    void save();
}
