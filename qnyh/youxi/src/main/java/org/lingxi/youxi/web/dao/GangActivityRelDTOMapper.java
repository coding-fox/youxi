package org.lingxi.youxi.web.dao;

import org.lingxi.youxi.web.model.GangActivityRelDTO;
import org.lingxi.youxi.web.model.GangActivityRelDTOKey;

public interface GangActivityRelDTOMapper {
    int deleteByPrimaryKey(GangActivityRelDTOKey key);

    int insert(GangActivityRelDTO record);

    int insertSelective(GangActivityRelDTO record);

    GangActivityRelDTO selectByPrimaryKey(GangActivityRelDTOKey key);

    int updateByPrimaryKeySelective(GangActivityRelDTO record);

    int updateByPrimaryKey(GangActivityRelDTO record);
}