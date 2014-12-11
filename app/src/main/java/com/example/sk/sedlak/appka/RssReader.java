package com.example.sk.sedlak.appka;

import java.net.URL;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

public class RssReader {
	private Serializer serializer;
	
	public RssReader() {
		serializer = new Persister();
	}
	
	public Rss getRss(String urlString) throws RssException{
		try {						
			URL url = new URL(urlString);					
			return serializer.read(Rss.class, url.openStream());			
		} catch (Exception e) {
			throw new RssException(e);
		}
	}
}
