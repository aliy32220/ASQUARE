/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asquare;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Ali
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    //for signup button 1
    private Button btn1;
    //for login button 1
    public TextField Login_Username=new TextField();
    public PasswordField Login_Password=new PasswordField();
    String LU,LP;
    //for signup button 2
    public TextField First_Name=new TextField();
    public TextField Last_Name=new TextField();
    public TextField Age=new TextField();
    public TextField Phone_Number=new TextField();
    public TextField SignUp_Email=new TextField();
    public TextField SignUp_Password=new TextField();
    public TextField Bank_Account=new TextField();
    public TextField Current_Balance=new TextField();
    public CheckBox Check1=new CheckBox();
    public CheckBox Check2=new CheckBox();
    public CheckBox Check3=new CheckBox();
    String FN,LN,A,PN,SE,SP,BA,CB;
    //for search button
    public TextField City=new TextField();
    public TextField Area=new TextField();
    public TextField PriceRange_min=new TextField();
    public TextField PriceRange_max=new TextField();
    public TextField PlotSize_min=new TextField();
    public TextField PlotSize_max=new TextField();
    public TextArea ResultArea=new TextArea();
    public TextField P_id=new TextField();
    String C,A1,Pr_min,Pr_max,Ps_min,Ps_max;
    //for add button
    public TextField City1=new TextField();
    public TextField Area1=new TextField();
    public TextField Price1=new TextField();
    public TextField Plot1=new TextField();
    public TextField Phone1=new TextField();
    public TextField Seller_Email=new TextField();
    //for Done Button
    public TextArea Buyer_Area=new TextArea();
    public TextArea Seller_Area=new TextArea();
    public TextArea Property_Area=new TextArea();
    public TextArea Price_Area=new TextArea();
    public Advisor AD1=new Advisor();
    public User U1=new User();
    public Seller SE1;
    public Buyer B1=Buyer.getInstance();//Singleton PATTERN Because ther should be only one buyer at one time
    public Dealer D1=new Dealer();
    public int ID;
    public BankAccount BA1=new BankAccount();
    public BankAccount SA1=new BankAccount();
    public Property PROP;
    public int pro_id;
    public Payment P11;
    String S_E;
    String P_C,P_A,P_S,P_pid;
    String LU1,LP1,BA11,CB11,F_N1,L_N1;
    int T1,T2;
    @FXML
    private void handleLoginButtonAction(ActionEvent event) throws SQLException,IOException {
        LU=Login_Username.getText();
        LP=Login_Password.getText();
        Testdb a=new Testdb();
        Connection c=a.getConnection();
        PreparedStatement stmt;  
        stmt = c.prepareStatement("select email,password,first_name,last_name,bank_account,current_balance from clients where email like ? and password like ?");
        stmt.setString(1,LU);
        stmt.setString(2,LP);
        ResultSet rs=stmt.executeQuery();
        if(rs.next())
        { 
            Stage SignUp_stage;
            Parent SignUp_root;
            SignUp_stage=(Stage) btn1.getScene().getWindow();
            //load up OTHER FXML document
            SignUp_root = FXMLLoader.load(getClass().getResource("FXMLDocument2.fxml"));
            Scene SignUp_scene = new Scene(SignUp_root);
            SignUp_stage.setScene(SignUp_scene);
            SignUp_stage.show();
            LU1=rs.getString("email");
            LP1=rs.getString("password");
            F_N1=rs.getString("first_name");
            L_N1=rs.getString("last_name");
            BA11=rs.getString("bank_account");
            CB11=rs.getString("current_balance");  
            //System.out.println(B_A11);
            //System.out.println(C_B11);
            
            T1 = Integer.parseInt(BA11);
            T2 = Integer.parseInt(CB11);
            BA1=new BankAccount(T1,T2);
            B1.SetAccount(BA1);
            B1.Name=F_N1+L_N1;
        }
    }
    public void handleSignupButtonAction(ActionEvent event) throws IOException {
        Stage SignUp_stage;
        Parent SignUp_root;
        SignUp_stage=(Stage) btn1.getScene().getWindow();
        //load up OTHER FXML document
        SignUp_root = FXMLLoader.load(getClass().getResource("FXMLDocument1.fxml"));
        Scene SignUp_scene = new Scene(SignUp_root);
        SignUp_stage.setScene(SignUp_scene);
        SignUp_stage.show();
    }
    public void handleSignupButtonAction2(ActionEvent event) throws IOException, SQLException {
        
        FN=First_Name.getText();
        LN=Last_Name.getText();
        A=Age.getText();
        PN=Phone_Number.getText();
        SE=SignUp_Email.getText();
        SP=SignUp_Password.getText();
        BA=Bank_Account.getText();
        CB=Current_Balance.getText();
        //String user,password;
        // user=in_c.readLine();
        // System.out.println(user);
        // password=in_c.readLine();
        //System.out.println(password);
            
        Testdb a=new Testdb();
        Connection c=a.getConnection();
        PreparedStatement stmt;  
        stmt = c.prepareStatement("select email from clients where email like ?");
        stmt.setString(1, SE);
        ResultSet rs=stmt.executeQuery();
        if(rs.next())
        {  
            System.out.println("Username Already Exists");
        }
        else
        {
            // Connection c=a.getConnection();
            PreparedStatement stmt2;  
            stmt2 = c.prepareStatement("insert into clients (email,password,first_name,last_name,bank_account,current_balance) values (?,?,?,?,?,?)");
            //String query="insert into clients (email,password) values ('user','password')";
            stmt2.setString (1, SE);
            stmt2.setString (2, SP);
            stmt2.setString (3, FN);
            stmt2.setString (4, LN);
            stmt2.setString (5, BA);
            stmt2.setString (6, CB);
            //System.out.println(query);
            stmt2.execute();
            //rs = stmt2.getGeneratedKeys();
            if (Check1.isSelected())
            {
                U1.User_ID=SE;
                U1.Password=SP;
                /*if(rs != null && rs.next()){
                    U1.Account_No=rs.getInt(1);
                }*/
            }
            else if(Check2.isSelected())
            {
                D1.User_ID=SE;
                D1.Password=SP;
                /*if(rs != null && rs.next())
                {
                    D1.Account_No=rs.getInt(1);
                }*/
            }   
            else if(Check3.isSelected())
            {
                AD1.User_ID=SE;
                AD1.Password=SP;
                /*if(rs != null && rs.next())
                {
                    AD1.Account_No=rs.getInt(1);
                }*/
            }
        }
        Stage SignUp_stage;
        Parent SignUp_root;
        SignUp_stage=(Stage) btn1.getScene().getWindow();
        //load up OTHER FXML document
        SignUp_root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene SignUp_scene = new Scene(SignUp_root);
        SignUp_stage.setScene(SignUp_scene);
        SignUp_stage.show();
    }
    public void handleSearchButtonAction(ActionEvent event) throws IOException, SQLException {
        //INSERT INTO `asquare`.`properties` (`P_id`, `City`, `Area`, `Price`, `Size`) VALUES ('1', 'Islamabad', 'G-9', '35000', '4');
        String Phone2=null;
        String price=null;
        C=City.getText();
        A1=Area.getText();
        Pr_min=PriceRange_min.getText();
        Pr_max=PriceRange_max.getText();
        Ps_min=PlotSize_min.getText();
        Ps_max=PlotSize_max.getText();
        String R="P_ID"+" "+"City"+" "+ "Area" +" "+ "Price"+" "+"Size"+" "+"Phone_No"+"\n";
        ResultArea.setText(R);
        //ResultArea.setText("Hello");
        Testdb a=new Testdb();
        Connection c=a.getConnection();
        PreparedStatement stmt;  
        stmt = c.prepareStatement("select * from properties");// where City like ? and Area like ?");// and Price like ? and Size like ? and Phone_No like ?");
        //stmt.setString(1,C);
        //stmt.setString(2,A);
        //stmt.setString(3,price);
        //stmt.setString(4`,Ps_min);
        //stmt.setString(5,Phone2);
        ResultSet rs=stmt.executeQuery();
        while(rs.next())
        {   
            if (C.equals(rs.getString("City")))
            {
                if (A1.equals(rs.getString("Area")))
                {
                    //System.out.println("Entered while Statement!");
                    int Priceint1 = Integer.parseInt(Pr_min);
                    int Priceint2 = Integer.parseInt(Pr_max);
                    int Priceint3 =Integer.parseInt(rs.getString("Price"));
                    if(Priceint3>Priceint1 && Priceint3<Priceint2)
                    {   
                        ID=rs.getInt("P_id");
                        String ID_to_String = Integer.toString(ID);
                        String Result=ID_to_String+" "+rs.getString("City") +" "+ rs.getString("Area") +" "+ rs.getString("Price")+" "+rs.getString("Size")+" "+rs.getString("Phone_No")+"\n";
                        ResultArea.appendText(Result);
                    }
                    else
                    {
                        ResultArea.appendText("No Result Found!");
                    }
                }
            }
        }        
  }
    public void handleAddButtonAction(ActionEvent event) throws IOException, SQLException {
        //INSERT INTO `asquare`.`properties` (`P_id`, `City`, `Area`, `Price`, `Size`) VALUES ('1', 'Islamabad', 'G-9', '35000', '4');
        String C1,A2,Pr1,Ps1,Ph1;
        C1=City1.getText();
        A2=Area1.getText();
        Pr1=Price1.getText();
        Ps1=Plot1.getText();
        Ph1=Phone1.getText();
        S_E=Seller_Email.getText();
        //ResultArea.setText("Hello");
        Testdb a=new Testdb();
        Connection c=a.getConnection();
        PreparedStatement stmt2;  
        stmt2 = c.prepareStatement("insert into properties (City,Area,Price,Size,Phone_No,Email) values (?,?,?,?,?,?)");
        //String query="insert into clients (email,password) values ('user','password')";
        stmt2.setString (1, C1);
        stmt2.setString (2, A2);
        stmt2.setString (3, Pr1);
        stmt2.setString (4, Ps1);
        stmt2.setString (5, Ph1);
        stmt2.setString (6, S_E);
        System.out.println("Information added to Database");
        
        //System.out.println(query);
        stmt2.execute();
  }
    public void handleSignoutButtonAction(ActionEvent event) throws IOException {
        Stage SignUp_stage;
        Parent SignUp_root;
        SignUp_stage=(Stage) btn1.getScene().getWindow();
        //load up OTHER FXML document
        SignUp_root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene SignUp_scene = new Scene(SignUp_root);
        SignUp_stage.setScene(SignUp_scene);
        SignUp_stage.show();
    }
    public void handleSubmitButtonAction(ActionEvent event) throws IOException, SQLException {
        SE1=new Seller();
        String pid=P_id.getText();
        String E,E1;
        pro_id=Integer.parseInt(pid);
        Testdb a=new Testdb();
        Connection c=a.getConnection();
        PreparedStatement stmt;  
        stmt = c.prepareStatement("select P_id,City,Area,Size,Price,Email from properties where P_id like ?");
        stmt.setInt(1, pro_id);
        ResultSet rs=stmt.executeQuery();
        if(rs.next())
        {   
            //System.out.println("ENTERED");
            P_pid=Integer.toString(rs.getInt("P_id"));
            P_C=rs.getString("City");
            P_A=rs.getString("Area");
            P_S=rs.getString("Size");
            String F_N,L_N,Pass,B_A1,C_B1;
            int T11,T22;
            E=rs.getString("Email");
            PreparedStatement stmt1;  
            stmt1 = c.prepareStatement("select email,password,first_name,last_name,bank_account,current_balance from clients where email like ?");
            stmt1.setString(1, E);  
            ResultSet rs1=stmt1.executeQuery();
            if(rs1.next())
            {   
                F_N=rs1.getString("first_name");
                L_N=rs1.getString("last_name");
                Pass=rs1.getString("password");
                B_A1=rs1.getString("bank_account");
                C_B1=rs1.getString("current_balance"); 
                System.out.println(F_N);
                System.out.println(L_N);
                System.out.println(B_A1);
                System.out.println(C_B1);
                T11 = Integer.parseInt(B_A1);
                T22 = Integer.parseInt(C_B1); 
                SE1.setUserid(E);
                SE1.Password=Pass;
                SE1.setName(F_N + " "+ L_N);
                BankAccount TB=new BankAccount(T11,T22);
                SE1.SetAccount(TB);
                BankAccount TB1=new BankAccount(T1,T2);
                BA1=TB;
            }
            System.out.println("Entered");
            P11=new Payment(B1.Bank1,SE1.B2);
            P11.set_amount(Integer.parseInt(rs.getString("Price")));
            System.out.println("Buyer");
            System.out.println(B1.Name);
            System.out.println(B1.Bank1.Get_Account_Number());
            System.out.println(B1.Bank1.Get_Current_Balance());
            System.out.println("Seller");
            System.out.println(SE1.getName());
            System.out.println(SE1.B2.Get_Account_Number());
            System.out.println(SE1.B2.Get_Current_Balance());
            String Buyers_Text,Sellers_Text,Price_Text,Property_Text;
            int B_t2,B_t1;
            B_t1=B1.Bank1.Get_Account_Number();
            Buyers_Text=B1.Name+" "+Integer.toString(B_t1);
            B_t2=SE1.B2.Get_Account_Number();
            Sellers_Text=SE1.getName() +" "+Integer.toString(B_t2);
            Price_Text=Integer.toString(P11.Amount);
            PROP=new Property(P_C,P_A,P_S,Integer.parseInt(P_pid));
            Property_Text=PROP.City+" "+PROP.Area +" "+PROP.Size+" "+Integer.toString(PROP.P_id);
            Buyer_Area.setText(Buyers_Text);
            Seller_Area.setText(Sellers_Text);
            Price_Area.setText(Price_Text);
            Property_Area.setText(Property_Text);
        }       
    }
    public void handleDoneButtonAction(ActionEvent event) throws IOException {
        P11.Done_Payment();
        System.out.println("Sellers Account Balance");
        System.out.println(SE1.B2.Get_Current_Balance());
        System.out.println("Buyers Account Balance");
        System.out.println(B1.Bank1.Get_Current_Balance());
        //int Total=BA1.Get_Current_Balance()-P11.Amount;
        //System.out.println(Total);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }        
}
