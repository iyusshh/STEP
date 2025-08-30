import java.util.Scanner;

class Product {
    String productId;
    String productName;
    double price;
    String category;
    int stockQuantity;

    static int totalProducts = 0;

    public Product(String productId, String productName, double price, String category, int stockQuantity) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.stockQuantity = stockQuantity;
        totalProducts++;
    }

    public static Product findProductById(Product[] products, String productId) {
        for (Product product : products) {
            if (product.productId.equalsIgnoreCase(productId)) {
                return product;
            }
        }
        return null;
    }
}

class ShoppingCart {
    Product[] products;
    int[] quantities;
    int count;
    double cartTotal;

    public ShoppingCart() {
        products = new Product[50];
        quantities = new int[50];
        count = 0;
        cartTotal = 0.0;
    }

    public void addProduct(Product product, int quantity) {
        if (product == null || quantity <= 0 || product.stockQuantity < quantity) {
            System.out.println("Cannot add product!");
            return;
        }
        for (int i = 0; i < count; i++) {
            if (products[i].productId.equals(product.productId)) {
                quantities[i] += quantity;
                product.stockQuantity -= quantity;
                calculateTotal();
                System.out.println("Added " + quantity + " more " + product.productName + " to cart.");
                return;
            }
        }
        products[count] = product;
        quantities[count] = quantity;
        product.stockQuantity -= quantity;
        count++;
        calculateTotal();
        System.out.println(product.productName + " added to cart.");
    }

    public void removeProduct(String productId) {
        for (int i = 0; i < count; i++) {
            if (products[i].productId.equalsIgnoreCase(productId)) {
                products[i].stockQuantity += quantities[i];
                for (int j = i; j < count - 1; j++) {
                    products[j] = products[j + 1];
                    quantities[j] = quantities[j + 1];
                }
                products[count - 1] = null;
                quantities[count - 1] = 0;
                count--;
                calculateTotal();
                System.out.println("Product removed.");
                return;
            }
        }
        System.out.println("Product not found in cart.");
    }

    public void calculateTotal() {
        cartTotal = 0;
        for (int i = 0; i < count; i++) {
            cartTotal += products[i].price * quantities[i];
        }
    }

    public void displayCart() {
        if (count == 0) {
            System.out.println("\nCart is empty.");
            return;
        }
        System.out.println("\n=== Shopping Cart ===");
        for (int i = 0; i < count; i++) {
            System.out.println(products[i].productId + " | " + products[i].productName + " | Rs." + products[i].price + " | Qty: " + quantities[i]);
        }
        System.out.println("Cart Total: Rs." + cartTotal);
    }

    public void checkout() {
        if (count == 0) {
            System.out.println("Cart is empty. Cannot checkout.");
            return;
        }
        displayCart();
        System.out.println("\nThank you for shopping!");
        products = new Product[50];
        quantities = new int[50];
        count = 0;
        cartTotal = 0.0;
    }
}

public class OnlineShoppingSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Product[] products = {
            new Product("P101", "Laptop", 55000, "Electronics", 10),
            new Product("P102", "Smartphone", 25000, "Electronics", 15),
            new Product("P103", "Headphones", 2000, "Electronics", 20),
            new Product("P104", "T-Shirt", 600, "Clothing", 30),
            new Product("P105", "Jeans", 1200, "Clothing", 25),
            new Product("P106", "Novel", 400, "Books", 40),
            new Product("P107", "Notebook", 50, "Books", 100),
            new Product("P108", "Rice Bag", 1200, "Groceries", 50),
            new Product("P109", "Milk Packet", 60, "Groceries", 100),
            new Product("P110", "Snacks Pack", 100, "Groceries", 80)
        };

        ShoppingCart cart = new ShoppingCart();
        int choice;

        do {
            System.out.println("\n=== Online Shopping Menu ===");
            System.out.println("1. View All Products");
            System.out.println("2. Search Product by ID");
            System.out.println("3. Add Product to Cart");
            System.out.println("4. Remove Product from Cart");
            System.out.println("5. View Cart");
            System.out.println("6. Checkout");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\n=== Available Products ===");
                    for (Product p : products) {
                        System.out.println(p.productId + " | " + p.productName + " | Rs." + p.price + " | Stock: " + p.stockQuantity);
                    }
                    break;

                case 2:
                    System.out.print("Enter Product ID: ");
                    String searchId = sc.nextLine();
                    Product found = Product.findProductById(products, searchId);
                    if (found != null) {
                        System.out.println(found.productId + " | " + found.productName + " | Rs." + found.price + " | Stock: " + found.stockQuantity);
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter Product ID: ");
                    String addId = sc.nextLine();
                    Product prod = Product.findProductById(products, addId);
                    if (prod == null) {
                        System.out.println("Product not found!");
                        break;
                    }
                    System.out.print("Enter Quantity: ");
                    int qty = sc.nextInt();
                    cart.addProduct(prod, qty);
                    break;

                case 4:
                    System.out.print("Enter Product ID to Remove: ");
                    String removeId = sc.nextLine();
                    cart.removeProduct(removeId);
                    break;

                case 5:
                    cart.displayCart();
                    break;

                case 6:
                    cart.checkout();
                    break;

                case 7:
                    System.out.println("Exiting... Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 7);

        sc.close();
    }
}
