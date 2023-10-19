package presentation.shop;

import business.entity.Clothing;
import business.entity.enum_type.GroupProduct;
import business.service.impl.ClothingService;
import business.service.impl.ShoesService;
import business.utils.InputData;
import business.utils.ScannerUtils;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


import static business.utils.ScannerUtils.inputInteger;

public class ClothingManagement {
    public static void menu() throws IOException {
        Optional<Clothing> optional = Optional.empty();
        String findValue = null;

        while (true) {
            System.out.println( "+------------------------------------------+\n" +
                                "|                CLOTHING                  |\n" +
                                "+---+--------------------------------------+\n" +
                                "| 1 | View all products                    |\n" +
                                "+---+--------------------------------------+\n" +
                                "| 2 | Add clothing                         |\n" +
                                "+---+--------------------------------------+\n" +
                                "| 3 | Update clothing                      |\n" +
                                "+---+--------------------------------------+\n" +
                                "| 4 | Block/Unblock status of clothing     |\n" +
                                "+---+--------------------------------------+\n" +
                                "| 5 | Change featured of clothing          |\n" +
                                "+---+--------------------------------------+\n" +
                                "| 6 | Back                                 |\n" +
                                "+---+--------------------------------------+"   );

            int choice = inputInteger();

            switch (choice) {
                case 1:
                    if (!ClothingService.getInstance().findAll().isEmpty()) {
                        showClothingList(ClothingService.getInstance().findAll());
                    }else {
                        System.out.println("Clothing list is empty!");
                    }

                    break;
                case 2:
                    String title = InputData.text("title:");
                    GroupProduct group = InputData.groupProduct();

                    if(!ClothingService.getInstance().isExistByTitleAndGroup(title, group)) {
                        ClothingService.getInstance().createClothing(title, group);
                        ClothingService.getInstance().save();
                    }else {
                        System.err.printf("The Clothing \"%s\" is exist!%n", title);
                    }

                    break;
                case 3:
                    System.out.println("Enter title or id:");
                    findValue = ScannerUtils.inputString();
                    optional = Optional.ofNullable(ClothingService.getInstance().findClothingByTitleOrId(findValue));

                    if (optional.isPresent()) {
                        ClothingService.getInstance().update(optional.get());
                        ClothingService.getInstance().save();
                    }else {
                        System.out.printf("The Shoes \"%s\" is not found!%n", findValue);
                    }

                    break;
                case 4:
                    System.out.println("Enter title or id:");
                    findValue = ScannerUtils.inputString();

                    optional = Optional.ofNullable(ClothingService.getInstance().findClothingByTitleOrId(findValue));

                    if (optional.isPresent()) {
                        optional.get().setStatus(!optional.get().isStatus());
                        ClothingService.getInstance().save();
                    }else {
                        System.out.printf("The Shoes \"%s\" is not found!%n", findValue);
                    }

                    break;
                case 5:
                    System.out.println("Enter title or id:");
                    findValue = ScannerUtils.inputString();

                    optional = Optional.ofNullable(ClothingService.getInstance().findClothingByTitleOrId(findValue));

                    if (optional.isPresent()) {
                        if(ClothingService.getInstance().changeFeatured(optional.get())) {
                            ShoesService.getInstance().save();
                            System.out.println("Updating featured successfully!");
                        }else {
                            System.out.println("Updating featured failed!");
                        }
                    }else {
                        System.out.printf("The Shoes \"%s\" is not found!%n", findValue);
                    }
                    break;
                case 6:
                    return;
                default:
                    System.err.printf("No choice \"%d\"%n", choice);
            }
        }
    }

    private static void header() {
        System.out.println("+-----+-----------------------------------------------------------------------------------------------------------------+");
        System.out.println("|                                                        CLOTHING TABLE                                                 |");
        System.out.println("+-----+-----------------------------------------------------------------------------------------------------------------+");
        System.out.printf( "| %-4s| %-20s| %-20s| %-12s| %-12s| %-12s| %-12s| %-12s|%n",
                             "STT", "Product ID", "Title", "Catalog", "Group", "Color", "Featured", "Status");
        System.out.println("+-----+-----------------------------------------------------------------------------------------------------------------+");
    }

    /**Get clothing catalog*/
    private static void clothingInformation(int stt, Clothing clothing) {
        System.out.printf("| %-4s| %-20s| %-20s| %-12s| %-12s| %-12s| %-12s| %-12s|%n",
                stt,
                clothing.getId(),
                clothing.getTitle(),
                clothing.getCatalog(),
                clothing.getGroupProduct(),
                clothing.getColor(),
                clothing.getFeatured(),
                clothing.isStatus() ? "Active" : "Non-active");
        System.out.println("+-----+-----------------------------------------------------------------------------------------------------------------+");
    }

    public static void showClothingList(List<Clothing> clothingList) {
        header();
        for (int i = 0; i < clothingList.size(); i++) {
            clothingInformation(i+1, clothingList.get(i));
        }
    }

}
