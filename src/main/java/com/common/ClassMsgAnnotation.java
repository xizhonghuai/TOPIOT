/**
 * 
 */
package com.common;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

 

/**
 *@author xizhonghuai
 *@date 2018Äê7ÔÂ11ÈÕ
 *@readme 
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ClassMsgAnnotation {
	
	public String mark() default "";
	public String version() default "V1.0";
	public String[] putStorageMethod() default {};
	public String dataModelClassName() default "";


}
