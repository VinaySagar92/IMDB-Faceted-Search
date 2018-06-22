package GUI;

import java.sql.*;
import java.util.ArrayList;
import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;



import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import javax.swing.JTextArea;

public class UI{

	private JFrame frame;
	static Connection con = null;
	//static Statement stmt = null;
	static ResultSet result = null;
	static PreparedStatement prepStatement = null;
	
	
	
	private JList<String> genreJList, countryJList, actorJList, directorJList, tagJList, movieQJList, userQJList;
	
	
	DefaultListModel genreModel = new DefaultListModel();
	DefaultListModel countryModel = new DefaultListModel();
	DefaultListModel actorModel = new DefaultListModel();
	DefaultListModel directorModel = new DefaultListModel();
	DefaultListModel tagModel = new DefaultListModel();
	DefaultListModel movieModel = new DefaultListModel();
	DefaultListModel userModel = new DefaultListModel();
	
	AutoCompleteDecorator decorator;
	
	ArrayList<String> genreList = new ArrayList();
	ArrayList<String> countryList = new ArrayList();
	ArrayList<String> actorList = new ArrayList();
	ArrayList<String> directorList = new ArrayList();
	ArrayList<String> tagList = new ArrayList();
	ArrayList<String> tagList_1 = new ArrayList();
	ArrayList<String> movieList = new ArrayList();
	ArrayList<String> userList = new ArrayList();
	private String start, stop, movie="", movieC="",movieD="", tagM="", value, movieS="", user="";
	private JTextField textField;
	private JTextField textField_1;
	private JComboBox comboBox, comboBox_6;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI window = new UI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UI() {
		initialize();
		run1();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	public void run1(){
		
        try {
            con = openConnection();
            populateGenre();
        } catch (SQLException e) { 
            System.err.println("Errors occurs when communicating with the database server: " + e.getMessage());
        } catch (ClassNotFoundException e) { 
            System.err.println("Cannot find the database driver"); 
        } finally { 
            // Never forget to close database connection
        } 
	}
	
	
	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 102, 153));
		frame.setBounds(100, 100, 850, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(12, 0, 574, 39);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Movie Attributes");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(0, 0, 569, 39);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.activeCaption);
		panel_1.setBounds(588, 0, 244, 39);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Movie Results");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1.setBackground(SystemColor.activeCaption);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 0, 244, 39);
		panel_1.add(lblNewLabel_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.scrollbar);
		panel_3.setBounds(12, 38, 141, 33);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Genres");
		lblNewLabel_2.setBounds(0, 0, 141, 31);
		panel_3.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 70, 141, 215);
		frame.getContentPane().add(scrollPane);
		
		genreJList = new JList<>();
		
		genreJList.setBounds(0, 184, 139, -184);
		JPanel genrePanel = new JPanel();
		scrollPane.setViewportView(genrePanel);
		genrePanel.setBackground(new Color(240, 240, 240));
		genrePanel.setForeground(new Color(0, 128, 128));
		//genrePanel.setLayout(null);
		genrePanel.add(genreJList);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.setBounds(12, 283, 141, 27);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Movie Year");
		lblNewLabel_3.setBounds(0, 0, 141, 27);
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(12, 311, 141, 58);
		frame.getContentPane().add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("From");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		lblNewLabel_4.setBounds(0, 0, 56, 29);
		panel_4.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("To");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		lblNewLabel_5.setBounds(0, 30, 56, 23);
		panel_4.add(lblNewLabel_5);
		
		textField = new JTextField();
		textField.setBounds(62, 2, 79, 23);
		panel_4.add(textField);
		textField.setColumns(10);
		textField.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            start=textField.getText();
	             }
	           });
		
		textField_1 = new JTextField();
		textField_1.setBounds(62, 31, 79, 22);
		panel_4.add(textField_1);
		textField_1.setColumns(10);
		textField_1.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            stop=textField_1.getText();
	             }
	           });
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.LIGHT_GRAY);
		panel_5.setBounds(154, 38, 141, 33);
		frame.getContentPane().add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblNewLabel_6 = new JLabel("Country");
		lblNewLabel_6.setBounds(0, 0, 141, 33);
		panel_5.add(lblNewLabel_6);
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(SystemColor.activeCaption);
		panel_7.setBounds(12, 395, 574, 45);
		frame.getContentPane().add(panel_7);
		panel_7.setLayout(null);
		
		JLabel lblNewLabel_7 = new JLabel("Search Between Attributes' Values:");
		lblNewLabel_7.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setBounds(0, 0, 357, 43);
		panel_7.add(lblNewLabel_7);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		comboBox.setBounds(332, 1, 242, 45);
		comboBox.setMaximumRowCount(3);
		comboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select AND, OR between attributes", "AND", "OR" }));
		panel_7.add(comboBox);
		
		JButton btnNewButton = new JButton("Get Country");
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnNewButton.setBackground(SystemColor.textHighlight);
		btnNewButton.setBounds(22, 370, 120, 25);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                populateCountry(evt);
            }
		});
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(154, 70, 141, 299);
		frame.getContentPane().add(scrollPane_1);
		
		countryJList = new JList();
		JPanel panel_6 = new JPanel();
		scrollPane_1.setViewportView(panel_6);
		panel_6.add(countryJList);
		
		JPanel panel_8 = new JPanel();
		panel_8.setLayout(null);
		panel_8.setBackground(Color.LIGHT_GRAY);
		panel_8.setBounds(295, 38, 141, 33);
		frame.getContentPane().add(panel_8);
		
		JLabel lblCast = new JLabel("Cast");
		lblCast.setHorizontalAlignment(SwingConstants.CENTER);
		lblCast.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblCast.setBounds(0, 0, 141, 33);
		panel_8.add(lblCast);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(295, 97, 141, 118);
		frame.getContentPane().add(scrollPane_5);
		
		JPanel panel_9 = new JPanel();
		scrollPane_5.setViewportView(panel_9);
		//panel_9.setLayout(null);
		
		actorJList = new JList();
		panel_9.add(actorJList);
		
		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(295, 265, 141, 104);
		frame.getContentPane().add(scrollPane_6);
		
		JPanel panel_10 = new JPanel();
		scrollPane_6.setViewportView(panel_10);
		//panel_10.setLayout(null);
		
		directorJList = new JList();
		panel_10.add(directorJList);
		
		JButton btnGetCast = new JButton("Get Cast");
		btnGetCast.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnGetCast.setBackground(SystemColor.textHighlight);
		btnGetCast.setBounds(164, 370, 120, 25);
		frame.getContentPane().add(btnGetCast);
		btnGetCast.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                populateActor(evt);
            }
		});
		
		JPanel panel_11 = new JPanel();
		panel_11.setLayout(null);
		panel_11.setBackground(Color.LIGHT_GRAY);
		panel_11.setBounds(437, 38, 149, 33);
		frame.getContentPane().add(panel_11);
		
		JLabel lblTagIdsAnd = new JLabel("Tag Ids and Values");
		lblTagIdsAnd.setHorizontalAlignment(SwingConstants.CENTER);
		lblTagIdsAnd.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblTagIdsAnd.setBounds(0, 0, 151, 33);
		panel_11.add(lblTagIdsAnd);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(437, 72, 149, 250);
		frame.getContentPane().add(scrollPane_2);
		
		JPanel panel_12 = new JPanel();
		scrollPane_2.setViewportView(panel_12);
		
		tagJList = new JList();
		panel_12.add(tagJList);
		
		JButton btnGetTags = new JButton("Get Tags");
		btnGetTags.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnGetTags.setBackground(SystemColor.textHighlight);
		btnGetTags.setBounds(305, 370, 120, 25);
		frame.getContentPane().add(btnGetTags);
		btnGetTags.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				populateTags(evt);
			}
		});
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(588, 41, 244, 354);
		frame.getContentPane().add(scrollPane_3);
		
		JPanel panel_13 = new JPanel();
		scrollPane_3.setViewportView(panel_13);
		
		movieQJList = new JList();
		panel_13.add(movieQJList);
		
		JPanel panel_14 = new JPanel();
		panel_14.setBounds(437, 324, 149, 71);
		frame.getContentPane().add(panel_14);
		panel_14.setLayout(null);
		
		JLabel lblTagWeight = new JLabel("Tag Weight");
		lblTagWeight.setBounds(12, 5, 55, 23);
		lblTagWeight.setHorizontalAlignment(SwingConstants.CENTER);
		lblTagWeight.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		panel_14.add(lblTagWeight);
		
		JLabel lblValue = new JLabel("Value");
		lblValue.setHorizontalAlignment(SwingConstants.CENTER);
		lblValue.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		lblValue.setBounds(12, 35, 55, 23);
		panel_14.add(lblValue);
		
		comboBox_6 = new JComboBox();
		comboBox_6.setBounds(79, 4, 70, 24);
		comboBox_6.setMaximumRowCount(3);
		comboBox_6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "=", ">", "<" }));
		panel_14.add(comboBox_6);
		
		textField_2 = new JTextField();
		textField_2.setBounds(70, 35, 79, 23);
		panel_14.add(textField_2);
		textField_2.setColumns(10);
		textField_2.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	value = textField_2.getText();
	             }
	           });
		
		JScrollPane scrollPane_7 = new JScrollPane();
		scrollPane_7.setBounds(12, 472, 574, 141);
		frame.getContentPane().add(scrollPane_7);
		
		JTextArea textArea = new JTextArea();
		scrollPane_7.setViewportView(textArea);
		
		JPanel panel_15 = new JPanel();
		panel_15.setLayout(null);
		panel_15.setBackground(SystemColor.activeCaption);
		panel_15.setBounds(588, 399, 244, 39);
		frame.getContentPane().add(panel_15);
		
		JLabel lblUserResults = new JLabel("User Results");
		lblUserResults.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserResults.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblUserResults.setBackground(SystemColor.activeCaption);
		lblUserResults.setBounds(0, 0, 244, 39);
		panel_15.add(lblUserResults);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(588, 440, 244, 263);
		frame.getContentPane().add(scrollPane_4);
		
		JPanel panel_16 = new JPanel();
		scrollPane_4.setViewportView(panel_16);
		userQJList = new JList();
		panel_16.add(userQJList);
		
		JButton btnNewButton_1 = new JButton("Execute Movie Query");
		btnNewButton_1.setBackground(new Color(64, 224, 208));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				populateMovieSearch(evt);
				textArea.setText(movieS);
			}
		});
		btnNewButton_1.setBounds(84, 626, 158, 64);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnExecuteUserQuery = new JButton("Execute User Query");
		btnExecuteUserQuery.setBounds(329, 626, 158, 64);
		btnExecuteUserQuery.setBackground(new Color(64, 224, 208));
		btnExecuteUserQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				populateUserSearch(evt);
				textArea.setText(user);
			}
		});
		frame.getContentPane().add(btnExecuteUserQuery);
		
		JButton button = new JButton("Get Directors");
		button.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		button.setBackground(SystemColor.textHighlight);
		button.setBounds(307, 216, 117, 25);
		button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                populateDirector(evt);
            }
		});
		frame.getContentPane().add(button);
		
		JLabel lblNewLabel_8 = new JLabel("Actor/Actress");
		lblNewLabel_8.setForeground(Color.WHITE);
		lblNewLabel_8.setBounds(307, 70, 117, 26);
		frame.getContentPane().add(lblNewLabel_8);
		lblNewLabel_8.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel label = new JLabel("Director");
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		label.setBackground(Color.WHITE);
		label.setBounds(327, 243, 84, 18);
		frame.getContentPane().add(label);
		
		JLabel lblNewLabel_9 = new JLabel("Query Display:");
		lblNewLabel_9.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_9.setForeground(Color.WHITE);
		lblNewLabel_9.setBackground(Color.DARK_GRAY);
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9.setBounds(12, 440, 141, 29);
		frame.getContentPane().add(lblNewLabel_9);
		btnExecuteUserQuery.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                populateDirector(evt);
            }
		});
	}
	
	private Connection openConnection() throws SQLException, ClassNotFoundException {
        // Load the Oracle database driver 
        DriverManager.registerDriver(new oracle.jdbc.OracleDriver()); 
 
        /* 
        Here is the information needed when connecting to a database 
        server. These values are now hard-coded in the program. In 
        general, they should be stored in some configuration file and 
        read at run time. 
        */ 
        String host = "localhost"; 
        String port = "1521";
        String dbName = "orcl"; 
        String userName = "scott"; 
        String password = "tiger"; 
 
        // Construct the JDBC URL 
        String dbURL = "jdbc:oracle:thin:@" + host + ":" + port + ":" + dbName; 
        return DriverManager.getConnection(dbURL, userName, password); 
    }
 
    /** 
     * Close the database connection 
     * @param con 
     */ 
    private void closeConnection(Connection con) { 
        try { 
            con.close(); 
        } catch (SQLException e) { 
            System.err.println("Cannot close connection: " + e.getMessage());
        } 
    }
	
	private void populateGenre() {
        
        String genre = "SELECT DISTINCT G.GENRE FROM MOVIE_GENRES G";
         try {
             ResultSet rS = null;
             prepStatement=con.prepareStatement(genre);
             rS = prepStatement.executeQuery(genre);
             //showResultSet(rS);
             while(rS.next())
             {
                 if(!genreModel.contains(rS.getString("genre")))
                 {
                     genreModel.addElement(rS.getString("genre"));
                 }
             }
            prepStatement.close();
            rS.close();
         } catch(Exception ex) {
        	 ex.printStackTrace();
         }
         genreJList.setModel(genreModel);
    
         MouseListener mouseListener = new MouseAdapter() 
         {
        	 public void mouseClicked(MouseEvent e) 
        	 {
        		 if (e.getClickCount() == 1)
        		 {
        			 genreList = (ArrayList<String>) genreJList.getSelectedValuesList();
        		 }
        	 }
         };
         genreJList.addMouseListener(mouseListener);
    }
	
	private void populateCountry(ActionEvent evt) {
		  // TODO Auto-generated method stub
		  
		if(genreList.size()!=0)
		{
			String country = "";
			String bAttr = "";
	        
			if(comboBox.getSelectedIndex()==1)
			{
				bAttr = "INTERSECT";
			}
			else
			{
				if(comboBox.getSelectedIndex()==0 || comboBox.getSelectedIndex()==2)
				{
					bAttr = "UNION";
				}
			}
			//Genre Within attributes
			int i=0;
			for(i=0;i<genreList.size()-1;i++)
			{
				//country += "SELECT DISTINCT MC.COUNTRY\nFROM MOVIE_GENRES MG, MOVIES M, MOVIE_COUNTRIES MC\nWHERE M.MOVIEID=MG.MOVIEID AND M.MOVIEID=MC.MOVIEID AND M.MYEAR >='"+ start +"' AND M.MYEAR <='"+stop+"' AND MG.GENRE = '"+genreList.get(i)+"'\n"+bAttr+"\n";
				movie += "SELECT DISTINCT M.MOVIEID\nFROM MOVIE_GENRES MG, MOVIES M, MOVIE_COUNTRIES MC\nWHERE M.MOVIEID=MG.MOVIEID AND M.MOVIEID=MC.MOVIEID AND M.MYEAR >='"+ start +"' AND M.MYEAR <='"+stop+"' AND MG.GENRE = '"+genreList.get(i)+"'\n"+bAttr+"\n";
			}
	            
			//country += "SELECT DISTINCT MC.COUNTRY\nFROM MOVIE_GENRES MG, MOVIES M, MOVIE_COUNTRIES MC\nWHERE M.MOVIEID=MG.MOVIEID AND M.MOVIEID=MC.MOVIEID AND M.MYEAR >='"+ start +"' AND M.MYEAR <='"+stop+"' AND MG.GENRE = '"+genreList.get(i)+"'";
			movie += "SELECT DISTINCT M.MOVIEID\nFROM MOVIE_GENRES MG, MOVIES M, MOVIE_COUNTRIES MC\nWHERE M.MOVIEID=MG.MOVIEID AND M.MOVIEID=MC.MOVIEID AND M.MYEAR >='"+ start +"' AND M.MYEAR <='"+stop+"' AND MG.GENRE = '"+genreList.get(i)+"'";
			
			country += "SELECT DISTINCT C.COUNTRY FROM MOVIE_COUNTRIES C \n WHERE C.MOVIEID IN ( " + movie +" ) \n";
			
			//System.out.println(country);
			
			
			ResultSet rS = null;
			
			try
			{
				prepStatement = con.prepareStatement(country);
				rS =prepStatement.executeQuery(country);
	               
	                
				while(rS.next())
				{
	                    
					if(!countryModel.contains(rS.getString("COUNTRY")))
					{
						countryModel.addElement(rS.getString("COUNTRY"));
					}
				}
				prepStatement.close();
				rS.close();
	                
			}catch(Exception ex)
			{
				ex.printStackTrace();
			}
			countryJList.setModel(countryModel);
	               
			MouseListener mouseListener = new MouseAdapter() 
			{
				public void mouseClicked(MouseEvent e) 
				{
					if (e.getClickCount() == 1)
					{
						countryList = (ArrayList<String>) countryJList.getSelectedValuesList();
					}	
				}
			};
			countryJList.addMouseListener(mouseListener);
		}
	}
	
	private void populateActor(ActionEvent evt) {
		
		String actor = "";
		//String director = "";
		String bAttr = "";
		
		if(comboBox.getSelectedIndex()==1)
		{
			bAttr = "INTERSECT";
		}
		else
		{
			if(comboBox.getSelectedIndex()==0 || comboBox.getSelectedIndex()==2)
			{
				bAttr = "UNION";
			}
		}
		  
		if(countryList.size() == 0)
		{
			int start = 0;
			int end = countryJList.getModel().getSize()-1;
			countryJList.setSelectionInterval(start, end);
			countryList = (ArrayList<String>) countryJList.getSelectedValuesList();	
			//countryJList.addMouseListener(mouseListener);
		}
		  
		
			//Genre Within attributes
			int i=0;
			
			for(i=0;i<countryList.size()-1;i++)
			{
			actor += "SELECT DISTINCT A.ACTORNAME\nFROM MOVIE_ACTORS A, MOVIE_COUNTRIES MMC\nWHERE A.MOVIEID= MMC.MOVIEID AND MMC.COUNTRY='"+countryList.get(i)+"' AND A.MOVIEID IN("+movie+")"+"\n"+bAttr+"\n";
			//director += "SELECT DISTINCT D.DIRECTORNAME\nFROM MOVIE_DIRECTORS MD, MOVIES MM\nWHERE MM.MOVIEID=D.MOVIEID AND MM.MOVIEID IN("+movie+")";
			movieC += "SELECT DISTINCT A.MOVIEID\nFROM MOVIE_ACTORS A, MOVIE_COUNTRIES MMC\nWHERE A.MOVIEID= MMC.MOVIEID AND MMC.COUNTRY='"+countryList.get(i)+"' AND A.MOVIEID IN("+movie+")"+"\n"+bAttr+"\n";
			}    
			actor += "SELECT DISTINCT A.ACTORNAME\nFROM MOVIE_ACTORS A, MOVIE_COUNTRIES MMC\nWHERE A.MOVIEID= MMC.MOVIEID AND MMC.COUNTRY='"+countryList.get(i)+"' AND A.MOVIEID IN("+movie+")"+"\n";
			movieC += "SELECT DISTINCT A.MOVIEID\nFROM MOVIE_ACTORS A, MOVIE_COUNTRIES MMC\nWHERE A.MOVIEID= MMC.MOVIEID AND MMC.COUNTRY='"+countryList.get(i)+"' AND A.MOVIEID IN("+movie+")"+"\n";
			//System.out.println(movie);
			//System.out.println(actor);
			ResultSet rS = null;
	          
			try
			{
				prepStatement = con.prepareStatement(actor);
				rS =prepStatement.executeQuery(actor);
	               
	            
				while(rS.next())
				{
					actorModel.addElement(rS.getString("ACTORNAME"));
//					actorModel2.addElement(rS.getString("ACTORNAME"));
//					actorModel3.addElement(rS.getString("ACTORNAME"));
//					actorModel4.addElement(rS.getString("ACTORNAME"));
				}
				prepStatement.close();
				rS.close();
	                
			}catch(Exception ex)
			{
				ex.printStackTrace();
			}
			actorJList.setModel(actorModel);
//			comboBox_2.setModel(actorModel2);
//			comboBox_3.setModel(actorModel3);
//			comboBox_4.setModel(actorModel4);
	               
			MouseListener mouseListener = new MouseAdapter() 
			{
				@SuppressWarnings("unchecked")
				public void mouseClicked(MouseEvent e) 
				{
					if (e.getClickCount() == 1)
					{
						actorList = (ArrayList<String>) actorJList.getSelectedValuesList();
					}	
				}
			};
			actorJList.addMouseListener(mouseListener);
		//}
	}
	
	private void populateDirector(ActionEvent evt) {
		  // TODO Auto-generated method stub
		  
//		if(countryList.size()!=0)
//		{
			String director = "";
			String bAttr = "";
	        
			if(comboBox.getSelectedIndex()==1)
			{
				bAttr = "INTERSECT";
			}
			else
			{
				if(comboBox.getSelectedIndex()==0 || comboBox.getSelectedIndex()==2)
				{
					bAttr = "UNION";
				}
			}
			
			if(actorList.size() == 0)
			{
				int start = 0;
				int end = actorJList.getModel().getSize()-1;
				actorJList.setSelectionInterval(start, end);
				actorList = (ArrayList<String>) actorJList.getSelectedValuesList();
				//countryJList.addMouseListener(mouseListener);
			}
			
			//Genre Within attributes
			int i=0;
			
			for(i=0;i<actorList.size()-1;i++)
			{
				director += "SELECT DISTINCT D.DIRECTORNAME\nFROM MOVIE_DIRECTORS D, MOVIE_ACTORS MA\nWHERE D.MOVIEID= MA.MOVIEID AND MA.ACTORNAME='"+actorList.get(i)+"' AND D.MOVIEID IN("+movieC+")"+"\n"+bAttr+"\n";
				movieD += "SELECT DISTINCT D.MOVIEID\nFROM MOVIE_DIRECTORS D, MOVIE_ACTORS MA\nWHERE D.MOVIEID= MA.MOVIEID AND MA.ACTORNAME='"+actorList.get(i)+"' AND D.MOVIEID IN("+movieC+")"+"\n"+bAttr+"\n";
			}
			director += "SELECT DISTINCT D.DIRECTORNAME\nFROM MOVIE_DIRECTORS D, MOVIE_ACTORS MA\nWHERE D.MOVIEID= MA.MOVIEID AND MA.ACTORNAME='"+actorList.get(i)+"' AND D.MOVIEID IN("+movieC+")"+"\n";
			
			movieD += "SELECT DISTINCT D.MOVIEID\nFROM MOVIE_DIRECTORS D, MOVIE_ACTORS MA\nWHERE D.MOVIEID= MA.MOVIEID AND MA.ACTORNAME='"+actorList.get(i)+"' AND D.MOVIEID IN("+movieC+")"+"\n";
			
			//System.out.println(director);
			//System.out.println(movieD);
			ResultSet rS = null;
	          
			try
			{
				prepStatement = con.prepareStatement(director);
				rS =prepStatement.executeQuery(director);
	               
	                
				while(rS.next())
				{
					directorModel.addElement(rS.getString("DIRECTORNAME"));
				}
				prepStatement.close();
				rS.close();
	                
			}catch(Exception ex)
			{
				ex.printStackTrace();
			}
			directorJList.setModel(directorModel);
	               
			MouseListener mouseListener = new MouseAdapter() 
			{
				@SuppressWarnings("unchecked")
				public void mouseClicked(MouseEvent e) 
				{
					if (e.getClickCount() == 1)
					{
						directorList = (ArrayList<String>) directorJList.getSelectedValuesList();
					}	
				}
			};
			directorJList.addMouseListener(mouseListener);
		//}
	}
	
	private void populateTags(ActionEvent evt) {
		  // TODO Auto-generated method stub
		  
//		if(actorList.size()!=0)
//		{
			String tags = "";
			String bAttr = "";
	        
			if(comboBox.getSelectedIndex()==1)
			{
				bAttr = "INTERSECT";
			}
			else
			{
				if(comboBox.getSelectedIndex()==0 || comboBox.getSelectedIndex()==2)
				{
					bAttr = "UNION";
				}
			}
			
			if(directorList.size() == 0)
			{
				int start = 0;
				int end = directorJList.getModel().getSize()-1;
				directorJList.setSelectionInterval(start, end);
				directorList = (ArrayList<String>) directorJList.getSelectedValuesList();	
				//countryJList.addMouseListener(mouseListener);
			}
			
			//Genre Within attributes
			int i=0;
			
			
			for(i=0;i<directorList.size()-1;i++)
			{
			tags += "SELECT DISTINCT T.TAGID, T.VALUE\nFROM TAGS T, MOVIE_TAGS MT, MOVIE_DIRECTORS MD\nWHERE MD.MOVIEID = MT.MOVIEID AND T.TAGID = MT.TAGID AND MD.DIRECTORNAME = '"+ directorList.get(i)+"' AND MT.MOVIEID IN("+movieD+") \n"+bAttr+"\n";
			}
			tags += "SELECT DISTINCT T.TAGID, T.VALUE\nFROM TAGS T, MOVIE_TAGS MT, MOVIE_DIRECTORS MD\nWHERE MD.MOVIEID = MT.MOVIEID AND T.TAGID = MT.TAGID AND MD.DIRECTORNAME = '"+ directorList.get(i)+"' AND MT.MOVIEID IN("+movieD+") \n";
			
//			"SELECT DISTINCT T.TAGID, T.VALUE\nFROM TAGS T, MOVIE_TAGS MT, MOVIE_ACTORS MA\nWHERE MA.MOVIEID = MT.MOVIEID AND T.TAGID = MT.TAGID AND MA.ACTORNAME = '"+ comboBox_1.getSelectedItem().toString()+"' AND MT.MOVIEID IN("+movieD+")"+"\n"+bAttr+"\n"+
//			"SELECT DISTINCT T.TAGID, T.VALUE\nFROM TAGS T, MOVIE_TAGS MT, MOVIE_ACTORS MA\nWHERE MA.MOVIEID = MT.MOVIEID AND T.TAGID = MT.TAGID AND MA.ACTORNAME = '"+ comboBox_2.getSelectedItem().toString()+"' AND MT.MOVIEID IN("+movieD+")"+"\n"+bAttr+"\n"+
//			"SELECT DISTINCT T.TAGID, T.VALUE\nFROM TAGS T, MOVIE_TAGS MT, MOVIE_ACTORS MA\nWHERE MA.MOVIEID = MT.MOVIEID AND T.TAGID = MT.TAGID AND MA.ACTORNAME = '"+ comboBox_3.getSelectedItem().toString()+"' AND MT.MOVIEID IN("+movieD+")"+"\n"+bAttr+"\n"+
//			"SELECT DISTINCT T.TAGID, T.VALUE\nFROM TAGS T, MOVIE_TAGS MT, MOVIE_ACTORS MA\nWHERE MA.MOVIEID = MT.MOVIEID AND T.TAGID = MT.TAGID AND MA.ACTORNAME = '"+ comboBox_4.getSelectedItem().toString()+"' AND MT.MOVIEID IN("+movieD+")"+"\n"+bAttr+"\n"+
			//actor += "SELECT DISTINCT MC.COUNTRY\nFROM MOVIE_GENRES MG, MOVIES M, MOVIE_COUNTRIES MC\nWHERE M.MOVIEID=MG.MOVIEID AND M.MOVIEID=MC.MOVIEID AND M.MYEAR >='"+ start +"' AND M.MYEAR <='"+stop+"' AND MG.GENRE = '"+genreList.get(i)+"'";
			//System.out.println(movie);
			//System.out.println(tags);
			ResultSet rS = null;
	          
			try
			{
				prepStatement = con.prepareStatement(tags);
				rS =prepStatement.executeQuery(tags);
	               
	                
				while(rS.next())
				{
					tagModel.addElement(rS.getString("TAGID")+" "+rS.getString("VALUE"));
				}
				prepStatement.close();
				rS.close();
	                
			}catch(Exception ex)
			{
				ex.printStackTrace();
			}
			tagJList.setModel(tagModel);
	               
			MouseListener mouseListener = new MouseAdapter() 
			{
				public void mouseClicked(MouseEvent e) 
				{
					if (e.getClickCount() == 1)
					{
						tagList = (ArrayList<String>) tagJList.getSelectedValuesList();
					}	
				}
			};
			tagJList.addMouseListener(mouseListener);
		//}
	}
	
	private void populateMovieSearch(ActionEvent evt) {
		  // TODO Auto-generated method stub
		  
//		if(tagList.size()!=0)
//		{
			//String movieS = "";
			String bAttr = "";
			String val = "";
	        
			if(comboBox.getSelectedIndex()==1)
			{
				bAttr = "INTERSECT";
			}
			else
			{
				if(comboBox.getSelectedIndex()==0 || comboBox.getSelectedIndex()==2)
				{
					bAttr = "UNION";
				}
			}
			
			if(comboBox_6.getSelectedIndex()==1)
			{
				val = ">";
			}
			else
			{
				if(comboBox_6.getSelectedIndex()==2)
				{
					val = "<";
				}
				if(comboBox_6.getSelectedIndex()==0)
				{
					val = "=";
				}
				
			}
			
			if(tagList.size() == 0)
			{
				int start = 0;
				int end = tagJList.getModel().getSize()-1;
				tagJList.setSelectionInterval(start, end);
				tagList = (ArrayList<String>) tagJList.getSelectedValuesList();	
				//countryJList.addMouseListener(mouseListener);
			}
			
			//Genre Within attributes
			int i=0;
			
			for(i=0;i<directorList.size()-1;i++)
			{
			tagM += "SELECT DISTINCT MT.MOVIEID\nFROM TAGS T, MOVIE_TAGS MT, MOVIE_DIRECTORS MD\nWHERE MD.MOVIEID = MT.MOVIEID AND T.TAGID = MT.TAGID AND MT.TAGWEIGHT " + val + " " + value + " AND MD.DIRECTORNAME = '"+ directorList.get(i)+"' AND MT.MOVIEID IN("+movieD+") \n"+bAttr+"\n";
			}
			
			tagM += "SELECT DISTINCT MT.MOVIEID\nFROM TAGS T, MOVIE_TAGS MT, MOVIE_DIRECTORS MD\nWHERE MD.MOVIEID = MT.MOVIEID AND T.TAGID = MT.TAGID AND MT.TAGWEIGHT " + val + " " + value + " AND MD.DIRECTORNAME = '"+ directorList.get(i)+"' AND MT.MOVIEID IN("+movieD+")";
			
//			for(i=0;i<tagList.size()-1;i++)
//			{
//				movieS += "SELECT DISTINCT MT.MOVIEID FROM MOVIE_TAGS MT, TAGS TA WHERE MT.TAGID = TA.TAGID AND TA.TAGID = " + tagList.get(i).split("\\s")[0] + "\n INTERSECT \n"
//						+ "SELECT DISTINCT MTT.MOVIEID FROM MOVIE_TAGS MTT, TAGS TAA WHERE MTT.TAGID = TAA.TAGID AND MTT.MOVIEID IN ("+tagM+")\n"+bAttr+"\n";
//			}
			
			for(i=0;i<tagList.size()-1;i++)
			{
			movieS += "SELECT DISTINCT M.MOVIEID, M.TITLE, MG.GENRE, M.MYEAR, MC.COUNTRY, M.RTAUDIENCERATING, M.RTAUDIENCENUMRATINGS FROM MOVIE_TAGS MT, TAGS TA, MOVIES M, MOVIE_GENRES MG, MOVIE_COUNTRIES MC WHERE M.MOVIEID = MG.MOVIEID AND M.MOVIEID = MC.MOVIEID AND M.MOVIEID = MT.MOVIEID AND MT.TAGID = TA.TAGID AND TA.TAGID = " + tagList.get(i).split("\\s")[0] + "\n INTERSECT \n"
					+ "SELECT DISTINCT MM.MOVIEID, MM.TITLE, MGG.GENRE, MM.MYEAR, MCC.COUNTRY, MM.RTAUDIENCERATING, MM.RTAUDIENCENUMRATINGS FROM MOVIE_TAGS MTT, TAGS TAA, MOVIES MM, MOVIE_GENRES MGG, MOVIE_COUNTRIES MCC WHERE MM.MOVIEID = MGG.MOVIEID AND MM.MOVIEID = MCC.MOVIEID AND MM.MOVIEID = MTT.MOVIEID AND MTT.TAGID = TAA.TAGID AND MTT.MOVIEID IN ("+tagM+")\n"+bAttr+"\n";
			}
			
			movieS += "SELECT DISTINCT M.MOVIEID, M.TITLE, MG.GENRE, M.MYEAR, MC.COUNTRY, M.RTAUDIENCERATING, M.RTAUDIENCENUMRATINGS FROM MOVIE_TAGS MT, TAGS TA, MOVIES M, MOVIE_GENRES MG, MOVIE_COUNTRIES MC WHERE M.MOVIEID = MG.MOVIEID AND M.MOVIEID = MC.MOVIEID AND M.MOVIEID = MT.MOVIEID AND MT.TAGID = TA.TAGID AND TA.TAGID = " + tagList.get(i).split("\\s")[0] + "\n INTERSECT \n"
					+ "SELECT DISTINCT MM.MOVIEID, MM.TITLE, MGG.GENRE, MM.MYEAR, MCC.COUNTRY, MM.RTAUDIENCERATING, MM.RTAUDIENCENUMRATINGS FROM MOVIE_TAGS MTT, TAGS TAA, MOVIES MM, MOVIE_GENRES MGG, MOVIE_COUNTRIES MCC WHERE MM.MOVIEID = MGG.MOVIEID AND MM.MOVIEID = MCC.MOVIEID AND MM.MOVIEID = MTT.MOVIEID AND MTT.TAGID = TAA.TAGID AND MTT.MOVIEID IN ("+tagM+")\n";
	            
			//actor += "SELECT DISTINCT MC.COUNTRY\nFROM MOVIE_GENRES MG, MOVIES M, MOVIE_COUNTRIES MC\nWHERE M.MOVIEID=MG.MOVIEID AND M.MOVIEID=MC.MOVIEID AND M.MYEAR >='"+ start +"' AND M.MYEAR <='"+stop+"' AND MG.GENRE = '"+genreList.get(i)+"'";
			//System.out.println(movie);
			//System.out.println(movieS);
			ResultSet rS = null;
	        
			try
			{
				prepStatement = con.prepareStatement(movieS);
				rS =prepStatement.executeQuery(movieS);
	               
	                
				while(rS.next())
				{
					movieModel.addElement(rS.getString("MOVIEID") + "," + rS.getString("TITLE") + "," + rS.getString("GENRE") + "," + rS.getString("MYEAR") + "," + rS.getString("COUNTRY") + "," + rS.getString("RTAUDIENCERATING") + "," + rS.getString("RTAUDIENCENUMRATINGS"));
				}
				prepStatement.close();
				rS.close();
	                
			}catch(Exception ex)
			{
				ex.printStackTrace();
			}
			movieQJList.setModel(movieModel);
	               
			MouseListener mouseListener = new MouseAdapter() 
			{
				public void mouseClicked(MouseEvent e) 
				{
					if (e.getClickCount() == 1)
					{
						movieList = (ArrayList<String>) movieQJList.getSelectedValuesList();
					}	
				}
			};
			movieQJList.addMouseListener(mouseListener);
		//}
	}
	
	private void populateUserSearch(ActionEvent evt) {
		  // TODO Auto-generated method stub
		  
//		if(movieList.size()!=0)
//		{
			//String user = "";
			String bAttr = "";
			//String val = "";
	        
			if(comboBox.getSelectedIndex()==1)
			{
				bAttr = "INTERSECT";
			}
			else
			{
				if(comboBox.getSelectedIndex()==0 || comboBox.getSelectedIndex()==2)
				{
					bAttr = "UNION";
				}
			}
			
//			if(comboBox_6.getSelectedIndex()==1)
//			{
//				val = ">";
//			}
//			else
//			{
//				if(comboBox_6.getSelectedIndex()==2)
//				{
//					val = "<";
//				}
//				if(comboBox_6.getSelectedIndex()==0)
//				{
//					val = "=";
//				}
//				
//			}
			
			if(movieList.size() == 0)
			{
				int start = 0;
				int end = movieQJList.getModel().getSize()-1;
				movieQJList.setSelectionInterval(start, end);
				movieList = (ArrayList<String>) movieQJList.getSelectedValuesList();
				//countryJList.addMouseListener(mouseListener);
			}
			
			//Genre Within attributes
			int i=0;
			
//			for(i=0;i<directorList.size()-1;i++)
//			{
//			tagM += "SELECT DISTINCT MT.MOVIEID\nFROM TAGS T, MOVIE_TAGS MT, MOVIE_DIRECTORS MD\nWHERE MD.MOVIEID = MT.MOVIEID AND T.TAGID = MT.TAGID AND MT.TAGWEIGHT " + val + " " + value + " AND MD.DIRECTORNAME = '"+ directorList.get(i)+"' AND MT.MOVIEID IN("+movieD+") \n"+bAttr+"\n";
//			}
//			
//			tagM += "SELECT DISTINCT MT.MOVIEID\nFROM TAGS T, MOVIE_TAGS MT, MOVIE_DIRECTORS MD\nWHERE MD.MOVIEID = MT.MOVIEID AND T.TAGID = MT.TAGID AND MT.TAGWEIGHT " + val + " " + value + " AND MD.DIRECTORNAME = '"+ directorList.get(i)+"' AND MT.MOVIEID IN("+movieD+")";
			
//			for(i=0;i<tagList.size()-1;i++)
//			{
//				movieS += "SELECT DISTINCT MT.MOVIEID FROM MOVIE_TAGS MT, TAGS TA WHERE MT.TAGID = TA.TAGID AND TA.TAGID = " + tagList.get(i).split("\\s")[0] + "\n INTERSECT \n"
//						+ "SELECT DISTINCT MTT.MOVIEID FROM MOVIE_TAGS MTT, TAGS TAA WHERE MTT.TAGID = TAA.TAGID AND MTT.MOVIEID IN ("+tagM+")\n"+bAttr+"\n";
//			}
			
			for(i=0;i<movieList.size()-1;i++)
			{
			user += "SELECT DISTINCT UTM.USERID FROM USER_TAGGEDMOVIES UTM WHERE UTM.MOVIEID =" + movieList.get(i).split(",")[0] +" \n" + bAttr + "\n";
			}
			
			user += "SELECT DISTINCT UTM.USERID FROM USER_TAGGEDMOVIES UTM WHERE UTM.MOVIEID =" + movieList.get(i).split(",")[0] +" \n";
	            
			//actor += "SELECT DISTINCT MC.COUNTRY\nFROM MOVIE_GENRES MG, MOVIES M, MOVIE_COUNTRIES MC\nWHERE M.MOVIEID=MG.MOVIEID AND M.MOVIEID=MC.MOVIEID AND M.MYEAR >='"+ start +"' AND M.MYEAR <='"+stop+"' AND MG.GENRE = '"+genreList.get(i)+"'";
			//System.out.println(movie);
			System.out.println(user);
			ResultSet rS = null;
	        
			try
			{
				prepStatement = con.prepareStatement(user);
				rS =prepStatement.executeQuery(user);
	               
	                
				while(rS.next())
				{
					userModel.addElement(rS.getString("USERID"));
				}
				prepStatement.close();
				rS.close();
	                
			}catch(Exception ex)
			{
				ex.printStackTrace();
			}
			userQJList.setModel(userModel);
	               
			MouseListener mouseListener = new MouseAdapter()
			{
				public void mouseClicked(MouseEvent e) 
				{
					if (e.getClickCount() == 1)
					{
						userList = (ArrayList<String>) userQJList.getSelectedValuesList();
					}	
				}
			};
			userQJList.addMouseListener(mouseListener);
		//}
	}
}