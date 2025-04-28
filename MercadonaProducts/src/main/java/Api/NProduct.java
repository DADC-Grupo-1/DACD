package Api;

public class NProduct {
    /*
    Used to identify the Products of the JSON response
     */
    public String id;
    public String display_name;
    public String packaging;
    public String thumbnail;
    PriceInstructions price_instructions;

    public String getThumbnail() { return thumbnail;}
    public String getUnit_price() {
        return price_instructions.unit_price;
    }
    public String getBulk_price() {
        return price_instructions.bulk_price;
    }
    public String getReference_format() {
        return price_instructions.reference_format;
    }
    public void setReference_format( String reference_format ) {
        price_instructions.reference_format = reference_format;
    }
    public void setBulk_price( String bulk_price ) {
        price_instructions.bulk_price = bulk_price;
    }
    public void getUnit_price( String unit_price ) {
        price_instructions.unit_price = unit_price;
    }

    public class PriceInstructions {
        public String unit_price;
        public String bulk_price;
        public String reference_format;

    }
    public String toString(){
        return "ID:" + id + " display_name: " + display_name + " packaging: " + packaging + " unit_price: " + getUnit_price()
                + " bulk_price: " + getBulk_price() + " reference_format: " + getReference_format() +  "thumbnail: " + getThumbnail();
    }

}
