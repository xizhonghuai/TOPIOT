/**
 * 
 */
package com.common;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *@author xizhonghuai
 *@date 2018��7��10��
 *@readme 
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface DataModelFieldAnnotation {
	
	public  String mark() default "";
	public String dataType();

}
