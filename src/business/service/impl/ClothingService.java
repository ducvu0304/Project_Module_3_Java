package business.service.impl;

import business.config.IOFile;
import business.entity.Clothing;

import business.entity.Shoes;
import business.entity.enum_type.ClothingCatalog;
import business.entity.enum_type.Color;
import business.entity.enum_type.Featured;
import business.entity.enum_type.GroupProduct;
import business.service.IClothingService;
import business.utils.IdGenerator;
import business.utils.InputData;
import business.utils.ScannerUtils;
;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;



public class ClothingService implements IClothingService {

    private static final List<Clothing> clothingList = IOFile.getInstance().readFromFile(IOFile.getInstance().getCLOTHING_PATH());

    private static volatile ClothingService clothingService = null;

    private ClothingService(){
    }

    public static ClothingService getInstance() {
        if (clothingService == null) {
            // To make thread safe
            synchronized (ShoesService.class) {
                // check again as multiple threads
                // can reach above step
                if (clothingService == null)
                    clothingService = new ClothingService();
            }
        }
        return clothingService;
    }

    /**
     * Clothing product
     */
    @Override
    public List<Clothing> findAll() throws IOException {
        return clothingList;
    }

    @Override
    public Clothing findById(String clothingId) {
        for (Clothing clothing : clothingList) {
            if (clothing.getId().equalsIgnoreCase(clothingId)) {
                return clothing;
            }
        }
        return null;
    }

    /**Version2*/
    @Override
    public void createClothing(String title, GroupProduct groupProduct) {
        Clothing clothing = new Clothing();
        /**Shoes id*/
        clothing.setId("CL"+ IdGenerator.generateUniqueId());
        /**Input shoes title*/
        clothing.setTitle(title);
        /**Color*/
        clothing.setColor(InputData.color());
        /**Catalog*/
        clothing.setCatalog(InputData.clothingCatalog());
        /**Group product*/
        clothing.setGroupProduct(groupProduct);
        /**Featured*/
        clothing.setFeatured(Featured.NEW);
        /**Status*/
        clothing.setStatus(true);
        /**Add list*/
        clothingList.add(clothing);

        System.out.println("Creating shoes successfully");
    }

    @Override
    public void create() {
        Clothing clothing = new Clothing();

        /**Shoes id*/
        clothing.setId("CL"+ IdGenerator.generateUniqueId());

        /**Input shoes title*/
        clothing.setTitle(InputData.text("Title"));

//        do {
//            System.out.println("Enter title:");
//            clothing.setTitle(ScannerUtils.inputString());
//            if (clothing.getTitle().isEmpty())
//                System.err.println("The title must be not null!");
//        } while (clothing.getTitle().isEmpty());

        /**Color*/
        clothing.setColor(InputData.color());
//        do {
//            System.out.println("Choice color: \n" +
//                    "1. Black\n" +
//                    "2. Blue\n" +
//                    "3. Red\n" +
//                    "4. White");
//
//            choice = ScannerUtils.inputInteger();
//
//            switch (choice) {
//                case 1:
//                    clothing.setColor(Color.BLACK);
//                    break;
//                case 2:
//                    clothing.setColor(Color.BLUE);
//                    break;
//                case 3:
//                    clothing.setColor(Color.RED);
//                    break;
//                case 4:
//                    clothing.setColor(Color.WHITE);
//                    break;
//                default:
//                    System.err.printf("No choice \"%d\"%n", choice);
//            }
//
//        } while (clothing.getColor() == null);

        /**Catalog*/
        clothing.setCatalog(InputData.clothingCatalog());
//        do {
//            System.out.println("Choice catalog: \n" +
//                    "1. Jacket\n" +
//                    "2. Shirt\n" +
//                    "3. Shorts\n" +
//                    "4. Tight");
//
//            choice = ScannerUtils.inputInteger();
//
//            switch (choice) {
//                case 1:
//                    clothing.setCatalog(ClothingCatalog.JACKETS);
//                    break;
//                case 2:
//                    clothing.setCatalog(ClothingCatalog.SHIRT);
//                    break;
//                case 3:
//                    clothing.setCatalog(ClothingCatalog.SHORTS);
//                    break;
//                case 4:
//                    clothing.setCatalog(ClothingCatalog.TIGHT);
//                    break;
//                default:
//                    System.err.printf("No choice \"%d\"%n", choice);
//            }
//        } while (clothing.getCatalog() == null);

        /**Group product*/
        clothing.setGroupProduct(InputData.groupProduct());
//        do {
//            System.out.println("Choice group: \n" +
//                    "1. Men\n" +
//                    "2. Women\n" +
//                    "3. Kid\n" +
//                    "4. Accessories");
//
//            choice = ScannerUtils.inputInteger();
//
//            switch (choice) {
//                case 1:
//                    clothing.setGroupProduct(GroupProduct.MEN);
//                    break;
//                case 2:
//                    clothing.setGroupProduct(GroupProduct.WOMEN);
//                    break;
//                case 3:
//                    clothing.setGroupProduct(GroupProduct.KID);
//                    break;
//                case 4:
//                    clothing.setGroupProduct(GroupProduct.ACCESSORIES);
//                    break;
//                default:
//                    System.err.printf("No choice \"%d\"%n", choice);
//            }
//        } while (clothing.getCatalog() == null);
        /**Featured*/
        clothing.setFeatured(Featured.NEW);
        /**Status*/
        clothing.setStatus(true);
        /**Add list*/
        clothingList.add(clothing);

        System.out.println("Creating shoes successfully");
    }

    @Override
    public void update(Clothing clothing) {
        int choice = 0;

        /**Update title*/
        System.out.println("Enter title:");
        String title = ScannerUtils.inputString().trim();

        if (!title.isEmpty()) {
            clothing.setTitle(title);
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
                    clothing.setColor(Color.BLACK);
                    break;
                case 2:
                    clothing.setColor(Color.BLUE);
                    break;
                case 3:
                    clothing.setColor(Color.RED);
                    break;
                case 4:
                    clothing.setColor(Color.WHITE);
                    break;
                case 0:
                    break;
                default:
                    System.err.printf("No choice \"%d\"%n", choice);
            }

        } while (!Pattern.matches("^[01234]$", String.valueOf(choice)));

        /**Catalog*/
        do {
            System.out.println( "Choice catalog:\n" +
                                "1. Jacket\n"       +
                                "2. Shirt\n"        +
                                "3. Shorts\n"       +
                                "4. Tight\n"        +
                                "0. No change"      );
            choice = ScannerUtils.inputInteger();

            switch (choice) {
                case 1:
                    clothing.setCatalog(ClothingCatalog.JACKETS);
                    break;
                case 2:
                    clothing.setCatalog(ClothingCatalog.SHIRT);
                    break;
                case 3:
                    clothing.setCatalog(ClothingCatalog.SHORTS);
                    break;
                case 4:
                    clothing.setCatalog(ClothingCatalog.TIGHT);
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
                    clothing.setGroupProduct(GroupProduct.MEN);
                    break;
                case 2:
                    clothing.setGroupProduct(GroupProduct.WOMEN);
                    break;
                case 3:
                    clothing.setGroupProduct(GroupProduct.KID);
                    break;
                case 4:
                    clothing.setGroupProduct(GroupProduct.ACCESSORIES);
                    break;
                case 0:
                    break;
                default:
                    System.err.printf("No choice \"%d\"%n", choice);
            }
        }while (!Pattern.matches("^[01234]$", String.valueOf(choice)));

        System.out.printf("Updating shoes \"%s\" successfully!%n", clothing.getTitle());
    }

    @Override
    public boolean isExistById(String clothingId) {
        for (Clothing clothing: clothingList) {
            if (clothing.getId().equalsIgnoreCase(clothingId)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void save() {
        IOFile.getInstance().writeToFile(IOFile.getInstance().getCLOTHING_PATH(), clothingList);
    }


    @Override
    public Clothing findClothingByTitleOrId(String findValue) {
        for (Clothing clothing: clothingList) {
            if(clothing.getTitle().equalsIgnoreCase(findValue) || clothing.getId().equalsIgnoreCase(findValue)){
                return clothing;
            }
        }
        return null;
    }

    @Override
    public boolean changeFeatured(Clothing clothing) {
        System.out.println("Choice featured:\n" +
                "1. New Arrivals\n" +
                "2. Best Seller\n"  +
                "3. Sale"           );

        int choice = ScannerUtils.inputInteger();

        switch (choice) {
            case 1:
                clothing.setFeatured(Featured.NEW);
                return true;
            case 2:
                clothing.setFeatured(Featured.BEST);
                return true;
            case 3:
                clothing.setFeatured(Featured.SALE);
                return true;
        }
        return false;
    }

    @Override
    public boolean isExistByTitleAndGroup(String title, GroupProduct groupProduct) {
        for (Clothing clothing: clothingList) {
            if(clothing.getTitle().equals(title) && clothing.getGroupProduct().equals(groupProduct)) {
                return true;
            }
        }
        return false;
    }


}
