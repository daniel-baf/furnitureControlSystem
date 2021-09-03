package GeneralUse;

import TransactionObjects.InsertObjectStatus;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertUtilities {

    public InsertUtilities() {
    }

    /**
     * remove comillas from "a string with comillas"
     *
     * @param word the word with comillas
     * @return the word without comillas
     */
    public String removeDoubleComillas(String word) {
        String newWord = "";
        boolean open = false;
        for (char c : word.toCharArray()) {
            if (c != '"') {
                newWord += c;
            } else if (c == '"' && open == false) {
                open = true;
            }
        }
        return newWord.trim();
    }

    /**
     * verify if a word have only 2 comillas
     *
     * @param st1 the word
     * @return Boolean, have or not the 2 comillas
     */
    public boolean haveTwoComillas(String st1) {
        return (st1.trim().replace("\"", "").length() == st1.trim().length() - 2);
    }

    /**
     * try to cast a String to double, return null if NumberFormatException
     *
     * @param doubleN the string
     * @return Double
     */
    public Double getDoubleFromString(String doubleN) {
        try {
            return Double.valueOf(doubleN);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * try to cast a String to Integer, return null if NumberFormatException
     *
     * @param integerN String
     * @return Integer
     */
    public Integer getIntegerFromString(String integerN) {
        try {
            return Integer.valueOf(integerN);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Cast a String to LocalDate, return null if get a error while casting
     *
     * @param dateDDMMYY LocalDate
     * @return LocalDate or null
     */
    public LocalDate stringToLocalDate(String dateDDMMYY) {
        try {
            return LocalDate.parse(dateDDMMYY, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * set a error status or success (0) if receive true from call 1 = logical
     * error, 2 = format error
     *
     * public void httpErrorShow(Http)
     *
     * @param typeError
     * @param object the object to save error
     */
    public void setStatusAndResult(int typeError, InsertObjectStatus object) {
        switch (typeError) {
            case 1 ->
                object.setStatus("Error logico");
            case 2 ->
                object.setStatus("Error de formato");
        }
    }

    /**
     * parses a local date to SQL date
     *
     * @param date
     * @return
     */
    public Date parseLocalDateSQLDate(LocalDate date) {
        try {
            return java.sql.Date.valueOf(date);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * parse SQL date to LocalDate
     *
     * @param date
     * @return
     */
    public LocalDate parseSQLDateToLocalDate(Date date) {
        try {
            return new java.sql.Date(date.getTime()).toLocalDate();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * parse a yyyy-mm-yy string to LocalDate
     *
     * @param dateyyyyMMdd
     * @return
     */
    public LocalDate stringToLocalDateyyyyMMMdd(String dateyyyyMMdd) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(dateyyyyMMdd, formatter);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * return true if both dates are not null, aovid NullPointerException using
     * this method
     *
     * @param date1
     * @param date2
     * @return
     */
    public boolean isBetweenDates(LocalDate date1, LocalDate date2) {
        return date1 != null && date2 != null;
    }

    /**
     * validate theres's no null or empty entries
     *
     * @param args
     * @return
     */
    public boolean haveNoNulls(String... args) {
        for (String arg : args) {
            if (arg.isBlank() || arg.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /**
     * return a message calling a page and show message if error
     *
     * @param response
     * @param request
     * @param e
     * @param message
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    public void sendErrorMessage(HttpServletResponse response, HttpServletRequest request, Exception e, String message) throws ServletException, IOException {
        request.setAttribute("rep-title", "Error");
        request.setAttribute("rep-body", "Ha ocurrido un error durante la operacion.<br>Una posible causa es: " + message + "<br>El error es: " + e.getMessage() + "<br>Un mensaje mas detallado: " + e.getLocalizedMessage());
        request.getRequestDispatcher("Reports/Message.jsp").forward(request, response);
    }

    /**
     * this is a common task from furniture, add attributes to show into
     * message.jsp
     *
     * @param request
     * @param repTitle
     * @param repBody
     */
    public void configAttibutes(HttpServletRequest request, String repTitle, String repBody) {
        request.setAttribute("rep-title", repTitle);
        request.setAttribute("rep-body", repBody);

    }
}
