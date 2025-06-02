<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Admin Panel</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css" rel="stylesheet">
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
  <style> .sidebar {
    height: 100vh;
    position: fixed;
    top: 0;
    left: 0;
    width: 250px;
    background-color: #343a40;
    color: white;
    padding: 20px 10px;
  }
  .sidebar a {
    color: white;
    text-decoration: none;
    display: block;
    padding: 10px;
    margin-bottom: 5px;
  }
  .sidebar a:hover {
    background-color: #495057;
    border-radius: 5px;
  }
  .content {
    margin-left: 260px; /* Sidebar width + margin */
    padding: 20px;
  }
  </style>

</head>
<body>
<!-- Sidebar -->
<%@ include file="/admin/sidebar.jsp" %>
<div class="content">
<div class="card mb-4">
  <div class="card-header bg-success text-white" style="background: #e7621b !important;">
    <h4>Đơn Hàng Hiện Tại</h4>
  </div>
  <div class="card-body">
    <table id="currentOrders" class="table table-bordered display">
      <thead>
      <tr>
        <th>Mã Đơn Hàng</th>
        <th>Tổng Tiền</th>
        <th>Ngày Đặt</th>
        <th>Trạng Thái</th>
        <th>Hành Động</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="order" items="${currentOrder}">
        <tr>
          <td>${order.id}</td>
          <td>${order.totalAmount}</td>
          <td>${order.orderDate}</td>
          <td>${order.status}</td>
          <td><button class="btn btn-info btn-sm" data-bs-toggle="modal" data-bs-target="#orderDetailsModal" data-order-id="${order.id}">Xem Chi Tiết</button>
            <button class="btn btn-danger btn-sm" data-bs-toggle="modal"
                    data-bs-target="#deleteOrderModal" data-order-id="${order.id}">Xóa</button>
          </td>

        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
</div>

<div class="card mb-4">
  <div class="card-header bg-secondary text-white">
    <h4>Lịch Sử Đơn Hàng</h4>
  </div>
  <div class="card-body">
    <table id="orderHistory" class="table table-bordered display">
      <thead>
      <tr>
        <th>Mã Đơn Hàng</th>
        <th>Tổng Tiền</th>
        <th>Ngày Đặt</th>
        <th>Ngày Giao</th>
        <th>Trạng Thái</th>
        <th>Hành Động</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="order" items="${historyOrder}">
        <tr>
          <td>${order.id}</td>
          <td>${order.totalAmount}</td>
          <td>${order.orderDate}</td>
          <td>${order.deliveryDate}</td>
          <td>${order.status}</td>
          <td><button class="btn btn-info btn-sm" data-bs-toggle="modal" data-bs-target="#orderDetailsModal" data-order-id="${order.id}">Xem Chi Tiết</button>
            <button class="btn btn-danger btn-sm" data-bs-toggle="modal"
                    data-bs-target="#deleteOrderModal" data-order-id="${order.id}">Xóa</button>
          </td>
        </tr>`;
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
</div>


  <div class="modal fade" id="deleteOrderModal" tabindex="-1" aria-labelledby="deleteOrderModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="deleteOrderModalLabel">Xác nhận xóa</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <form action="${pageContext.request.contextPath}/admin/orders/delete" method="POST">
          <div class="modal-body">
            <p>Bạn có chắc chắn muốn xóa đơn hàng này?</p>
            <input type="hidden" id="orderIdToDelete" name="orderId">
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
            <button type="submit" class="btn btn-danger">Xóa</button>
          </div>
        </form>
      </div>
    </div>
  </div>
  <div class="modal fade" id="orderDetailsModal" tabindex="-1" aria-labelledby="orderDetailsModalLabel"
  aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="orderDetailsModalLabel">Chi Tiết Đơn Hàng</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <div id="orderRecipientInfo">
        </div>
        <table class="table table-striped">
          <thead>
          <tr>
            <th>Max sản phẩm</th>
            <th>Tên Sản Phẩm</th>
            <th>Ảnh</th>
            <th>Kích Thước</th>
            <th>Số Lượng</th>
            <th>Giá</th>
          </tr>
          </thead>
          <tbody id="orderDetailsBody"></tbody>
        </table>
        <div id="totalPrice">
        </div>

        <div id="orderStatusInfo">
          <p><strong>Trạng thái: </strong><span id="orderStatus"></span></p>

          <label for="statusSelect">Cập nhật trạng thái:</label>
          <select id="statusSelect" class="form-select">
            <option value="chờ">chờ</option>
            <option value="đang giao">đang giao</option>
            <option value="hoàn thành">hoàn thành</option>
            <option value="thất bại">thất bại</option>
            <option value="dã hủy">dã hủy</option>
          </select>

          <button id="updateStatusBtn" class="btn btn-primary mt-3">Cập nhật đơn hàng</button>
        </div>
      </div>
      </div>
    </div>
  </div>
</div>


</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
  $(document).ready(function() {
    $('#currentOrders').DataTable();
    $('#orderHistory').DataTable();
  });

  $(document).ready(function() {
    $('#artists').DataTable();
  });

  document.querySelectorAll('[data-bs-target="#deleteOrderModal"]').forEach(button => {
    button.addEventListener('click', function() {
      let orderId = this.getAttribute('data-order-id');
      document.getElementById('orderIdToDelete').value = orderId;
    });
  });
</script>
<script src="${pageContext.request.contextPath}/assets/js/admin/order.js"></script>
</body>
</html>
