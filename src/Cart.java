import java.util.ArrayList;
import java.util.List;

class Cart {
    private final List<Product> items = new ArrayList<>();

    public void addItem(Product product) {
        items.add(product);
    }

    public List<Product> getItems() {
        return new ArrayList<>(items);
    }

    public void clear() {
        items.clear();
    }
}
