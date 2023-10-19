package business.service;

import business.entity.Clothing;
import business.entity.Shoes;
import business.entity.enum_type.GroupProduct;


public interface IClothingService extends IGeneric<Clothing, String> {
    void createClothing(String title, GroupProduct groupProduct);
    Clothing findClothingByTitleOrId(String findValue);
    boolean changeFeatured(Clothing clothing);
    boolean isExistByTitleAndGroup(String title, GroupProduct groupProduct);
}
