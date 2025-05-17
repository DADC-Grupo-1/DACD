package domain.model;

public class Product {
    /*
    Used to identify the Products of the JSON response
     */
    private String id;
    private String display_name;
    private String packaging;
    private String thumbnail;
    PriceInstructions price_instructions;

    public String getPackaging() {return packaging;}
    public String getId() {return id;}
    public String getDisplay_name() {return display_name;}
    public String getThumbnail() { return thumbnail;}



    public class PriceInstructions {
        public String unit_price;
        public String bulk_price;
        public String reference_format;

        public String getUnit_price() {
            return this.unit_price;
        }
        public String getBulk_price() {
            return this.bulk_price;
        }
        public String getReference_format() {
            return this.reference_format;
        }

    }

}
