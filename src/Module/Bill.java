package Module;

import java.io.Serializable;
import java.time.LocalDate;

public class Bill implements Serializable {

    int billNo;
    double price;
    LocalDate datePrinted;

    public Bill() {
        billNo = getTotNrOfBills();
        price = 0.0;
        datePrinted = LocalDate.now();
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getBillNo() {
        return billNo;
    }

    public LocalDate getDatePrinted() {
        return datePrinted;
    }

    int getTotNrOfBills()
    {
        int nrOfBills = 0;

        for (Cashier c : DataBase.getCashiers())
        {
            nrOfBills += c.getNumOfBills();
        }

        return nrOfBills;
    }
}
