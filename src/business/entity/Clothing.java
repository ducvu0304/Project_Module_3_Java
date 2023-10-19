package business.entity;

import business.entity.enum_type.ClothingCatalog;
import business.entity.enum_type.Color;
import business.entity.enum_type.Featured;
import business.entity.enum_type.GroupProduct;

import java.io.Serializable;

public class Clothing  implements Serializable {
    private String id;
    private String title;
    private Color color;
    private ClothingCatalog catalog;
    private GroupProduct groupProduct;
    private Featured featured;
    private boolean status;

    /**Constructor*/
    public Clothing(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ClothingCatalog getCatalog() {
        return catalog;
    }

    public void setCatalog(ClothingCatalog catalog) {
        this.catalog = catalog;
    }

    public GroupProduct getGroupProduct() {
        return groupProduct;
    }

    public void setGroupProduct(GroupProduct groupProduct) {
        this.groupProduct = groupProduct;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Featured getFeatured() {
        return featured;
    }

    public void setFeatured(Featured featured) {
        this.featured = featured;
    }

    @Override
    public String toString() {
        return "Clothing{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", color=" + color +
                ", catalog=" + catalog +
                ", groupProduct=" + groupProduct +
                ", status=" + status +
                '}';
    }
}
