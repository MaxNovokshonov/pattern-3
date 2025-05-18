interface ProductFilter {
    boolean matches(Product product);

    default ProductFilter and(ProductFilter other) {
        return product -> this.matches(product) && other.matches(product);
    }
}
