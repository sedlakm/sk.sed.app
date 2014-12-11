package com.example.sk.sedlak.appka;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(strict = false)
public class Rss {
	@Element(required = false)
	private RssChannel channel;
	
	public RssChannel getChannel() {
		return channel;
	}
	
	public void setChannel(RssChannel channel) {
		this.channel = channel;
	}
}
