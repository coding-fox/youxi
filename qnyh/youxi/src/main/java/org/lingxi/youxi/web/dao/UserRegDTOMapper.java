package org.lingxi.youxi.web.dao;

import org.lingxi.youxi.web.model.UserRegDTO;

public interface UserRegDTOMapper {
    int deleteByPrimaryKey(String userId);

    int insert(UserRegDTO record);

    int insertSelective(UserRegDTO record);

    UserRegDTO selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(UserRegDTO record);

    int updateByPrimaryKey(UserRegDTO record);
}