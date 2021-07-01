import java.util.regex.Matcher;
import java.util.regex.Pattern;

//compare Strings/int by parameter from searcher
public class ParamCompare {

    protected boolean stringCompare(String fromSearch, String fromDatabase, int param){

        if (fromSearch.isEmpty())   //empty field
            return true;
        
        switch (param){
            case 0:
                return fromSearch.equals(fromDatabase);
            case 1:
                Pattern pattern1 = Pattern.compile("^" + fromSearch, Pattern.CASE_INSENSITIVE);
                Matcher matcher1 = pattern1.matcher(fromDatabase);
                return matcher1.find();
            case 2:
                Pattern pattern2 = Pattern.compile(fromSearch+"$", Pattern.CASE_INSENSITIVE);
                Matcher matcher2 = pattern2.matcher(fromDatabase);
                return matcher2.find();
            case 3:
                Pattern pattern3 = Pattern.compile(fromSearch, Pattern.CASE_INSENSITIVE);
                Matcher matcher3 = pattern3.matcher(fromDatabase);
                return matcher3.find();

            default:
                return false;
        }

    }

    protected boolean intCompare(int fromSearch, int fromDatabase, int param){

        if (fromSearch == 0)  //empty field
            return true;
        
        switch (param){
            case 0:
                return fromSearch == fromDatabase;
            case 1:
                return fromDatabase < fromSearch;
            case 2:
                return fromDatabase <= fromSearch;
            case 3:
                return fromDatabase > fromSearch;
            case 4:
                return fromDatabase >= fromSearch;

            default:
                return false;
        }

    }

}
