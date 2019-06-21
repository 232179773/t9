package com.t9.p2p.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.t9.system.entity.BaseDomain;

/**
 * null
 * 
 * @author generate auto
 * 
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "${TABLE_CODE}")
public class ${CLASS_PATH} extends BaseDomain {
<#list columnList as col>
	private ${col.JAVA_TYPE} ${col.COL_CODE};//${col.COL_NAME} ${col.COL_COMMENT}
</#list>

<#list columnList as col>
	public ${col.JAVA_TYPE} get${col.COL_CODE}() {
		return ${col.COL_CODE};
	}
	public void set${col.COL_CODE}(${col.JAVA_TYPE} ${col.COL_CODE}) {
		this.${col.COL_CODE} = ${col.COL_CODE};
	}
</#list>
}