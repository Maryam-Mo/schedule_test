
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
            addDaysSeparatesWithComma();
        }
    }

    private void addDaysSeparatesWithComma() {
        if (split[1].contains(",")) {
            String[] splitDay = split[1].split(", ");
            everyList.clear();
            for (String day: splitDay){
                everyList.add(day);
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
