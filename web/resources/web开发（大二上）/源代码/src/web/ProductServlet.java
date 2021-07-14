package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.*;
import dao.*;

public class ProductServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String source = request.getParameter("source");

        if (source.equals("modifyProduct")) {//�޸���Ʒ��Ϣ
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String origin = request.getParameter("origin");
            double price = Double.parseDouble(request.getParameter("price"));
            int inventory = Integer.parseInt(request.getParameter("inventory"));
            Product product = new Product();
            product.setId(id);
            product.setName(name);
            product.setOrigin(origin);
            product.setPrice(price);
            product.setInventory(inventory);
            ProductDAO dao = new ProductDAO();
            dao.modifyProduct(product);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }

        if (source.equals("addProduct")) {//������Ʒ��Ϣ
            String name = request.getParameter("name");
            String origin = request.getParameter("origin");
            double price = Double.parseDouble(request.getParameter("price"));
            int inventory = Integer.parseInt(request.getParameter("inventory"));
            Product product = new Product();
            product.setName(name);
            product.setOrigin(origin);
            product.setPrice(price);
            product.setInventory(inventory);
            ProductDAO dao = new ProductDAO();
            dao.addProduct(product);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }

        if (source.equals("removeProduct")) {//�¼���Ʒ
            int proId = Integer.parseInt(request.getParameter("id"));
            ProductDAO dao = new ProductDAO();
            dao.removeProduct(proId);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }

        if (source.equals("deleteStorage")) {//ɾ���ֿ⹺���¼
            int id = Integer.parseInt(request.getParameter("id"));
            StorageDAO dao = new StorageDAO();
            dao.deleteStorage(id);
            request.getRequestDispatcher("/storage.jsp").forward(request, response);
        }
    }
}
