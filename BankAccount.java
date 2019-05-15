/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asquare;

/**
 *
 * @author Ali
 */
public class BankAccount {
    private int BankAccountNumber;
    private int CurrentBalance;
    BankAccount()
    {
        BankAccountNumber=0;
        CurrentBalance=0;
    }
    BankAccount(int i, int i2)
    {
        BankAccountNumber=i;
        CurrentBalance=i2;
    }
    int Get_Account_Number()
    {
        return BankAccountNumber;
    }
    int Get_Current_Balance()
    {
        return CurrentBalance;
    }
    void set_Account_Number(int acc)
    {
        BankAccountNumber=acc;
    }
    void set_Account_Balance(int bal)
    {
        CurrentBalance=bal;
    }
    void UpdateBalance(int amount,int C)
    {
        if(C==1)//If C==1 then User is buying Property and Amount is Subtracted from Current Balance
        {
            CurrentBalance=CurrentBalance - amount;
        }
        else if(C==0)//If C==0 then User is Selling Property and Amount is Added from Current Balance
        {
            CurrentBalance=CurrentBalance + amount;
        }
    }
}
