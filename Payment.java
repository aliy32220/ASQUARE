package asquare;
public class Payment
{
    public int Amount;
    public BankAccount From;
    public BankAccount To;
    Payment(BankAccount b,BankAccount s)
    {
        From = b;
        To = s;
    }
    int Get_Amount()
    {
        return Amount;
    }
    void set_amount(int a)
    {
        this.Amount=a;
    }
    void Done_Payment()
    {
        From.UpdateBalance(Amount, 1);
        To.UpdateBalance(Amount, 0);
    }
}
