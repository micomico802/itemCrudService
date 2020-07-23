package model.dao;

import java.util.HashMap;
import java.util.List;

import model.pojo.InputDataDto;
import model.pojo.ItemsDto;

public interface ItemsDao {
    public List<ItemsDto> doSearch(List<String> queryList);
    public List<String> buildQuery(
            HashMap<String,String> inputData,
            HashMap<String,String> columnNames,
            HashMap<String,String> queryRules);
    public boolean doDelete(String itemId);
    public boolean doUpdate(InputDataDto dto);
    public boolean doInsert(InputDataDto dto);
}
