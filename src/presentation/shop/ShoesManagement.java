package presentation.shop;

import business.entity.Shoes;
import business.entity.enum_type.GroupProduct;
import business.service.impl.ShoesService;
import business.utils.InputData;
import business.utils.ScannerUtils;

import java.io.IOException;
import java.util.*;

import static business.utils.ScannerUtils.inputInteger;

public class ShoesManagement {
    public static void menu() throws IOException {
        Optional<Shoes> optional = Optional.empty();
        String findValue = null;

        while (true) {
            System.out.println("+---------------------------------------+\n" +
                                "|                SHOES                  |\n" +
                                "+---+-----------------------------------+\n" +
                                "| 1 | View all products                 |\n" +
                                "+---+-----------------------------------+\n" +
                                "| 2 | Add shoes                         |\n" +
                                "+---+-----------------------------------+\n" +
                                "| 3 | Update shoes                      |\n" +
                                "+---+-----------------------------------+\n" +
                                "| 4 | Block/Unblock status of shoes     |\n" +
                                "+---+-----------------------------------+\n" +
                                "| 5 | Change featured of shoes          |\n" +
                                "+---+-----------------------------------+\n" +
                                "| 6 | Back                              |\n" +
                                "+---+-----------------------------------+"   );

            System.out.println("Enter choice function:");
            int choice = inputInteger();

            switch (choice) {
                case 1:
                    if (!ShoesService.getInstance().findAll().isEmpty()) {
                        showListShoes(ShoesService.getInstance().findAll());
                    } else {
                        System.out.println("Shoes list is empty");
                    }
                    break;
                case 2:
                    String title = InputData.text("title:").toUpperCase();
                    GroupProduct group = InputData.groupProduct();

                    if(!ShoesService.getInstance().isExistByTitleAndGroup(title, group)) {
                        ShoesService.getInstance().createShoes(title, group);
                        ShoesService.getInstance().save();
                    }else {
                        System.err.printf("The Shoes \"%s\" is exist!%n", title);
                    }
                    break;
                case 3:
                    System.out.println("Enter title or id:");
                    findValue = ScannerUtils.inputString();
                    optional = Optional.ofNullable(ShoesService.getInstance().findShoesByTitleOrId(findValue));

                    if (optional.isPresent()) {
                        ShoesService.getInstance().update(optional.get());
                        ShoesService.getInstance().save();
                    }else {
                        System.out.printf("The Shoes \"%s\" is not found!%n", findValue);
                    }
                    break;
                case 4:
                    System.out.println("Enter title or id:");
                    findValue = ScannerUtils.inputString();

                    optional = Optional.ofNullable(ShoesService.getInstance().findShoesByTitleOrId(findValue));

                    if (optional.isPresent()) {
                        optional.get().setStatus(!optional.get().isStatus());
                        ShoesService.getInstance().save();
                    }else {
                        System.out.printf("The Shoes \"%s\" is not found!%n", findValue);
                    }
                    break;
                case 5:
                    System.out.println("Enter title or id:");
                    findValue = ScannerUtils.inputString();

                    optional = Optional.ofNullable(ShoesService.getInstance().findShoesByTitleOrId(findValue));

                    if (optional.isPresent()) {
                        if(ShoesService.getInstance().changeFeatured(optional.get())) {
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

    public static void header() {
        System.out.println("+-----+-----------------------------------------------------------------------------------------------------------------+");
        System.out.println("|                                                          SHOES TABLE                                                  |");
        System.out.println("+-----+-----------------------------------------------------------------------------------------------------------------+");
        System.out.printf( "| %-4s| %-20s| %-20s| %-12s| %-12s| %-12s| %-12s| %-12s|%n",
                            "STT", "Product ID", "Title", "Catalog", "Group", "Color", "Featured", "Status");
        System.out.println("+-----+-----------------------------------------------------------------------------------------------------------------+");
    }

    public static void shoesInformation(int stt, Shoes shoes) {
        System.out.printf("| %-4s| %-20s| %-20s| %-12s| %-12s| %-12s| %-12s| %-12s|%n",
                stt,
                shoes.getId(),
                shoes.getTitle(),
                shoes.getCatalog(),
                shoes.getGroupProduct(),
                shoes.getColor(),
                shoes.getFeatured(),
                shoes.isStatus() ? "Active" : "Non-active");
        System.out.println("+-----+-----------------------------------------------------------------------------------------------------------------+");
    }

    public static void showListShoes(List<Shoes> list) {
        header();
        for (int i = 0; i < list.size(); i++) {
            shoesInformation(i+1, list.get(i));
        }
    }
}
