import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ListAdder {

    public List<String> addToListOfString(List<String> list, String...expectedStrings){
        for (String expectedString: expectedStrings){
            list.add(expectedString);
        }
        return list;
    }

    public List<Date> addToListOfDate(List<Date> list, String...expectedStrings){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm a z");
        for (String expectedString: expectedStrings){
            try {
                list.add(simpleDateFormat.parse(expectedString));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public List<String> addInOrderNumbersFrom4To20(List<String> list){
        for (int i=4; i<=20; i++){
            list.add(i + "th");
        }
        return list;
    }

}
