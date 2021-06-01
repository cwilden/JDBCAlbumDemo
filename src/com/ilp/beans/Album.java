package com.ilp.beans;

public class Album {

	private Integer id;
	private String name;
	private String bandOrArtist;
	private String genre;
	private String yearReleased;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBandOrArtist() {
		return bandOrArtist;
	}
	public void setBandOrArtist(String bandOrArtist) {
		this.bandOrArtist = bandOrArtist;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getYearReleased() {
		return yearReleased;
	}
	public void setYearReleased(String yearReleased) {
		this.yearReleased = yearReleased;
	}
	
	@Override
	public String toString() {
		return "Album " + id + ": \"" + name + "\" by " + bandOrArtist + " (" + 
				yearReleased + "), genre: " + genre;
	}
	
	
}
