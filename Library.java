
/**
 * Homework 1b
 * Cameron Chiaramonte, ccc7sej
 * 
 * Sources: TA OH, Big Java Book
 */
import java.util.*;

public class Library extends PhotoContainer {
	/**
	 * Holds the id number of the Library
	 */
	private final int id;

	/**
	 * A HashSet of albums in the Library
	 */
	private HashSet<Album> albums;

	/**
	 * A constuctor for the Library to instantiate the fields above
	 * 
	 * @param newName the new name of the library
	 * @param newId   the new id number of the library
	 */
	public Library(String newName, int newId) {
		super(newName);
		id = newId;
		albums = new HashSet<Album>();
	}

	/**
	 * A getter to return the id number
	 * 
	 * @return ID number
	 */
	public int getId() {
		return id;
	}

	/**
	 * A getter for the HashSet of albums
	 * 
	 * @return returns the HashSet of albums
	 */
	public HashSet<Album> getAlbums() {
		return albums;
	}

	/**
	 * This method deletes a photo from the photo feed and from the albums
	 * 
	 * @param p The photo object to remove
	 * @return returns true if the photo was removed and false if not
	 */
	public boolean removePhoto(Photo p) {
		for (Album a : albums) {
			for (Photo b : photos) {
				if (a.hasPhoto(b)) {
					a.removePhoto(b);
					photos.remove(b);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Overrides the equals method with Object o to check the equality of the ID
	 * numbers in the library and returns true if there is equality and false if
	 * there is not
	 */
	public boolean equals(Object o) {
		if (o instanceof Library) {
			Library p = (Library) o;
			if (this.id == p.id) {
				return true;
			}

		}
		return false;
	}

	/**
	 * Overrides the toString method to print out the name and id of the library and
	 * the list of photos as well as the set of albums
	 */
	public String toString() {
		String values = name + " " + id + " " + photos + " Albums: " + albums;
		return values;
	}

	/**
	 * Checks how many common pictures there are between libraries
	 * 
	 * @param a A library object
	 * @param b A library object
	 * @return returns the ArrayList of common photos between the two libraries
	 */
	public static ArrayList<Photo> commonPhotos(Library a, Library b) {
		ArrayList<Photo> commons = new ArrayList<Photo>(); // creating empty arraylist of photos to add the common ones

		ArrayList<Photo> picsA = a.getPhotos();// photos from lib a

		ArrayList<Photo> picsB = b.getPhotos();// photos from lib b

		for (Photo x : picsA) {// have to go through both a and b list of photos
			for (Photo y : picsB) {
				if (x.equals(y)) { // check equality
					commons.add(x); // add same ones
				}
			}
		}
		return commons;
	}

	/**
	 * Tests the similarity of two libraries in terms of common pictures
	 * 
	 * @param a A library object
	 * @param b A library object
	 * @return returns the double value of the number of common pictures between the
	 *         two libraries divided by the library with the lesser number of photos
	 */
	public static double similarity(Library a, Library b) {
		ArrayList<Photo> similar = commonPhotos(a, b);
		System.out.println(similar);

		if (a.numPhotos() == 0 || b.numPhotos() == 0) { // if no pics in either library
			return 0.0;
		}

		if (a.numPhotos() < b.numPhotos()) { // if similar pics divide total of them by lesser similar ones from b
			return (similar.size() / a.numPhotos());
		}

		return (similar.size() / b.numPhotos()); // same as above but with similar ones from a
	}

	/**
	 * Creates an album if it does not already exist
	 * 
	 * @param albumName the name to check that is not already existing
	 * @return returns true if the album was created and false if not
	 */
	public boolean createAlbum(String albumName) {
		Album newAlbum = new Album(albumName);
		albums.add(newAlbum);
		return true;

	}

	/**
	 * Deleted an album from the list of albums
	 * 
	 * @param albumName the name of the album to remove
	 * @return returns true if the album was removed and false if not
	 */
	public boolean removeAlbum(String albumName) {
		for (Album a : albums) {
			if (a.getName().equals(albumName)) {// Checks to see if removing the correct album name
				albums.remove(a);
				return true;
			}
		}
		return false;
	}

	/**
	 * This method will add a photo to the album with a certain album name if it is
	 * not already in the album
	 * 
	 * @param p         the photo object to add
	 * @param albumName the album name to add to
	 * @return returns true if the photo was added and false if not
	 */
	public boolean addPhotoToAlbum(Photo p, String albumName) {
		for (Album a : albums) {
			if (a.getName().equals(albumName)) { // making sure it is the right album
				for (Photo f : photos) {
					if (a.hasPhoto(f) != true) {
						a.addPhoto(f);
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * This method removes a photo from the album
	 * 
	 * @param p         the photo object to remove
	 * @param albumName the album to remove the photo from
	 * @return returns true if the photo was removed and false if it was not
	 */
	public boolean removePhotoFromAlbum(Photo p, String albumName) {
		for (Album a : albums) {
			if (a.getName() == albumName) { // making sure it is the right album
				a.removePhoto(p);
				return true;
			}
		}
		return false;
	}

	/**
	 * This method is a getter for the album but with a parameter of the name
	 * 
	 * @param albumName name of the album to get
	 * @return returns the album with said album name
	 */
	private Album getAlbumByName(String albumName) {
		for (Album a : albums) {
			if (a.getName() == albumName) { // checking to see if the name is correct
				return a;
			}
		}
		return null;
	}

	/*
	 * Main method tests
	 */
	public static void main(String[] args) {
		// creating photo objects to test
		Photo pic = new Photo("a", "1");
		Photo image = new Photo("hi", "aabb");
		Photo picture = new Photo("a", "12");
		Photo photograph = new Photo("hi", "aabb");

		// creating library objects to test
		Library l1 = new Library("Lib", 1234);
		Library l2 = new Library("Stack", 1212);
		Library l3 = new Library("Libr", 111);

		// adding a bunch of photos to varous libraries
		l1.addPhoto(pic);
		l1.addPhoto(image);
		l1.addPhoto(image);
		l1.addPhoto(image);
		l1.addPhoto(photograph);
		l1.addPhoto(picture);
		l1.addPhoto(photograph);
		l2.addPhoto(pic);
		l3.addPhoto(pic);

		// testing the has photo method
		l1.hasPhoto(pic);
		l1.hasPhoto(picture);

		// testing to see if the libraries will delete photos
		l1.removePhoto(image);
		l1.removePhoto(photograph);

		// testing the size of the photos
		System.out.println(l1.numPhotos());
		System.out.println(l2.numPhotos());

		// checking equality of two libraries
		System.out.println(l1.equals(l2));
		System.out.println(l2.equals(l3));

		// changing the name of the libraries
		l1.setName("hello");
		l3.setName("hi");

		// printing out the contents of libraries with toString
		System.out.println(l1.toString());
		System.out.println(l2.toString());

		// checking to see the amount of common photos in two libraries
		System.out.println(commonPhotos(l1, l3));
		System.out.println(commonPhotos(l2, l3));

		// checking the similarity of two libraries
		System.out.println(similarity(l1, l3));
		System.out.println(similarity(l2, l1));

	}
}
