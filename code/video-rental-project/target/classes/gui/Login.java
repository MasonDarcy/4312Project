package gui;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;

import data_objects.Movie;
import data_objects.OrderItem;
import database_access.VideoRentalDA;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.EtchedBorder;
 
public class Login  {
    JPanel cards; //a panel that uses CardLayout
    final static String LOGINPANEL = "Card with login features";
    final static String TEXTPANEL = "Card with JTextField";
    final static String MOVIEVIEW = "Card with movie info and add cart";
    final static String ORDERVIEW = "Card with order information";
    private JTextField emailField;
    private JTextField passwordField;
    private VideoRentalDA dba = new VideoRentalDA(); 
    private int iterator = 0;
    private ArrayList<JButton> buttons = new ArrayList<JButton>();
    private ArrayList<Movie> horrorList = null;
    private JTextField searchField;
    private String email;
    private Double price = 0.0;
    private JTextField creditCardField;
    private boolean loyalty = false;
    private int tier = 0;
    private int quantity = 0;
    private int  videoID = 0;
    private int orderID = 0;
    private JTextField quantityField;
    
    public void addComponentToPane(Container pane) {
  
    	
        
        //Create the "cards".
        JPanel loginCard = new JPanel();
    
        JButton loginButton = new JButton("login");
        loginButton.setBounds(165, 144, 114, 26);
        loginCard.setLayout(null);
        loginCard.add(loginButton);
        
        final JPanel galleryCard = new JPanel();
        galleryCard.setBackground(Color.LIGHT_GRAY);
         
        /*Create the panel that contains the "cards".-------------------------*/
        cards = new JPanel(new CardLayout());
        /*--------------------------------------------------------------------*/
        
        
        cards.add(loginCard, LOGINPANEL);
        
        emailField = new JTextField();
        emailField.setBounds(165, 80, 114, 20);
        loginCard.add(emailField);
        emailField.setColumns(10);
        
        JLabel emailLabel = new JLabel("Enter email");
        emailLabel.setBounds(89, 81, 76, 19);
        loginCard.add(emailLabel);
        
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(89, 112, 66, 20);
        loginCard.add(passwordLabel);
        
        passwordField = new JTextField();
        passwordField.setBounds(165, 112, 114, 20);
        loginCard.add(passwordField);
        passwordField.setColumns(10);
        
        JLabel titleLabel = new JLabel("Video Rental Service");
        titleLabel.setBounds(85, 25, 160, 20);
        loginCard.add(titleLabel);
        
        
        
        cards.add(galleryCard, TEXTPANEL);
        galleryCard.setLayout(null);
        
        JLabel galleryLabel = new JLabel("Gallery");
        galleryLabel.setBounds(12, 12, 64, 16);
        galleryCard.add(galleryLabel);
        
        JLabel horrorLabel = new JLabel("Horror");
        horrorLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        horrorLabel.setBounds(41, 79, 83, 16);
        galleryCard.add(horrorLabel);
        
        JLabel lblScifi = new JLabel("Sci-fi");
        lblScifi.setFont(new Font("Dialog", Font.BOLD, 18));
        lblScifi.setBounds(246, 79, 55, 16);
        galleryCard.add(lblScifi);
        
        JLabel lblComedy = new JLabel("Comedy");
        lblComedy.setFont(new Font("Dialog", Font.BOLD, 18));
        lblComedy.setBounds(450, 79, 90, 16);
        galleryCard.add(lblComedy);
        
        JScrollPane horrorScroll = new JScrollPane();
        horrorScroll.setBounds(40, 107, 148, 279);
        galleryCard.add(horrorScroll);
        
        final JPanel horrorPanel = new JPanel();
        horrorPanel.setBackground(SystemColor.inactiveCaption);
        horrorPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        horrorScroll.setViewportView(horrorPanel);
        horrorPanel.setLayout(new BoxLayout(horrorPanel, BoxLayout.Y_AXIS));
        
        JScrollPane scifiScroll = new JScrollPane();
        scifiScroll.setBounds(246, 105, 148, 281);
        galleryCard.add(scifiScroll);
        
        JPanel scifiPanel = new JPanel();
        scifiPanel.setBackground(SystemColor.inactiveCaption);
        scifiPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        scifiScroll.setViewportView(scifiPanel);
        
        JScrollPane comedyScroll = new JScrollPane();
        comedyScroll.setBounds(450, 107, 148, 279);
        galleryCard.add(comedyScroll);
        
        JPanel comedyPanel = new JPanel();
        comedyPanel.setBackground(SystemColor.inactiveCaption);
        comedyPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        comedyScroll.setViewportView(comedyPanel);
        
        JScrollPane searchScroll = new JScrollPane();
        searchScroll.setBounds(657, 107, 184, 279);
        galleryCard.add(searchScroll);
        
        final JPanel searchPane = new JPanel();
        searchPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        searchPane.setBackground(SystemColor.inactiveCaption);
        searchScroll.setViewportView(searchPane);
        searchPane.setLayout(new BoxLayout(searchPane, BoxLayout.Y_AXIS));
        
        searchField = new JTextField();
        searchField.setBounds(657, 79, 100, 20);
        galleryCard.add(searchField);
        searchField.setColumns(10);
        
        JButton searchButton = new JButton("Search");

        searchButton.setBounds(758, 76, 83, 26);
        galleryCard.add(searchButton);
        
        JButton orderButton = new JButton("Orders");
    
        orderButton.setBounds(41, 437, 98, 26);
        galleryCard.add(orderButton);
        
      
        
        
       // pane.add(comboBoxPane, BorderLayout.PAGE_START);
        pane.add(cards, BorderLayout.CENTER);
        
        final JPanel movieCard = new JPanel();
        cards.add(movieCard, MOVIEVIEW);
        movieCard.setLayout(null);
        
        JButton btnBack = new JButton("Back");
        btnBack.setBounds(795, 12, 98, 26);
        movieCard.add(btnBack);
     
    

        
        
        JPanel valueFrame = new JPanel();
        valueFrame.setBounds(138, 101, 405, 300);
        movieCard.add(valueFrame);
        valueFrame.setLayout(new BoxLayout(valueFrame, BoxLayout.Y_AXIS));
        
        final JLabel titleHolder = new JLabel("");
        titleHolder.setFont(new Font("Dialog", Font.PLAIN, 18));
        valueFrame.add(titleHolder);
        
        final JLabel yearHolder = new JLabel("");
        yearHolder.setFont(new Font("Dialog", Font.PLAIN, 18));
        valueFrame.add(yearHolder);
        
        final JLabel categoryHolder = new JLabel("");
        categoryHolder.setFont(new Font("Dialog", Font.PLAIN, 18));
        valueFrame.add(categoryHolder);
        
        final JLabel introductionHolder = new JLabel("");
        introductionHolder.setFont(new Font("Dialog", Font.PLAIN, 18));
        valueFrame.add(introductionHolder);
        
        final JLabel directorHolder = new JLabel("");
        directorHolder.setFont(new Font("Dialog", Font.PLAIN, 18));
        valueFrame.add(directorHolder);
        
        final JLabel producerHolder = new JLabel("");
        producerHolder.setFont(new Font("Dialog", Font.PLAIN, 18));
        valueFrame.add(producerHolder);
        
        final JLabel stockHolder = new JLabel("");
        stockHolder.setFont(new Font("Dialog", Font.PLAIN, 18));
        valueFrame.add(stockHolder);
        
        JPanel attributePanel = new JPanel();
        attributePanel.setBounds(22, 101, 110, 300);
        movieCard.add(attributePanel);
        attributePanel.setLayout(new BoxLayout(attributePanel, BoxLayout.Y_AXIS));
        
        JLabel titleMovieLabel = new JLabel("Title");
        titleMovieLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        titleMovieLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        attributePanel.add(titleMovieLabel);
        
        JLabel yearLabel = new JLabel("Year");
        yearLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        attributePanel.add(yearLabel);
        
        JLabel categoryLabel = new JLabel("Category");
        categoryLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        attributePanel.add(categoryLabel);
        
        JLabel introductionLabel = new JLabel("Introduction");
        introductionLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        attributePanel.add(introductionLabel);
        
        JLabel directorLabel = new JLabel("Directors");
        directorLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        attributePanel.add(directorLabel);
        
        JLabel producerLabel = new JLabel("Producers");
        producerLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        attributePanel.add(producerLabel);
        
        JLabel stockLabel = new JLabel("Stock");
        stockLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        attributePanel.add(stockLabel);
        
        JPanel orderPanel = new JPanel();
        orderPanel.setBounds(583, 101, 130, 157);
        movieCard.add(orderPanel);
        orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.Y_AXIS));
        
        JButton addToOrderButton = new JButton("Add To Order");
        orderPanel.add(addToOrderButton);
        
        JRadioButton tierOneRadioButton = new JRadioButton("1-10 Days");
        orderPanel.add(tierOneRadioButton);
        
        JRadioButton tierTwoRadioButton = new JRadioButton("11-20 Days");
        orderPanel.add(tierTwoRadioButton);
        
        JRadioButton tierThreeRadioButton = new JRadioButton("21-30 Days");
        orderPanel.add(tierThreeRadioButton);
        
        ButtonGroup periodGroup = new ButtonGroup();
        
        periodGroup.add(tierOneRadioButton);
        periodGroup.add(tierTwoRadioButton);
        periodGroup.add(tierThreeRadioButton);
        
        JLabel quantityLabel = new JLabel("Quantity");
        orderPanel.add(quantityLabel);
        
        quantityField = new JTextField();
        orderPanel.add(quantityField);
        quantityField.setColumns(10);
        
        final JLabel addedStatusLabel = new JLabel("");
        addedStatusLabel.setBounds(583, 282, 130, 16);
        movieCard.add(addedStatusLabel);

        
        JPanel orderCard = new JPanel();
        cards.add(orderCard, ORDERVIEW);
        orderCard.setLayout(null);
        
        JScrollPane orderItemScroll = new JScrollPane();
        orderItemScroll.setBounds(95, 98, 420, 135);
        orderCard.add(orderItemScroll);
        
        final JPanel orderItemPanel = new JPanel();
        orderItemScroll.setColumnHeaderView(orderItemPanel);
        orderItemPanel.setLayout(new BoxLayout(orderItemPanel, BoxLayout.Y_AXIS));
        
        JLabel orderItemLabel = new JLabel("Current Order Items");
        orderItemLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        orderItemLabel.setBounds(95, 29, 186, 16);
        orderCard.add(orderItemLabel);
        
        JLabel orderInformationLabel = new JLabel("Current Order Information");
        orderInformationLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        orderInformationLabel.setBounds(626, 98, 208, 16);
        orderCard.add(orderInformationLabel);
        
        JButton payButton = new JButton("Pay / Order");
      
        payButton.setBounds(626, 240, 98, 26);
        orderCard.add(payButton);
        
        JLabel useLoyaltyLabel = new JLabel("Use Loyalty");
        useLoyaltyLabel.setBounds(626, 184, 88, 16);
        orderCard.add(useLoyaltyLabel);
        
        JButton backFromOrderButton = new JButton("Back");
      
        
        backFromOrderButton.setBounds(795, 12, 98, 26);
        orderCard.add(backFromOrderButton);
        
        JScrollPane orderScroll = new JScrollPane();
        orderScroll.setBounds(95, 294, 420, 151);
        orderCard.add(orderScroll);
        
        JPanel customerOrderPanel = new JPanel();
        orderScroll.setViewportView(customerOrderPanel);
        customerOrderPanel.setLayout(new BoxLayout(customerOrderPanel, BoxLayout.Y_AXIS));
        
        JLabel columnHeaderLabel = new JLabel("Title, year, quantity, orderID, customer email");
        columnHeaderLabel.setFont(new Font("Dialog", Font.BOLD, 14));
        columnHeaderLabel.setBounds(95, 77, 416, 16);
        orderCard.add(columnHeaderLabel);
        
        final JLabel costLabel = new JLabel("Cost: ");
        costLabel.setBounds(626, 126, 208, 16);
        orderCard.add(costLabel);
        
        JLabel creditcardLabel = new JLabel("Credit card:");
        creditcardLabel.setBounds(626, 154, 74, 16);
        orderCard.add(creditcardLabel);
        
        creditCardField = new JTextField();
        creditCardField.setBounds(700, 154, 114, 20);
        orderCard.add(creditCardField);
        creditCardField.setColumns(10);
        
       
        JRadioButton loyaltyYesRadioBtn = new JRadioButton("yes");
        loyaltyYesRadioBtn.setBounds(700, 180, 121, 24);
        orderCard.add(loyaltyYesRadioBtn);
        
        JRadioButton loyaltyNoRadioBtn = new JRadioButton("no");
        loyaltyNoRadioBtn.setBounds(700, 208, 121, 24);
        orderCard.add(loyaltyNoRadioBtn);
        
        
        ButtonGroup group = new ButtonGroup();
        group.add(loyaltyYesRadioBtn);
        group.add(loyaltyNoRadioBtn);
        
        loyaltyYesRadioBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        	loyalty = true;	
        	}
        });
        
        loyaltyNoRadioBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        	loyalty = false;	
        	}
        });
        
        
        tierOneRadioButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        	tier = 1;	
        	}
        });
        
        tierTwoRadioButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        	tier = 2;	
        	}
        });
        
        tierThreeRadioButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        	tier = 3;	
        	}
        });
        
        
        JLabel label = new JLabel("");
        label.setBounds(626, 302, 55, 16);
        orderCard.add(label);
        
        
        payButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		try {
					int orderID = dba.hasUnpaidOrder(email);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        });
        
        orderButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		
        		
        		
					try {
					ArrayList<OrderItem> list = dba.getCurrentOrderItemsByEmail(email);
					populateOrderItems(list, orderItemPanel, costLabel);
        			
					 CardLayout cl = (CardLayout)(cards.getLayout());
					 cl.show(cards, ORDERVIEW);		
					} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
        		
        		
        		
        	}
        });
        
        addToOrderButton.addActionListener(new ActionListener() {    
 			@Override
 			public void actionPerformed(ActionEvent arg0) {
 			
 				
 			int quantity = Integer.parseInt(quantityField.getText());
 			int period = tier;
 			
 						try {
 						
 							int orderID = dba.hasUnpaidOrder(email);
 							
 							if(!dba.movieAlreadyExistsOnUnpaidOrder(orderID, videoID)) {
 								
 								addedStatusLabel.setText("orderItem already on order.");

 							} else if(orderID > 0) {
 								dba.addOrderItem(orderID, videoID, quantity, period); 
 								addedStatusLabel.setText("orderItem added.");
 							} else {
 								//If there are no open orders, it created ones
 								dba.createCustomerOrder(email);
 								int nOrderID = dba.hasUnpaidOrder(email);
 								dba.addOrderItem(orderID, videoID, quantity, period); 
 								addedStatusLabel.setText("orderItem added");
 							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
 						
 			}
         });
        
        btnBack.addActionListener(new ActionListener() {    
     			@Override
     			public void actionPerformed(ActionEvent arg0) {
     			
     			
     						
     						try {
     							ArrayList<Movie> list = dba.retrieveMoviesByCat("Horror");
								populateCategory(list, horrorPanel, titleHolder, introductionHolder, yearHolder, categoryHolder, directorHolder, producerHolder, stockHolder);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
     						goToGallery();
    
     			}
             });
        
        searchButton.addActionListener(new ActionListener() {    
 			@Override
 			public void actionPerformed(ActionEvent arg0) {
 				
 		
		try {
			ArrayList<Movie> list = dba.getMoviesByTitle(searchField.getText());
			populateCategory(list, searchPane, titleHolder, introductionHolder, yearHolder, categoryHolder, directorHolder, producerHolder, stockHolder);
			searchPane.setVisible(false);
			searchPane.setVisible(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

 			}
         });
        
        backFromOrderButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		try {
						ArrayList<Movie> list = dba.retrieveMoviesByCat("Horror");
					populateCategory(list, horrorPanel, titleHolder, introductionHolder, yearHolder, categoryHolder, directorHolder, producerHolder, stockHolder);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					goToGallery();

		}
        	
        });
        
        loginButton.addActionListener(new ActionListener() {    
    			@Override
    			public void actionPerformed(ActionEvent arg0) {
    				String field = emailField.getText();
    				try {
						if(dba.customerExists(field)) {
							ArrayList<Movie> list = dba.retrieveMoviesByCat("Horror");
							populateCategory(list, horrorPanel, titleHolder, introductionHolder, yearHolder, categoryHolder, directorHolder, producerHolder, stockHolder);
							email = emailField.getText();
							goToGallery();


						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
    			}
            });
        
    }
     
//    public void itemStateChanged(ItemEvent evt) {
//        CardLayout cl = (CardLayout)(cards.getLayout());
//        cl.show(cards, (String)evt.getItem());
//    }
     
    private void goToGallery() {
    	 CardLayout cl = (CardLayout)(cards.getLayout());
		 cl.show(cards, TEXTPANEL);
    }
    
   
    
    private void populateCategory(final ArrayList<Movie> list, JPanel horrorPanel, final JLabel titleHolder, final JLabel introductionHolder, final JLabel yearHolder, final JLabel categoryHolder, final JLabel directorHolder, final JLabel producerHolder, final JLabel stockHolder) throws SQLException {
    	
    	horrorPanel.removeAll();
		buttons.clear();
	//	final ArrayList<Movie> list = dba.retrieveMoviesByCat("Horror");
		
		
		for(int i = 0; i < list.size(); i ++) {
		iterator = i;
		buttons.add(new JButton(list.get(i).getTitle()));
		
	
		
		buttons.get(iterator).addActionListener(new ActionListener() {
        final int buttonIndex = iterator; 
        final int vidID = list.get(buttonIndex).getVideoID();
			@Override
			public void actionPerformed(ActionEvent arg0) {
				titleHolder.setText(list.get(buttonIndex).getTitle());
				introductionHolder.setText(list.get(buttonIndex).getIntroduction());
				yearHolder.setText(Integer.toString(list.get(buttonIndex).getYear()));
				categoryHolder.setText(list.get(buttonIndex).getCategory());
				directorHolder.setText(list.get(buttonIndex).getDirectors());
				producerHolder.setText(list.get(buttonIndex).getProducers());
				stockHolder.setText(Integer.toString(list.get(buttonIndex).getStock()));
				videoID =  vidID;
					 CardLayout cl = (CardLayout)(cards.getLayout());
					 cl.show(cards, MOVIEVIEW);			
			}
        });
		
        horrorPanel.add(buttons.get(i));
		}
    }
 
private Double getPrice(int period) {
	
	if(period < 11) {
		return 10.0;
	} else if (period < 21) {
		return 20.0;
	} else {
		return 30.0;
	}
	
	
}
    
private void populateOrderItems(final ArrayList<OrderItem> list, JPanel horrorPanel, final JLabel costLabel) throws SQLException {
    	
    	horrorPanel.removeAll();
		price = 0.0;
		for(int i = 0; i < list.size(); i ++) {

	
				String buttonText = list.get(i).getTitle();
				buttonText += ", " + list.get(i).getYear(); 
				buttonText += ", " + list.get(i).getQuantity();
				buttonText += ", " + list.get(i).getOrderID();
				buttonText += ", " + list.get(i).getEmail();
				Double currentPrice = getPrice(list.get(i).getPeriod()) * list.get(i).getQuantity();
				buttonText += ", $" + currentPrice;
				price += currentPrice;
				JLabel n = new JLabel(buttonText);
				horrorPanel.add(n);
		}
					
		costLabel.setText("Cost with $15.00 shipping: $" + (price + 15.0));
		

		}
    
    
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("CardLayoutDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
        //Create and set up the content pane.
        Login demo = new Login();
        demo.addComponentToPane(frame.getContentPane());
         
        //Display the window.
        //frame.pack();
        
        frame.setVisible(true);
    }
     
    public static void main(String[] args) {
        /* Use an appropriate Look and Feel */
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
         
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
