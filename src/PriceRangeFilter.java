class PriceRangeFilter implements ProductFilter {
    private final double minPrice;
    private final double maxPrice;
    public static final double MAX_PRICE = 100_000.0;

    public PriceRangeFilter(double minPrice) {
        this(minPrice, MAX_PRICE);
    }

    public PriceRangeFilter(double minPrice, double maxPrice) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    @Override
    public boolean matches(Product product) {
        return product.getPrice() >= minPrice && product.getPrice() <= maxPrice;
    }
}
