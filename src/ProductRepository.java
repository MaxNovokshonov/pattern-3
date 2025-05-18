import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class ProductRepository {
    private final List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getFilteredProducts(ProductFilter filter) {
        return products.stream()
                .filter(filter::matches)
                .collect(Collectors.toList());
    }
}
