package com.t9.system.dao;

import java.util.List;
import java.util.Map;

import com.t9.system.web.T9Exception;

public interface RoleDao extends BaseDao{
  public List<Map> queryRoleList(Map<String,String>map,boolean pageInfo);

//  public List<Map> queryUsersWithRole(String roleId, boolean page);
//  public List<Map> queryUsersWithoutRoleByPage(String roleId, String userName, String nickName, boolean page);
  
  /**
   * parentIds 用“，”隔开
   * @param parentIds
   * @return
   */
  public List<Map> queryRoleListByParentId(String parentIds);
  
  public int deleteRole(String id) throws T9Exception;
  
  
}
