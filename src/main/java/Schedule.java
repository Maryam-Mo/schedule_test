
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Schedule {
    private static final String BY = "by";
    private static final String EVERY = "every ";
    private static final String DAY = "day";
    private static final String HOUR = "hour";

    private List<Date> byList = new ArrayList<Date>();
    private List<String> everyList = new ArrayList<String>();
    private List<String> dayList = Arrays.asList("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat");
    private List<String> dateList = Arrays.asList("1st", "2nd", "3rd", "4th", "5th", "6th", "7th", "8th",
            "9th", "10th", "11th", "12th", "13th", "14th", "15th", "16th", "17th", "18th", "19th", "20th",
            "21st", "22nd", "23rd", "24th", "25th", "26th", "27th", "28th", "29th", "30th", "31st");
    private boolean everyDay;
    private boolean everyHour;
    private String[] split;
    private SimpleDateFormat simpleDateFormat;
    private String scheduleString;

    public Schedule(String scheduleString) {
        this.scheduleString = scheduleString;
        split = scheduleString.split(EVERY);
        simpleDateFormat = new SimpleDateFormat("h:mm a z");
        chooseEveryDay();
        chooseEveryHour();
        if (!split[0].isEmpty()) {
            addAnHourToList();
            addMoreThanOneHourToList();
        }
        if (!(split[1].equalsIgnoreCase(DAY) || split[1].equalsIgnoreCase(HOUR))) {
            addADayToList();
            addMoreThanOneDayToList();
        }
    }

    private void chooseEveryHour() {
        if (split[1].equalsIgnoreCase(HOUR)) {
            everyHour = true;
        }
    }

    private void chooseEveryDay() {
        if (split[1].equalsIgnoreCase(DAY)) {
            everyDay = true;
        }
    }

    private void addADayToList() {
        if (!split[1].contains(",") && !split[1].contains("-")){
            everyList.clear();
            everyList.add(split[1]);
        }
    }

    private void addMoreThanOneDayToList() {
        if (split[1].contains(",") || split[1].contains("-")){
            if (split[1].contains(",")) {
                addDaysSeparatedByComma();
            } else {
                addDaysSeparatedByDash();
            }
        }
    }

    private void addDaysSeparatedByComma() {
        String[] splitDay = split[1].split(", ");
        everyList.clear();
        for (String day: splitDay){
            everyList.add(day);
        }
    }

    private void addDaysSeparatedByDash() {
        String[] splitDay = split[1].split("-");
        int firstIndex = dayList.indexOf(splitDay[0]);
        int lastIndex = dayList.indexOf(splitDay[1]);
        everyList.clear();
        if (firstIndex == -1 && lastIndex== -1){
            int firstIndexDate = dateList.indexOf(splitDay[0]);
            int lastIndexDate = dateList.indexOf(splitDay[1]);
            addDaysToList(firstIndexDate, lastIndexDate, dateList);
        } else {
            addDaysToList(firstIndex, lastIndex, dayList);
        }
    }

    private void addDaysToList(int firstIndex, int lastIndex, List<String> dayList) {
        if ((lastIndex >= firstIndex)){
            for (int i=firstIndex; i<=lastIndex; i++){
                everyList.add(dayList.get(i));
            }
        } else {
            for (int i = firstIndex; i<=(dayList.size()-1); i++){
                everyList.add(dayList.get(i));
            }
            for (int i=0; i<=lastIndex; i++){
                everyList.add(dayList.get(i));
            }
        }
    }

    private void addAnHourToList() {
        if (!split[0].contains(",")){
            split[0] = split[0].substring(3,split[0].length()-1);
            byList.clear();
            try {
                byList.add(simpleDateFormat.parse(split[0]));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    private void addMoreThanOneHourToList(){
        if (split[0].contains(",")){
            String[] splitHour = split[0].split(", ");
            splitHour[0] = splitHour[0].substring(3);
            int size = (splitHour.length) - 1;
            splitHour[size] = splitHour[size].substring(0, splitHour[size].length()-1);
            byList.clear();
            for (String s: splitHour){
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
        return everyDay;
    }

    public boolean isEveryHour() {
        return everyHour;
    }

    public List<String> getEveryList() {
        return everyList;
    }
}
