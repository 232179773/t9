package com.t9.system.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.t9.system.dao.SystemConfigDao;
import com.t9.system.entity.SystemConfig;
import com.t9.system.entity.SystemUser;
import com.t9.system.web.T9Result;
import com.t9.system.web.T9Exception;

@Service
@Transactional
public class SystemConfigServiceImpl implements SystemConfigService{

	@Autowired
	private SystemConfigDao systemConfigDao;
    
	@Autowired
	private SystemUserService systemUserService;

	public T9Result updateSystemConfig(String ID, String value) {
		T9Result result=new T9Result();
		try {
			SystemConfig config = querySystemConfigById(ID);
			if(config == null) {
				throw new T9Exception("systemConfig.SystemConfigService.systemConfigDoesNotExists", ID);
			}
			
			verifyEditRight(config);
			
		    validateValue(config.getDATATYPE(), config.getPARAM_OPTION(), value);
		    
		    
			if (SystemConfig.DATATYPE_BOOL.equals(config.getDATATYPE())) {
				if ("1".equals(value) || "true".equalsIgnoreCase(value)) {
					config.setVALUE("1");
				} else {
					config.setVALUE("0");
				}
			} else {
				config.setVALUE(value);
			}
		        
		
			systemConfigDao.update(config);
		}catch(T9Exception e) {
			result.setResult(false, e.getMessage());
		}
		
		return result;
	}
	
	@Override
	public List<Map> searchSystemCongfig(Map map, String userId) throws T9Exception {
//    	String paramName, PageInfo pageInfo, HttpServletRequest request
        String paramName = (String)map.get("paramName");

		return systemConfigDao.querySystemConfigListByUser(paramName, userId, true);
		
	}

	public SystemConfig querySystemConfigById(String id) {
		return (SystemConfig)systemConfigDao.get(id);
	}
	
	public List<Map> querySystemConfigByCodes(String... codes) {
		List<Map> list = this.systemConfigDao.querySystemConfigByCodes(codes);
		return list;
	}

	/**
	 * 检查系统参数是否可编辑
	 * @param config
	 * @throws T9Exception 
	 */
	private void verifyEditRight(SystemConfig config) throws T9Exception {
		SystemUser sysUser = systemUserService.getSessionCurrentUser();
		boolean isSuperAdmin = systemUserService.isSuperAdmin(sysUser.getID());
		
		//DISPLAY_ATTRIBUTE
		if(!isSuperAdmin) {
			if("1".equals(config.getDISPLAY_ATTRIBUTE())) {
				throw new T9Exception("systemConfig.SystemConfigService.noRight", config.getNAME());
			}
		}
		
		//EDIT_ATTRIBUTE
		if("3".equals(config.getEDIT_ATTRIBUTE())) {
			throw new T9Exception("systemConfig.SystemConfigService.editNotAllowed", config.getNAME());
			
		} else if ("1".equals(config.getEDIT_ATTRIBUTE())) {  //仅超级管理员可编辑
			if(!isSuperAdmin) {
				throw new T9Exception("systemConfig.SystemConfigService.noRight", config.getNAME());
			}
		} else if ("2".equals(config.getEDIT_ATTRIBUTE())) {  //有权限可编辑
		}
	}
	
  /**
    * 
    * @author 
    * @version 1.0 Nov 6, 2012
    * @param
    * @return
 * @throws T9Exception 
    * @功能描述 检查参数值是否合法
    * @修订历史：
     */
    private static void validateValue(String dataType, String paramOption, String value) throws T9Exception {

        if(StringUtils.isBlank(value)){
           throw new T9Exception("000001", "系统参数值");
        }
        
        if(SystemConfig.DATATYPE_BOOL.equals(dataType)){
            if(!Pattern.compile("^(1|0|true|false)$", Pattern.CASE_INSENSITIVE).matcher(value).matches()){
                throw new T9Exception("system.fieldType.typeMismatch", "布尔型:"+value);
            }
   
            
        } else if (SystemConfig.DATATYPE_INT.equals(dataType)){
            if(!Pattern.compile("^(-?(0|([1-9]\\d*)))$").matcher(value).matches()){
                throw new T9Exception("system.fieldType.typeMismatch", "整型:"+value);
            }

            //[0,9] [,9]
            if(!StringUtils.isBlank(paramOption)){
                if(!Pattern.compile("^(\\[|\\()\\d*\\,\\d*(\\)|\\])$").matcher(paramOption).find()){
                    throw new T9Exception("systemConfig.SystemConfigService.invalidParamOption",paramOption);
                }
                
                //最大最小值，边界是否包括
                Integer minInt = null;
                Integer maxInt = null;
                int indexOfCommas = paramOption.indexOf(",");
                String num1 = paramOption.substring(1, indexOfCommas);
                String num2 = paramOption.substring(indexOfCommas+1, paramOption.length()-1);
                if(num1.length() == 0 && num2.length() == 0) {
                    throw new T9Exception("systemConfig.SystemConfigService.invalidParamOption",paramOption);
                }
                
                if(num1 != null && num1.length() > 0) {
                    if(paramOption.startsWith("(")){
                        minInt = Integer.valueOf(num1) +1;
                    } else if (paramOption.startsWith("[")){
                        minInt = Integer.valueOf(num1);
                    } 
                }
                
                if(num2 != null && num2.length() > 0) {
                    if(paramOption.endsWith(")")){
                        maxInt = Integer.valueOf(num2)-1;
                    } else if (paramOption.endsWith("]")){
                        maxInt = Integer.valueOf(num2);
                    } 
                }
                if(maxInt!=null && minInt !=null){
                    if(minInt<=maxInt){
                    
                    }else{
                        
                        throw new T9Exception("systemConfig.SystemConfigService.invalidParamOption",paramOption);
                    }
                }
             
                if(minInt != null) {
                    if(Integer.valueOf(value) < minInt){
                        throw new T9Exception("system.invalidValue", value+"小于最小值:"+minInt);
                    }
                }
                if(maxInt != null) {
                    if(Integer.valueOf(value) > maxInt){
                        throw new T9Exception("system.invalidValue", value+"大于最小值:"+maxInt);
                    }
                }
                
            }
           
            
        } else if (SystemConfig.DATATYPE_NUMBER.equals(dataType)){
            if(!Pattern.compile("^(-?(0|([1-9]\\d*))(\\.\\d+)?)$").matcher(value).matches()){
                throw new T9Exception("system.fieldType.typeMismatch", "数字:"+value);
            }

            if(!StringUtils.isBlank(paramOption)){
//                "( \\[ | \\( )  ( (0|([1-9]\\d*) ) (\\.\\d+)?)+  \\,  ((0|([1-9]\\d*)) (\\.\\d+)?)+  (\\)|\\]) "
//              "( \\[ | \\( )   \\,  ((0|([1-9]\\d*)) (\\.\\d+)?)+  (\\)|\\]) "
//              "( \\[ | \\( )  ( (0|([1-9]\\d*) ) (\\.\\d+)?)+  \\,  (\\)|\\]) "

                
                StringBuffer regExp = new StringBuffer("^");
                regExp.append("(").append("(\\[|\\()((0|([1-9]\\d*))(\\.\\d+)?)+\\,((0|([1-9]\\d*))(\\.\\d+)?)+(\\)|\\])").append(")");
                regExp.append("|(").append("(\\[|\\()\\,((0|([1-9]\\d*))(\\.\\d+)?)+(\\)|\\])").append(")");
                regExp.append("|(").append("(\\[|\\()((0|([1-9]\\d*))(\\.\\d+)?)+\\,(\\)|\\])").append(")");
                regExp.append("$");
                if(!Pattern.compile(regExp.toString()).matcher(paramOption).find()){
                    throw new T9Exception("systemConfig.SystemConfigService.invalidParamOption",paramOption);
                }
                
              //最大最小值，边界是否包括
                Double minExclude = null;
                Double minInclude = null;
                Double maxExclude = null;
                Double maxInclude = null;
                int indexOfCommas = paramOption.indexOf(",");
                String num1 = paramOption.substring(1, indexOfCommas);
                if(num1 != null && num1.length() > 0) {
                    if(paramOption.startsWith("(")){
                        minExclude = Double.valueOf(num1);
                    } else if (paramOption.startsWith("[")){
                        minInclude = Double.valueOf(num1);
                    } 
                }
                String num2 = paramOption.substring(indexOfCommas+1, paramOption.length()-1);
                if(num2 != null && num2.length() > 0) {
                    if(paramOption.endsWith(")")){
                        maxExclude = Double.valueOf(num2);
                    } else if (paramOption.endsWith("]")){
                        maxInclude = Double.valueOf(num2);
                    } 
                }
                if(minExclude!=null && maxExclude !=null){
                    if(minExclude<maxExclude){
                      
                    }else{
                       
                        throw new T9Exception("systemConfig.SystemConfigService.invalidParamOption",paramOption); 
                    }
                }
                if(minExclude!=null && maxInclude !=null){
                    if(minExclude<maxInclude){
                       
                    }else{
                        
                        throw new T9Exception("systemConfig.SystemConfigService.invalidParamOption",paramOption); 
                    }
                }
                if(minInclude!=null && maxExclude !=null){
                    if(minInclude<maxExclude){
                      
                    }else{
                       
                        throw new T9Exception("systemConfig.SystemConfigService.invalidParamOption",paramOption); 
                    }
                }
                if(minInclude!=null && maxInclude !=null){
                    if(minInclude<=maxInclude){
                        
                    }else{
                      
                        throw new T9Exception("systemConfig.SystemConfigService.invalidParamOption",paramOption); 
                    }
                }
            
                if(minExclude != null) {
                    if(BigDecimal.valueOf(Double.valueOf(value)).compareTo(BigDecimal.valueOf(minExclude)) != 1){
                        throw new T9Exception("system.invalidValue",value+"小于最小值:"+minExclude);
                    }
                } else if (minInclude != null) {
                    if(BigDecimal.valueOf(Double.valueOf(value)).compareTo(BigDecimal.valueOf(minInclude)) == -1){
                        throw new T9Exception("system.invalidValue", value+"小于最小值:"+minInclude);
                    }
                }
                
                if(maxExclude != null) {
                    if(BigDecimal.valueOf(Double.valueOf(value)).compareTo(BigDecimal.valueOf(maxExclude)) != -1){ //-1
                        throw new T9Exception("system.invalidValue", value+"大于最大值:"+maxExclude);
                    }
                } else if (maxInclude != null) {
                    if(BigDecimal.valueOf(Double.valueOf(value)).compareTo(BigDecimal.valueOf(maxInclude)) == 1){//-1,0
                        throw new T9Exception("system.invalidValue", value+"大于最大值:"+maxInclude);
                    }
                }
            }
            
            
        } else if (SystemConfig.DATATYPE_DATE.equals(dataType)){
               //TODO
        } else if (SystemConfig.DATATYPE_STRING.equals(dataType)){
            if(!StringUtils.isBlank(paramOption)){
                Pattern p = Pattern.compile(paramOption);
                if(!p.matcher(value).matches()){
                    throw new T9Exception("system.invalidValue", value);
                }
            }
        }
        
    }
}
