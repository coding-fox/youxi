package org.lingxi.youxi.web.dao;

import java.util.List;

import org.lingxi.youxi.web.model.MemberRegDTO;

public interface MemberRegDTOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MemberRegDTO record);

    int insertSelective(MemberRegDTO record);

    MemberRegDTO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MemberRegDTO record);

    int updateByPrimaryKey(MemberRegDTO record);

	/**
	 * @param curGangID
	 * @return
	 */
	List<MemberRegDTO> selectByGangKey(Integer gangID);
}