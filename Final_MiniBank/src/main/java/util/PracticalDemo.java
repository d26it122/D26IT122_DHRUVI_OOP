package util;
import model.*;
import service.BankSession;
import exception.InvalidAmountException;

/** Small demonstration helper for concepts that are not needed on every menu transaction. */
public final class PracticalDemo {
    private PracticalDemo() { }
    public static void run() throws InvalidAmountException {
        System.out.println("\n--- Practical concepts demonstration ---");
        System.out.println("Mobile valid/invalid: "+Validator.isValidMobile("9876543210")+" / "+Validator.isValidMobile("12345"));
        System.out.println("Email valid/invalid: "+Validator.isValidEmail("student@example.com")+" / "+Validator.isValidEmail("wrong-email"));
        System.out.println("PAN valid/invalid: "+Validator.isValidPan("ABCDE1234F")+" / "+Validator.isValidPan("12345ABCDE"));
        System.out.println("IFSC valid/invalid: "+Validator.isValidIfsc("HDFC0001234")+" / "+Validator.isValidIfsc("BADIFSC"));
        Command command=CommandParser.parse("DEPOSIT AC0001 500");
        System.out.println("Parsed command: "+command.type()+", "+command.accountNumber()+", Rs. "+command.amount());

        Account sample=new SavingsAccount("Demo Customer",2000,500);
        WithdrawRule anonymousRule=new WithdrawRule(){@Override public boolean allow(Account account,long amount){return account.getBalance()-amount>=500;}};
        WithdrawRule lambdaRule=(account,amount)->account.getBalance()-amount>=500;
        System.out.println("Withdraw rule (anonymous class): "+anonymousRule.allow(sample,1000));
        System.out.println("Withdraw rule (lambda): "+lambdaRule.allow(sample,1000));
        if(sample instanceof SavingsAccount savings)System.out.println("instanceof: savings minimum balance = Rs. "+savings.getMinBalance());
        try(BankSession session=new BankSession()){System.out.println("Try-with-resources example is running.");}
        try {
            var field=Account.class.getDeclaredField("balance"); field.setAccessible(true); field.setLong(sample,-10);
            String[] errors=AnnotationValidator.validate(sample);
            for(String error:errors)System.out.println("Annotation validation: "+error);
        } catch(ReflectiveOperationException e){System.out.println("Reflection demo error: "+e.getMessage());}
        System.out.println("----------------------------------------");
    }
}
