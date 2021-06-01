package com.ilp.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.ilp.beans.Album;
import com.ilp.utilities.DbCon;

/*
 CREATE TABLE ALBUMS
(
	ALBUM_ID NUMBER(12,0) primary key, 
	ALBUM_NAME VARCHAR2(60 BYTE) NOT NULL, 
	BAND_OR_ARTIST VARCHAR2(60 BYTE) NOT NULL,
	GENRE VARCHAR2(20) NOT NULL,
	YEAR_RELEASED VARCHAR2(4) NOT NULL
);


CREATE SEQUENCE ALBUM_ID_SEQ START WITH 1;

CREATE OR REPLACE TRIGGER ALBUM_TR
BEFORE INSERT
ON ALBUMS REFERENCING NEW as New OLD as Old
FOR EACH ROW
WHEN (New.album_id is null)
BEGIN
	:new.album_id := ALBUM_ID_SEQ.nextval;
END;
/
ALTER TRIGGER ALBUME_TR ENABLE;

SET DEFINE OFF;
Insert into ALBUMS (ALBUM_NAME,BAND_OR_ARTIST, GENRE, YEAR_RELEASED) values ('Thriller','Michael Jackson','Pop', '1982');
Insert into ALBUMS (ALBUM_NAME,BAND_OR_ARTIST, GENRE, YEAR_RELEASED) values ('Led Zeppelin IV','Led Zeppelin','Rock','1971');
Insert into ALBUMS (ALBUM_NAME,BAND_OR_ARTIST, GENRE, YEAR_RELEASED) values ('Who''s Next','The Who','Rock','1971');
Insert into ALBUMS (ALBUM_NAME,BAND_OR_ARTIST, GENRE, YEAR_RELEASED) values ('Master of Reality','Black Sabbath','Heavy Metal','1971');
Insert into ALBUMS (ALBUM_NAME,BAND_OR_ARTIST, GENRE, YEAR_RELEASED) values ('Duke','Genesis','Pop','1980');
Insert into ALBUMS (ALBUM_NAME,BAND_OR_ARTIST, GENRE, YEAR_RELEASED) values ('Rio','Duran Duran','Pop','1982');

commit;

 */
public class AlbumDao implements DaoInterface<Album> {

	public static final String TABLE = "albums";

	public ArrayList<Album> findAll() {
		ArrayList<Album> albums = new ArrayList<>();

		Connection con = DbCon.getConnection();

		try {
			String qry = "select * from " + TABLE;
			PreparedStatement st = con.prepareStatement(qry);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Album album = new Album();
				album.setId(rs.getInt(1));
				album.setName(rs.getString(2));
				album.setBandOrArtist(rs.getString(3));
				album.setGenre(rs.getString(4));
				album.setYearReleased(rs.getString(5));
				albums.add(album);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbCon.closeConnection();
		}

		return albums;
	}

	public Album findById(int id) {
		Connection con = DbCon.getConnection();
		Album album = new Album();

		try {
			String qry = "select * from " + TABLE + " where album_id = ?";
			PreparedStatement st = con.prepareStatement(qry);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				album.setId(rs.getInt(1));
				album.setName(rs.getString(2));
				album.setBandOrArtist(rs.getString(3));
				album.setGenre(rs.getString(4));
				album.setYearReleased(rs.getString(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbCon.closeConnection();
		}

		return album;
	}

	public void update(Album album) {

		Connection con = DbCon.getConnection();

		try {
			String qry = "update " + TABLE + " set album_name = ?, band_or_artist = ?, "+
					"genre = ?, year_released = ? where album_id = ?";
			PreparedStatement st = con.prepareStatement(qry);
			st.setString(1, album.getName());
			st.setString(2, album.getBandOrArtist());
			st.setString(3, album.getGenre());
			st.setString(4, album.getYearReleased());
			st.setInt(5, album.getId());

			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbCon.closeConnection();
		}
	}

	public Album save(Album album) {
		Connection con = DbCon.getConnection();

		try {
			String qry = "insert into " + TABLE + "( album_name, band_or_artist, "+
					"genre, year_released) values (?, ?, ?, ?)";
			PreparedStatement st = con.prepareStatement(qry);
			st.setString(1, album.getName());
			st.setString(2, album.getBandOrArtist());
			st.setString(3, album.getGenre());
			st.setString(4, album.getYearReleased());

			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbCon.closeConnection();
		}
		return album;
	}

	public void delete(int albumId) {
		Connection con = DbCon.getConnection();

		try {

			String qry = "delete from " + TABLE + " where album_id = ?";
			PreparedStatement ps = con.prepareStatement(qry);
			ps.setInt(1, albumId);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbCon.closeConnection();
		}
	}

	public ArrayList<Album> findAllByGenre(String genre) {
		ArrayList<Album> albums = new ArrayList<>();

		Connection con = DbCon.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement("select * from " + TABLE + " where genre=?");

			ps.setString(1, genre);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Album album = new Album();
				album.setId(rs.getInt(1));
				album.setName(rs.getString(2));
				album.setBandOrArtist(rs.getString(3));
				album.setGenre(rs.getString(4));
				album.setYearReleased(rs.getString(5));
				albums.add(album);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbCon.closeConnection();
		}

		return albums;
	}
	
	public ArrayList<Album> findAllByYearReleased(String yearReleased) {
		ArrayList<Album> albums = new ArrayList<>();

		Connection con = DbCon.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement("select * from " + TABLE + " where year_released=?");

			ps.setString(1, yearReleased);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Album album = new Album();
				album.setId(rs.getInt(1));
				album.setName(rs.getString(2));
				album.setBandOrArtist(rs.getString(3));
				album.setGenre(rs.getString(4));
				album.setYearReleased(rs.getString(5));
				albums.add(album);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbCon.closeConnection();
		}

		return albums;
	}
}
