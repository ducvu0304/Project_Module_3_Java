package business.utils;

import business.entity.enum_type.*;

public class InputData {

    /**Quantity*/
    public static int quantity() {
        int quantity;
        do {
            System.out.println("Enter quantity:");
            quantity = ScannerUtils.inputInteger();

            if (quantity < 0) {
                System.err.println("The quantity must be greater than 0 !");
            }

        }while (quantity < 0);

        return quantity;
    }
    /**Price*/
    public static double price() {
        double price;

        do {
            System.out.println("Enter price:");
            price = ScannerUtils.inputDouble();

            if (price < 0) {
                System.err.println("The price must be greater than 0 !");
            }

        }while (price < 0);

        return price;
    }
    /**Text*/
    public static String text(String title) {
        String str;

        do{
            System.out.println("Enter " + title + " :");
            str = ScannerUtils.inputString().trim();

            if (str.isEmpty())
                System.err.printf("The %s is must be not null !%n", title);

        }while (str.isEmpty());

        return str;
    }

    /**Size*/
    public static Size size(){
        while (true) {
            System.out.println( "Choice Size:\n" +
                    "1. Small\n" +
                    "2. Medium\n"+
                    "3. Large\n" +
                    "4. XLarge"   );
            int choiceSize =  ScannerUtils.inputInteger();

            switch (choiceSize) {
                case 1:
                    return Size.SMALL;
                case 2:
                    return Size.MEDIUM;
                case 3:
                    return Size.LARGE;
                case 4:
                    return Size.XLARGE;
                default:
                    System.err.printf("No choice \"%d\"%n", choiceSize);
            }
        }
    }

    /**Clothing catalog*/
    public static ShoesCatalog shoesCatalog(){
        while (true) {
            System.out.println( "Choice catalog:\n" +
                                "1. Running\n"      +
                                "2. Hiking\n"       +
                                "3. Tennis\n"       +
                                "4. Indoor"         );
            int choiceSize =  ScannerUtils.inputInteger();

            switch (choiceSize) {
                case 1:
                    return ShoesCatalog.RUNNING;
                case 2:
                    return ShoesCatalog.HIKING;
                case 3:
                    return ShoesCatalog.TENNIS;
                case 4:
                    return ShoesCatalog.INDOOR;
                default:
                    System.err.printf("No choice \"%d\"%n", choiceSize);
            }
        }
    }

    /**Clothing catalog*/
    public static ClothingCatalog clothingCatalog(){
        while (true) {
            System.out.println( "Choice catalog:\n" +
                                "1. Jackets\n"      +
                                "2. Shirt\n"        +
                                "3. Shorts\n"       +
                                "4. Tight"          );
            int choiceSize =  ScannerUtils.inputInteger();

            switch (choiceSize) {
                case 1:
                    return ClothingCatalog.JACKETS;
                case 2:
                    return ClothingCatalog.SHIRT;
                case 3:
                    return ClothingCatalog.SHORTS;
                case 4:
                    return ClothingCatalog.TIGHT;
                default:
                    System.err.printf("No choice \"%d\"%n", choiceSize);
            }
        }
    }

    /**Clothing catalog*/
    public static GroupProduct groupProduct(){
        while (true) {
            System.out.println( "Choice group:\n" +
                                "1. Men\n"      +
                                "2. Women\n"        +
                                "3. Kid\n"       +
                                "4. Accessories"          );
            int choiceSize =  ScannerUtils.inputInteger();

            switch (choiceSize) {
                case 1:
                    return GroupProduct.MEN;
                case 2:
                    return GroupProduct.WOMEN;
                case 3:
                    return GroupProduct.KID;
                case 4:
                    return GroupProduct.ACCESSORIES;
                default:
                    System.err.printf("No choice \"%d\"%n", choiceSize);
            }
        }
    }

    /**Color */
    public static Color color(){
        while (true) {
            System.out.println( "Choice color: \n" +
                                "1. Black\n" +
                                "2. Blue\n" +
                                "3. Red\n" +
                                "4. White");

            int choice =  ScannerUtils.inputInteger();

            switch (choice) {
                case 1:
                    return Color.BLACK;
                case 2:
                    return Color.BLUE;
                case 3:
                    return Color.RED;
                case 4:
                    return Color.WHITE;
                default:
                    System.err.printf("No choice \"%d\"%n", choice);
            }
        }
    }


}

