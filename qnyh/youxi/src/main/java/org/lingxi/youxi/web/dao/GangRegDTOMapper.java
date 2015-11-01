package org.lingxi.youxi.web.dao;

import java.util.List;

import org.lingxi.youxi.web.model.GangRegDTO;

public interface GangRegDTOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GangRegDTO record);

    int insertSelective(GangRegDTO record);

    GangRegDTO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GangRegDTO record);

    int updateByPrimaryKey(GangRegDTO record);

	/**
	 * @return
	 */
	List<GangRegDTO> selectAll();
}