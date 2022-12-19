import  java.util.*; 
public  class  caesarencrypt  {
    public  static  final  String  alpha  =  "abcdefghijklmnopqrstuvwxyz";
     public  static  String  encrypt(String  message,  int  shiftKey)  {
    message  =  message.toLowerCase(); String  cipherText  =  "";
    
    for  (int  ii  =  0;  ii  <  message.length();  ii++)  {
    int  charPosition  =  alpha.indexOf(message.charAt(ii)); int  keyVal  =  (shiftKey  +  charPosition)  %  26;
    char  replaceVal  =  alpha.charAt(keyVal); cipherText  +=  replaceVal;
    }
    return  cipherText;
    }
    
    public  static  void  main(String[]  args)  {
    
    Scanner  sc  =  new  Scanner(System.in); String  message  =  new  String();
    
    
    System.out.println("\nEnter  the  String  for  Encryption:  "); message  =  sc.nextLine();
    
    System.out.println("\nEnter  Shift  Key:  "); int  key  =  sc.nextInt();
    
    System.out.println("\nEncrpyted  Message:  "); System.out.println(encrypt(message,key));
    }
    }
