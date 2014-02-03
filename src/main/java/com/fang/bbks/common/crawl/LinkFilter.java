/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fang.bbks.common.crawl;

/**
 *
 * @author Lee
 */
public interface LinkFilter {
	public boolean accept(String url);
}