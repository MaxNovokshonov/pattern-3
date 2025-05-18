class KeywordFilter implements ProductFilter {
    private final String keyword;

    public KeywordFilter(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    @Override
    public boolean matches(Product product) {
        return product.getName().toLowerCase().contains(keyword);
    }
}
