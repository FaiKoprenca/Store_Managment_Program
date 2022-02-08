package Module;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Cashier extends User implements Serializable {

    String name;
    String phoneNumber;
    Date birthday;
    String email;
    double salary;
    String idCardNumber;

    ArrayList<SoldProduct> soldProduct;
    ArrayList<Bill> bills;
    int numOfBills;
    int numOfProdsSold;
    double moneyGenerated;

    String birthdayDayString;
    int billOverAPeriod;
    int soldProdsOverAPeriod;
    double moneyGenOverAPeriod;
    double salaryOverAPeriod;

    public Cashier() {
        super();
        soldProduct = new ArrayList<>();
        bills = new ArrayList<>();
        numOfBills = 0;
        birthday = new Date();
        billOverAPeriod = 0;
        soldProdsOverAPeriod = 0;
        moneyGenOverAPeriod = 0;
        salaryOverAPeriod = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public ArrayList<SoldProduct> getSoldProduct() {
        return soldProduct;
    }

    public void setSoldProduct(ArrayList<SoldProduct> soldProduct) {
        this.soldProduct = soldProduct;
    }

    public ArrayList<Bill> getBills() {
        return bills;
    }

    public void setBills(ArrayList<Bill> bills) {
        this.bills = bills;
    }

    public int getNumOfBills()
    {
        numOfBills = bills.size();
        return numOfBills;
    }

    public int getNumOfProdsSold()
    {
        numOfProdsSold = soldProduct.size();
        return numOfProdsSold;
    }

    public double getMoneyGeneratedTot()
    {
        moneyGenerated = 0;

        for (Bill b : bills)
        {
            moneyGenerated += b.getPrice();
        }

        return moneyGenerated;
    }

    public String getBirthdayDayString() {

        birthdayDayString = birthday.getDate();

        return birthdayDayString;
    }

    public int getBillOverAPeriod() {
        return billOverAPeriod;
    }

    public int getSoldProdsOverAPeriod() {
        return soldProdsOverAPeriod;
    }

    public double getMoneyGenOverAPeriod() {
        return moneyGenOverAPeriod;
    }

    public void setBillAndMoneyGenOverAPeriod(int dayFrom, int monthFrom, int yearFrom, int dayTo, int monthTo, int yearTo)
    {
        ArrayList<Bill> billsOverAPeriodList = new ArrayList<>();
        LocalDate dateFrom = LocalDate.of(yearFrom, monthFrom, dayFrom);
        LocalDate dateTo = LocalDate.of(yearTo, monthTo, dayTo);

        for(Bill b : bills)
            if(b.getDatePrinted().isAfter(dateFrom) && b.getDatePrinted().isBefore(dateTo))
                billsOverAPeriodList.add(b);
            else if(b.getDatePrinted().isEqual(dateFrom) || b.getDatePrinted().isEqual(dateTo))
                billsOverAPeriodList.add(b);

        billOverAPeriod = billsOverAPeriodList.size();

        moneyGenOverAPeriod = 0.0;
        for(Bill b : billsOverAPeriodList)
            moneyGenOverAPeriod += b.getPrice();
    }


    public void setSoldProdsOverAPeriod(int dayFrom, int monthFrom, int yearFrom, int dayTo, int monthTo, int yearTo)
    {
        ArrayList<SoldProduct> prodsOverAPeriodList = new ArrayList<>();
        LocalDate dateFrom = LocalDate.of(yearFrom, monthFrom, dayFrom);
        LocalDate dateTo = LocalDate.of(yearTo, monthTo, dayTo);

        for(SoldProduct sp : soldProduct)
        {
            if (sp.getSoldDate().isAfter(dateFrom) && sp.getSoldDate().isBefore(dateTo))
            {
                prodsOverAPeriodList.add(sp);
            }
            else if (sp.getSoldDate().isEqual(dateFrom) || sp.getSoldDate().isEqual(dateTo))
            {
                prodsOverAPeriodList.add(sp);
            }
        }

        soldProdsOverAPeriod = 0;

        for (SoldProduct sp : prodsOverAPeriodList)
        {
            soldProdsOverAPeriod += sp.getQuantity();
        }

    }


    public double getSalaryOverAPeriod() {
        return salaryOverAPeriod;
    }

    public void setSalaryOverAPeriod(int monthFrom, int yearFrom, int monthTo, int yearTo) {

        int yearsBetween = yearTo - yearFrom;
        int monthsBetween = monthTo - monthFrom;
        int nrOfMonths = (yearsBetween * 12) + monthsBetween;

        salaryOverAPeriod = salary * nrOfMonths;
    }

    public void resetStatsOverAPeriod()
    {
        billOverAPeriod = 0;
        soldProdsOverAPeriod = 0;
        moneyGenOverAPeriod = 0;
        salaryOverAPeriod = 0;
    }
}
