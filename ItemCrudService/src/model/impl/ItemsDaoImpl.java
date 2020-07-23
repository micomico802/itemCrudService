package model.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lib.CommonMethods;
import model.dao.ItemsDao;
import model.pojo.InputDataDto;
import model.pojo.ItemsDto;

public class ItemsDaoImpl implements ItemsDao {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rset;

    public ItemsDaoImpl(Connection connection) {
        this.conn = connection;
    }

    @Override
    public List<ItemsDto> doSearch(List<String> queryList) {
        List<ItemsDto> searchList = new ArrayList<>();

        StringBuffer sql = new StringBuffer("SELECT * FROM items_master WHERE 1= 1");
        if (!CommonMethods.isEmpty(queryList)) {
            for (String query : queryList) {
                sql.append(" AND " + query);
            }
        }
        sql.append(" AND " + "deleted_at IS NULL;");

        try {
            ps = conn.prepareStatement(new String(sql));
            rset = ps.executeQuery();

            while (rset.next()) {
                ItemsDto dto = new ItemsDto();
                dto.setItemId(rset.getInt("item_id"));
                dto.setItemName(rset.getString("item_name"));
                dto.setMakerName(rset.getString("maker_name"));
                dto.setPrice(rset.getInt("price"));
                dto.setCreatedAt(rset.getTimestamp("created_at"));
                dto.setUpdatedAt(rset.getTimestamp("updated_at"));
                searchList.add(dto);
            }
        } catch (SQLException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
        return searchList;
    }

    @Override
    public List<String> buildQuery(
            HashMap<String, String> inputData,
            HashMap<String, String> columnNames,
            HashMap<String, String> queryRules) {
        List<String> queryList = new ArrayList<>();
        String query = null;

        for (Map.Entry<String, String> entry : inputData.entrySet()) {
            String inputText = entry.getValue();
            if (CommonMethods.isEmpty(inputText)) {
                continue;
            }

            String key = entry.getKey();
            String columnName = columnNames.get(key);

            switch (queryRules.get(key)) {
            case "LIKE":
                query = columnName + " LIKE '%" + inputText + "%'";
                break;
            case "EQUAL_STRING":
                query = columnName + " = '" + inputText + "'";
                break;
            case "EQUAL_NUMERIC":
                query = columnName + " = " + inputText;
                break;
            }
            if (query != null) {
                queryList.add(query);
            }
        }
        return queryList;
    }

    @Override
    public boolean doDelete(String itemId) {
        String sql = "UPDATE items_master SET deleted_at = ? WHERE item_id = ?;";
        int deleteCounter = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            ps.setLong(2, Integer.parseInt(itemId));
            deleteCounter = ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
        return deleteCounter != 0;
    }

    @Override
    public boolean doUpdate(InputDataDto dto) {
        String sql = "UPDATE items_master SET "
                + "item_name = ?, "
                + "maker_name = ?, "
                + "price = ? "
                + "WHERE item_id = ?;";
        int updateCounter = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, dto.getItemName());
            ps.setString(2, dto.getMakerName());
            ps.setInt(3, Integer.parseInt(dto.getPrice()));
            ps.setInt(4, Integer.parseInt(dto.getItemId()));
            updateCounter = ps.executeUpdate();
            ps.close();


        } catch (SQLException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
        return updateCounter != 0;
    }

    @Override
    public boolean doInsert(InputDataDto dto) {
        String sql = "INSERT INTO items_master("
                + "item_name, "
                + "maker_name, "
                + "price ) "
                + "VALUES(?,?,?)";
        int insertCounter = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, dto.getItemName());
            ps.setString(2, dto.getMakerName());
            ps.setInt(3, Integer.parseInt(dto.getPrice()));
            insertCounter = ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
        return insertCounter != 0;
    }
}
