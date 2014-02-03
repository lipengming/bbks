package com.fang.bbks.common.persistence.jdbc;

/**
 * @Intro descrption here
 * @author Lee
 * @Date 2013-8-13
 */
@SuppressWarnings("serial")
public class DaoException extends RuntimeException{
	public DaoException() {
		super();
	}

	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public DaoException(String message) {
		super(message);
	}

	public DaoException(Throwable cause) {
		super(cause);
	}
}
