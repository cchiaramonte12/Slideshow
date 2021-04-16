/**
 * Homework 3
 * Cameron Chiaramonte, ccc7sej
 * 
 * Sources: TA OH, Big Java Book
 */
import java.util.*;

public abstract class PhotoContainer {
	// fields
	protected String name;

	protected ArrayList<Photo> photos;

	// constructor
	public PhotoContainer(String newName) {
		name = newName;
		photos = new ArrayList<Photo>();
	}

	/**
	 * a getter for the name of the album
	 * @return returns the name of the album
	 */
	public String getName() {
		return name;
	}

	/**
	 * a getter for the ArrayList of photos
	 * @return returns the list of photos
	 */
	public ArrayList<Photo> getPhotos() {
		return photos;
	}

	/**
	 * a mutator for the name of the album
	 * @param newName the new name that the album will be upated to have
	 */
	public void setName(String newName) {
		name = newName;

	}

	/**
	 * this method adds a photo to the album if it does not already have it
	 * @param p the photo object that is being added
	 * @return returns true if the photo was added and false if it was not
	 */
	// HAVE TO REWRITE THIS FOR ARRAYLIST NOT HASHSET
	public boolean addPhoto(Photo p) {
		if (p == null) {
			return false;
		}

		if (!(photos.contains(p))) {// Making sure the photo set does not already have p
			photos.add(p);
			return true;
		}
		return false;
	}

	/**
	 * a method to check if the album contains a photo
	 * @param p the photo object in question of being in the album
	 * @return returns true if the photo is in the album and false if not
	 */
	public boolean hasPhoto(Photo p) {
		if (photos.contains(p)) {
			return true;
		}
		return false;
	}

	/**
	 * a method to remove a photo from the album
	 * @param p the photo object that is being removed
	 * @return returns true if it was removed and false if not
	 */
	public boolean removePhoto(Photo p) {
		if (photos.contains(p)) { // making sure the photo is even in the album
			photos.remove(p);
			return true;
		}
		return false;
	}

	/**
	 * a method to check the number of photos in the set photos
	 * @return returns the number of photos in photos
	 */
	public int numPhotos() {
		return photos.size();
	}

	/**
	 * an overriding of the equals method using object o to check the equality
	 * of the names of albums which returns true if they are equal and false if not
	 */
	public boolean equals(Object o) {
		if (o instanceof Album) {
			PhotoContainer a = (PhotoContainer) o;
			if (this.name.equals(a.getName())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * an overriding of the toString method to return the name and list of photos
	 */
	public String toString() {
		ArrayList<String> files = new ArrayList<String>();
		String values = name + "\n";
		for (Photo p : photos) {
			files.add(p.getFilename());
			values += files;
		}
		return values;
	}

	/**
	 * an overriding of the hashCode method to return the hashcode of the name of the album
	 */
	public int hashCode() {
		return this.name.hashCode();
	}
	
	/**
	 * Checks the photos to see their ratings and adds them to a list if their
	 * rating is greater than 1 and less than 5 and if their rating is greater than
	 * or equal to the parameter rating
	 * 
	 * @param rating the rating inputted that the photo must be equal to or greater
	 *               than
	 * @return returns the list of photos added with the valid rating
	 */
	public ArrayList<Photo> getPhotos(int rating) {
		ArrayList<Photo> goodPics = new ArrayList<Photo>(); // ArrayList for the photos with correct ratings

		if (rating < 1 || rating > 5) {
			return null;
		}

		for (Photo p : photos) {
			if (p.getRating() >= rating) { // Makes sure rating is greater than or equal to parameter
				goodPics.add(p);
			}
		}
		return goodPics;
	}
	
	/**
	 * Gets the photos from a given year into an ArrayList
	 * 
	 * @param year The year that the photos are required to be from
	 * @return returns an ArrayList of photos from the given year
	 */
	public ArrayList<Photo> getPhotosInYear(int year) {
		ArrayList<Photo> goodYear = new ArrayList<Photo>();

		if (year < 0000 || year > 9999) { // Checks to see if the year is valid
			return null;
		}

		for (Photo p : photos) {
			if (DateLibrary.getYear(p.getDateTaken()) == year) { // Makes sure the year is correct
				goodYear.add(p);
			}
		}
		return goodYear;
	}
	
	/**
	 * Gets the photos from a given year and month into an ArrayList]
	 * 
	 * @param month The month the picture needs to be from
	 * @param year  The year the picture needs to be from
	 * @return returns the list of photos from the correct month and year
	 */
	public ArrayList<Photo> getPhotosInMonth(int month, int year) {
		ArrayList<Photo> goodMonth = new ArrayList<Photo>();

		if (year < 0 || year > 9999 || month < 1 || month > 12) { // Checks validity of month and year
			return null;
		}
		for (Photo p : photos) {
			if (DateLibrary.getMonth(p.getDateTaken()) == month && DateLibrary.getYear(p.getDateTaken()) == year) { 
				//Makes sure the month and year are the correct ones
				goodMonth.add(p);
			}
		}

		return goodMonth;
	}
	
	/**
	 * Gets an ArrayList of photos between two dates
	 * 
	 * @param beginDate The start date of the range
	 * @param endDate   The end date of the range
	 * @return returns an ArrayList of photos between begin and end date
	 */
	public ArrayList<Photo> getPhotosBetween(String beginDate, String endDate) {
		ArrayList<Photo> betweenPics = new ArrayList<Photo>();

		if (DateLibrary.compare(beginDate, endDate) > 0) { // Makes sure that they are not equal
			return null;
		}
		if (DateLibrary.isValidDateFormat(beginDate) != true || DateLibrary.isValidDateFormat(endDate) != true) {
			return null; // Checks the validity of the format of the two dates
		}
		for (Photo p : photos) {
			if (DateLibrary.compare(p.getDateTaken(), endDate) < 0
					&& DateLibrary.compare(p.getDateTaken(), beginDate) > 0 || p.getDateTaken().equals(beginDate)
					|| p.getDateTaken().equals(endDate)) // Makes sure that the photo is between the dates and not equal to them
				betweenPics.add(p);
		}
		return betweenPics;
	}

}
