package controllor.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lib.CommonMethods;
import lib.ConnectionHandler;
import lib.Validator;
import model.dao.ItemsDao;
import model.impl.ItemsDaoImpl;
import model.pojo.InputDataDto;
import model.pojo.ItemsDto;

/**
 * @author Mizumoto
 * データベースitems_masterへの処理実行サーブレットクラス
 *
 */
@WebServlet("/CrudServlet")
public class CrudServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrudServlet() {
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

        // 各画面のbuttonのValueの情報を取得(それにより処理分岐)
        String screenTransition = request.getParameter("screenTransition");

        // MySQLへの接続、items_masterのDAOクラスをインスタンス化
        Connection conn = ConnectionHandler.init();
        ItemsDao dao = new ItemsDaoImpl(conn);

        Validator validator;
        List<String> errorMessages;
        String resultMessage;   // 処理結果表示用

        switch (screenTransition) {
        /*
         * 検索
         */
        case "searchResult":
            InputDataDto inputData = new InputDataDto(request);
            validator = new Validator(
                    inputData.getItemsMap(),
                    inputData.getSearchValidationRules(),
                    inputData.getLogicalNamesMap());
            errorMessages = validator.validate();

            if(!CommonMethods.isEmpty(errorMessages)) {
                request.setAttribute("errorMessages", errorMessages);
                request.setAttribute("inputData", inputData);
                request.getRequestDispatcher("/JSP/SearchForm.jsp").forward(request, response);
            break;
            }

            List<String> queryList = dao.buildQuery(
                    inputData.getItemsMap(),
                    inputData.getPhysicalNameMap(),
                    inputData.getQueryRules());
            List<ItemsDto> searchList = dao.doSearch(queryList);

            request.setAttribute("searchList", searchList);
            request.getRequestDispatcher("/JSP/SearchResult.jsp").forward(request, response);
            break;

        /*
         * 削除処理
         */
        case "delete":
            String itemId = request.getParameter("item_id");
            resultMessage = "削除に失敗しました";

            boolean deleteResult = dao.doDelete(itemId);
            if(deleteResult) {
                resultMessage = "削除に成功しました";
            }

            request.setAttribute("resultMessage", resultMessage);
            request.getRequestDispatcher("/JSP/Result.jsp").forward(request, response);
            break;

        /*
         * 更新処理
         */
        case "update":
            resultMessage = "変更に失敗しました";
            InputDataDto editData = new InputDataDto(request);
            validator = new Validator(
                    editData.getItemsMap(),
                    editData.getValidationRules(),
                    editData.getLogicalNamesMap());
            errorMessages = validator.validate();

            if(!CommonMethods.isEmpty(errorMessages)) {
                request.setAttribute("errorMessages", errorMessages);
                request.setAttribute("editData", editData);
                request.getRequestDispatcher("/JSP/EditForm.jsp").forward(request, response);
            break;
            }

            boolean updateResult = dao.doUpdate(editData);
            if(updateResult) {
                resultMessage = "変更に成功しました";
            }

            request.setAttribute("resultMessage", resultMessage);
            request.getRequestDispatcher("/JSP/Result.jsp").forward(request, response);
            break;

        /*
         * 登録処理
         */
        case "insert":
            resultMessage = "登録に失敗しました";
            InputDataDto registData = new InputDataDto(request);

            validator = new Validator(
                    registData.getItemsMap(),
                    registData.getValidationRules(),
                    registData.getLogicalNamesMap());
            errorMessages = validator.validate();

            if(!CommonMethods.isEmpty(errorMessages)) {
                request.setAttribute("errorMessages", errorMessages);
                request.setAttribute("inputData", registData);
                request.getRequestDispatcher("/JSP/RegistForm.jsp").forward(request, response);
            break;
            }

            boolean insertResult = dao.doInsert(registData);
            if(insertResult) {
                resultMessage = "登録に成功しました";
            }

            request.setAttribute("resultMessage", resultMessage);
            request.getRequestDispatcher("/JSP/Result.jsp").forward(request, response);
            break;
        }
        ConnectionHandler.close(conn);
    }
}
