package com.t9.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.t9.system.common.SQLQuery;
import com.t9.system.service.SystemUserService;
import com.t9.system.util.StringUtil;
import com.t9.system.web.T9Exception;

@Repository
public class SystemConfigDaoImpl extends BaseDaoImpl implements SystemConfigDao{

	@Autowired
	private SystemUserService systemUserService;
	
	@Override
	public List<Map> querySystemConfigListByUser(String paramName, String userId, boolean paging) throws T9Exception {
		
		SQLQuery sqlQuery=new SQLQuery("SELECT * FROM TB_SYSTEM_CONFIG WHERE 1=1 ");
		sqlQuery.addLikeParam("NAME", paramName);
		
		//可见性控制 (1仅超级管理员可见、2有权限即可见)
		if(!StringUtils.isEmpty(userId)) {
			//非超级管理员，受权限控制
			if(!systemUserService.isSuperAdmin(userId)) {
				sqlQuery.addEquealParam("DISPLAY_ATTRIBUTE", "2");
			} else {
				//超级管理员，有权限看 1,2
			}
		} else {
			throw new T9Exception("systemConfig.SystemConfigDao.UserRequired");
		}
		
        sqlQuery.addSQL(" ORDER BY SORT ");
        
        List<Map> list = null;
        if(paging) {
    		list = this.queryPageSql(sqlQuery);
        } else {
    		list = this.querySql(sqlQuery);
        }
		return list;
	}

	/**通过code获取config对象
	 * @param code
	 * @return
	 */
	public List<Map> querySystemConfigByCodes(String... codes) {
		SQLQuery sqlQuery=new SQLQuery("SELECT NAME,VALUE,CODE FROM TB_SYSTEM_CONFIG WHERE 1=1 ");
		sqlQuery.addSQL("AND CODE in ("+StringUtil.getInStr(codes)+")");
		List<Map> list = this.querySql(sqlQuery);
		
		return list;
	}
}
