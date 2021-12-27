package work.jimmmy.foodie.service.impl;

import com.github.pagehelper.PageInfo;
import work.jimmy.foodie.common.utils.PagedGridResult;

import java.util.List;

public class BaseService {
    protected PagedGridResult setPagedGrid(List<?> list, Integer page) {
        PageInfo<?> pageList = new PageInfo<>(list);
        PagedGridResult grid = new PagedGridResult();
        grid.setPage(page);
        grid.setRows(list);
        grid.setTotal(pageList.getPages());
        grid.setRecords(pageList.getTotal());
        return grid;
    }
}
