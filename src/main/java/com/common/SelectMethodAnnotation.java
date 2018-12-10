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
public @interface SelectMethodAnnotation {
	
	public String handler() default "";
 
}
