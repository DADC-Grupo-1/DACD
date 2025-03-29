package Api;

import java.util.List;

public class Category {
    public int id;
    public String name;
    public List<Categories> categories;

    public class Categories {
        public int id;
        public String name;
        public List<NProduct> products;
    }

    public String getcategories(){
        String ans = "";
        for (int i = 0; i < categories.size(); i++){
            ans += categories.get(i).id + " " + categories.get(i).name + " " + categories.get(i).products.toString();
        }
        return ans;
    }
}
