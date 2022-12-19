#include<bits/stdc++.h>
using namespace std;

int main(){
    
int noOfRounds=5;
int l=4;
int m=4;
int n = l*m;
string plainText="0010011010110111";
string cipherText=plainText;
string masterKey ="001110101001010011010110";
int substitutionFrom[16]={0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
 int substitutionTo[16]={13,14,4,1,2,15,11,8,3,10,6,12,5,9,0,7};
int permutationFrom[16]={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
int permutationTo[16]={1,5,9,13,2,6,10,14,3,7,11,15,4,8,12,16};
cout<<"SPN Cipher implementation in C++."<<endl;

//loop over the rounds
for (int i = 1; i <= noOfRounds; i++)
{
    cout<<"-----------------------------------"<<endl;
    string roundKey;
    cout<<"Round "<<i<<endl;

    //generate keys according to the round
    int starting = ((4*i)-3);
    cout<<n<<" bits starting from "<<starting<<endl;
    int counter=0;
    for (int j = starting; j < (starting+n); j++)
    {
        if (j<masterKey.length())
        {
            roundKey.push_back(masterKey[j-1]);
        }
        else{
            roundKey.push_back(masterKey[counter]);
            counter++;
        }
    }
    cout<<"key for round "<<i<<" is :"<<roundKey<<endl;
    
    //apply XOR operator
    for (int j = 0; j < n; j++)
    {
        char charText = cipherText[j];
        char keyText = roundKey[j];
        int resultantTextInt = charText xor keyText;
        char resultantTextChar;
        if (resultantTextInt == 1)
        {
            resultantTextChar = '1';
        }else{
            resultantTextChar = '0';
        }
        //cout<<charText<<" xor "<<keyText<<" = "<<resultantTextChar<<endl;
    }

    //apply substitution cipher
    for (int j = 0; j < n; j=j+4)
    {
        string bit;
        for (int k = j; k < (j+4); k++)
        {
            bit.push_back(cipherText[k]);
        }
        //convert to decimal
        int keyDec = std::bitset<4>(bit).to_ulong();
        //cout<<"bit : "<<bit<<" = "<<keyDec<<endl;
        int replacementInt = substitutionTo[keyDec];
        std::string replacementBinString = std::bitset<4>(replacementInt).to_string();//converting to binary
        //cout<<replacementBinString<<endl;
        int count=0;
        for (int k = j; k < (j+4); k++)
        {
            cipherText[k] = replacementBinString[count];
            count++;
        }
        
    }
    
    //apply permutation cipher
    string tempText = cipherText;
     for (int j = 0; j < n; j++)
    {
        cipherText[(permutationFrom[i])] = tempText[(permutationTo[i])];
    }
    cout<<cipherText<<endl;
    cout<<"-----------------------------------"<<endl;

    
}
    cout<<"Plain text enterted : "<<plainText<<endl;
    cout<<"Encrypted Cipher text :"<<cipherText<<endl;

    return 0;
}
