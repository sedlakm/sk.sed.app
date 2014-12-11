package com.example.sk.sedlak.appka;

import java.io.Serializable;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(strict = false)
public class Enclosure implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1255944136725747658L;
	@Attribute(required = false)
	String type;
	@Attribute(required = false)
	String url;
}
