package fit.iuh.week_5.utils;


public class Template {
  public static String sendSuccess(String customerName, String customerEmail, String productName, long quantityOrder) {
    return "<html><body>"
            + "<h2>Cảm ơn bạn đã mua hàng!</h2>"
            + "<p>Xin chào " + customerName + ",</p>"
            + "<p>Chúng tôi xin gửi lời cảm ơn sâu sắc đến bạn đã mua hàng tại cửa hàng của chúng tôi.</p>"
            + "<p>Mong rằng bạn sẽ hài lòng với sản phẩm mà bạn đã mua. Nếu bạn có bất kỳ câu hỏi hoặc yêu cầu nào, đừng ngần ngại liên hệ với chúng tôi.</p>"
            + "<p>Dưới đây là thông tin về đơn hàng của bạn:</p>"
            + "<p><strong>Tên Khách Hàng:</strong> " + customerName + "</p>"
            + "<p><strong>Email:</strong> " + customerEmail + "</p>"
            + "<p><strong>Tên Sản Phẩm:</strong> " + productName + "</p>"
            + "<p><strong>Số Lượng Đặt:</strong> " + quantityOrder + "</p>"
            + "<p>Xin cảm ơn một lần nữa!</p>"
            + "<p>Trân trọng,</p>"
            + "<p>Đội ngũ cửa hàng của chúng tôi</p>"
            + "</body></html>";
  }

  public static String sendReject(String customerName, String productName, long quantityOrdered, String customerEmail, String support) {
    return "<html><body>"
            + "<h2>Thông báo về đơn hàng của bạn</h2>"
            + "<p>Xin chào " + customerName + ",</p>"
            + "<p>Chúng tôi rất tiếc phải thông báo rằng sản phẩm " + productName
            + " bạn đặt hàng không có sẵn hoặc không đủ số lượng (" + quantityOrdered
            + " sản phẩm) để đáp ứng yêu cầu của bạn tại thời điểm này.</p>"
            + "<p>Xin lưu ý rằng chúng tôi sẽ cố gắng hết sức để cung cấp sản phẩm cho bạn trong thời gian sớm nhất có thể. Trong trường hợp bạn cần hỗ trợ hoặc muốn biết thông tin cụ thể hơn, vui lòng liên hệ với chúng tôi qua email: "
            + support + ".</p>"
            + "<p>Xin chân thành xin lỗi về sự bất tiện này!</p>"
            + "<p>Trân trọng,</p>"
            + "<p>Đội ngũ cửa hàng của chúng tôi</p>"
            + "</body></html>";
  }
}