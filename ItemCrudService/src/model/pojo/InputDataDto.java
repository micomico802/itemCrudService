package model.pojo;

import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;

/**
 * Viewの入力値をバリデーションするために取得するDtoクラス
 * @author Mizumoto
 *
 */
public class InputDataDto {
    // JSPでdtoをインスタンス化する場合に空文字を取得するように初期化
    private String itemId = "";
    private String itemName = "";
    private String makerName = "";
    private String price = "";

    /**
     * コンストラクタの設定
     * 今回はrequestを受け取った場合にも呼び出すため、引数がない場合の何も行わない
     * コンストラクタを設置している
     * @param request Viewの入力値
     */
    public InputDataDto() {
    }

    // オーバーロード(requestを受け取った場合のコンストラクタを分岐)
    public InputDataDto(HttpServletRequest request) {
        this.itemId = request.getParameter("item_id");
        this.itemName = request.getParameter("item_name");
        this.makerName = request.getParameter("maker_name");
        this.price = request.getParameter("price");
    }

    /*
     * Getter,Setterを設置
     * DTOを通してデータの受け渡しをする場合、privateで設置したフィールドに対して
     * setメソッドで値を受け取り、取得時はgetメソッドで値を返すようにする
     */
    public String getItemId() {
        return itemId;
    }
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public String getMakerName() {
        return makerName;
    }
    public void setMakerName(String makerName) {
        this.makerName = makerName;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * バリデーション時にkeyを結びつけるためにLinkedHashMapにして返す
     * @return items_masterの絡むごとの入力値
     */
    public LinkedHashMap<String, String> getItemsMap() {
        return new LinkedHashMap<String, String>() {
            {
                put("itemId", itemId);
                put("itemName", itemName);
                put("makerName", makerName);
                put("price", price);
            }
        };
    }

    /**
     * @return items_masterのcolumnの物理名
     */
    public HashMap<String, String> getPhysicalNameMap(){
        return new HashMap<String, String>() {
            {
                put("itemId", "item_id");
                put("itemName", "item_name");
                put("makerName", "maker_name");
                put("price", "price");
            }
        };
    }

    /**
     * @return items_masterのcolumnの論理名
     */
    public HashMap<String, String> getLogicalNamesMap(){
        return new HashMap<String, String>() {
            {
                put("itemId", "商品ID");
                put("itemName", "商品名");
                put("makerName", "メーカー名");
                put("price", "価格");
            }
        };
    }

    /**
     *
     * @return 検索実行時のValidationのcase
     */
    public HashMap<String, String> getSearchValidationRules(){
        return new HashMap<String, String>() {
            {
                put("itemId", "typeInteger");
                put("price", "typeInteger");
            }
        };
    }

    /**
     *
     * @return　更新、新規登録時のValidationのcase
     */
    public HashMap<String, String> getValidationRules(){
        return new HashMap<String, String>() {
            {
                put("itemName", "Empty");
                put("makerName", "Empty");
                put("price", "Empty/typeInteger");
            }
        };
    }

    /**
     * 検索実行時のWHERE以降のクエリ生成時のcase分けに使用
     * @return 検索時のcolumnごとのクエリのcase
     */
    public HashMap<String, String> getQueryRules(){
        return new HashMap<String, String>() {
            {
                put("itemId", "EQUAL_NUMERIC");
                put("itemName", "LIKE");
                put("makerName", "EQUAL_STRING");
                put("price", "EQUAL_NUMERIC");
            }
        };
    }
}
