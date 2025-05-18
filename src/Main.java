import java.util.List;
import java.util.Scanner;

class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ProductRepository productRepository = new ProductRepository();
    private static final Cart cart = new Cart();
    private static final OrderService orderService = new OrderService(cart);

    public Main() {
        initializeProducts();
    }

    public static void main(String[] args) {
        new Main().run();
    }

    private void initializeProducts() {
        productRepository.addProduct(new Product("1", "Смартфон", 15000, "Xiaomi"));
        productRepository.addProduct(new DiscountedProduct("2", "Ноутбук", 55000, "Lenovo", 0.15));
        productRepository.addProduct(new Product("3", "Наушники", 5000, "Sony"));
        productRepository.addProduct(new Product("4", "Монитор", 20000, "Philips"));
        productRepository.addProduct(new DiscountedProduct("5", "Чайник", 2000, "Bosch", 0.2));
    }

    public void run() {
        while (true) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> showProducts();
                case 2 -> filterProducts();
                case 3 -> addToCart();
                case 4 -> checkout();
                case 5 -> System.exit(0);
                default -> System.out.println("Неверный выбор");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n1. Показать все товары");
        System.out.println("2. Фильтровать товары");
        System.out.println("3. Добавить в корзину");
        System.out.println("4. Оформить заказ");
        System.out.println("5. Выход");
        System.out.print("Выберите опцию: ");
    }

    private static void showProducts() {
        displayProducts(productRepository.getFilteredProducts(p -> true));
    }

    private static void filterProducts() {
        System.out.print("Введите ключевое слово: ");
        String keyword = scanner.nextLine();
        System.out.print("Минимальная цена: ");
        double minPrice = scanner.nextDouble();
        scanner.nextLine();

        ProductFilter filter = new KeywordFilter(keyword)
                .and(new PriceRangeFilter(minPrice, Double.MAX_VALUE));

        displayProducts(productRepository.getFilteredProducts(filter));
    }

    private void addToCart() {
        showProducts();
        System.out.print("Введите ID товара: ");
        String id = scanner.nextLine();

        productRepository.getFilteredProducts(p -> true).stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .ifPresentOrElse(
                        product -> {
                            cart.addItem(product);
                            System.out.println("Товар добавлен в корзину: " + product.getName());
                        },
                        () -> System.out.println("Товар с ID " + id + " не найден")
                );
    }

    private void checkout() {
        if (cart.getItems().isEmpty()) {
            System.out.println("Корзина пуста!");
            return;
        }

        System.out.println("\nВаш заказ:");
        cart.getItems().forEach(item ->
                System.out.printf("- %s (%.2f руб.)\n", item.getName(), item.getPrice()));

        double total = cart.getItems().stream()
                .mapToDouble(Product::getPrice)
                .sum();

        System.out.printf("Итого: %.2f руб.\n", total);
        System.out.print("Подтвердить заказ (да/нет)? ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("да")) {
            orderService.placeOrder();
            System.out.println("Заказ успешно оформлен!");
        } else {
            System.out.println("Заказ отменен");
        }
    }

    private static void displayProducts(List<Product> products) {
        System.out.println("\nДоступные товары:");
        products.forEach(p -> System.out.printf(
                "%s - %s (%.2f руб.)\n",
                p.getId(),
                p.getName(),
                p.getPrice()
        ));
    }
}