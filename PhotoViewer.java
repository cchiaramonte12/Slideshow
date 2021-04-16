
/**
 * Homework 4
 * Cameron Chiaramonte, ccc7sej
 * 
 * Sources: TA OH, Big Java Book, Lab Slides
 */

//importing all necessary imports to use GUI
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;

public class PhotoViewer extends JFrame {

	private PhotoContainer imageLibrary;

	//Creating variables for all necessary GUI components
	private JButton next = new JButton("Next"); //next button
	private JButton previous = new JButton("Previous"); //previous button
	private FlowLayout layout = new FlowLayout(); //flow layour for the radio buttons
	private JRadioButton button1 = new JRadioButton("1"); //radio buttons 1 through 5
	private JRadioButton button2 = new JRadioButton("2");
	private JRadioButton button3 = new JRadioButton("3");
	private JRadioButton button4 = new JRadioButton("4");
	private JRadioButton button5 = new JRadioButton("5");
	private ButtonGroup bgroup = new ButtonGroup(); //button group for the radio buttons
	private int imageCounter = 0; //index counter for the pictures
	private JLabel label = new JLabel(); // label to use with pictures as the icon
	private BufferedImage b1, b2, b3, b4, b5; // the 5 images 
	public Photo p1 = new Photo("images/image1.jpg", "An Eye", "2019-02-02", 5); //the 5 images as photos with ratings
	public Photo p2 = new Photo("images/image2.jpg", "Funny Boxing Match", "2019-02-03", 4);
	public Photo p3 = new Photo("images/image3.jpg", "The Looney Tunes", "2019-02-04", 5);
	public Photo p4 = new Photo("images/image4.jpg", "Some Ducks", "2019-02-05", 2);
	public Photo p5 = new Photo("images/image5.jpg", "Johnny Bravo in the Corner", "2019-02-06", 3);

	/**
	 * setter for the image library which will set as the newImageLibrary
	 * @param newImageLibrary a PhotoContainer which will be set as the new image library
	 */
	public void setImageLibrary(PhotoContainer newImageLibrary) {
		imageLibrary = newImageLibrary;
	}

	/**
	 * a getter for the imageLibrary
	 * @return returns the PhotoContainer imageLibrary
	 */
	public PhotoContainer getImageLibrary() {
		return imageLibrary;
	}

	/**
	 * this function is where all of the components are added to the GUI frame 
	 * @param pane is the Container for the GUI
	 */
	public void addComponentsToPane(Container pane) {
		//creating 5 panels for the 5 components
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		JPanel panel5 = new JPanel();

		//setting the panels in their respective locations on the frame
		pane.add(panel1, BorderLayout.NORTH);
		pane.add(panel2, BorderLayout.SOUTH);
		pane.add(panel3, BorderLayout.EAST);
		pane.add(panel4, BorderLayout.WEST);
		pane.add(panel5, BorderLayout.CENTER);

		//adding the next and previous buttons to the right and left sides of the screen, respectively
		panel3.add(next);
		panel4.add(previous);
		
		//simply setting the caption header with filename and caption
		JLabel caption = new JLabel(p1.getFilename() + "  " + "|" + "  " + "\"" + p1.getCaption() + "\"");
		panel1.add(caption);

		//this sets the bottom panel, where the radio buttons will be, to have a flowlayout
		panel2.setLayout(layout);

		//this adds all of the radio buttons to a button group so that they can only be 
		//selected one at a time
		bgroup.add(button1);
		bgroup.add(button2);
		bgroup.add(button3);
		bgroup.add(button4);
		bgroup.add(button5);

		//adding all of the radio buttons to the second panel on the bottom of the frame
		panel2.add(button1);
		panel2.add(button2);
		panel2.add(button3);
		panel2.add(button4);
		panel2.add(button5);

		//making each image location into a File object so that it can be read as a BufferedImage
		File imageFile = new File("images/image1.jpg");
		File imageFile2 = new File("images/image2.jpg");
		File imageFile3 = new File("images/image3.jpg");
		File imageFile4 = new File("images/image4.jpg");
		File imageFile5 = new File("images/image5.jpg");

		//a try catch block that makes the 5 BufferedImages by reading the Files created above
		//and the catch block catches a broad Exception e and will give the Error message
		try {
			b1 = ImageIO.read(imageFile);
			b2 = ImageIO.read(imageFile2);
			b3 = ImageIO.read(imageFile3);
			b4 = ImageIO.read(imageFile4);
			b5 = ImageIO.read(imageFile5);
		} catch (Exception e) {
			label.setText("Error loading image");
		}
		
		//the following 5 lines create icons with the 5 images and scales them
		ImageIcon thisIcon1 = new ImageIcon(b1.getScaledInstance(1024, 768, Image.SCALE_DEFAULT));

		ImageIcon thisIcon2 = new ImageIcon(b2.getScaledInstance(1024, 768, Image.SCALE_DEFAULT));

		ImageIcon thisIcon3 = new ImageIcon(b3.getScaledInstance(1024, 768, Image.SCALE_DEFAULT));

		ImageIcon thisIcon4 = new ImageIcon(b4.getScaledInstance(1024, 768, Image.SCALE_DEFAULT));

		ImageIcon thisIcon5 = new ImageIcon(b5.getScaledInstance(1024, 768, Image.SCALE_DEFAULT));

		//this creates an Array of the 5 icons
		ImageIcon[] pics = { thisIcon1, thisIcon2, thisIcon3, thisIcon4, thisIcon5 };

		//this is an array of the 5 photos as Photo ovjects
		Photo[] picList = { p1, p2, p3, p4, p5 };

		//setting the location of the label
		label.setLocation(100, 100);
		
		//this sets the first image to come up by making it the icon of the label
		label.setIcon(pics[imageCounter]);
		
		//adding the label to the center panel
		panel5.add(label);
		
		//this button listener is for radio button1.  It is added to button1 so that 
		//when button1 is selected in the GUI it will use setRating() from the photo
		//class to change the rating to 1 on the picture
		class radioButton1Listener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				picList[imageCounter].setRating(1);
			}
		}
		button1.addActionListener(new radioButton1Listener());
		
		//this button listener is for radio button2.  It is added to button2 so that 
		//when button2 is selected in the GUI it will use setRating() from the photo
		//class to change the rating to 2 on the picture
		class radioButton2Listener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
					picList[imageCounter].setRating(2);
			}
		}
		button2.addActionListener(new radioButton2Listener());
		
		//this button listener is for radio button3.  It is added to button3 so that 
		//when button3 is selected in the GUI it will use setRating() from the photo
		//class to change the rating to 3 on the picture
		class radioButton3Listener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
					picList[imageCounter].setRating(3);
			}
		}
		button3.addActionListener(new radioButton3Listener());
		
		//this button listener is for radio button4.  It is added to button4 so that 
		//when button4 is selected in the GUI it will use setRating() from the photo
		//class to change the rating to 4 on the picture
		class radioButton4Listener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
					picList[imageCounter].setRating(4);
			}
		}
		button4.addActionListener(new radioButton4Listener());
		
		//this button listener is for radio button5.  It is added to button5 so that 
		//when button5 is selected in the GUI it will use setRating() from the photo
		//class to change the rating to 5 on the picture
		class radioButton5Listener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
					picList[imageCounter].setRating(5);
			}
		}
		button5.addActionListener(new radioButton5Listener());

		//this sets the rating of the first image to be its given rating
		if (picList[imageCounter].getRating() == 5) {
			button5.setSelected(true);
		} else if (picList[imageCounter].getRating() == 4) {
			button4.setSelected(true);
		} else if (picList[imageCounter].getRating() == 3) {
			button3.setSelected(true);
		} else if (picList[imageCounter].getRating() == 2) {
			button2.setSelected(true);
		} else {
			button1.setSelected(true);
		}
		

		//this is a button listener for the next button that, when clicked, moves to the next image
		//by incrementing the counter by one and then changing the rating by repeating the code above
		//and then adds the listener to the next button below
		class nextButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if (imageCounter == pics.length - 1) {
					imageCounter = 0;
				} else {
					imageCounter++;
				}
				label.setIcon(pics[imageCounter]);
				if (picList[imageCounter].getRating() == 5) {
					button5.setSelected(true);
				} else if (picList[imageCounter].getRating() == 4) {
					button4.setSelected(true);
				} else if (picList[imageCounter].getRating() == 3) {
					button3.setSelected(true);
				} else if (picList[imageCounter].getRating() == 2) {
					button2.setSelected(true);
				} else {
					button1.setSelected(true);
				}
				caption.setText(picList[imageCounter].getFilename() + "  " + "|" + "  " + "\""
						+ picList[imageCounter].getCaption() + "\"");
			}
		}
		next.addActionListener(new nextButtonListener());

		//this listener does the same as above but going backwards with the previous button
		//where the next button would go to the first image when at the end of the list 
		//this listener tells the image to go to the last image when at the first item in the list
		//this will also update the rating with the same code as above and then
		//add the listener to the previous button
		class previousButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if (imageCounter == 0) {
					imageCounter = pics.length - 1;
				} else {
					imageCounter--;
				}
				label.setIcon(pics[imageCounter]);
				if (picList[imageCounter].getRating() == 5) {
					button5.setSelected(true);
				} else if (picList[imageCounter].getRating() == 4) {
					button4.setSelected(true);
				} else if (picList[imageCounter].getRating() == 3) {
					button3.setSelected(true);
				} else if (picList[imageCounter].getRating() == 2) {
					button2.setSelected(true);
				} else {
					button1.setSelected(true);
				}
				caption.setText(picList[imageCounter].getFilename() + "  " + "|" + "  " + "\""
						+ picList[imageCounter].getCaption() + "\"");
			}
		}
		previous.addActionListener(new previousButtonListener());

		//sets the components to be visible 
		setVisible(true);
	}

	/**
	 * this method creates the GUI titled myViewer and makes it visible
	 */
	private static void createAndShowGUI() {
		//creating the GUI
		PhotoViewer myViewer = new PhotoViewer();

		//setting the title
		myViewer.setTitle("Cameron Chiaramonte (ccc7sej)");

		//making sure it will close when the x is clicked
		myViewer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//calling the method to add the components to the pane and then making it visible
		myViewer.addComponentsToPane(myViewer.getContentPane());
		myViewer.pack();
		myViewer.setVisible(true);
	}

	/**
	 * in the main method I simply call the above method and run the Swing GUI 
	 */
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}
