package assignment;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

//FRONTEND(JAVA)+BACKEND(MYSQL) INTEGRATION::::

/*
 * JDBC::
 * --java.sql packaage
 * --Type 4 driver is used!
 * 1.Establish the DB connection
 * 2.Execute SQL statements
 * 3.Retrieve the results
 * 4.Close DB CONNECTION
 */

/*
 * Database Drivers:::
 * 
▪ Database Drivers or JDBC Drivers are required to 
connect to different databases. The JDBC requires 
different drivers for each database
▪ JDBC drivers provide the connection to the 
database and implement the protocol necessary 
for sending queries and retrieving results
 */
/*
 * Database Drivers Types:::
 * 
Type 1 JDBC ODBC Bridge + ODBC Driver::

▪ Translates all JDBC calls into ODBC (Open Database Connectivity)
▪ Need to have ODBC client installed on the machine
In Java 8, the JDBC-ODBC Bridge has been removed.

Type 2 Native API / Partly Java technology enabled driver:::

--Converts JDBC calls into db-specific calls 
▪ Communicates directly with the db server
▪ Requires some binary code be present on the client machine. 
▪ Better performance than type 1 driver

Type 3 Pure Java Driver for Database Middleware::

Net-protocol – 100% Java driver follows a three-tiered approach
▪ JDBC database requests passed to the middle-tier server
▪ Middle-tier server translates the request to the database-specific native-connectivity interface 
▪ May use a type 1 or type 2 JDBC driver 
▪ Request forwarded to the database server

Type 4 Direct to Database Pure Java Driver::
		--Native protocol 100% Java
		--Converts JDBC calls into the vendor specific DBMS protocol
		--Client applications communicate directly with the database server	
		--Best performance
		--Need a different driver for each database
 */

/*
 * | Interface               | Description                                                      |
| ----------------------- | ---------------------------------------------------------------- |
| **`Driver`**            | Interface implemented by database drivers (e.g., MySQLDriver).   |
| **`Connection`**        | Represents a connection to the database.                         |
| **`Statement`**         | Used to execute **static SQL queries** (no parameters).          |
| **`PreparedStatement`** | Used for **precompiled SQL queries** with parameters.            |
| **`CallableStatement`** | Used to call **stored procedures**.                              |
| **`ResultSet`**         | Represents a table of data returned by executing SQL queries.    |
| **`ResultSetMetaData`** | Provides metadata about a `ResultSet` (column names, types).     |
| **`DatabaseMetaData`**  | Provides metadata about the database itself.                     |
| **`Savepoint`**         | Marks a point in a transaction to rollback to.                   |
| **`Blob`, `Clob`**      | Used to handle binary (Blob) and character (Clob) large objects. |

 */
/*
 * | Class                           | Description                                                   |
| ------------------------------- | ------------------------------------------------------------- |
| **`DriverManager`**             | Manages JDBC drivers and provides connections to databases.   |
| **`Types`**                     | Contains constants for SQL types (like `VARCHAR`, `INTEGER`). |
| **`Date`, `Time`, `Timestamp`** | JDBC-specific classes for handling SQL date/time types.       |
*/
 /*
  * C:\Users\Administrator>mysql -u root -p
Enter password:
Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 3
Server version: 5.7.39-log MySQL Community Server (GPL)

Copyright (c) 2000, 2022, Oracle and/or its affiliates.

Oracle is a registered trademark of Oracle Corporation and/or its
affiliates. Other names may be trademarks of their respective
owners.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

mysql> show databases;
+--------------------+
| Database           |
+--------------------+
| information_schema |
| comcast2023        |
| comcast_march      |
| mysql              |
| performance_schema |
| sys                |
| vwits              |
| vwits2024          |
| wipro              |
+--------------------+
9 rows in set (0.01 sec)

mysql> create database wipro2025;
Query OK, 1 row affected (0.01 sec)

mysql> use wipro2025;
Database changed
mysql> create table wipro_emps(eno int,name varchar(20),salary float,dept varchar(20));
Query OK, 0 rows affected (0.07 sec)

mysql> desc wipro_emps;
+--------+-------------+------+-----+---------+-------+
| Field  | Type        | Null | Key | Default | Extra |
+--------+-------------+------+-----+---------+-------+
| eno    | int(11)     | YES  |     | NULL    |       |
| name   | varchar(20) | YES  |     | NULL    |       |
| salary | float       | YES  |     | NULL    |       |
| dept   | varchar(20) | YES  |     | NULL    |       |
+--------+-------------+------+-----+---------+-------+
4 rows in set (0.01 sec)

mysql> insert into wipro_emps values(1001,'Thananya',34932.32,'HR');
Query OK, 1 row affected (0.01 sec)

mysql> insert into wipro_emps values(1002,'Abi',39932.32,'Training');
Query OK, 1 row affected (0.01 sec)

mysql> insert into wipro_emps values(1003,'Devi',89032.32,'HR');
Query OK, 1 row affected (0.01 sec)

mysql> insert into wipro_emps values(1004,'Sai',9000032.32,'Testing');
Query OK, 1 row affected (0.01 sec)

mysql> select * from wipro_emps;
+------+----------+---------+----------+
| eno  | name     | salary  | dept     |
+------+----------+---------+----------+
| 1001 | Thananya | 34932.3 | HR       |
| 1002 | Abi      | 39932.3 | Training |
| 1003 | Devi     | 89032.3 | HR       |
| 1004 | Sai      | 9000030 | Testing  |
+------+----------+---------+----------+
4 rows in set (0.00 sec)

  */

//*************download mysql-connector-java-5.1.16.jar add to the project
//Right click project->build path->configure build path->libraries tab->add external jars->add the jar file 
//Referenced Libraries folder will be automatically created in eclipse project

public class JDBCDemo {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try
		{
			//loading the mysql driver
			//drivername is case sensitive
			

			/*
			 * Since JDBC 4.0, explicitly registering the driver is optional. 
			 * We just need to put vender's Jar in the classpath, 
			 * and then JDBC driver manager can detect and load the driver automatically.
			 */

			//classname.methodname=>static method
			//Class.forName("com.mysql.jdbc.Driver");  //type 4 mysql driver

			//3306-DEFAULT mysql port number
			//wipro2025:: mysql database name
			//username:root
			//my password is empty
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/wipro2025","root","Nut5823@");
			
			//con=DriverManager.getConnection("jdbc:mysql:@127.0.0.1:3306/wipro","root","");//valid
			st=con.createStatement();//build sql statements
			rs=st.executeQuery("select * from wipro_emps");//ResultSet means rows and columns
			while(rs.next())//iterate the records one by one!
			{
				//EITHER COLUMN NAME OR COLUMN POSITION INDEX 1,2,3....
				int a=rs.getInt("eno");//column name
				String b=rs.getString(2);//2nd column=>name
				float c=rs.getFloat("salary");//float c=rs.getFloat(3);
				String d=rs.getString("dept");

				System.out.println(a+ " "+b + " "+c + " "+d );
				
			}
		}
		catch(Exception e)//SQLException is a checked exception
		{
			e.printStackTrace();
		}

		finally
		{
			try
			{
				if(rs!=null) 
					rs.close();
				if(st!=null)
					st.close();
				if(con!=null)
					con.close();
			}
			catch(Exception e)
			{
				System.out.println("Finally Block::"+e);
			}
		
		}




	}

}
/*
1001 Thananya 34932.3 HR
1002 Abi 39932.3 Training
1003 Devi 89032.3 HR
1004 Sai 9000030.0 Testing
*/