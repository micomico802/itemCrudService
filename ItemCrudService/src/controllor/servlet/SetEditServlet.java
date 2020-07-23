package controllor.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.pojo.ItemsDto;

/**
 * Servlet implementation class ItemsSearchServlet
 */
@WebServlet("/SetEditServlet")
public class SetEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // JSPから受け取る情報に日本語パラメーターがあった場合、文字化けを防ぐためにエンコードの対応が必要
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String screenTransition = request.getParameter("screenTransition");


        switch (screenTransition) {
        case "setEdit":
            ItemsDto dto = new ItemsDto(request);
            request.setAttribute("editData", dto);
            request.getRequestDispatcher("/JSP/EditForm.jsp").forward(request, response);
            break;

        }
    }
}
