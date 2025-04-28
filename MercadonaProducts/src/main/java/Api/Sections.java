package Api;

import java.util.ArrayList;
import java.util.List;

public class Sections {
    /*
    Used to identify the different Sections of the JSON response
     */
    public int id;
    public String name;
    public List<Sections> results;
    public List<Sections> categories;

    public List<Integer> getcategories(){
        List<Integer> ans = new ArrayList<>();
        for (int i = 0 ; i< results.size(); i++){
            for (int j = 0 ; j< results.get(i).categories.size(); j++){
                ans.add(results.get(i).categories.get(j).id);
            }
        }
        return ans;
    }
}
