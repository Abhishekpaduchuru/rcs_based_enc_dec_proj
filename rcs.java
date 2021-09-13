package rabinusingascii;
import java.io.*;
import java.util.*;
import java.lang.*;
public class AsciiRabin {
static int e = 2, n;
static int p, q;
static int[] cip=new int[100];
static char[] ch=new char[100];
static int[] newch=new int[100];
static int[] p1=new int[100];
 static int[] p2=new int[100];
static int[] p3=new int[100];
static int[] p4=new int[100];
static char ch1[]=new char[100];
static char ch2[]=new char[100];
static char ch3[]=new char[100];
 static char ch4[]=new char[100];
static String dec1="";
static String dec2="";

static String dec3="";
static String dec4="";
static int FindT(int a, int m, int n)
{
int r;
int[] y =new int[1] ;
int[] x =new int[1] ;
x[0]=a;
y[0]=1;
while (m > 0) {
r = m % 2;
FastExponention(r, n, y, x);
m = m / 2;
}
return y[0];
}
static void FastExponention(int bit, int n, int[] y, int[] x)
{
if (bit == 1)
y[0] = (y[0] * x[0]) % n;

x[0] = (x[0] * x[0]) % n;
}
static int Encryption(int PlainText)
{
int cipher = FindT(PlainText, e, n);
return cipher;
}
static int inverse(int a, int b)
{
int inv=0;
int q, r, r1 = a, r2 = b, t, t1 = 0, t2 = 1;
while (r2 > 0) {
q = r1 / r2;
r = r1 - q * r2;
r1 = r2;
r2 = r;
t = t1 - q * t2;
t1 = t2;
t2 = t;
}
if (r1 == 1)

inv = t1;
if (inv < 0)
inv = inv + a;
return inv;
}
static void Decryption(int Cipher)
{
int i=0;
 int P1, P2, P3, P4;
int a1, a2, b1, b2;
a1 = FindT(Cipher, (p + 1) / 4, p);
a2 = p - a1;
b1 = FindT(Cipher, (q + 1) / 4, q);
b2 = q - b1;
P1 = ChineseRemainderTheorem(a1, b1, p, q);
P2 = ChineseRemainderTheorem(a1, b2, p, q);
P3 = ChineseRemainderTheorem(a2, b1, p, q);
P4 = ChineseRemainderTheorem(a2, b2, p, q);
 p1[i]=P1;
 p2[i]=P2;

 p3[i]=P3;
 p4[i]=P4;
 dec1+=(char)p1[i];
 dec2+=(char)p2[i];
 dec3+=(char)p3[i];
 dec4+=(char)p4[i];
 i++;
}
static int ChineseRemainderTheorem(int a, int b, int m1, int m2)
{
int M, M1, M2, M1_inv, M2_inv;
int result;
M = m1 * m2;
M1 = M / m1;
M2 = M / m2;
M1_inv = inverse(m1, M1);
M2_inv = inverse(m2, M2);
result = (a * M1 * M1_inv + b * M2 * M2_inv) % M;
return result;
}

public static void FileWriting(String filename, String text) {
 PrintWriter outputstream=null;
 try {
 outputstream=new PrintWriter(new FileOutputStream(filename,false));
 }
 catch(FileNotFoundException e) {
 System.out.println("Error in opening file"+filename);
 System.exit(0);
 }
 outputstream.println(text);
 outputstream.close();
}
public static String FileReading(String filename) {
 Scanner inputstream=null;
 try {
 inputstream=new Scanner(new File(filename));
 }
 catch(FileNotFoundException e) {
 System.out.println("The file \""+filename+"\" could not be opened");
 System.exit(0);

 }
 String text="";
 int c=0;
 System.out.println("\n");
 while(inputstream.hasNextLine()) {
 text=inputstream.nextLine();
 }
 inputstream.close();
 return text;
}
public static void main(String args[])
{
Scanner sc=new Scanner(System.in);
 System.out.println("Enter the private keys p and q");
 p=sc.nextInt();
 q=sc.nextInt();
 n=p*q;
 System.out.println("Enter the filename that cointains the plaintext");
 sc=new Scanner(System.in);
String filename=sc.nextLine();
 String plaintext=FileReading(filename);

 System.out.println("Plaintext is = "+plaintext);
for(int i=0;i<plaintext.length();i++) {
 char ch=plaintext.charAt(i);
 int PlainText=(int)ch;
 int Cipher = Encryption(PlainText);
 cip[i]=Cipher;
 }
System.out.print("Ciphertext is = ");
String ciphertext="";
 for(int i=0;i<plaintext.length();i++) {
 System.out.print(cip[i]+" ");
 ciphertext+=cip[i]+" ";
 }
 System.out.println();
System.out.println("Enter the filename where the ciphertext is to be
stored: ");
 sc=new Scanner(System.in);
 filename=sc.nextLine();
 FileWriting(filename,ciphertext);
 for(int i=0;i<plaintext.length();i++) {
 Decryption(cip[i]);

 }
 System.out.println("dec1 = "+dec1);
 System.out.println("dec2 = "+dec2);
 System.out.println("dec3 = "+dec3);
 System.out.println("dec4 = "+dec4);
 String decrypted_str="";
 for(int i=0;i<plaintext.length();i++) {
 if((dec1.charAt(i)>='A'&&dec1.charAt(i)<='Z')||(dec1.charAt(i)=='
')||(dec1.charAt(i)>='0'&&dec1.charAt(i)<='9')) {
 decrypted_str+=dec1.charAt(i);
 }
 if((dec2.charAt(i)>='A'&&dec2.charAt(i)<='Z')||(dec2.charAt(i)=='
')||(dec2.charAt(i)>='0'&&dec2.charAt(i)<='9')) {
 decrypted_str+=dec2.charAt(i);
 }
 if((dec3.charAt(i)>='A'&&dec3.charAt(i)<='Z')||(dec3.charAt(i)=='
')||(dec3.charAt(i)>='0'&&dec3.charAt(i)<='9')) {
 decrypted_str+=dec3.charAt(i);
 }
 if((dec4.charAt(i)>='A'&&dec4.charAt(i)<='Z')||(dec4.charAt(i)=='
')||(dec4.charAt(i)>='0'&&dec4.charAt(i)<='9')) {
 decrypted_str+=dec4.charAt(i);
 }
 }
 System.out.println("Enter the filename where the decryptrd String is to be
stored: ");
 sc=new Scanner(System.in);
 filename=sc.nextLine();
 FileWriting(filename,decrypted_str);
 System.out.println("\nDecrypted String = "+decrypted_str);
}