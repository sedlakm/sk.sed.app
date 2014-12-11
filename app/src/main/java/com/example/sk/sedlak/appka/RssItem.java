package com.example.sk.sedlak.appka;

import java.io.Serializable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(strict = false)
public class RssItem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7736328560237386587L;
	@Element 
	String title;
	@Element(required = false) 
	String description;
	@Element 
	String link;
	@Element(required = false)
	Enclosure enclosure;
	
	//private String imageLink;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getLink() {
		return link;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	public Enclosure getEnclosure() {
		return enclosure;
	}
	public void setEnclosure(Enclosure enclosure) {
		this.enclosure = enclosure;
	}
	@Override
	public String toString() {
		return title;
	}
	
	public boolean hasImageLink(){
			return enclosure != null && enclosure.url != null && (enclosure.type != null && enclosure.type.startsWith("image"));	
	}
	
	public String getImageUrl(){
		if(enclosure != null){
			return enclosure.url;
		} else {
			return null;
		}
	}
	
	
}
