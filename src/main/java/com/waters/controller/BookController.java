package com.waters.controller;

import com.waters.pojo.Book;
import com.waters.pojo.User;
import com.waters.pojo.UserLendBook;
import com.waters.service.BookService;
import com.waters.service.LendService;
import com.waters.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.RequestScope;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/*
 * 书籍控制器，主要用于接受前端的请求
 * 完成操作后返回相应的视图
 *
 * 功能包括增删改查
 * */
@Controller
@RequestMapping("/book")
// 原型模式（prototype），每次请求开启新的线程处理，防止阻塞
@RequestScope
public class BookController {

    //使用mybatis框架获得数据库服务接口
    @Autowired
    BookService bookService;
    @Autowired
    UserService userService;
    @Autowired
    LendService lendService;


    // 显示所有书本并返回主页视图
    @RequestMapping("/allBook")
    public String allBook(Model model, HttpServletRequest httpServletRequest, HttpSession session) {

        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userName")) {
                    User user = userService.queryByName(cookie.getValue());
                    session.setAttribute("control", user.getControlLevel());
                }
            }
        }
        List<Book> books = bookService.queryAll();
        model.addAttribute("books", books);
        return "/book/allBook";
    }

    //转到添加书本视图
    @RequestMapping("/toAddBook")
    public String toAddBook() {
        return "book/addBook";
    }

    //添加一本书
    @RequestMapping("/addBook")
    public String addBook(Book book, Model model) {
        bookService.insert(book);
        return "redirect:/book/allBook";
    }

    //转到更新书本页面
    @RequestMapping("/toUpdateBook")
    public String toUpdateBook(Model model, int bookID) {
        Book book = bookService.query(bookID);
        model.addAttribute("book", book);
        return "/book/updateBook";
    }

    //更新一本书
    @RequestMapping("/updateBook")
    public String updateBook(Book book) {
        bookService.update(book);
        return "redirect:/book/allBook";
    }

    //删除一本书
    @RequestMapping("/deleteBook/{bookID}")
    public String deleteBook(@PathVariable("bookID") int bookID, HttpServletRequest httpServletRequest) {
        Cookie[] cookies = httpServletRequest.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("control") && cookie.getValue().equals("2")) {
                bookService.delete(bookID);
            }
        }
        return "redirect:/book/allBook";
    }

    //按书名模糊搜索
    @RequestMapping("/queryByName")
    public String queryByName(Model model, String name) {
        List<Book> books = bookService.queryByName(name);
        model.addAttribute("books", books);
        if (books.size() == 0) {
            model.addAttribute("error", "未查到相关结果！");
        }
        return "/book/allBook";
    }

    @Transactional
    @RequestMapping("/lendBook")
    public String lendBook(int bookID,int userID, Model model){
        List<UserLendBook> userLendBooks = lendService.queryByBookAndUser(bookID, userID);

        for (UserLendBook userLendBook : userLendBooks) {
            if(userLendBook.getReturnTime().equals("not return")){
                model.addAttribute("message","您已借阅该书，请先归还书本!");
                return "book/allBook";
            }
        }

        Book book = bookService.query(bookID);
        if (book.getBookCount() > 0){
            book.setBookCount(book.getBookCount()-1);
            lendService.lendBook(bookID, userID);
            bookService.update(book);
            model.addAttribute("message","借阅成功！");
        } else {
            model.addAttribute("message","书本已借完！");
        }
        model.addAttribute("userID",userID);
        return "/book/lendBook";
    }

    @Transactional
    @RequestMapping("/returnBook")
    public String returnBook(int lendID,int bookID,Model model){
        Book book = bookService.query(bookID);
        lendService.returnBook(lendID);
        book.setBookCount(book.getBookCount()+1);
        bookService.update(book);
        model.addAttribute("message","返还成功！");
        return "redirect:/book/allBook";
    }

    @RequestMapping("/toLendBook")
    public String toLendBook(int userID,Model model){
        List<UserLendBook> userLendBooks = lendService.queryByUser(userID);
        model.addAttribute("lendBooks",userLendBooks);
        if(userLendBooks.size() == 0)
            model.addAttribute("error","未查到相关结果");
        return "/book/lendBook";
    }

    @RequestMapping("/searchLendBook")
    public String searchLendBook(int userID,Model model,String name){
        List<Book> books = bookService.queryByName(name);
        List<UserLendBook> userLendBooks = new ArrayList<>();
        for (Book book : books) {
            List<UserLendBook> userLendBooks1 = lendService.queryByBookAndUser(book.getBookID(), userID);
            userLendBooks.addAll(userLendBooks1);
        }
        model.addAttribute("lendBooks",userLendBooks);
        if(userLendBooks.size() == 0)
            model.addAttribute("error","未查到相关结果");
        return "/book/lendBook";
    }

}
