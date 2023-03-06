import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
public class Teststudent {
	public Connection getCon(){
		Connection connection=null;
		try{

			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/shivusanju", "root", "Shivu@123");
		}catch(Exception e){System.out.println(e);}
		return connection;
	}



	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Scanner sc =new Scanner(System.in);
		Teststudent  teststudent = new Teststudent();
		Connection connection=teststudent.getCon();
		while(true) {
			System.out.print("=========================\npress 1 : Adding admin  \npress 2 : SignIn\npress 3 : Exit\n=========================\n");
			int a=sc.nextInt();
			switch(a) {
			case 1:{
				System.out.println("Enter the \t name\t password\t email\t contactno=\n");


				User b=new User(sc.next(),sc.next(),sc.next(),sc.next());
				//Class.forName("com.mysql.jdbc.Driver");
				//Connection connection=teststudent.getCon();


				PreparedStatement ps=connection.prepareStatement("insert into admin_Table(name,password,email,contactno) values(?,?,?,?)");
				ps.setString(1,b.getName());
				ps.setString(2,b.getPassword());
				ps.setString(3,b.getEmail());
				ps.setString(4,b.getContactno());
				int status=ps.executeUpdate();
				if(status>0) {
					System.out.println("user added succesfull!..");
				}
				else {
					System.out.println("user not added succesfull");
				}

				break;
			}
			case 2:{
				System.out.println("Enter the username and password");
				String name=sc.next();
				String password=sc.next();
				boolean status=false;
				PreparedStatement ps=connection.prepareStatement("select * from admin_table where name=? and password=?");
				ps.setString(1,name);
				ps.setString(2,password);
				ResultSet rs=ps.executeQuery();
				status=rs.next();
				if(status){
					System.out.println("login succesfull...");
					int flag=0;
					while(flag==0) {


						System.out.println("=========================\nenter 1 for insert student\nenter 2 for update student fee\nenter 3 for delete student\nenter 4 for list of students with due fees\nenter 5 for list of student with no due fees\nenter 6 for search student by name\nenter 7 for list of all the student\nenter 8 for exit\n=========================");
						int ch=sc.nextInt();
						switch(ch) {
						case 1:{
							System.out.println("enter rollno  name  email  course  fixedfee  paid  due  contactno");
							Student student=new Student(sc.nextInt(),sc.next(),sc.next(),sc.next(),sc.nextInt(),sc.nextInt(),sc.nextInt(),sc.next());
							//Connection con=teststudent.getCon();


							PreparedStatement ps1=connection.prepareStatement("insert into student_table(rollno,name,email,course,fixedfee,paid,due,contactno) values(?,?,?,?,?,?,?,?)");
							ps1.setInt(1,student.getRollno());
							ps1.setString(2,student.getName());
							ps1.setString(3,student.getEmail());
							ps1.setString(4,student.getCourse());
							ps1.setInt(5,student.getFee());
							ps1.setInt(6,student.getPaid());
							ps1.setInt(7,student.getDue());
							ps1.setString(8,student.getContactno());
							int s1=ps1.executeUpdate();
							if(s1>0) {
								System.out.println("insert student is  succesfull!..");
							}
							else {
								System.out.println("insert student is not succesfull");
							}

							break;
						}
						case 2:{
							//update student_table set paid=5000,due=5000 where name='shivu';
							System.out.println("enter the name of the student to be updated");
							String name1=sc.next();
							System.out.println("enter the paid amount and due amount");
							int paid=sc.nextInt();
							int due=sc.nextInt();
							PreparedStatement ps2=connection.prepareStatement("update student_table set paid=?,due=? where name=?");
							ps2.setInt(1,paid);
							ps2.setInt(2,due);
							ps2.setString(3,name1);
							int s2=ps2.executeUpdate();
							if(s2>0) {
								System.out.println("update student succesfull!..");
							}
							else {
								System.out.println("update student is not succesfull.");
							}



							break;
						}
						case 3:{

							System.out.println("enter the student name to be deleted");
							String name2 = sc.next();
							PreparedStatement ps3=connection.prepareStatement("delete from student_table where name=?");
							ps3.setString(1,name2);
							int s3=ps3.executeUpdate();
							if(s3>0) {
								System.out.println("delete student succesfull!..");
							}
							else {
								System.out.println("delete student is not succesfull.");
							}
							break;
						}
						case 4:{
							
							List<Student> list=new ArrayList<>();
							
							PreparedStatement ps4=connection.prepareStatement("select name,due from student_table where due=0");
							ResultSet rs1=ps4.executeQuery();
							while(rs1.next()){
								Student st=new Student();
								st.setName(rs1.getString(1));
								st.setDue(rs1.getInt(2));
								list.add(st);
							}
							for(Student t : list) {
								System.out.println(t);
							}
						
							
							
							
							break;
						}
						case 5:{
							
							List<Student> list=new ArrayList<>();
							
							PreparedStatement ps4=connection.prepareStatement("select * from student_table where due>0");
							ResultSet rs1=ps4.executeQuery();
							while(rs1.next()){
								Student st=new Student();
								st.setRollno(rs1.getInt(1));
								st.setName(rs1.getString(2));
								st.setEmail(rs1.getString(3));
								st.setCourse(rs1.getString(4));
								st.setFee(rs1.getInt(5));
								st.setDue(rs1.getInt(6));
								st.setDue(rs1.getInt(7));
								st.setContactno(rs1.getString(8));
								list.add(st);
							}
							for(Student t : list) {
								System.out.println(t);
							}
						
							break;
						}
						case 6:{
							System.out.println("enter the student to be search");
							String name3=sc.next();
							PreparedStatement ps5=connection.prepareStatement("select * from student_table where name=?");
							ps5.setString(1,name3);
							ResultSet rs2=ps5.executeQuery();
							List<Student> list=new ArrayList<>();
							while(rs2.next()){
								Student st1=new Student();
								st1.setRollno(rs2.getInt(1));
								st1.setName(rs2.getString(2));
								st1.setEmail(rs2.getString(3));
								st1.setCourse(rs2.getString(4));
								st1.setFee(rs2.getInt(5));
								st1.setDue(rs2.getInt(6));
								st1.setDue(rs2.getInt(7));
								st1.setContactno(rs2.getString(8));;
								list.add(st1);
								
								for(Student t : list) {
									System.out.println(t);
								}
							}
							break;
						}
						case 7:{
							PreparedStatement ps6=connection.prepareStatement("select * from student_table");
							
							ResultSet rs3=ps6.executeQuery();
							List<Student> list=new ArrayList<>();
							while(rs3.next()){
								Student st1=new Student();
								st1.setRollno(rs3.getInt(1));
								st1.setName(rs3.getString(2));
								st1.setEmail(rs3.getString(3));
								st1.setCourse(rs3.getString(4));
								st1.setFee(rs3.getInt(5));
								st1.setDue(rs3.getInt(6));
								st1.setDue(rs3.getInt(7));
								st1.setContactno(rs3.getString(8));
								list.add(st1);
								
								for(Student t : list) {
									System.out.println(t);
								}
							}
							
							break;
						}
						case 8:{
							//System.exit(0);
							flag=1;
							break;
						}
						default:{
							System.out.println("enter 1 to 7 only");

						}

						}

					}
				}
				else {
					System.out.println("your login is unsuccesfull username or password is invalid pls try again");
				}

				break;

			}	
			case 3:{

				System.exit(0);
			}
			default:{
				System.out.println("enter only 1,2 & 3");


			}
			connection.close();
			sc.close();
			}
		}

	}
}
