/**
 * 
 */
package com.common;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *@author xizhonghuai
 *@date 2018Äê7ÔÂ10ÈÕ
 *@readme 
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface DataModelFieldAnnotation {
	
	public  String mark() default "";
	public String dataType();

}
