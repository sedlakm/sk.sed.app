package com.example.sk.sedlak.appka;

public class RssException extends Exception {

	public RssException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

	public RssException(Throwable throwable) {
		super(throwable);
	}
	
}
