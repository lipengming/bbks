package com.fang.bbks.common.utils;

import java.util.regex.Pattern;

/**
 * @Intro 校验
 * @author Lee [shouli1990@gmail.com]
 * @Version V0.0.1
 * @Date 2013-12-3
 * @since 下午1:48:50
 */
public class Validate {
	/**
	 * 验证是否是数字
	 */
	public static final String NUMBER_PATTERN = "^[0-9]*$";

	/**
	 * 身份证号码正则表达式
	 */
	public static final String IDENTITYCARD_PATTERN = "\\d{15}|\\d{17}[\\dx]";

	/**
	 * 移动手机号码正则表达式
	 */
	public static final String MOBILENUM_PATTERN = "(134|135|136|137|138|139|158|159|150|151|152|157|188|187|147|182|183)\\d{8}";

	/**
	 * Email正则表达式
	 */
	public static final String EMAIL_PATTERN = "[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+";
	/**
	 * 正则表达式 验证数字是否连着升序，例如123456
	 */
	public static String ascPattern = "^(0(?=1|$)|1(?=2|$)|2(?=3|$)|3(?=4|$)|4(?=5|$)|5(?=6|$)|6(?=7|$)|7(?=8|$)|8(?=9|$)|9(?=0|$)|0(?=$))*$";
	/**
	 * 正则表达式 验证数字是否连着降序，例如654321
	 */
	public static String descPattern = "^(9(?=8|$)|8(?=7|$)|7(?=6|$)|6(?=5|$)|5(?=4|$)|4(?=3|$)|3(?=2|$)|2(?=1|$)|1(?=0|$)|0(?=$))*$";

	/**
	 * 正则表达式 验证数字是否连续相同数字，例如000000
	 */
	public static String samePattern = "^(9(?=9|$)|8(?=8|$)|7(?=7|$)|6(?=6|$)|5(?=5|$)|4(?=4|$)|3(?=3|$)|2(?=2|$)|1(?=1|$)|0(?=0|$))*$";

	/**
	 * 验证密码是否是简单密码
	 * 
	 * @param password
	 * @return
	 */
	public static boolean isLowPassword(String password) {
		boolean isPassword = false;
		if (Pattern.matches(ascPattern, password)
				|| Pattern.matches(descPattern, password)
				|| Pattern.matches(samePattern, password)) {
			isPassword = true;
		}
		return isPassword;
	}

	/**
	 * 判断身份证号码格式是否正确：正确返回true，否则返回false
	 * 
	 * @param identityNo
	 * @return
	 */
	public static boolean isIdentityNo(String identityNo) {
		boolean isNo = false;
		if (Pattern.matches(IDENTITYCARD_PATTERN, identityNo)) {
			isNo = true;
		}
		return isNo;
	}

	/**
	 * 验证Email格式是否正确
	 * 
	 * @param inputEmail
	 * @return
	 */
	public static boolean isEmail(String inputEmail) {
		boolean isChecked = false;
		if (Pattern.matches(EMAIL_PATTERN, inputEmail)) {
			isChecked = true;
		}
		return isChecked;
	}

	/**
	 * 验证是否是数字
	 * 
	 * @param inputStr
	 * @return
	 */
	public static boolean isNumber(String inputStr) {
		boolean isChecked = false;
		if (Pattern.matches(NUMBER_PATTERN, inputStr)) {
			isChecked = true;
		}
		return isChecked;
	}

	/**
	 * 验证是否是移动的手机号码
	 * 
	 * @param inputNo
	 * @return
	 */
	public static boolean isMobileNo(String inputNo) {
		boolean isChecked = false;
		if (Pattern.matches(MOBILENUM_PATTERN, inputNo)) {
			isChecked = true;
		}
		return isChecked;
	}

}
