package cn.helloworld1999.mapper;

import cn.helloworld1999.bean.Book;

import java.util.List;

public interface BookMapper {
    /**
     * 得到所有的书的列表
     * @return 所有的书籍组成的列表
     */
    List<Book> selectAllBook();

    /**
     * 模糊查询
     * @param book 一个用 bookId、bookName、price、stock 任意组合构造的 Book
     * @return Book 列表
     */
    List<Book> selectBookSelective(Book book);

    /**
     * 添加一本书
     * @param book 使用标准 Book构造出来的
     * @return 影响行数
     */
    int insertBook(Book book);

    /**
     * 模糊修改，其实就是模糊查找加可选修改
     * 但其实这个功能有点太强大了，得严格控制输入的对象，以防结果与预期不符，比如在不想批量修改的时候，批量修改了
     * @param book 模糊 Book对象 bookId、booName、price、stock
     * @return 影响行数
     */
    int updateBook(Book book);

    /**
     * 删除Book，严格根据 bookId删除
     * @param book 必须包含 bookId 的 Book
     * @return 影响行数
     */
    int deleteBook(Book book);
}
