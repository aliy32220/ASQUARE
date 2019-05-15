package asquare;
public class Buyer extends User 
{
public String Name;
public BankAccount Bank1=new BankAccount(); 
static Buyer B;
private Buyer()
{
    
}
Buyer(String n)
{
    Name=n;
}
public void SetAccount(BankAccount b)
{
    Bank1=b;
}
public static Buyer getInstance()
{
    if(B==null)
    {
        B=new Buyer();
    }
    return B;    
}
void AssignAdvisor(Advisor A)
{
    Advisor A1=new Advisor();
    A1=A;
}
}
