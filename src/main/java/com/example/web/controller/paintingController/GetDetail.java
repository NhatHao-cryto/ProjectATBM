package com.example.web.controller.paintingController;

import com.example.web.dao.PaintingDao;
import com.example.web.dao.model.Painting;
import com.example.web.dao.model.ProductReview;
import com.example.web.service.PaintingService;
import com.example.web.service.PrivewService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name ="Painting-detail", value = "/painting-detail")
public class GetDetail extends HttpServlet {
    private PaintingService paintingService = new PaintingService();
    private PrivewService privewService = new PrivewService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String pid = req.getParameter("pid");
            if (pid == null || pid.isEmpty()) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Thiếu tham số ID tranh");
                return;
            }

            int id = Integer.parseInt(pid);
            System.out.println("PID nhận được: " + id); // Debug log

            // Lấy thông tin tranh
            Painting painting = paintingService.getPaintingDetail(id);
            if (painting == null) {
                System.out.println("Không tìm thấy tranh với ID: " + id);
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy tranh");
                return;
            }

            // Lấy đánh giá
            List<ProductReview> reviews = privewService.getReviewByPaintingId(id);

            // Đặt attributes
            req.setAttribute("painting", painting);
            req.setAttribute("reviews", reviews);
            req.setAttribute("p", painting); // Tương tự như trên, có thể bỏ nếu không cần

            // Chuyển hướng
            System.out.println("Chuyển hướng tới trang chi tiết..."); // Debug log
            req.getRequestDispatcher("/user/painting-detail.jsp").forward(req, resp);

        } catch (NumberFormatException e) {
            System.err.println("ID tranh không hợp lệ: " + e.getMessage());
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID tranh không hợp lệ");
        } catch (SQLException e) {
            System.err.println("Lỗi database: " + e.getMessage());
            req.setAttribute("error", "Lỗi hệ thống khi tải thông tin tranh");
            req.getRequestDispatcher("/user/painting-detail.jsp").forward(req, resp);
        } catch (Exception e) {
            System.err.println("Lỗi không xác định: " + e.getMessage());
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi server");
        }
    }
}