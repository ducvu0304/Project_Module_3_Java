package business.service.impl;

import business.config.IOFile;
import business.entity.Clothing;
import business.entity.Shoes;
import business.entity.enum_type.Color;
import business.entity.enum_type.Featured;
import business.entity.enum_type.GroupProduct;
import business.entity.enum_type.ShoesCatalog;
import business.service.IShoesService;
import business.utils.IdGenerator;
import business.utils.InputData;
import business.utils.ScannerUtils;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

public class ShoesService implements IShoesService {
    private static final List<Shoes> shoesList = IOFile.getInstance().readFromFile(IOFile.getInstance().getSHOES_PATH());
    private static volatile ShoesService shoesService = null;

    private ShoesService() {
    }

    public static ShoesService getInstance() {
        if (shoesService == null) {
            // To make thread safe
            synchronized (ShoesService.class) {
                // check again as multiple threads
                // can reach above step
                if (shoesService == null)
                    shoesService = new ShoesService();
            }
        }
        return shoesService;
    }

    @Override
    public void createShoes(String title, GroupProduct groupProduct) {
        Shoes shoes = new Shoes();
        /**Shoes id*/
        shoes.setId("SH"+ IdGenerator.generateUniqueId());
        /**Input shoes title*/
        shoes.setTitle(title);
        /**Color*/
        shoes.setColor(InputData.color());
        /**Catalog*/
        shoes.setCatalog(InputData.shoesCatalog());
        /**Group product*/
        shoes.setGroupProduct(groupProduct);
        /**Featured*/
        shoes.setFeatured(Featured.NEW);
        /**Status*/
        shoes.setStatus(true);
        /**Add list*/
        shoesList.add(shoes);
        System.out.println("Creating shoes successfully");
    }

    @Override
    public List<Shoes> findAll() throws IOException {
        return shoesList;
    }

    @Override
    public Shoes findById(String shoesId) {
        for (Shoes shoes : shoesList) {
            if (shoes.getId().equalsIgnoreCase(shoesId)) {
                return shoes;
            }
        }
        return null;
    }

    @Override
    public void create() {
        int choice = 0;
        Shoes shoes = new Shoes();

        /**Shoes id*/
        shoes.setId("SH"+ IdGenerator.generateUniqueId());
        /**Input shoes title*/
        shoes.setTitle(InputData.text("Title"));
        /**Color*/
        shoes.setColor(InputData.color());
        /**Catalog*/
        shoes.setCatalog(InputData.shoesCatalog());
        /**Group product*/
        shoes.setGroupProduct(InputData.groupProduct());
        /**Featured*/
        shoes.setFeatured(Featured.NEW);
        /**Status*/
        shoes.setStatus(true);
        /**Add list*/
        shoesList.add(shoes);

        System.out.println("Creating shoes successfully");
    }

    @Override
    public boolean isExistByTitleAndGroup(String title, GroupProduct groupProduct) {
        for (Shoes shoes: shoesList) {
            if(shoes.getTitle().equals(title) && shoes.getGroupProduct().equals(groupProduct)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void update(Shoes shoes) {
        int choice = 0;

        /**Update title*/
        System.out.println("Enter title:");
        String title = ScannerUtils.inputString().trim();

        if (!title.isEmpty()) {
            shoes.setTitle(title);
        }

        /**Update color*/
        do {
            System.out.println("Choice color: \n" +
                                "1. Black\n" +
                                "2. Blue\n" +
                                "3. Red\n" +
                                "4. White\n" +
                                "0. No change");
                        choice = ScannerUtils.inputInteger();

            switch (choice) {
                case 1:
                    shoes.setColor(Color.BLACK);
                    break;
                case 2:
                    shoes.setColor(Color.BLUE);
                    break;
                case 3:
                    shoes.setColor(Color.RED);
                    break;
                case 4:
                    shoes.setColor(Color.WHITE);
                    break;
                case 0:
                    break;
                default:
                    System.err.printf("No choice \"%d\"%n", choice);
            }
        } while (!Pattern.matches("^[01234]$", String.valueOf(choice)));

        /**Catalog*/
        do {
            System.out.println("Choice catalog: \n" +
                                "1. Running\n" +
                                "2. Hiking\n" +
                                "3. Tennis\n" +
                                "4. Indoor\n" +
                                "0. No change");
            choice = ScannerUtils.inputInteger();

            switch (choice) {
                case 1:
                    shoes.setCatalog(ShoesCatalog.RUNNING);
                    break;
                case 2:
                    shoes.setCatalog(ShoesCatalog.HIKING);
                    break;
                case 3:
                    shoes.setCatalog(ShoesCatalog.TENNIS);
                    break;
                case 4:
                    shoes.setCatalog(ShoesCatalog.INDOOR);
                    break;
                case 0:
                    break;
                default:
                    System.err.printf("No choice \"%d\"%n", choice);
            }

        } while (!Pattern.matches("^[01234]$", String.valueOf(choice)));

        /**Group product*/
        do {
            System.out.println( "Choice group: \n" +
                                "1. Men\n" +
                                "2. Women\n" +
                                "3. Kid\n" +
                                "4. Accessories\n" +
                                "0. No change");

            System.out.println("Enter choice");
            choice = ScannerUtils.inputInteger();

            switch (choice) {
                case 1:
                    shoes.setGroupProduct(GroupProduct.MEN);
                    break;
                case 2:
                    shoes.setGroupProduct(GroupProduct.WOMEN);
                    break;
                case 3:
                    shoes.setGroupProduct(GroupProduct.KID);
                    break;
                case 4:
                    shoes.setGroupProduct(GroupProduct.ACCESSORIES);
                    break;
                case 0:
                    break;
                default:
                    System.err.printf("No choice \"%d\"%n", choice);
            }
        }while (!Pattern.matches("^[01234]$", String.valueOf(choice)));

        System.out.printf("Updating shoes \"%s\" successfully!%n", shoes.getTitle());
    }

    @Override
    public boolean isExistById(String shoesId) {
        for (Shoes shoes : shoesList) {
            if (shoes.getId().equalsIgnoreCase(shoesId)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void save() {
        IOFile.getInstance().writeToFile(IOFile.getInstance().getSHOES_PATH(), shoesList);
    }

    @Override
    public Shoes findShoesByTitleOrId(String findValue) {
        for (Shoes shoes: shoesList) {
            if(shoes.getTitle().equalsIgnoreCase(findValue) || shoes.getId().equalsIgnoreCase(findValue)){
                return shoes;
            }
        }
        return null;
    }

    @Override
    public boolean changeFeatured(Shoes shoes) {

        System.out.println("Choice featured:\n" +
                            "1. New Arrivals\n" +
                            "2. Best Seller\n"  +
                            "3. Sale"           );

        int choice = ScannerUtils.inputInteger();

        switch (choice) {
            case 1:
                shoes.setFeatured(Featured.NEW);
                return true;
            case 2:
                shoes.setFeatured(Featured.BEST);
                return true;
            case 3:
                shoes.setFeatured(Featured.SALE);
                return true;
        }
        return false;
    }

}
