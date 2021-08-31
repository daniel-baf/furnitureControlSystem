package Web.DashboardAcions;

import Database.BillDAO;
import Database.EarningsDAO;
import Database.RefundDAO;
import Domain.Bill;
import Domain.Earning;
import Domain.Refund;
import Domain.User;
import GeneralUse.InsertUtilities;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FinancialActionMethods {

    public ArrayList<Refund> getListRefunds(LocalDate startDate, LocalDate endDate) {
        return new RefundDAO().getRefundsReport(startDate, endDate, new InsertUtilities().isBetweenDates(startDate, endDate));
    }

    public ArrayList<Bill> getListSells(LocalDate startDate, LocalDate endDate, User usr, HttpServletResponse response) throws IOException {
        return new BillDAO().getBillingReport(startDate, endDate, new InsertUtilities().isBetweenDates(startDate, endDate), usr, response);
    }

    public ArrayList<Earning> getListEarnings(LocalDate startDate, LocalDate endDate) {
        return new EarningsDAO().getEarningsReport(new InsertUtilities().isBetweenDates(startDate, endDate), startDate, endDate);
    }

    public void confReportCommonTask(HttpServletRequest request, String tableTitle, String reportMsg, String[] thTitles) {
        request.setAttribute("tableTitle", tableTitle);
        request.setAttribute("reportMsg", reportMsg);
        request.setAttribute("thTitles", thTitles);
    }

    public void conReportCommonTask(HttpServletRequest request, double profit, double loses, double refunded) {
        request.setAttribute("profitAmmount", profit);
        request.setAttribute("refundMoney", loses);
        request.setAttribute("refunded", refunded);
    }

    public double getSumBills(ArrayList<Bill> bills) {
        double cuantity = 0;
        cuantity = bills.stream().map(bill -> bill.getAmmount()).reduce(cuantity, (accumulator, _item) -> accumulator + _item);
        return cuantity;
    }

    public double getSumRefund(ArrayList<Refund> refunds) {
        double cuantity = 0;
        return refunds.stream().map(refund -> refund.getRefund()).reduce(cuantity, (accumulator, _item) -> accumulator + _item);
    }

    public double getSumLoses(ArrayList<Refund> refunds) {
        double cuantity = 0;
        return refunds.stream().map(refund -> refund.getMoneyLost()).reduce(cuantity, (accumulator, _item) -> accumulator + _item);
    }

    public int confSells(HttpServletRequest request, HttpServletResponse response, LocalDate date1, LocalDate date2, String arrayNameAttribute, User usr) throws IOException {
        ArrayList<Bill> bills = getListSells(date1, date2, usr, response);
        response.getWriter().print(bills);
        request.setAttribute(arrayNameAttribute, bills);
        return (int) getSumBills(bills);
    }

    public int[] confRefunds(HttpServletRequest request, LocalDate date1, LocalDate date2, String arrayNameAttribute) {
        ArrayList<Refund> refunds = getListRefunds(date1, date2);
        request.setAttribute(arrayNameAttribute, refunds);
        return new int[]{(int) getSumRefund(refunds), (int) getSumLoses(refunds)};
    }

    public int[] confEarnings(HttpServletRequest request, LocalDate date1, LocalDate date2, String arrayNameAttribute) {
        ArrayList<Earning> earnings = getListEarnings(date1, date2);
        // sum values
        int[] sumsTmp = {0, 0, 0}; // v[0] = sells, v[1] = loses, v[2] = lost
        for (Earning earn : earnings) {
            sumsTmp[0] += earn.getSellAmmount();
            sumsTmp[1] += earn.getFurnitureState() == 2 ? earn.getSellAmmount() : 0; // state 2  = refunded
            sumsTmp[2] += earn.getFurnitureState() == 2 ? (earn.getSellAmmount() - earn.getEarning()) / 3 : 0; // ammount -profit = assembly price
        }
        request.setAttribute(arrayNameAttribute, earnings);
        return sumsTmp;
    }
}
