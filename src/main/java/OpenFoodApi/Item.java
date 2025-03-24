package OpenFoodApi;

public class Item {

    private int id;
    private int code;
    private String product_name;
    private String product_type;
    private String imagen_url;
    private String currency;
    private String price;
    private Boolean discount;

    public String to_string() {
        return "Item [id=" + id + ", code=" + code + ", product_name=" + product_name + ", product_type=" + product_type + ", imagen_url=" + imagen_url + ", currency=" + currency + ", price=" + price +
                ", discount=" + discount + "]";
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getImagen_url() {
        return imagen_url;
    }

    public void setImagen_url(String imagen_url) {
        this.imagen_url = imagen_url;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Boolean getDiscount() {
        return discount;
    }

    public void setDiscount(Boolean discount) {
        this.discount = discount;
    }
}
