package org.lingxi.youxi.web.dao;

import org.lingxi.youxi.web.model.ActivityDTO;
import org.lingxi.youxi.web.model.ActivityDTOKey;

public interface ActivityDTOMapper {
    int deleteByPrimaryKey(ActivityDTOKey key);

    int insert(ActivityDTO record);

    int insertSelective(ActivityDTO record);

    ActivityDTO selectByPrimaryKey(ActivityDTOKey key);

    int updateByPrimaryKeySelective(ActivityDTO record);

    int updateByPrimaryKey(ActivityDTO record);
}