class OrderService {
    private final Cart cart;

    public OrderService(Cart cart) {
        this.cart = cart;
    }

    public void placeOrder() {
        System.out.println("Заказ оформлен. Товаров: " + cart.getItems().size());
        cart.clear();
    }
}
