package Api;

public class URL {
    private String API_URL;
    private String PRODUCT_ID;
    private String PRODUCT;
    private String CATEGORY;
    private String CATEGORY_ID;



    public URL(String API_URL, String PRODUCT_ID, String PRODUCT, String CATEGORY, String CATEGORY_ID) {
        this.API_URL = API_URL;
        this.PRODUCT_ID = PRODUCT_ID;
        this.PRODUCT = PRODUCT;
        this.CATEGORY = CATEGORY;
        this.CATEGORY_ID = CATEGORY_ID;
    }
    public String getAPI_URL() {
        return API_URL;
    }
    public String getPRODUCT() {
        return PRODUCT;
    }
    public String getCATEGORY() {
        return CATEGORY;
    }
    public String getPRODUCT_ID() {
        return PRODUCT_ID;
    }
    public void setPRODUCT_ID(String PRODUCT_ID) {
        this.PRODUCT_ID = PRODUCT_ID;
    }
    public String getCATEGORY_ID() {
        return CATEGORY_ID;
    }
    public void setCATEGORY_ID(String CATEGORY_ID) {
        this.CATEGORY_ID = CATEGORY_ID;
    }
}

