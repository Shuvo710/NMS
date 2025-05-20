package bd.edu.seu.nursery_management_system.model;

public class Product {
    private Integer id;
    private String name;
    private String description;
    private Double rate;
    private Double price;

    public Product(Integer id, String name, String description, Double rate, Double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rate = rate;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
