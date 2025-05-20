package bd.edu.seu.nursery_management_system.model;

public class Cart {
    private int id;
    private String customer;
    private int product;
    private int quantity;
    private int isConfirmed;
    private int isApproved;
    private String employee;

    public Cart(int id, String customer, int product, int quantity, int isConfirmed, int isApproved, String employee) {
        this.id = id;
        this.customer = customer;
        this.product = product;
        this.quantity = quantity;
        this.isConfirmed = isConfirmed;
        this.isApproved = isApproved;
        this.employee = employee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getIsConfirmed() {
        return isConfirmed;
    }

    public void setIsConfirmed(int isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

    public int getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(int isApproved) {
        this.isApproved = isApproved;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }
}
