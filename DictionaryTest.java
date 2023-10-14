import java.io.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.event.*;
import javax.swing.*;
import java.util.*;
import static java.lang.System.*;
public class DictionaryTest{
	public static void main(String args[]){
		Dictionary app = new Dictionary();
		app.setSize(600,600);
		app.setVisible(true);
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
public class Dictionary extends JFrame implements ActionListener{
	private JButton[] buttons;
	private static final String names[]={"Description","Add City","List of cities","Clear","About"};
	private JPanel panel1;


	private static final String[] keys={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z",":","\" ","Shift",
		"Spc","1","2","3","4","5","6","7","8","9","0"};
	private JTextField input;
	private JButton[] buttons2;
	private JPanel keys_panel;
	private JPanel panel2;


	private JPanel panel3;
	private JPanel panel3_buttons;
	private JTextArea screen;
	private JButton Edit,Save;


	private JPanel panel4;
	private JTextField city_name_input;
	private JTextField description_input;
	private JButton add_new,cancel;
	public int flag=0, count=0;


	private JPanel panel5;
	private JPanel panel6;
	private JList cities_list;
	private JButton[] panel6_buttons;
	private static final String panel6_names[]={"Delete Selected","Delete All","Hide List"};
	private JTextField numberOFcities;
	public String [] content = {"Al quds: eternal capital of palestine", 
	"Nablus: a palestinian city in the west Bank", 
	"hebron: in the south"};
	public ArrayList <String> city_name_and_description =new ArrayList <String>(); 
	public String text1;


	public Dictionary(){
		super("Virtual Cities Dictionary");
		setLayout(new FlowLayout());

//--------------------------------------------------------PANEL1--------------------------------------------------------
		panel1= new JPanel();
		panel1.setLayout(new GridLayout(5,1,5,5));
		buttons= new JButton[names.length];
		for(int i=0;i<names.length;i++){
			buttons[i]= new JButton(names[i]);
			buttons[i].addActionListener(this);
			panel1.add(buttons[i]);
		}
		add(panel1);

//--------------------------------------------------------PANEL2--------------------------------------------------------
		panel2=new JPanel();
		panel2.setLayout(new BorderLayout());
		keys_panel= new JPanel();
		keys_panel.setLayout(new GridLayout(8,5));
		buttons2= new JButton[keys.length];
		for(int i=0;i<keys.length;i++){
			buttons2[i]=new JButton(keys[i]);
			buttons2[i].addActionListener(this);
			keys_panel.add(buttons2[i]);
		}
		input=new JTextField();
		input.setEditable(true); //<----------------------------------------------------------- make it true
		input.setHorizontalAlignment(JTextField.LEFT);
		panel2.add(input,BorderLayout.NORTH);
		panel2.add(keys_panel,BorderLayout.CENTER);
		add(panel2);

//--------------------------------------------------------PANEL3--------------------------------------------------------
		panel3=new JPanel();
		panel3_buttons= new JPanel();
		panel3_buttons.setLayout(new FlowLayout());
		panel3.setLayout(new BorderLayout());
		screen= new JTextArea(10,15);
		Edit = new JButton("Edit");
		Box box=Box.createHorizontalBox();
		box.add(new JScrollPane(screen));
		screen.setEditable(false);
		Save=new JButton("Save");
		Edit.addActionListener(this);
		Save.addActionListener(this);
		panel3_buttons.add(Edit);
		panel3_buttons.add(Save);
		panel3.add(panel3_buttons,BorderLayout.SOUTH);
		panel3.add(screen,BorderLayout.CENTER);
		add(panel3);

//--------------------------------------------------------PANEL4--------------------------------------------------------
		panel4=new JPanel();
		panel4.setLayout(new FlowLayout());
		city_name_input=new JTextField("Add city name",10);
		description_input=new JTextField("Add description",25);
		add_new= new JButton("Add new");
		cancel=new JButton("Cancel");
		add_new.addActionListener(this);
		cancel.addActionListener(this);
		panel4.add(add_new);
		panel4.add(cancel);
		panel4.add(city_name_input);
		panel4.add(description_input);
		add(panel4);
		panel4.setVisible(false);

//--------------------------------------------------------PANEL5 + PANEL6--------------------------------------------------------
		panel5=new JPanel();
		panel5.setLayout(new FlowLayout());
		cities_list=new JList(content);
		cities_list.setFixedCellWidth(300);
		cities_list.setFixedCellHeight(50);


		cities_list.setVisibleRowCount(3);
		cities_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		add(new JScrollPane(cities_list));
		panel6_buttons= new JButton[panel6_names.length];
		panel6=new JPanel();
		panel6.setLayout(new GridLayout(3,1,2,2));
		for(int i=0;i<panel6_names.length;i++){
			panel6_buttons[i]=new JButton(panel6_names[i]);
			panel6_buttons[i].addActionListener(this);
			panel6.add(panel6_buttons[i]);
		}

		numberOFcities=new JTextField();
		panel5.add(numberOFcities);
		panel5.add(cities_list);
		panel5.add(panel6);
		add(panel5);
		panel5.setVisible(false);
		
		
		
		
	}//end of default constructor
	

	public void actionPerformed(ActionEvent event){
		if(event.getSource()==buttons[4])
			JOptionPane.showMessageDialog(Dictionary.this,"NAME: Mohamad Kittani\nSTUDENT ID: 12115142");

		for(int i=0;i<keys.length;i++){
			if(event.getSource()==buttons2[i]){
				if(i==29)
					input.setText(input.getText()+" ");
				if(i==28)
					input.setText(input.getText().toLowerCase());
			if(i!=29 && i!=28)
				input.setText(input.getText() + buttons2[i].getText());
			}
		}

		if(event.getSource()==buttons[0])
			screen.setText(screen.getText()+input.getText());

		if(event.getSource()==buttons[3])
			input.setText("");

		if(event.getSource()==buttons[1]){
			panel4.setVisible(true);
		}

		if(event.getSource()==Edit){
			screen.setEditable(true);
		}
		if(event.getSource()==Save){
			screen.setEditable(false);
		}
		
		if(event.getSource()==add_new){
			if(city_name_input.getText()!=null){
				try{
					BufferedWriter out=new BufferedWriter(new FileWriter("city_names.txt",true));
					out.write(city_name_input.getText()+"\n");
					out.close();
					count++;

					BufferedWriter out2=new BufferedWriter(new FileWriter("descriptions.txt",true));
					out2.write(description_input.getText()+"\n");
					out2.close();

					BufferedWriter out3=new BufferedWriter(new FileWriter("combined.txt",true));
					out3.write(city_name_input.getText()+": "+description_input.getText()+"\n");
					out3.close();
					

					BufferedReader in=new BufferedReader(new FileReader("combined.txt"));
					while(in.readLine()!=null){
						text1=in.readLine();
						city_name_and_description.add(text1);
					}
					in.close(); 
				}
				catch(IOException e){
					e.printStackTrace();
				}
			}
		}
		if(event.getSource()==cancel){
			city_name_input.setText("");
			description_input.setText("");
		}

		if(event.getSource()==buttons[2]){
			numberOFcities.setText("we have "+count+" cities.");
			panel5.setVisible(true);
		}
		
		if(event.getSource()==panel6_buttons[0]){
			content[1]="";
		}

		if(event.getSource()==panel6_buttons[1]){
			String empty[]={" "," "," "};
			cities_list.setListData(empty);
		}

		if(event.getSource()==panel6_buttons[2]){
			cities_list.setVisible(false);
			}
		
	}

	


}