package model.pojo;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

public class ItemsDto {
    private int itemId;
    private String itemName;
    private String makerName;
    private int price;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    /* コンストラクタの設定
    * このクラスをnewしてインスタンス化した際、呼び出されるのがコンストラクタ
    * インスタンス化時に必要ない場合は書かなくてもOK
    * 今回はrequestを受け取った場合にも呼び出すため、引数がない場合の何も行わない
    * コンストラクタを設置している
    */
    public ItemsDto() {
    }

    // オーバーロード(requestを受け取った場合のコンストラクタを分岐)
    public ItemsDto(HttpServletRequest request) {
        this.itemId = Integer.parseInt(request.getParameter("item_id"));
        this.itemName = request.getParameter("item_name");
        this.makerName = request.getParameter("maker_name");
        this.price = Integer.parseInt(request.getParameter("price"));
    }

    // Getter,Setter
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
