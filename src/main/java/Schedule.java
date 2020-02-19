
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
    private boolean by;
    private String[] split;
    private String stringBeforeEvery;
    private String stringAfterEvery;
    private SimpleDateFormat simpleDateFormat;

    /**
     * Split the received string by "every" and then the text before every saved in stringBeforeEvery and the text
     * after every saved in stringAfterEvery and at the end do some parsing operations on these 2 Strings to access
     * the right time and days that user already entered
     */
    public Schedule(String scheduleString) {
        split = scheduleString.split(EVERY);
        stringBeforeEvery = split[0];
        stringAfterEvery = split[1];
        simpleDateFormat = new SimpleDateFormat("h:mm a z");
        setBy();
        setEveryDay();
        setEveryHour();
        setByList();
        setEveryList();
    }

    /**
     * Check if stringAfterEvery(day) is not equal to Every Day or Every Hour and if not then add it
     * to the everyList
     */
    private void setEveryList() {
        if (!(stringAfterEvery.equalsIgnoreCase(DAY) || stringAfterEvery.equalsIgnoreCase(HOUR))) {
            addASingleDayToList();
            addMoreThanOneDayToList();
        }
    }

    /**
     * Check if stringBeforeEvery(time) is not empty and then add it to the byList
     */
    private void setByList() {
        if (!stringBeforeEvery.isEmpty()) {
            addAnHourToList();
            addMoreThanOneHourToList();
        }
    }

    /**
     * Check if stringAfterEvery(day) is equal to Every Hour
     */
    private void setEveryHour() {
        if (stringAfterEvery.equalsIgnoreCase(HOUR)) {
            everyHour = true;
        }
    }

    /**
     * Check if stringAfterEvery(day) is equal to Every Day
     */
    private void setEveryDay() {
        if (stringAfterEvery.equalsIgnoreCase(DAY)) {
            everyDay = true;
        }
    }

    /**
     * Check if stringBeforeEvery(time) contains By
     */
    private void setBy() {
        if (stringBeforeEvery.contains(BY)){
            by = true;
        }
    }

    /**
     * Check if stringAfterEvery(day) doesn't include comma(,) or dash(-) and then add the stringAfterEvery to
     * everyList
     */
    private void addASingleDayToList() {
        final boolean isNotSeparatedByComma = !stringAfterEvery.contains(",");
        final boolean isNotSeparatedByDash = !stringAfterEvery.contains("-");
        if (isNotSeparatedByComma && isNotSeparatedByDash){
            everyList.clear();
            everyList.add(stringAfterEvery);
        }
    }

    /**
     * Check if stringAfterEvery(day) include comma(,) or dash(-) and then based on each, add them to everyList
     */
    private void addMoreThanOneDayToList() {
        final boolean isSeparatedByComma = stringAfterEvery.contains(",");
        final boolean isSeparatedByDash = stringAfterEvery.contains("-");
        if (isSeparatedByComma || isSeparatedByDash){
            if (isSeparatedByComma) {
                addDaysSeparatedByComma();
            } else {
                addDaysSeparatedByDash();
            }
        }
    }

    /**
     * Split the stringAfterEvery(day) by comma(,) and then add each day to the list
     */
    private void addDaysSeparatedByComma() {
        String[] splitDays = stringAfterEvery.split(", ");
        everyList.clear();
        for (String day: splitDays){
            everyList.add(day);
        }
    }

    /**
     * Split the stringAfterEvery(day) by dash(-) and then add the text before dash to "dayBeforeDash" and the text
     * after dash to "dayAfterDash" and find the index of them in dayList. If the variables are not existed in
     * dayList then will find them in dateList and at the end add them to the list
     */
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

    /**
     * Based on the indexes, add the elements between firstIndex and LastIndex to the list
     * Check if lastIndex is greater than firstIndex, then easily add the elements between firstIndex and lastIndex
     * to the list
     * if lastIndex is smaller than lastIndex then we should traverse the list two times. one time from firstIndex till
     * the end of list and then next time from the start of the list till the lastIndex
     */
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

    /**
     * Check if stringBeforeEvery(day) doesn't include comma(,) and then convert the string to date and add it to
     * everyList
     */
    private void addAnHourToList() {
        if (!stringBeforeEvery.contains(",")){
            final int indexAfterByAndSpace = 3;
            final int indexBeforeLastSpace = stringBeforeEvery.length() - 1;
            String stringFrom4thLetterTillTheSecondLast = stringBeforeEvery.substring(indexAfterByAndSpace, indexBeforeLastSpace);
            byList.clear();
            try {
                byList.add(simpleDateFormat.parse(stringFrom4thLetterTillTheSecondLast));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Check if stringBeforeEvery(day) includes comma(,) and then split the string with comma(,) and for each of
     * the times convert them to date and add them to everyList
     */
    private void addMoreThanOneHourToList(){
        if (stringBeforeEvery.contains(",")){
            String[] splitHour = stringBeforeEvery.split(", ");
            splitHour = prepareSplitHour(splitHour);
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

    /**
     * Check if received string(day) includes comma(,) and then split the string with comma(,) and for each of
     * the times convert them to date and add them to everyList
     */
    private String[] prepareSplitHour(String[] splitHour) {
        final int firstHourIndex = 0;
        final int indexAfterByAndSpace = 3;
        String stringFrom4thLetterTillTheEnd = splitHour[firstHourIndex].substring(indexAfterByAndSpace);
        splitHour[firstHourIndex] = stringFrom4thLetterTillTheEnd;
        int lastIndexOfTheLastHour = (splitHour.length) - 1;
        splitHour[lastIndexOfTheLastHour] = splitHour[lastIndexOfTheLastHour].trim();
        return splitHour;
    }

    public boolean hasBy() {
        return by;
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
