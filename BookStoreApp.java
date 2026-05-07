import javax.swing.*;
import java.awt.*;

public class BookStoreApp {
    public static void main(String[] args) {
        new HomeScreen();
    }
}

class BackgroundPanel extends JPanel {
    private final Image backgroundImage;

    BackgroundPanel(String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath);
        backgroundImage = icon.getImage();
        setLayout(new BorderLayout());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

class HomeScreen {
    JFrame frame;

    HomeScreen() {
        frame = new JFrame("St Agnes Book Center");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(760, 480);
        frame.setLocationRelativeTo(null);

        BackgroundPanel backgroundPanel = new BackgroundPanel("background.png");
        backgroundPanel.setLayout(new BorderLayout(20, 20));
        backgroundPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("St Agnes Book Center Mangaluru", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Georgia", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);

        JLabel subtitleLabel = new JLabel("A colorful bookstore for every student", SwingConstants.CENTER);
        subtitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        subtitleLabel.setForeground(new Color(235, 235, 235));

        JPanel titlePanel = new JPanel(new BorderLayout(10, 10));
        titlePanel.setOpaque(true);
        titlePanel.setBackground(new Color(0, 51, 102, 200));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(18, 20, 18, 20));
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        titlePanel.add(subtitleLabel, BorderLayout.SOUTH);

        JButton enterStoreButton = new JButton("Enter Book Store");
        enterStoreButton.setFont(new Font("SansSerif", Font.BOLD, 20));
        enterStoreButton.setBackground(new Color(255, 140, 0));
        enterStoreButton.setForeground(Color.WHITE);
        enterStoreButton.setFocusPainted(false);
        enterStoreButton.setPreferredSize(new Dimension(280, 60));
        enterStoreButton.setBorder(BorderFactory.createLineBorder(new Color(255, 215, 0), 2));
        enterStoreButton.addActionListener(e -> {
            frame.dispose();
            new BookEntryForm();
        });

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false);
        buttonPanel.add(enterStoreButton);

        JPanel bottomPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        bottomPanel.setOpaque(false);
        JLabel quoteLabel = new JLabel("Books are the passport to knowledge, reading is the journey.", SwingConstants.LEFT);
        quoteLabel.setFont(new Font("SansSerif", Font.ITALIC, 14));
        quoteLabel.setForeground(new Color(240, 240, 240));
        bottomPanel.add(quoteLabel);
        bottomPanel.add(new JLabel());

        backgroundPanel.add(titlePanel, BorderLayout.NORTH);
        backgroundPanel.add(buttonPanel, BorderLayout.CENTER);
        backgroundPanel.add(bottomPanel, BorderLayout.SOUTH);

        frame.setContentPane(backgroundPanel);
        frame.setVisible(true);
    }
}

class Book {
    String id;
    String name;
    double price;
    String imagePath;

    Book(String id, String name, double price, String imagePath) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return id + " - " + name + " ($" + String.format("%.2f", price) + ")";
    }
}

class BookEntryForm {
    JFrame frame;
    JTextField bookIdField, bookNameField, amountField, discountField;
    JLabel bookImageLabel;
    DefaultListModel<Book> catalogModel;
    JList<Book> catalogList;
    DefaultListModel<String> cartModel;
    JList<String> cartList;
    JLabel cartTotalLabel;
    double cartTotal = 0.0;

    BookEntryForm() {
        frame = new JFrame("Book Store Point of Sale");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(950, 600);
        frame.setLocationRelativeTo(null);

        BackgroundPanel backgroundPanel = new BackgroundPanel("background.png");
        backgroundPanel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        catalogModel = new DefaultListModel<>();
        initializeCatalog();
        catalogList = new JList<>(catalogModel);
        catalogList.setFont(new Font("SansSerif", Font.PLAIN, 14));
        catalogList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        catalogList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Book selectedBook = catalogList.getSelectedValue();
                if (selectedBook != null) {
                    populateSelectedBook(selectedBook);
                }
            }
        });

        JScrollPane catalogScrollPane = new JScrollPane(catalogList);
        catalogScrollPane.setBorder(BorderFactory.createTitledBorder("Available Books"));
        catalogScrollPane.setPreferredSize(new Dimension(320, 420));

        JPanel leftPanel = new JPanel(new BorderLayout(10, 10));
        leftPanel.setBackground(new Color(245, 245, 255));
        leftPanel.add(catalogScrollPane, BorderLayout.CENTER);
        leftPanel.add(createCartPanel(), BorderLayout.SOUTH);

        JPanel formPanel = createEntryPanel();

        backgroundPanel.add(leftPanel, BorderLayout.WEST);
        backgroundPanel.add(formPanel, BorderLayout.CENTER);

        frame.setContentPane(backgroundPanel);
        frame.setVisible(true);
    }

    private void initializeCatalog() {
        catalogModel.addElement(new Book("B101", "Java Programming", 450.00, "java_programming.png"));
        catalogModel.addElement(new Book("B102", "Data Structures", 320.00, "data_structures.png"));
        catalogModel.addElement(new Book("B103", "Web Design Basics", 275.50, "web_design_basics.png"));
        catalogModel.addElement(new Book("B104", "Database Systems", 390.00, "database_systems.png"));
        catalogModel.addElement(new Book("B105", "Algorithms in Java", 510.75, "algorithms_in_java.png"));
        catalogModel.addElement(new Book("B106", "Python for Beginners", 295.00, "python_beginners.png"));
        catalogModel.addElement(new Book("B107", "Machine Learning", 625.00, "machine_learning.png"));
        catalogModel.addElement(new Book("B108", "English Grammar", 185.00, "english_grammar.png"));
        catalogModel.addElement(new Book("B109", "Physics Fundamentals", 345.50, "physics_fundamentals.png"));
        catalogModel.addElement(new Book("B110", "Chemistry Concepts", 330.00, "chemistry_concepts.png"));
    }

    private JPanel createEntryPanel() {
        JPanel formArea = new JPanel(new BorderLayout(12, 12));
        formArea.setOpaque(true);
        formArea.setBackground(new Color(255, 255, 255, 220));
        formArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 190, 230), 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        JLabel heading = new JLabel("Book Selection & Checkout", SwingConstants.CENTER);
        heading.setFont(new Font("Georgia", Font.BOLD, 26));
        heading.setForeground(new Color(15, 35, 85));
        heading.setBorder(BorderFactory.createEmptyBorder(10, 10, 4, 10));

        JLabel subtitle = new JLabel("Choose a book, preview its cover, then add it to your student cart.", SwingConstants.CENTER);
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 14));
        subtitle.setForeground(new Color(55, 65, 100));

        JPanel topPanel = new JPanel(new BorderLayout(4, 4));
        topPanel.setOpaque(true);
        topPanel.setBackground(new Color(245, 248, 255, 220));
        topPanel.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
        topPanel.add(heading, BorderLayout.NORTH);
        topPanel.add(subtitle, BorderLayout.SOUTH);

        bookImageLabel = new JLabel("Select a book", SwingConstants.CENTER);
        bookImageLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        bookImageLabel.setVerticalTextPosition(SwingConstants.CENTER);
        bookImageLabel.setPreferredSize(new Dimension(260, 260));
        bookImageLabel.setOpaque(true);
        bookImageLabel.setBackground(new Color(255, 255, 255, 220));
        bookImageLabel.setBorder(BorderFactory.createLineBorder(new Color(140, 170, 210), 3));

        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setOpaque(false);
        imagePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(120, 150, 200), 2), "Cover Preview"));
        imagePanel.add(bookImageLabel, BorderLayout.CENTER);

        JPanel fieldsPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        fieldsPanel.setOpaque(true);
        fieldsPanel.setBackground(new Color(245, 250, 255, 240));
        fieldsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel bookIdLabel = new JLabel("Book ID:");
        bookIdLabel.setForeground(new Color(20, 35, 75));
        bookIdField = new JTextField();
        bookIdField.setEditable(false);
        bookIdField.setBackground(new Color(255, 255, 255));
        bookIdField.setForeground(new Color(20, 35, 75));

        JLabel bookNameLabel = new JLabel("Book Name:");
        bookNameLabel.setForeground(new Color(20, 35, 75));
        bookNameField = new JTextField();
        bookNameField.setEditable(false);
        bookNameField.setBackground(new Color(255, 255, 255));
        bookNameField.setForeground(new Color(20, 35, 75));

        JLabel amountLabel = new JLabel("Price:");
        amountLabel.setForeground(new Color(20, 35, 75));
        amountField = new JTextField();
        amountField.setEditable(false);
        amountField.setBackground(new Color(255, 255, 255));
        amountField.setForeground(new Color(20, 35, 75));

        JLabel discountLabel = new JLabel("Discount (%):");
        discountLabel.setForeground(new Color(20, 35, 75));
        discountField = new JTextField("0");
        discountField.setBackground(new Color(255, 255, 255));
        discountField.setForeground(new Color(20, 35, 75));

        fieldsPanel.add(bookIdLabel);
        fieldsPanel.add(bookIdField);
        fieldsPanel.add(bookNameLabel);
        fieldsPanel.add(bookNameField);
        fieldsPanel.add(amountLabel);
        fieldsPanel.add(amountField);
        fieldsPanel.add(discountLabel);
        fieldsPanel.add(discountField);

        JPanel selectionPanel = new JPanel(new BorderLayout(14, 14));
        selectionPanel.setOpaque(true);
        selectionPanel.setBackground(new Color(255, 255, 255, 200));
        selectionPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        selectionPanel.add(imagePanel, BorderLayout.WEST);
        selectionPanel.add(fieldsPanel, BorderLayout.CENTER);

        cartTotalLabel = new JLabel("Cart Total: $0.00");
        cartTotalLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        cartTotalLabel.setForeground(new Color(10, 90, 55));
        cartTotalLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel actionPanel = new JPanel(new GridLayout(2, 3, 12, 12));
        actionPanel.setOpaque(true);
        actionPanel.setBackground(new Color(245, 250, 255, 220));

        JButton addToCartButton = createStyledButton("Add to Cart", new Color(56, 142, 60));
        JButton displayBooksButton = createStyledButton("Show Books", new Color(37, 109, 183));
        JButton convertButton = createStyledButton("Convert", new Color(0, 123, 167));
        JButton clearButton = createStyledButton("Clear", new Color(255, 159, 67));
        JButton generateBillButton = createStyledButton("Generate Bill", new Color(79, 70, 229));
        JButton exitButton = createStyledButton("Exit", new Color(203, 56, 92));

        addToCartButton.addActionListener(e -> addSelectedBookToCart());
        displayBooksButton.addActionListener(e -> showBookCatalog());
        convertButton.addActionListener(e -> convertAmount());
        clearButton.addActionListener(e -> clearFields());
        generateBillButton.addActionListener(e -> generateBill());
        exitButton.addActionListener(e -> exitApplication());

        actionPanel.add(addToCartButton);
        actionPanel.add(displayBooksButton);
        actionPanel.add(convertButton);
        actionPanel.add(clearButton);
        actionPanel.add(generateBillButton);
        actionPanel.add(exitButton);

        JPanel bottomPanel = new JPanel(new BorderLayout(10, 10));
        bottomPanel.setOpaque(false);
        bottomPanel.add(cartTotalLabel, BorderLayout.NORTH);
        bottomPanel.add(actionPanel, BorderLayout.CENTER);

        formArea.add(topPanel, BorderLayout.NORTH);
        formArea.add(selectionPanel, BorderLayout.CENTER);
        formArea.add(bottomPanel, BorderLayout.SOUTH);

        JPanel wrapper = new JPanel(new BorderLayout(10, 10));
        wrapper.setOpaque(false);
        wrapper.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        wrapper.add(formArea, BorderLayout.CENTER);

        return wrapper;
    }

    private JPanel createCartPanel() {
        cartModel = new DefaultListModel<>();
        cartList = new JList<>(cartModel);
        cartList.setFont(new Font("SansSerif", Font.PLAIN, 14));

        JScrollPane cartScrollPane = new JScrollPane(cartList);
        cartScrollPane.setPreferredSize(new Dimension(320, 220));
        cartScrollPane.setBorder(BorderFactory.createTitledBorder("Cart Items"));

        JPanel cartPanel = new JPanel(new BorderLayout(8, 8));
        cartPanel.setBackground(new Color(245, 245, 255));
        cartPanel.add(cartScrollPane, BorderLayout.CENTER);

        return cartPanel;
    }

    private JButton createStyledButton(String label, Color background) {
        JButton button = new JButton(label);
        button.setBackground(background);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        return button;
    }

    private void populateSelectedBook(Book book) {
        bookIdField.setText(book.id);
        bookNameField.setText(book.name);
        amountField.setText(String.format("%.2f", book.price));
        discountField.setText("0");
        ImageIcon icon = getScaledImageIcon(book.imagePath, 220, 220);
        if (icon != null) {
            bookImageLabel.setText("");
            bookImageLabel.setIcon(icon);
        } else {
            bookImageLabel.setIcon(null);
            bookImageLabel.setText("Image not found");
        }
    }

    private void addSelectedBookToCart() {
        Book selectedBook = catalogList.getSelectedValue();
        if (selectedBook == null) {
            JOptionPane.showMessageDialog(frame,
                    "Please select a book from the catalog.",
                    "No Book Selected",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            double discount = Double.parseDouble(discountField.getText());
            if (discount < 0 || discount > 100) {
                throw new NumberFormatException();
            }
            double finalPrice = selectedBook.price - (selectedBook.price * discount / 100);
            String cartItem = String.format("%s | %s | $%.2f | %.0f%% => $%.2f",
                    selectedBook.id,
                    selectedBook.name,
                    selectedBook.price,
                    discount,
                    finalPrice);
            cartModel.addElement(cartItem);
            cartTotal += finalPrice;
            updateCartTotal();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame,
                    "Enter a valid discount percentage between 0 and 100.",
                    "Invalid Discount",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateCartTotal() {
        cartTotalLabel.setText(String.format("Cart Total: $%.2f", cartTotal));
    }

    private ImageIcon getScaledImageIcon(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(path);
        if (icon.getIconWidth() <= 0 || icon.getIconHeight() <= 0) {
            return null;
        }
        Image scaled = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }

    private void showBookCatalog() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < catalogModel.getSize(); i++) {
            builder.append(catalogModel.get(i)).append("\n");
        }
        JOptionPane.showMessageDialog(frame,
                builder.toString(),
                "Book Catalog",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void convertAmount() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            double discount = Double.parseDouble(discountField.getText());
            double discountedPrice = amount - (amount * discount / 100);

            JOptionPane.showMessageDialog(frame,
                    String.format("Discounted Price: $%.2f", discountedPrice),
                    "Price Conversion",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame,
                    "Enter valid numeric values.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        bookIdField.setText("");
        bookNameField.setText("");
        amountField.setText("");
        discountField.setText("0");
        bookImageLabel.setIcon(null);
        bookImageLabel.setText("Select a book");
        catalogList.clearSelection();
    }

    private void exitApplication() {
        System.exit(0);
    }

    private void generateBill() {
        if (cartModel.isEmpty()) {
            JOptionPane.showMessageDialog(frame,
                    "Cart is empty. Add books to the cart before generating a bill.",
                    "Empty Cart",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        StringBuilder billBuilder = new StringBuilder();
        billBuilder.append("==== BOOK BILL ====\n");
        for (int i = 0; i < cartModel.getSize(); i++) {
            billBuilder.append(cartModel.get(i)).append("\n");
        }
        billBuilder.append("\nTotal Payable: ").append(String.format("$%.2f", cartTotal));

        new BillDisplayFrame(billBuilder.toString());
        frame.dispose();
    }
}

class BillDisplayFrame {
    JFrame frame;

    BillDisplayFrame(String billDetails) {
        frame = new JFrame("Bill Details");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 360);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout(10, 10));
        frame.setResizable(false);

        frame.getContentPane().setBackground(new Color(245, 248, 255));

        JLabel captionLabel = new JLabel("Books empower every student to learn, grow and succeed.", SwingConstants.CENTER);
        captionLabel.setFont(new Font("Serif", Font.ITALIC, 14));
        captionLabel.setForeground(new Color(25, 25, 112));
        captionLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));

        JTextArea billArea = new JTextArea(billDetails);
        billArea.setEditable(false);
        billArea.setFont(new Font("Arial", Font.PLAIN, 14));
        billArea.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 160), 1));
        billArea.setBackground(new Color(255, 255, 245));

        JScrollPane scrollPane = new JScrollPane(billArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel thankYouLabel = new JLabel("Thank you for visiting! Come back soon.", SwingConstants.CENTER);
        thankYouLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        thankYouLabel.setForeground(new Color(0, 90, 0));
        thankYouLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        JButton closeButton = new JButton("CLOSE");
        closeButton.setBackground(new Color(70, 130, 180));
        closeButton.setForeground(Color.WHITE);
        closeButton.setFocusPainted(false);
        closeButton.setFont(new Font("Arial", Font.BOLD, 14));
        closeButton.addActionListener(e -> frame.dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(245, 248, 255));
        buttonPanel.add(closeButton);

        frame.add(captionLabel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(thankYouLabel, BorderLayout.SOUTH);
        frame.add(buttonPanel, BorderLayout.PAGE_END);

        frame.setVisible(true);
    }
}