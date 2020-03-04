package app;

import javafx.beans.property.SimpleStringProperty;

public class Song implements Comparable<Song> {
	
	SimpleStringProperty name;
	SimpleStringProperty artist;
	String album;
	int year;
	
	
	public Song(String name, String artist) {
		this.name = new SimpleStringProperty(name);
		this.artist = new SimpleStringProperty(artist);
		this.album = "";
	}


	@Override
	/**
	 * Compare name, then artist (case insensitive)
	 */
	public int compareTo(Song o) {
		int x = this.name.get().toLowerCase().compareTo(
				o.name.get().toLowerCase());
		if(x == 0)
			return this.artist.get().toLowerCase().compareTo(
					o.artist.get().toLowerCase());
		return x;
	}
	
	
	@Override
	public boolean equals(Object o) {
		Song s = (Song) o;
		if(this.name.get().toLowerCase().compareTo(
				s.name.get().toLowerCase()) != 0)
			return false;
		
		if(this.artist.get().toLowerCase().compareTo(
				s.artist.get().toLowerCase()) != 0)
			return false;
		return true;
	}
	
	
	public String toString() {
		return this.name.get().toString()+"_"+this.artist.get();
	}
	
	
	
	
	//getter & setter methods
	public void setName(String s) {
		name.set(s);
	}
	public void setArtist(String s) {
		artist.set(s);
	}

	
	public String getName() {
		return name.get();
	}
	public String getArtist() {
		return artist.get();
	}
	
}
