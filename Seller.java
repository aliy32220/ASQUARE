package asquare;
public class Seller extends User
{
public String Name;
public BankAccount B2;

public Seller()
{
    this.Account_No=0;
    this.B2=new BankAccount();
    this.Name=null;
}

void setName(String n){
    this.Name=n;
}
String getName()
{
    return this.Name;
}
void SetAccount(BankAccount b)
{
    B2.set_Account_Balance(b.Get_Current_Balance());
    B2.set_Account_Number(b.Get_Account_Number());
}
Advisor Assign_Advisor(){
    Advisor A=new Advisor();
    return A;
}
}
