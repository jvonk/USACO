/*
ID: 100021881
LANG: C++
PROG: palsquare
*/
#include <iostream>
#include <fstream>
#include <string>
#include <algorithm>

using namespace std;

const string numbers = "0123456789ABCDEFG";
string convert (int input, int b) {
    string result = "";
    while (input > 0) {
        int remainder = input % b;
        result+=numbers[remainder];
        input-=remainder;
        input/=b;
    }
    string reverse(result.rbegin(), result.rend());
    return reverse;
}

int main() {
    ifstream fin("palsquare.in");
    ofstream fout("palsquare.out");
    int base;
    fin >> base;
    for (int i = 1; i <= 300; i++) {
        int s = i*i;
        string square = convert(s, base);
        string iterate = convert(i, base);
        string reverse(square.rbegin(), square.rend());
        if (reverse==square) fout << iterate << " " << square << endl;
    }
    exit(0);
}