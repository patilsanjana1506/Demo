import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class FDemo extends JFrame implements ActionListener
{
CardLayout card;
Container cn=getContentPane();
HomeDemo hm;
LoginDemo lg;
RegisDemo regis;
MenuDemo menu;
FDemo()
{
card=new CardLayout();
setLayout(card);
hm=new HomeDemo();
lg=new LoginDemo();
regis=new RegisDemo();
menu=new MenuDemo();

add("home",hm);
add("login",lg);
add("regis",regis);
add("menu",menu);

hm.b1.addActionListener(this);
hm.b2.addActionListener(this);
lg.b1.addActionListener(this);
lg.b2.addActionListener(this);
regis.b2.addActionListener(this);
}
public void actionPerformed(ActionEvent e)
{
if(e.getSource()==hm.b1)
{
card.show(cn,"login");
} 
if(e.getSource()==hm.b2)
{
card.show(cn,"regis");
} 
if(e.getSource()==lg.b2 || e.getSource()==regis.b2)
{
card.show(cn,"home");
} 
if(e.getSource()==lg.b1)
{
String s1=lg.tx1.getText();
String s2=lg.tx2.getText();
try
{
	Class.forName("com.mysql.cj.jdbc.Driver");
	
	Connection con=DriverManager.getConnection("jdbc:mysql:///abhidb","root","Root");
	
	Statement st=con.createStatement();
	
	String q="select * from regis where UNAME='"+s1+"' AND UPASS='"+s2+"'";
 ResultSet rs=st.executeQuery(q);
	if(rs.next())
	{
		card.show(cn,"menu");
	}
	else
	{
JOptionPane.showMessageDialog(lg.b1,"invalid user name and password");
	}
	con.close();
	
}
catch(Exception e1)
{
	System.out.println(e1);
}
}
}
}
class Demo
{
public static void main(String ar[])
{
FDemo f1=new FDemo();
f1.setVisible(true);
f1.setBounds(100,100,500,500);
f1.setDefaultCloseOperation(f1.EXIT_ON_CLOSE);
}
}