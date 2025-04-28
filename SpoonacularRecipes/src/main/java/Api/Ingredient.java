package Api;

public class Ingredient {
    /*
    Defines the ingredient from JSON
     */

    private int id;
    private String nameClean;
    private String amount;
    private String unit;
    private Measures measures;

    public class Measures {
        public metrics us;
        public metrics metric;
    }

    public class metrics{
        public double amount;
        public String unitShort;
        public String unitLong;
    }

    public int getId() {
        return id;
    }
    public String getNameClean() {
        return nameClean;
    }
    public String getAmount() {
        return amount;
    }
    public String getUnit() {
        return unit;
    }
    public Measures getMeasures() {
        return measures;
    }
}
