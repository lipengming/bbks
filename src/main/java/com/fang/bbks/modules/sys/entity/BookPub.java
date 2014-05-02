package com.fang.bbks.modules.sys.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="t_bookpub")
@SuppressWarnings("serial")
public class BookPub extends BookDetail implements Serializable{

}
