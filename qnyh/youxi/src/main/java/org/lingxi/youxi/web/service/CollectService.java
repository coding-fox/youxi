/**
 * 
 */
package org.lingxi.youxi.web.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.lingxi.base.common.StringUtil;
import org.lingxi.youxi.collect.beans.Auxiliary;
import org.lingxi.youxi.collect.beans.Comprehensive;
import org.lingxi.youxi.collect.beans.Outline;
import org.lingxi.youxi.collect.beans.OutputAmount;
import org.lingxi.youxi.collect.beans.Tank;
import org.lingxi.youxi.collect.parser.TextParser;
import org.lingxi.youxi.web.dao.ActivityDTOMapper;
import org.lingxi.youxi.web.dao.GangActivityRelDTOMapper;
import org.lingxi.youxi.web.dao.GangRegDTOMapper;
import org.lingxi.youxi.web.dao.MemberRegDTOMapper;
import org.lingxi.youxi.web.model.ActivityDTO;
import org.lingxi.youxi.web.model.GangActivityRelDTO;
import org.lingxi.youxi.web.model.GangRegDTO;
import org.lingxi.youxi.web.model.MemberRegDTO;
import org.lingxi.youxi.web.model.QnyhCollectArg;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * 2015年10月9日
 */
@Service("collectService")
public class CollectService {

	private static Logger _log = Logger.getLogger(CollectService.class);
	@Resource
	private TextParser textParser;
	@Resource
	private ActivityDTOMapper activityDao;
	@Resource
	private GangActivityRelDTOMapper gaRelDao;
	@Resource
	private GangRegDTOMapper gangDao;
	@Resource
	private MemberRegDTOMapper memberDao;
	
	public GangRegDTO getGang(int id){
		return gangDao.selectByPrimaryKey(id);
	}
	public void insertGang(GangRegDTO arg){
		gangDao.insert(arg);
	}
	public String insertFightingSummary(List<QnyhCollectArg> args){
		if(args==null || args.size()==0){
			return "文件解析异常";
		}
		Outline outline=null;
		Date fightingTime=null;
		for(QnyhCollectArg arg:args){
			try {
	        	List<String> fetchContent = textParser.fetchContent(arg.getContent());
	        	outline = textParser.parseOutline(fetchContent);
	        	if(StringUtil.isEmpty(outline.getGang())){
	        		_log.error("文件解析异常-帮会名称为空，目标文件名称："+arg.getName());
	        		return "帮会名称解析失败";
	        	}
				fightingTime = textParser.parseDate(outline.getGang(),arg.getName());
				summaryAndPersist(fightingTime,outline);
			} catch (Exception e) {
				e.printStackTrace();
				_log.error("文件解析异常，目标文件名称："+arg.getName());
				return "文件解析异常";
			}
		}
		return null;
	}
	/**
	 * @param fightingTime
	 * @param outline
	 */
	private void summaryAndPersist(Date fightingTime, Outline outline) {

		
		List<GangRegDTO> gangs = gangDao.selectAll();
		String curGangName = outline.getGang();
		Integer curGangID = getGangID(curGangName, gangs);
		if(curGangID==null){
			//插入新帮会
			GangRegDTO record = new GangRegDTO();
			record.setName(curGangName);
			gangDao.insert(record);
			curGangID = record.getId();
		}
		
		//日历工具类辅助计算
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fightingTime);
		int round = calcRound(calendar);
		String ywrID = calendar.get(Calendar.YEAR)+"_"+calendar.get(Calendar.WEEK_OF_YEAR)+"_"+round;
		//查询所有会员
		//是否新会员
		//插入新会员
		//活动数据-人员ID，是否英雄，是否开车
		List<MemberRegDTO> members = memberDao.selectByGangKey(curGangID);
		List<ActivityDTO> acts = generateActivitys(outline,members,curGangID,ywrID);
		
		//活动统计表-id,参战人数，不挂机人数，持续时间，创建时间
		GangActivityRelDTO statistic = generateGangActivitys(acts,calendar,curGangID,round);
		statistic.setYwrId(ywrID);
		gaRelDao.insert(statistic);
		
		for(ActivityDTO act:acts){
			activityDao.insert(act);
		}
		
	}
	/**
	 * @param calendar
	 * @return 
	 */
	private int calcRound(Calendar calendar) {
		int round;
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int min = calendar.get(Calendar.MINUTE);
		if(calendar.get(Calendar.DAY_OF_WEEK)==Calendar.FRIDAY){
			if(hour==20){
				round=1;
			}else if(hour==21){
				if(min<45){
					round =1;
				}else{
					round =2;
				}
			}else if(hour>21 && hour<=23){
				round = 2;
			}else{
				throw new RuntimeException("不允许的小时范围");
			}
		}else if(calendar.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY){
			if(hour>=20 && hour<=23){
				round = 3;
			}else{
				throw new RuntimeException("不允许的小时范围");
			}
		}else{
			throw new RuntimeException("不允许的天范围");
		}
		return round;
	}

	/**
	 * @param acts
	 * @param calendar
	 * @param curGangID 
	 * @param ywrID 
	 * @return
	 */
	private GangActivityRelDTO generateGangActivitys(List<ActivityDTO> acts, Calendar calendar, Integer curGangID, int round) {
		GangActivityRelDTO res = new GangActivityRelDTO();
		res.setCreateTime(calendar.getTime());
		res.setFightCount(((Integer)acts.size()).shortValue());
		res.setGangId(curGangID);
		res.setDuration(getOffsetMinute(calendar, round).byteValue());
		res.setDeadCount(getDeadMemberCount(acts));
		return res;
	}
	/**
	 * @param acts
	 * @return
	 */
	private Short getDeadMemberCount(List<ActivityDTO> acts) {
		Short count  = 0;
		for(ActivityDTO act :acts){
			if(act.getKillQ()<1 && act.getCarryQ()<2 && act.getAssistQ()<5 && act.getTankKillQ()<1
					&& act.getCureM()<10000 && act.getCorpseQ()<5 && act.getBearM()<100000L  
					&& act.getOutputM()<100000L && act.getReliveQ()<3 && act.getKilledQ()<3){
				count++;
			}
			
		}
		return count;
	}
	/**
	 * @param calendar
	 * @param round
	 * @return
	 */
	private Long getOffsetMinute(Calendar calendar, int round) {
		int f_hour = 0,f_min = 0;
		if(round==1 || round ==3){
			f_hour = 20;
			
		}else{
			f_hour =21;
			f_min = 45;
		}
		Calendar clone = (Calendar) calendar.clone();
		clone.set(Calendar.HOUR_OF_DAY, f_hour);
		clone.set(Calendar.MINUTE,f_min);
		return (calendar.getTimeInMillis()-clone.getTimeInMillis())/60000;
	}
	/**
	 * @param outline
	 * @param members 
	 * @param curGangID 
	 * @param ywrID 
	 * @return
	 */
	private List<ActivityDTO> generateActivitys(Outline outline, List<MemberRegDTO> members, Integer curGangID, String ywrID) {

		List<Comprehensive> main = outline.getMain();
		List<Auxiliary> assist = outline.getAssist();
		List<Tank> tanks = outline.getTank();
		List<OutputAmount> out = outline.getOut();
		List<OutputAmount> cure = outline.getCure();
		List<OutputAmount> bear = outline.getBear();

		List<ActivityDTO> acts = new ArrayList<ActivityDTO>();
		ActivityDTO act = null;
		MemberRegDTO reg = null;
		for(int i=0;i<main.size();i++){
			Comprehensive comprehensive = main.get(i);
			Auxiliary auxiliary = assist.get(i);
			Tank tank = tanks.get(i);
			
			String memberName = comprehensive.getName();
			
			Integer memberID = getMemberID(memberName,members);
			if(memberID==null){
				reg = new MemberRegDTO();
				reg.setGangId(curGangID);
				reg.setLevel(main.get(i).getLevel());
				reg.setName(memberName);
				reg.setRole(comprehensive.getProfession());
				memberDao.insert(reg);
				memberID = reg.getId();
			}
			
			act = new ActivityDTO();
			act.setObjId(memberID);
			act.setGangId(curGangID);
			act.setGangName(outline.getGang());
			
			act.setKillQ(comprehensive.getKill().shortValue());
			act.setAssistQ(comprehensive.getAssists().shortValue());
			act.setCarryQ(comprehensive.getRepair().shortValue());
			act.setHero(comprehensive.getAccumulate()==0?"0":"1");
			
			act.setKilledQ(auxiliary.getKilledCount().shortValue());
			act.setCorpseQ(auxiliary.getBaoshi().shortValue());
			act.setReliveQ(auxiliary.getRelive().shortValue());
			
			act.setTankKillQ(tank.getKill().shortValue());
			act.setDriver(tank.getDrive()==0?"0":"1");
			
			act.setBearM(getFromList(comprehensive.getName(),bear));
			act.setOutputM(getFromList(comprehensive.getName(),out));
			act.setCureM(getFromList(comprehensive.getName(),cure));
			
			act.setYwrId(ywrID);
			acts.add(act);
		}
		return acts;
	}
	/**
	 * @param name
	 * @param bear
	 * @return
	 */
	private Long getFromList(String name, List<OutputAmount> list) {
		for(OutputAmount a:list){
			if(a.getName().equals(name)){
				
				return a.getAmount();
			}
		}
		return null;
	}
	/**
	 * @param memberName
	 * @param members
	 * @return
	 */
	private Integer getMemberID(String memberName, List<MemberRegDTO> members) {
		for(MemberRegDTO reg:members){
			if(reg.getName().equals(memberName)){
				return reg.getId();
			}
		}
		return null;
	}
	/**
	 * @param curGangID
	 * @param gangs
	 * @return 
	 */
	private Integer getGangID(String curGangName, List<GangRegDTO> gangs) {
		for(GangRegDTO reg:gangs){
			
			if(reg.getName().equals(curGangName)){
				return reg.getId();
			}
		}
		return null;
	}
}
