/**
 * Homework 1a
 * Cameron Chiaramonte, ccc7sej
 * 
 * Sources: TA OH, Big Java Book
 */
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Photo implements Comparable<Photo> {
	/**
	 * holds the caption of the picture
	 */
	private final String caption;
	/**
	 * the fileName of the photo
	 */
	private final String fileName;
	/**
	 * the rating of the photo
	 */
	private int rating;
	/**
	 * the date that the photo was taken
	 */
	private final String dateTaken;

	/**
	 * Constructor that instantiates the fileName, caption, default rating, and default caption
	 * 
	 * @param name The new name of the file
	 * @param cap The new caption of the picture
	 */
	
	/**
	 * the image data of type BufferedImage
	 */
	protected BufferedImage imageData;
	
	public Photo(String name, String cap) {
		rating = 1;
		caption = cap;
		fileName = name;
		dateTaken = "1901-01-01";
	}
	/**
	 * Overriding constructor with more parameters to instantiate and set defuault values
	 * 
	 * @param newFileName The new File Name of the photo
	 * @param newCaption The new Caption of the photo
	 * @param newDateTaken The new date taken with a default value
	 * @param newRating The new rating of the photo with a default
	 */
	public Photo(String newFileName, String newCaption, String newDateTaken, int newRating) {
		caption = newCaption;
		fileName = newFileName;
		if (newRating >= 1 && newRating <= 5) { // Makes sure rating is between 1 and 5
			rating = newRating;
		}
		else {
			rating = 1;
		}
		
		if (DateLibrary.isValidDate(newDateTaken) == true) { // Makes sure the date is valid and then sets a defualt if not
			dateTaken = newDateTaken;
		}
		else {
			dateTaken = "1901-01-01";
		}
		
	}

	/**
	 * Getter to return the caption
	 * @return returns the caption
	 */
	public String getCaption() {
		return caption;
	}
	/**
	 *  Getter to return the filename
	 *  @return returns the file name
	 */
	public String getFilename() {
		return fileName;
	}
	/**
	 * Getter to return the rating
	 * @return returns the rating
	 */
	public int getRating() {
		return rating;
	}
	/**
	 * Getter to return the date the photo was taken
	 * @return returns the date the photo was taken
	 */
	public String getDateTaken() {
		return dateTaken;
	}
	
	/**
	 * a getter to return the imageData
	 * @return returns the BufferedImage imageData
	 */
	public BufferedImage getImageData() {
		return imageData;
	}
	
	/**
	 * a setter to set a new imageData
	 * @param newImageData the new BufferedImage
	 */
	public void setImageData(BufferedImage newImageData) {
		this.imageData = newImageData;
	}
	/**
	 * Setter that sets the new rating of the photo if its value is valid, between 1 and 5
	 * and not the same as the previous rating
	 * 
	 * @param newRating The inputted new rating for the photo
	 * @return returns true or false depending on if the new rating was set
	 */
	public boolean setRating(int newRating) {
		if (newRating != rating && newRating <= 5 && newRating >= 1) {
			rating = newRating;
			return true;
		}
		return false;
	}
	
	/**
	 * Overrides the equals method to check the equality of the caption and file name of the photo
	 * with the parameter Object o that is what checks instanceof and equality as the method 
	 * returns whether or not the objects are equal
	 */
	public boolean equals(Object o) {
		if (o instanceof Photo) {
			Photo p = (Photo) o;
			return fileName.equals(p.fileName) && caption.equals(p.caption);
		}
		return false;
	}
	/**
	 * Overrides the toString method to print out the caption and filename of the photo
	 */
	public String toString() {
		String values = caption + fileName;
		return values;
	}
	/**
	 * Overrides the hasCode method to return the hash code of the file name
	 */
	public int hashCode() {
		return fileName.hashCode();
	}
	
	/**
	 * this method takes in a filename and converts it to a buffered image
	 * 
	 * @param filename this is the filename that is taken in to convert
	 * @return returns true on success and false on failure
	 */
	public boolean loadImageData(String filename) {
		try {
			BufferedImage img = ImageIO.read(new File(filename));
			imageData = img;
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	/**
	 * main method tests
	 */
	public static void main(String[] args) {
		// Creating instances of Photo to test
		Photo pic = new Photo("photo", "hi");
		Photo image = new Photo("picture", "hello");
		Photo picture = new Photo("photograph", "What's up");
		
		//Testing to see if setRating works with various ratings
		System.out.println(pic.setRating(10));
		System.out.println(image.setRating(3));
		System.out.println(picture.setRating(4));

		//Testing the equals method to test equality
		System.out.println(pic.equals(image));
		System.out.println(picture.equals(pic));
		System.out.println(picture.equals(image));
		System.out.println(pic.equals(pic));
	}
	@Override
	public int compareTo(Photo p) {
		if (this.getDateTaken().equals(p.getDateTaken())) {
			return this.getCaption().compareTo(p.getCaption());
		}
		return this.getDateTaken().compareTo(p.getDateTaken());
	}

}
