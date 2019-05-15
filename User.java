package asquare;
public class User 
{
public  String Password;
public  String User_ID;
public int Account_No;

int GetAcc()
    {
	return Account_No;
    }
String getUserid()
{
    return this.User_ID;
}
void setUserid(String u)
{
    this.User_ID=u;
}
}