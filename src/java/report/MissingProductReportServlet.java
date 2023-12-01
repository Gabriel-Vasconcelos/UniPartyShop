package report;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.category.Category;
import model.category.CategoryDAO;
import model.product.Product;
import model.product.ProductDAO;

public class MissingProductReportServlet extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try (PrintWriter report = new PrintWriter(byteArrayOutputStream)) {
            ProductDAO productDAO = new ProductDAO();
            CategoryDAO categoryDAO = new CategoryDAO();

            List<Product> products = productDAO.listMissingProducts();

            if (products != null || !products.isEmpty()) {
                for (Product product : products) {
                    Category category = categoryDAO.selectCategory(product.getCategory_id());

                    report.print(product.getId() + "\t" + product.getTitle() + "\t" + product.getDescription() + "\t" + product.getPrice());

                    // Verifica se category é nulo antes de chamar getName()
                    if (category != null) {
                        report.print("\t" + category.getName());
                    }

                    report.print("\n");
                }
            } else {
                report.print("Nenhum produto faltando!");
            }

            report.flush();
        }
        response.setContentType("text/plain;charset=UTF-8");
        response.setContentLength((int) byteArrayOutputStream.size());
        String key = "Content-Disposition";
        String value = String.format("attachment; filename=\"%s\"",
                "report1.txt");
        response.setHeader(key, value);
        OutputStream out = response.getOutputStream();
        out.write(byteArrayOutputStream.toByteArray());
        out.close();
    }

}
