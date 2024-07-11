package cn.helloworld1999.bean;

public class Book {
    private Integer bookId;
    private String bookName;
    private Double price;
    private Integer stock;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    /**
     * 标准构造
     * @param bookName 名称
     * @param price 价格
     * @param stock 库存
     */
    public Book( Integer bookId,String bookName, Double price,Integer stock) {
        this.bookId = bookId;
        this.stock = stock;
        this.price = price;
        this.bookName = bookName;
    }

    /**
     * 用于调试的空参构造
     */
    public Book() {
    }
}
