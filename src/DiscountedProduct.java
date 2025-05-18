class DiscountedProduct extends Product {
    private final double discount;

    public DiscountedProduct(String id, String name, double price, String manufacturer, double discount) {
        super(id, name, price * (1 - discount), manufacturer);
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }
}
