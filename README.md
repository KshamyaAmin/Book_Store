# St Agnes Book Center - Book Store Application

A colorful, feature-rich Java Swing desktop application for a student bookstore with book selection, cart management, and billing system. Built for St Agnes Book Center Mangaluru.

## Features

✨ **Book Catalog Management**
- Browse 10+ books with titles, IDs, and prices
- Book cover image preview support
- Search and select books from interactive list

🛒 **Shopping Cart**
- Add selected books to cart
- Apply discount percentages per book
- Real-time cart total calculation
- View all cart items in a dedicated panel

💰 **Pricing & Billing**
- Display prices in Indian Rupees (Rs.)
- Price conversion with discount calculations
- Generate detailed bills with itemized list
- Professional bill display window

🎨 **Beautiful UI**
- Colorful, modern Swing interface
- Background bookshelf imagery
- Responsive layout with clear typography
- Semi-transparent panels for better visibility
- Color-coded action buttons

## Book Catalog (Pre-loaded)

1. B101 - Java Programming (Rs. 450.00)
2. B102 - Data Structures (Rs. 320.00)
3. B103 - Web Design Basics (Rs. 275.50)
4. B104 - Database Systems (Rs. 390.00)
5. B105 - Algorithms in Java (Rs. 510.75)
6. B106 - Python for Beginners (Rs. 295.00)
7. B107 - Machine Learning (Rs. 625.00)
8. B108 - English Grammar (Rs. 185.00)
9. B109 - Physics Fundamentals (Rs. 345.50)
10. B110 - Chemistry Concepts (Rs. 330.00)

## System Requirements

- **Java**: JDK 8 or higher
- **OS**: Windows, macOS, Linux
- **Memory**: 256 MB minimum
- **Display**: 800x600 or higher resolution

## Installation & Setup

### 1. Clone the Repository
```bash
git clone https://github.com/KshamyaAmin/Book_Store.git
cd Book_Store
```

### 2. Compile the Application
```bash
javac BookStoreApp.java
```

### 3. Run the Application
```bash
java BookStoreApp
```

## How to Use

### Home Screen
1. Launch the application
2. Click **"Enter Book Store"** button
3. Proceed to the main POS interface

### Book Selection & Cart
1. **Browse Books**: Select a book from the "Available Books" list on the left
2. **View Details**: Book ID, name, and price appear in the "Selected Book Details" section
3. **Book Cover**: Preview the book's cover image (if available)
4. **Apply Discount**: Enter a discount percentage (0-100) in the "Discount (%)" field
5. **Add to Cart**: Click **"Add to Cart"** to add the selected book
6. **View Cart**: Cart items display in the right panel with price and discount applied

### Checkout & Billing
1. Click **"Show Books"** to view the full catalog
2. Click **"Convert"** to calculate discounted price for the selected book
3. Click **"Clear"** to reset the form and cart
4. Click **"Generate Bill"** to create and display the final bill
5. View the bill with:
   - All cart items with prices and discounts
   - Total payable amount in Indian Rupees
   - Inspirational message and thank you note

## Project Structure

```
Book_Store/
├── BookStoreApp.java         # Main application class
├── HomeScreen               # Initial home screen window
├── BookEntryForm           # Main POS interface
├── Book                    # Book data model class
├── BackgroundPanel         # Custom panel with background image
├── BillDisplayFrame        # Bill display window
├── background.png          # Background image file
├── *.png                   # Book cover images
└── README.md               # This file
```

## Classes Overview

### `BookStoreApp`
- Entry point of the application
- Launches the home screen

### `HomeScreen`
- Welcome screen with store branding
- Navigation to book store interface
- Displays store name and inspirational quote

### `Book`
- Data model for book information
- Properties: id, name, price, imagePath
- String representation for display

### `BackgroundPanel`
- Custom JPanel with background image support
- Transparent drawing with image overlay

### `BookEntryForm`
- Main Point-of-Sale (POS) interface
- Handles book selection, cart management, and billing
- Key methods:
  - `initializeCatalog()` - Load all books
  - `addSelectedBookToCart()` - Add books to cart
  - `generateBill()` - Create bill
  - `createEntryPanel()` - Build UI layout
  - `getScaledImageIcon()` - Scale book images

### `BillDisplayFrame`
- Displays the final bill in a new window
- Shows total payable amount
- Displays thank you message

## Currency

All prices are displayed in **Indian Rupees (Rs.)** format:
- Example: Rs. 450.00
- Symbol: Rs.
- Format: Two decimal places

## Features Implemented

✅ Multi-book catalog with 10+ items  
✅ Dynamic cart with running total  
✅ Discount calculation per item  
✅ Book image preview (if images provided)  
✅ Bill generation and display  
✅ Professional UI with background images  
✅ Color-coded action buttons  
✅ Student-focused messaging  
✅ Indian Rupees (Rs.) currency format  
✅ Clear and intuitive navigation  

## Future Enhancements

- Database integration for persistent storage
- Payment gateway integration
- Student discount codes
- Search and filter functionality
- Quantity selection per book
- Order history and receipts
- Admin panel for inventory management
- Email receipt functionality
- Barcode scanning support

## Troubleshooting

### Images Not Displaying
- Ensure background.png and book*.png files are in the same directory as BookStoreApp.java
- If missing, the app displays placeholder text

### Text Not Visible
- Ensure your display supports at least 800x600 resolution
- Try running on a larger window

### Compilation Issues
- Verify Java is installed: `java -version`
- Ensure you're in the correct directory: `cd c:\Users\HP\Java`

## Author

**KshamyaAmin**  
Repository: https://github.com/KshamyaAmin/Book_Store

## License

This project is open source. Feel free to use, modify, and distribute.

## Acknowledgments

- Built with Java Swing GUI toolkit
- Designed for St Agnes Book Center, Mangaluru
- Inspired by student learning needs and bookstore operations

---

**Welcome to St Agnes Book Center!**  
*Books empower every student to learn, grow and succeed.*
