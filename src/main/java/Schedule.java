
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
    private String stringBeforeEvery;
    private String stringAfterEvery;
    private SimpleDateFormat simpleDateFormat;
    private String scheduleString;

    public Schedule(String scheduleString) {
        this.scheduleString = scheduleString;
        split = scheduleString.split(EVERY);
        stringBeforeEvery = split[0];
        stringAfterEvery = split[1];
        simpleDateFormat = new SimpleDateFormat("h:mm a z");
        setEveryDay();
        setEveryHour();
        setByList();
        setEveryList();
    }

    private void setEveryList() {
        if (!(stringAfterEvery.equalsIgnoreCase(DAY) || stringAfterEvery.equalsIgnoreCase(HOUR))) {
            addASingleDayToList();
            addMoreThanOneDayToList();
        }
    }

    private void setByList() {
        if (!stringBeforeEvery.isEmpty()) {
            addAnHourToList();
            addMoreThanOneHourToList();
        }
    }

    private void setEveryHour() {
        if (stringAfterEvery.equalsIgnoreCase(HOUR)) {
            everyHour = true;
        }
    }

    private void setEveryDay() {
        if (stringAfterEvery.equalsIgnoreCase(DAY)) {
            everyDay = true;
        }
    }

    private void addASingleDayToList() {
        if (!stringAfterEvery.contains(",") && !stringAfterEvery.contains("-")){
            everyList.clear();
            everyList.add(stringAfterEvery);
        }
    }

    private void addMoreThanOneDayToList() {
        final boolean isSeparatedByComma = stringAfterEvery.contains(",");
        final boolean isSeperatedByDash = stringAfterEvery.contains("-");
        if (isSeparatedByComma || isSeperatedByDash){
            if (isSeparatedByComma) {
                addDaysSeparatedByComma();
            } else {
                addDaysSeparatedByDash();
            }
        }
    }

    private void addDaysSeparatedByComma() {
        String[] splitDays = stringAfterEvery.split(", ");
        everyList.clear();
        for (String day: splitDays){
            everyList.add(day);
        }
    }

    private void addDaysSeparatedByDash() {
        String[] splitDay = stringAfterEvery.split("-");
        String dayBeforeDash = splitDay[0];
        String dayAfterDash = splitDay[1];
        int firstIndex = dayList.indexOf(dayBeforeDash);
        int lastIndex = dayList.indexOf(dayAfterDash);
        everyList.clear();
        final boolean indexesAreNotExisted = firstIndex == -1 && lastIndex == -1;
        if (indexesAreNotExisted){
            int firstIndexDate = dateList.indexOf(dayBeforeDash);
            int lastIndexDate = dateList.indexOf(dayAfterDash);
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
        if (!stringBeforeEvery.contains(",")){
            String stringFrom4thLetterTillTheSecondLast = stringBeforeEvery.substring(3,stringBeforeEvery.length()-1);
            byList.clear();
            try {
                byList.add(simpleDateFormat.parse(stringFrom4thLetterTillTheSecondLast));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    private void addMoreThanOneHourToList(){
        if (stringBeforeEvery.contains(",")){
            String[] splitHour = stringBeforeEvery.split(", ");
            prepareSplitHour(splitHour);
            byList.clear();
            for (String hour: splitHour){
                try {
                    byList.add(simpleDateFormat.parse(hour));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void prepareSplitHour(String[] splitHour) {
        String firstHour = splitHour[0];
        String stringFrom4thLetterTillTheEnd = firstHour.substring(3);
        splitHour[0] = stringFrom4thLetterTillTheEnd;
        int lastIndex = (splitHour.length) - 1;
        String lastHour = splitHour[lastIndex];
        String removeSpacesFromTheLastHour = lastHour.trim();
        lastHour = removeSpacesFromTheLastHour;
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
