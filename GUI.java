
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectOutputStream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

	public static Formula1Driver newDriver;


	// COLUMN DESCRIPTION
	public String[] columns = {"Driver Name", "Team Name", "Location", "First positions won", "Second positions won", "Third positions won", "Numb of points won", "Number of races"};
	
	// ROW DATA
	public Object[][] data;
	

	// BUTTONS 
	public JButton SaveButton = new JButton("Save");  //save button
	public JButton ClearButton = new JButton("Clear");	//clear button
	public JButton LoadButton = new JButton("Load");	//load button
	public JButton RandomRaceButton = new JButton("Random Race"); //random race button

	// modify the table
	public DefaultTableModel defaultTableModel = new DefaultTableModel(data, columns);

	// JTable
	public JTable myTable = new JTable(defaultTableModel);
	
		

	public GUI() {
		// SORT TABLE BY COLUMN
		myTable.setAutoCreateRowSorter(true);

		// SHOW GRID
		myTable.setShowGrid(true);   //default value is 'true'
		
		// JFrame TITLE, SIZE AND DEFAULT CLOSE OPERATION IS SET
		setTitle("Formula1 Championhip");
		setSize(1200, 300);                                                    
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// FLOWLAYOUT FOR 4 BUTTONS (default)
		JPanel p2 = new JPanel();
		p2.add(SaveButton);
		p2.add(ClearButton);
		p2.add(LoadButton);
		p2.add(RandomRaceButton);
		
		// CENTERED p2 IS ADDED TO PANEL p3
		JPanel p3 = new JPanel(new BorderLayout());
		p3.add(p2, BorderLayout.CENTER);
		
		// SCROLL PANE MAKES TABLE SCROLLABLE
		add(new JScrollPane(myTable));

		// p3 IS AT THE BOTTOM
		add(p3, BorderLayout.SOUTH);
		

		
		// SAVING DATA
		SaveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					// save the data to a file using object output stream 
					ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("GUIdata.txt"));
					// saving row data
					out.writeObject(defaultTableModel.getDataVector()); //the vector of vectors containing the tables data values
					// saving column names
					out.writeObject(getColumnNames());
					out.close();
				} 
				catch(Exception ex) {
					
				}
			}
		});
		
		// DELETE DATA
		ClearButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// row count set to zero to delete data
				defaultTableModel.setRowCount(0);
			}
		});
		
		// RESTORE DATA
		LoadButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String filePath = "champion.txt";
        		File file = new File(filePath);
				
				try {
					
					BufferedReader br = new BufferedReader(new FileReader(file));

					DefaultTableModel defaultableModel = (DefaultTableModel)myTable.getModel();

					
					 // get lines from txt file
					Object[] tableLine = br.lines().toArray();
            
					 // READ DATA FROM 'tableLine'AND SET 'dataRow' TO JTable
					for(int i = 0; i < tableLine.length; i++){
						String line = tableLine[i].toString().trim(); //eliminates spaces
						String[] dataRow = line.split(",");
						defaultableModel.addRow(dataRow);
					}


				} 
				catch(Exception ex) {
					
				}
			}
		});

		RandomRaceButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e){
				String filePath = "champion.txt";
        		File file = new File(filePath);

				Integer[]array = new Integer[10];
				for (int i = 1; i <= array.length; i++) {
					array[i] = i;
				}
				Collections.shuffle(Arrays.asList(array));
				int randomNumber = Integer.parseInt(Arrays.toString(array));

				try {
					
					BufferedReader br = new BufferedReader(new FileReader(file));

					DefaultTableModel model = (DefaultTableModel)myTable.getModel();

					
					 // get lines from txt file
					 Object[] tableLine = br.lines().toArray();
            
					 // set data to jtable model
					for(int i = 0; i < tableLine.length; i++){


						String line = tableLine[i].toString().trim(); //eliminates spaces
						String[] dataRow = line.split(",");

					}



				} 
				catch(Exception ex) {
					
				}
				
			}
		});
			
		setVisible(true);
	}
	
	// GET COULUMN  NAME
	public ArrayList<String> getColumnNames() {
		
		ArrayList<String> columns = new ArrayList<String>();
		
		for(int i = 0; i < myTable.getColumnCount(); i++) {
			columns.add(myTable.getColumnName(i));
		}
		
		return columns;
		
	}
	
	
}








/*
Reference

https://1bestcsharp.blogspot.com/2017/08/java-import-text-file-data-to-jtable.html
https://www.javatpoint.com/how-to-generate-random-number-in-java

*/