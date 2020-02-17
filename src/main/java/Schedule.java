
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private static final String SATURDAY= "Saturday";
    private static final String EVERY= "every";

    private List<Date> byList = new ArrayList<Date>();

    private boolean everyDay;

    private boolean everyHour;

    private List<String> everyList = new ArrayList<String>();

    private List<String> dayList = Arrays.asList(SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY);

    private String scheduleString;

    private ListAdder listAdder;

    public Schedule(String scheduleString) {
        listAdder = new ListAdder();
        this.scheduleString = scheduleString;
        String[] split = new String[0];
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm a z");
        split = scheduleString.split(EVERY);
        if (!split[0].contains(",")){
            split[0] = split[0].substring(3,split[0].length()-1);
            byList.clear();
            try {
                byList.add(simpleDateFormat.parse(split[0]));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            split = split[0].split(", ");
            split[0] = split[0].substring(3);
            int size = (split.length) - 1;
            split[size] = split[size].substring(0, split[size].length()-1);
            byList.clear();
            for (String s: split){
                try {
                    byList.add(simpleDateFormat.parse(s));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean hasBy() {
        if (scheduleString.contains(BY)){
            return true;
        }
        return false;
    }

    public List<Date> getByList() {
        return byList;
    }

    public boolean isEveryDay() {
//        if (scheduleString.contains(EVERY_DAY)) {
//            return true;
//        }
        return false;
    }

    public boolean isEveryHour() {
//        if (scheduleString.contains(EVERY_HOUR)) {
//            return true;
//        }
        return false;
    }

    public List<String> getEveryList() {

        return null;
    }
}
