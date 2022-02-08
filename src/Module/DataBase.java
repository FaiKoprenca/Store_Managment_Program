package Module;

import java.io.*;
import java.util.ArrayList;

public class DataBase {


    static String cashiersPath = "resources/Employees/Cashiers.ser";
    static String managersPath = "resources/Employees/Manager.ser";
    static String categoriesPath = "resources/Statistics/Categories.ser";
    static String productsPath = "resources/Statistics/Products.ser";
    static String boughtProdsPath = "resources/Statistics/BoughtProducts.ser";
    static String suppliersPath = "resources/Suppliers/Suppliers.ser";

    static ArrayList<Cashier> cashiers;
    static ArrayList<Manager> managers;
    static ArrayList<String> genre;
    static ArrayList<Product> products;
    static ArrayList<BoughtProduct> boughtProducts;
    static ArrayList<Supplier> suppliers;

    public static ArrayList<Cashier> getCashiers() { return cashiers; }
    public static ArrayList<Manager> getManagers() { return managers; }
    public static ArrayList<String> getGenre() { return genre; }
    public static ArrayList<Product> getProducts() { return products; }
    public static ArrayList<BoughtProduct> getBoughtProducts() { return boughtProducts; }
    public static ArrayList<Supplier> getSuppliers() { return suppliers; }


    public static void putDataInLists()
    {
        cashiers = new ArrayList<Cashier>();
        managers = new ArrayList<Manager>();
        products = new ArrayList<Product>();
        boughtProducts = new ArrayList<BoughtProduct>();
        genre = new ArrayList<>();
        suppliers = new ArrayList<Supplier>();


        fillCashiers();
        fillManager();
        fillProducts();
        fillGenre();
        fillSuppliers();
        load();
    }


    public static void load()
    {
        try {
            FileInputStream cashiersListFile = new FileInputStream(cashiersPath);
            ObjectInputStream inCashiers = new ObjectInputStream(cashiersListFile);

            FileInputStream managerListFile = new FileInputStream(managersPath);
            ObjectInputStream inManagers = new ObjectInputStream(managerListFile);

            FileInputStream categoriesListFile = new FileInputStream(categoriesPath);
            ObjectInputStream inCategories = new ObjectInputStream(categoriesListFile);

            FileInputStream productsListFile = new FileInputStream(productsPath);
            ObjectInputStream inProducts = new ObjectInputStream(productsListFile);

            FileInputStream boughtProductsListFile = new FileInputStream(boughtProdsPath);
            ObjectInputStream inBoughtProducts = new ObjectInputStream(boughtProductsListFile);

            FileInputStream suppliersListFile = new FileInputStream(suppliersPath);
            ObjectInputStream inSuppliers = new ObjectInputStream(suppliersListFile);

            cashiers = (ArrayList<Cashier>)inCashiers.readObject();
            managers = (ArrayList<Manager>)inManagers.readObject();
            genre = (ArrayList)inCategories.readObject();
            products = (ArrayList<Product>)inProducts.readObject();
            boughtProducts = (ArrayList<BoughtProduct>)inBoughtProducts.readObject();
            suppliers = (ArrayList<Supplier>)inSuppliers.readObject();

            inCashiers.close();
            cashiersListFile.close();

            inManagers.close();
            managerListFile.close();

            inCategories.close();
            categoriesListFile.close();

            inProducts.close();
            productsListFile.close();

            inBoughtProducts.close();
            boughtProductsListFile.close();

            inSuppliers.close();
            suppliersListFile.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("database exception error");
        }
    }

    public static void save()
    {
        try {

            FileOutputStream fosCashiers = new FileOutputStream(cashiersPath);
            ObjectOutputStream oosCashiers = new ObjectOutputStream(fosCashiers);

            FileOutputStream fosManagers = new FileOutputStream(managersPath);
            ObjectOutputStream oosManagers = new ObjectOutputStream(fosManagers);

            FileOutputStream fosProducts = new FileOutputStream(productsPath);
            ObjectOutputStream oosProducts = new ObjectOutputStream(fosProducts);

            FileOutputStream fosProdsBought = new FileOutputStream(boughtProdsPath);
            ObjectOutputStream oosProdsBought = new ObjectOutputStream(fosProdsBought);

            FileOutputStream fosCategories = new FileOutputStream(categoriesPath);
            ObjectOutputStream oosCategories = new ObjectOutputStream(fosCategories);

            FileOutputStream fosSuppliers = new FileOutputStream(suppliersPath);
            ObjectOutputStream oosSuppliers = new ObjectOutputStream(fosSuppliers);

            oosCashiers.writeObject(cashiers);
            oosManagers.writeObject(managers);
            oosProducts.writeObject(products);
            oosProdsBought.writeObject(boughtProducts);
            oosCategories.writeObject(genre);
            oosSuppliers.writeObject(suppliers);

            oosCashiers.close();
            fosCashiers.close();

            oosManagers.close();
            fosManagers.close();

            oosProducts.close();
            fosProducts.close();

            oosProdsBought.close();
            fosProdsBought.close();

            oosCategories.close();
            fosCategories.close();

            oosSuppliers.close();
            fosSuppliers.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Database save error");
        }

        System.out.println("********SAVED********DB");
    }

    static void fillCashiers() {
        cashiers.add(new Cashier());
        cashiers.get(0).setUsername("cashier1");
        cashiers.get(0).setPassword("cashier123");
        cashiers.get(0).setRole("Cashier");
        cashiers.get(0).setName("Cashier1");
        cashiers.get(0).setPhoneNumber("068-5623045");
        cashiers.get(0).setBirthday(new Date(5, 7, 1990));
        cashiers.get(0).setEmail("jim1990@gmail.com");
        cashiers.get(0).setSalary(700.0);
        cashiers.get(0).setIdCardNumber("J67439835I");

        cashiers.add(new Cashier());
        cashiers.get(1).setUsername("cashier2");
        cashiers.get(1).setPassword("cashier123");
        cashiers.get(1).setRole("Cashier");
        cashiers.get(1).setName("Cashier2");
        cashiers.get(1).setPhoneNumber("068-5617273");
        cashiers.get(1).setBirthday(new Date(7, 2, 1990));
        cashiers.get(1).setEmail("john1990@gmail.com");
        cashiers.get(1).setSalary(700.0);
        cashiers.get(1).setIdCardNumber("J57483097I");
    }

    static void fillManager()
    {
        managers.add(new Manager());
        managers.get(0).setUsername("manager1");
        managers.get(0).setPassword("manager123");
        managers.get(0).setRole("Manager");
        managers.get(0).setName("Manager1");
        managers.get(0).setPhoneNumber("068-5609234");
        managers.get(0).setBirthday(new Date(8, 8, 1990));
        managers.get(0).setEmail("mark990@gmail.com");
        managers.get(0).setSalary(700.0);
        managers.get(0).setIdCardNumber("J50984531I");

        managers.add(new Manager());
        managers.get(1).setUsername("manager2");
        managers.get(1).setPassword("manager123");
        managers.get(1).setRole("Manager");
        managers.get(1).setName("Manager2");
        managers.get(1).setPhoneNumber("068-5640382");
        managers.get(1).setBirthday(new Date(1, 4, 1990));
        managers.get(1).setEmail("allen1990@gmail.com");
        managers.get(1).setSalary(700.0);
        managers.get(1).setIdCardNumber("J60398710I");
    }

    static void fillProducts()
    {
        products.add(new Product());
        products.get(0).setName("CD1");
        products.get(0).setGenre("rock");
        products.get(0).setSinger("Jotaro");
        products.get(0).setSupplierID(1);
        products.get(0).setPrice(17.5);
        products.get(0).setBarcode("ajbawRGawr.wr..wrgJWE");
        products.get(0).setQuantity(200);
        products.get(0).setReleaseDate(new Date(10, 2, 2010));

        products.add(new Product());
        products.get(1).setName("Trilogy");
        products.get(1).setGenre("MixTape");
        products.get(1).setSinger("The Weeknd");
        products.get(1).setSupplierID(2);
        products.get(1).setPrice(15.36);
        products.get(1).setBarcode("aerogiaore..aergkQREGaj");
        products.get(1).setQuantity(200);
        products.get(1).setReleaseDate(new Date(10, 2, 2021));
    }

    static void fillGenre()
    {
        genre.add("Rock");
        genre.add("R&B");
        genre.add("Soul");
    }

    private static void fillSuppliers()
    {

        suppliers.add(new Supplier("Domino & Warner Bros"));
        suppliers.add(new Supplier("Republic Records"));
        suppliers.add(new Supplier("Supplier 3"));
        suppliers.add(new Supplier("Supplier 4"));

        suppliers.get(0).getProductsOffered().add("505 Arctic Monkeys");
        suppliers.get(0).getProductsOffered().add("Brain Storm Arctic Monkeys");

        suppliers.get(1).getProductsOffered().add("The Town");
        suppliers.get(1).getProductsOffered().add("Wanderlust");

        suppliers.get(2).getProductsOffered().add("S3 1");
        suppliers.get(2).getProductsOffered().add("S3 2");

        suppliers.get(3).getProductsOffered().add("S4 1");
        suppliers.get(3).getProductsOffered().add("S4 2");
    }

}
