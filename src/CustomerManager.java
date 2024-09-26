import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerManager {
    private static  final  String FILE_NAME = config.customerDataPath;
    private List<Customer> customers;
    public CustomerManager() {
        this.customers =  new ArrayList<>();
        readCustomersFromFile();
    }

    public void updateCustomer(int customerID, String customerName, String customerPhone,
                               String customerPurchases, String customerPreferences) {
        for (Customer customer : customers) {
            if (customer.getCustomerID() == customerID) {
                customer.setCustomerName(customerName);
                customer.setCustomerPhone(customerPhone);
                customer.setCustomerPurchases(customerPurchases);
                customer.setCustomerPreferences(customerPreferences);
                break;
            }
        }
        saveCustomersToFile();
    }

    private void readCustomersFromFile() {
        try(BufferedReader br = new BufferedReader(new FileReader(FILE_NAME)) ){
            String line;
            Customer customer = null;

            while((line = br.readLine()) != null){
                if(line.startsWith("ID:")){
                    if(customer != null){
                        customers.add(customer);
                    }
                    customer =  new Customer();
                    customer.setCustomerID(Integer.parseInt(line.substring(3)));
                }else if(line.startsWith("Name:")){
                    customer.setCustomerName(line.substring(5));
                }else if(line.startsWith("Phone Number:")){
                    customer.setCustomerPhone(line.substring(13));
                }else if(line.startsWith("Artwork Purchases:")){
                    customer.setCustomerPurchases(line.substring(18));
                }else if(line.startsWith("Artist Preferences:")){
                    customer.setCustomerPreferences(line.substring(19));
                }
            }

            if(customer != null){
                customers.add(customer);
            }
        }catch (IOException e){
            System.out.println("Error reading file " + FILE_NAME);
            e.printStackTrace();
        }
    }

    private void saveCustomersToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Customer customer : customers) {
                bw.write("ID:" + customer.getCustomerID());
                bw.newLine();
                bw.write("Name:" + customer.getCustomerName());
                bw.newLine();
                bw.write("Phone Number:" + customer.getCustomerPhone());
                bw.newLine();
                bw.write("Artwork Purchases:" + customer.getCustomerPurchases());
                bw.newLine();
                bw.write("Artist Preferences:" + customer.getCustomerPreferences());
                bw.newLine();

            }
        } catch (IOException e) {
            System.out.println("Error writing customers to file.");
            e.printStackTrace();
        }
    }

    public void addCustomer(int ID , String customerName, String customerPhone,
                            String customerPurchases, String customerPreferences) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            bw.write("ID:" + ID);
            bw.newLine();
            bw.write("Name:" + customerName);
            bw.newLine();
            bw.write("Phone Number:" + customerPhone);
            bw.newLine();
            bw.write("Artwork Purchases:" + customerPurchases);
            bw.newLine();
            bw.write("Artist Preferences:" + customerPreferences);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error writing customers to file.");
            e.printStackTrace();
        }

    }


        public static class Customer{
            private int customerID ;
            private String customerName ;
            private String customerPhone ;
            private String customerPurchases;
            private String customerPreferences;


        public int getCustomerID() {
            return customerID;
        }

        public void setCustomerID(int customerID) {
            this.customerID = customerID;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getCustomerPhone() {
            return customerPhone;
        }

        public void setCustomerPhone(String customerPhone) {
            this.customerPhone = customerPhone;
        }

        public String getCustomerPurchases() {
            return customerPurchases;
        }

        public void setCustomerPurchases(String customerPurchases) {
            this.customerPurchases = customerPurchases;
        }

        public String getCustomerPreferences() {
            return customerPreferences;
        }

        public void setCustomerPreferences(String customerPreferences) {
            this.customerPreferences = customerPreferences;
        }

    }
}
