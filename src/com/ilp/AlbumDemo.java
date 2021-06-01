package com.ilp;

import java.util.ArrayList;

import com.ilp.beans.Album;
import com.ilp.dal.AlbumDao;

/*
Before rerunning this class, run the following SQL statements in SQL Developer:

DROP SEQUENCE ALBUM_ID_SEQ;
CREATE SEQUENCE ALBUM_ID_SEQ START WITH 7;

*/
public class AlbumDemo {

	private static AlbumDao dao = new AlbumDao();
	
	public static void main(String args[]) {
		
		System.out.println("Listing all albums");
		ArrayList<Album> albums = dao.findAll();
		displayAllAlbums(albums);

	}
	
	private static void displayAllAlbums(ArrayList<Album> albums) {
		for(Album album: albums)
		{
			System.out.println(album);
		}
	}
	
	private static void displayAllAlbumsByGenre(String genre) {
		ArrayList<Album> albums = dao.findAllByGenre(genre);
		
		System.out.println("\n"+genre+" albums");	
		displayAllAlbums(albums);
	}
	
	private static void displayAllAlbumsByYearReleased(String yearReleased) {
		ArrayList<Album> albums = dao.findAllByYearReleased(yearReleased);
		
		System.out.println("\nAlbums released in "+yearReleased+".");	
		displayAllAlbums(albums);
	}
	
}
