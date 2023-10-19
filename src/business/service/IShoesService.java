package business.service;

import business.entity.Shoes;
import business.entity.enum_type.GroupProduct;

public interface IShoesService extends IGeneric<Shoes, String> {
    void createShoes(String title, GroupProduct groupProduct);
    Shoes findShoesByTitleOrId(String findValue);
    boolean changeFeatured(Shoes shoes);
    boolean isExistByTitleAndGroup(String title, GroupProduct groupProduct);
}
