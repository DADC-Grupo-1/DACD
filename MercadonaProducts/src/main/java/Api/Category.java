package Api;

import java.util.List;

public class Category {
    /*
    Defines the primary category of the JSON response
     */
    private int id;
    private String name;
    private List<Categories> categories;
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public List<Categories> getCategories() {
        return categories;
    }

    public class Categories {
        /*
        Defines the second category within the main one, where all the products must be
        */
        private int id;
        private String name;
        private List<NProduct> products;
        public int getId() {
            return id;
        }
        public String getName() {
            return name;
        }
        public List<NProduct> getProducts() {
            return products;
        }
    }

    public String getcategories(){
        String ans = "";
        for (int i = 0; i < categories.size(); i++){
            ans += categories.get(i).id + " " + categories.get(i).name + " " + categories.get(i).products.toString();
        }
        return ans;
    }
}
