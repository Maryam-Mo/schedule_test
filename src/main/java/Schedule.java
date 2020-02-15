
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Schedule {
    private List<Date> byList = new ArrayList<Date>();

    private boolean everyDay;

    private boolean everyHour;

    private List<String> everyList = new ArrayList<String>();

    private String scheduleString;

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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm a z");
        if (scheduleString.contains("1:00 AM EST")){
            try {
                byList.clear();
                byList.add(simpleDateFormat.parse("1:00 AM EST"));
                return byList;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else if (scheduleString.contains("9:00 AM EST, 5:00 PM EST")){
            try {
                byList.clear();
                byList.add(simpleDateFormat.parse("9:00 AM EST"));
                byList.add(simpleDateFormat.parse("5:00 PM EST"));
                return byList;
            } catch (ParseException e) {
                e.printStackTrace();
            }
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
            everyList.add("Mon");
            everyList.add("Tue");
            everyList.add("Wed");
            everyList.add("Thu");
            everyList.add("Fri");
            return everyList;
        } else if (scheduleString.contains("every Mon, Wed, Fri")) {
            everyList.clear();
            everyList.add("Mon");
            everyList.add("Wed");
            everyList.add("Fri");
            return everyList;
        } else if (scheduleString.contains("every 6 hours")){
            everyList.clear();
            everyList.add("6 hours");
            return everyList;
        } else if (scheduleString.contains("every 30 minutes")){
            everyList.clear();
            everyList.add("30 minutes");
            return everyList;
        } else if (scheduleString.contains("every Fri")){
            everyList.clear();
            everyList.add("Fri");
            return everyList;
        } else if (scheduleString.contains("every 1st")){
            everyList.clear();
            everyList.add("1st");
            return everyList;
        } else if (scheduleString.contains("every 15th, 30th")){
            everyList.clear();
            everyList.add("15th");
            everyList.add("30th");
            return everyList;
        } else if (scheduleString.contains("very 10th-12th")){
            everyList.clear();
            everyList.add("10th");
            everyList.add("11th");
            everyList.add("12th");
            return everyList;
        } else if (scheduleString.contains("every 31st-3rd")){
            everyList.clear();
            everyList.add("31st");
            everyList.add("1st");
            everyList.add("2nd");
            everyList.add("3rd");
            return everyList;
        } else if (scheduleString.contains("every 2nd-22nd")){
            everyList.clear();
            everyList.add("2nd");
            everyList.add("3rd");
            for (int i=4; i<=20; i++){
                everyList.add(i + "th");
            }
            everyList.add("21st");
            everyList.add("22nd");
            return everyList;
        } else if (scheduleString.contains("every Sun-Fri")) {
            everyList.clear();
            everyList.add("Sun");
            everyList.add("Mon");
            everyList.add("Tue");
            everyList.add("Wed");
            everyList.add("Thu");
            everyList.add("Fri");
            return everyList;
        }
        everyList.clear();
        return everyList;
    }
}
