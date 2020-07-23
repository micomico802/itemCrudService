package lib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * @author Mizumoto
 * 検索、更新、登録時の入力値のバリデーションクラス
 *
 */
public class Validator {
    Map<String, String> inputData;
    Map<String, String> rules;
    Map<String, String> logicalNames;

    /**
     * @param、inputData 入力値
     * @param rules バリデーション時のmethodname
     * @param logicalNames items_masterのカラムの物理名
     */
    public Validator(
            HashMap<String, String> inputData,
            HashMap<String, String> rules,
            HashMap<String, String> logicalNames
    ) {
        this.inputData = Objects.requireNonNull(inputData);
        this.rules = Objects.requireNonNull(rules);
        this.logicalNames = Objects.requireNonNull(logicalNames);
    }

    /**
     * バリデーション実行メソッド
     * @return 判定で問題があった場合のエラーメッセージList
     */
    public List<String> validate() {
        List<String> errorMessages = new ArrayList<>();

        for (Map.Entry<String, String> entry : inputData.entrySet()) {
            String key = entry.getKey();
            if (CommonMethods.isEmpty(rules.get(key))) {
                continue;
            }

            String inputText = entry.getValue();
            String logicalName = logicalNames.get(key);
            String[] methodNames = rules.get(key).split("/");

            /*
             * 取得したValidationRuleにのっとって、値を判定
             * 問題があった場合はエラーメッセージを残し、Listに格納していく
             */
            for (String methodName : methodNames) {
                String errorMessage = null;

                switch (methodName) {
                // 空文字・Null判定
                case "Empty":
                    if (CommonMethods.isEmpty(inputText)) {
                        errorMessage = logicalName + "を入力してください";
                        errorMessages.add(errorMessage);
                    }
                    break;

                // int型の入力値判定
                case "typeInteger":
                    // Null判定はしているため、そのままbreakする。
                    if (CommonMethods.isEmpty(inputText)) {
                        break;
                    }
                    // 数値判定
                    if (!NumberUtils.isDigits(inputText)) {
                        errorMessage = logicalName + "に数値以外の文字が含まれています";
                        errorMessages.add(errorMessage);
                        break;
                    }

                    // parse時にint型で扱える値の範囲をオーバーしていないか判定(例外処理で判定)
                    try {
                        Integer.parseInt(inputText);

                    } catch (NumberFormatException e) {
                        errorMessage = logicalName + "の数値が大きすぎます";
                        errorMessages.add(errorMessage);
                    }
                    break;
                }
            }
        }
        return errorMessages;
    }
}
