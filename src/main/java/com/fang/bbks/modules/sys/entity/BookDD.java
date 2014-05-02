package com.fang.bbks.modules.sys.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 图书明细----当当网站点
 * @author LPM
 * @Date 13-07-31
 */
@Entity
@Table(name="t_bookdd")
public class BookDD extends BookDetail implements Serializable{
}
