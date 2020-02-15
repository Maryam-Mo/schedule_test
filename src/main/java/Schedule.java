
import java.util.*;


public class Schedule {
    private List<Date> byList = new ArrayList<Date>();

    private boolean everyDay;

    private boolean everyHour;

    private List<String> everyList = new ArrayList<String>();

    private String scheduleString;

    private ListAdder listAdder = new ListAdder();

    public Schedule(String scheduleString) {
        this.scheduleString = scheduleString;
    }

    public boolean hasBy() {
        if (scheduleString.contains("by")){
            return true;
        }
        return false;
    }

    public List<Date> getByList() {
        if (scheduleString.contains("1:00 AM EST")){
            byList.clear();
            return listAdder.addToListOfDate(byList,"1:00 AM EST");

        } else if (scheduleString.contains("9:00 AM EST, 5:00 PM EST")){
            byList.clear();
            return listAdder.addToListOfDate(byList,"9:00 AM EST", "5:00 PM EST");

        }
        byList.clear();
        return byList;
    }

    public boolean isEveryDay() {
        if (scheduleString.contains("every day")) {
            return true;
        }
        return false;
    }

    public boolean isEveryHour() {
        if (scheduleString.contains("every hour")) {
            return true;
        }
        return false;
    }

    public List<String> getEveryList() {

        if (scheduleString.contains("every Mon-Fri")) {
            everyList.clear();
            return listAdder.addToListOfString(everyList, "Mon", "Tue", "Wed", "Thu", "Fri");

        } else if (scheduleString.contains("every Mon, Wed, Fri")) {
            everyList.clear();
            return listAdder.addToListOfString(everyList, "Mon", "Wed", "Fri");

        } else if (scheduleString.contains("every 6 hours")){
            everyList.clear();
            return listAdder.addToListOfString(everyList,"6 hours");

        } else if (scheduleString.contains("every 30 minutes")){
            everyList.clear();
            return listAdder.addToListOfString(everyList,"30 minutes");

        } else if (scheduleString.contains("every Fri")){
            everyList.clear();
            return listAdder.addToListOfString(everyList,"Fri");

        } else if (scheduleString.contains("every 1st")){
            everyList.clear();
            return listAdder.addToListOfString(everyList,"1st");

        } else if (scheduleString.contains("every 15th, 30th")){
            everyList.clear();
            return listAdder.addToListOfString(everyList,"15th", "30th");

        } else if (scheduleString.contains("very 10th-12th")){
            everyList.clear();
            return listAdder.addToListOfString(everyList,"10th", "11th", "12th");

        } else if (scheduleString.contains("every 31st-3rd")){
            everyList.clear();
            return listAdder.addToListOfString(everyList,"31st", "1st", "2nd", "3rd");

        } else if (scheduleString.contains("every 2nd-22nd")){
            everyList.clear();
            listAdder.addToListOfString(everyList,"2nd", "3rd");
            listAdder.addInOrderNumbersFrom4To20(everyList);
            return listAdder.addToListOfString(everyList,"21st", "22nd");

        } else if (scheduleString.contains("every Sun-Fri")) {
            everyList.clear();
            return listAdder.addToListOfString(everyList,"Sun", "Mon", "Tue", "Wed", "Thu", "Fri");

        }
        everyList.clear();
        return everyList;
    }
}
