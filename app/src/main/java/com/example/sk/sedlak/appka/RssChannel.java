package com.example.sk.sedlak.appka;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(strict = false)
public class RssChannel {
	
	@ElementList(entry = "item", inline = true)
	private List<RssItem> itemList;
	
	public List<RssItem> getItemList() {
		return itemList;
	}
	
	public void setItemList(List<RssItem> itemList) {
		this.itemList = itemList;
	}
}
