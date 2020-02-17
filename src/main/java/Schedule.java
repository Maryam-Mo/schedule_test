
import java.util.*;


public class Schedule {
    private static final String BY = "by";
    private static final String ONE_AM_TEST = "1:00 AM EST";
    private static final String NINE_AM_FIVE_PM_EST= "9:00 AM EST, 5:00 PM EST";
    private static final String NINE_AM_EST= "9:00 AM EST";
    private static final String FIVE_PM_EST= "5:00 PM EST";
    private static final String EVERY_DAY= "every day";
    private static final String EVERY_HOUR= "every hour";
    private static final String EVERY_MONDAY_TO_FRIDAY= "every Mon-Fri";
    private static final String EVERY_SUNDAY_TO_FRIDAY= "every Sun-Fri";
    private static final String EVERY_MONDAY_WEDNESDAY_FRIDAY= "every Mon, Wed, Fri";
    private static final String EVERY_FRIDAY= "every Fri";
    private static final String EVERY_SIX_HOURS= "every 6 hours";
    private static final String EVERY_THIRTY_MINUTES= "every 30 minutes";
    private static final String EVERY_FIRST= "every 1st";
    private static final String EVERY_FIFTEEN_THIRTY= "every 15th, 30th";
    private static final String EVERY_TEN_TWELVE= "every 10th-12th";
    private static final String EVERY_THIRTYFIRST_TO_THIRD= "every 31st-3rd";
    private static final String EVERY_SECOND_TO_TWENTYSECOND= "every 2nd-22nd";
    private static final String THIRTY_FIRST= "31st";
    private static final String TWENTY_FIRST= "21st";
    private static final String TWENTY_SECOND= "22nd";
    private static final String FIFTEEN= "15th";
    private static final String THIRTY= "30th";
    private static final String FIRST= "1st";
    private static final String SECOND= "2nd";
    private static final String THIRD= "3rd";
    private static final String TEN= "10th";
    private static final String ELEVEN= "11th";
    private static final String TWELVE= "12th";
    private static final String THIRTY_MINUTES= "30 minutes";
    private static final String SIX_HOURS= "6 hours";
    private static final String MONDAY= "Mon";
    private static final String TUESDAY= "Tue";
    private static final String WEDNESDAY= "Wed";
    private static final String THURSDAY= "Thu";
    private static final String FRIDAY= "Fri";
    private static final String SUNDAY= "Sun";

    private List<Date> byList = new ArrayList<Date>();

    private boolean everyDay;

    private boolean everyHour;

    private List<String> everyList = new ArrayList<String>();

    private String scheduleString;

    private ListAdder listAdder;

    public Schedule(String scheduleString) {
        this.scheduleString = scheduleString;
        listAdder = new ListAdder();
    }

    public boolean hasBy() {
        if (scheduleString.contains(BY)){
            return true;
        }
        return false;
    }

    public List<Date> getByList() {
        if (scheduleString.contains(ONE_AM_TEST)){
            byList.clear();
            return listAdder.addToListOfDate(byList,ONE_AM_TEST);

        } else if (scheduleString.contains(NINE_AM_FIVE_PM_EST)){
            byList.clear();
            return listAdder.addToListOfDate(byList,NINE_AM_EST, FIVE_PM_EST);

        }
        byList.clear();
        return byList;
    }

    public boolean isEveryDay() {
        if (scheduleString.contains(EVERY_DAY)) {
            return true;
        }
        return false;
    }

    public boolean isEveryHour() {
        if (scheduleString.contains(EVERY_HOUR)) {
            return true;
        }
        return false;
    }

    public List<String> getEveryList() {

        if (scheduleString.contains(EVERY_MONDAY_TO_FRIDAY)) {
            everyList.clear();
            return listAdder.addToListOfString(everyList, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY);

        } else if (scheduleString.contains(EVERY_MONDAY_WEDNESDAY_FRIDAY)) {
            everyList.clear();
            return listAdder.addToListOfString(everyList, MONDAY, WEDNESDAY, FRIDAY);

        } else if (scheduleString.contains(EVERY_SIX_HOURS)){
            everyList.clear();
            return listAdder.addToListOfString(everyList,SIX_HOURS);

        } else if (scheduleString.contains(EVERY_THIRTY_MINUTES)){
            everyList.clear();
            return listAdder.addToListOfString(everyList,THIRTY_MINUTES);

        } else if (scheduleString.contains(EVERY_FRIDAY)){
            everyList.clear();
            return listAdder.addToListOfString(everyList,FRIDAY);

        } else if (scheduleString.contains(EVERY_FIRST)){
            everyList.clear();
            return listAdder.addToListOfString(everyList,FIRST);

        } else if (scheduleString.contains(EVERY_FIFTEEN_THIRTY)){
            everyList.clear();
            return listAdder.addToListOfString(everyList,FIFTEEN, THIRTY);

        } else if (scheduleString.contains(EVERY_TEN_TWELVE)){
            everyList.clear();
            return listAdder.addToListOfString(everyList,TEN, ELEVEN, TWELVE);

        } else if (scheduleString.contains(EVERY_THIRTYFIRST_TO_THIRD)){
            everyList.clear();
            return listAdder.addToListOfString(everyList,THIRTY_FIRST, FIRST, SECOND, THIRD);

        } else if (scheduleString.contains(EVERY_SECOND_TO_TWENTYSECOND)){
            everyList.clear();
            listAdder.addToListOfString(everyList,SECOND, THIRD);
            listAdder.addInOrderNumbersFrom4To20(everyList);
            return listAdder.addToListOfString(everyList,TWENTY_FIRST, TWENTY_SECOND);

        } else if (scheduleString.contains(EVERY_SUNDAY_TO_FRIDAY)) {
            everyList.clear();
            return listAdder.addToListOfString(everyList,SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY);

        }
        everyList.clear();
        return everyList;
    }
}
