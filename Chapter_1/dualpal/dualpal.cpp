/*
ID: 100021881
LANG: C++
PROG: dualpal
*/
#include <iostream>
#include <fstream>
#include <string>
#include <algorithm>

using namespace std;

int main() {
    ifstream fin("dualpal.in");
    ofstream fout("dualpal.out");
    int N, S;
    fin >> N >> S;
    int count = 0;
    for (int i = S+1; count < N; i++) {
        int numBases = 0;
        for (int base = 2; base <= 10; base++) {
            char buffer[33];
            itoa(i, buffer, base);
            string result(buffer);
            string reverse(result.rbegin(), result.rend());
            if (result==reverse) numBases++;
        }
        if (numBases>=2) {
            count++;
            fout << i <<  endl;
        }
    }
    exit(0);
}