package business.config;

import business.entity.account.Account;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class IOFile {
    private static IOFile ioFile;

    private IOFile() {
    }

    public static IOFile getInstance() {
        if (ioFile == null)
            ioFile = new IOFile();
        return ioFile;
    }

    private static final String BASE_PATH = "src\\business\\data\\";
    private final String SHOES_PATH = buildPath("ShoesTable.txt");
    private final String ACCOUNT_PATH = buildPath("AccountData.txt");
    private final String ID_PATH = buildPath("GeneratedIdData.txt");
    private final String CLOTHING_PATH = buildPath("ClothingTable.txt");
    private final String STOCK_PATH = buildPath("StockTable.txt");
    private final String CART_PATH = buildPath("CartTable.txt");
    private final String COOKIE_PATH = buildPath("Cookie.txt");

    public String getSHOES_PATH() {
        return SHOES_PATH;
    }

    public String getACCOUNT_PATH() {
        return ACCOUNT_PATH;
    }

    public String getID_PATH() {
        return ID_PATH;
    }

    public String getCLOTHING_PATH() {
        return CLOTHING_PATH;
    }

    public String getSTOCK_PATH() {
        return STOCK_PATH;
    }

    public String getCART_PATH() {
        return CART_PATH;
    }

    public String getCOOKIE_PATH() {
        return COOKIE_PATH;
    }

    /**
     * name: readFile
     *
     * @param: path
     */
    public <T> List<T> readFromFile(String path) {
        List<T> list = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(path);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            list = (List<T>) ois.readObject();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (EOFException ignored) {
            // Do nothing on EOFException
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * name: writeFile
     *
     * @param: path
     */
    public <T> void writeToFile(String path, List<T> list) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read and Write Cart table
     */
    public <E, T> TreeMap<E, T> readFromFileCart(String path) {
        TreeMap<E, T> list = new TreeMap<>();

        try (FileInputStream fis = new FileInputStream(path);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            list = (TreeMap<E, T>) ois.readObject();
        } catch (FileNotFoundException e) {
            return  new TreeMap<>();
        } catch (EOFException ignored) {
            // Do nothing on EOFException
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }


    public <E, T> void writeToFileCarts(String path, TreeMap<E, T> list) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Read and Wrire cookie
     */
    public Account readFromCookie(String path) {
        Account account = new Account();

        try (FileInputStream fis = new FileInputStream(path);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            account = (Account) ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (EOFException ignored) {
            // Do nothing on EOFException
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return account;
    }


    public  void writeToCookie(String path, Account account) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(account);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static String buildPath(String fileName) {
        return BASE_PATH + fileName;
    }
}
