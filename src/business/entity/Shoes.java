package business.entity;
import business.entity.enum_type.Color;
import business.entity.enum_type.Featured;
import business.entity.enum_type.GroupProduct;
import business.entity.enum_type.ShoesCatalog;

import java.io.Serializable;

public class Shoes implements Serializable {
    private String id;
    private String title;
    private Color color;
    private ShoesCatalog catalog;
    private GroupProduct groupProduct;
    private Featured featured;
    private boolean status;


    /**Constructor*/
    public Shoes() {
    }

    public Shoes(String id, String title, Color color, ShoesCatalog catalog, GroupProduct groupProduct) {
        this.id = id;
        this.title = title;
        this.color = color;
        this.catalog = catalog;
        this.groupProduct = groupProduct;
    }



    /**Getter & Setter*/
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

    public ShoesCatalog getCatalog() {
        return catalog;
    }

    public void setCatalog(ShoesCatalog catalog) {
        this.catalog = catalog;
    }

    public GroupProduct getGroupProduct() {
        return groupProduct;
    }

    public void setGroupProduct(GroupProduct groupProduct) {
        this.groupProduct = groupProduct;
    }

    public Featured getFeatured() {
        return featured;
    }

    public void setFeatured(Featured featured) {
        this.featured = featured;
    }

    @Override
    public String toString() {
        return "Shoes{" +
                "shoesId='" + id + '\'' +
                ", title='" + title + '\'' +
                ", color=" + color +
                ", catalog=" + catalog +
                ", groupProduct=" + groupProduct +
                '}';
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
