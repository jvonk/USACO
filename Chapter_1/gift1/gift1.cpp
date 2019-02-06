/*/*
ID: 10002181
LANG: C++
TASK: gift1
*/

#include <iostream>
#include <fstream>
#include <string.h>

using namespace std;
#define MAXPEOPLE 10

int npeople;
string names[MAXPEOPLE];
int totals[MAXPEOPLE];

int lookup(string name) {
    for(int i = 0; i < npeople; i++) {
        if(name==names[i]) {
            return i;
        }
    }
}

int main() {
    int giver, receiver;
    ofstream fout("ride.out");
    ifstream fin("ride.in");
    int np;
    fin >> np;
    for(int i = 0; i < np; i++) {
        fin >> names[npeople++];
    }
    for(int i = 0; i < np; i++) {
        int amt, ng;
        string name;
        fin >> name >> amt >> ng;
        giver = lookup(name);
        for(int j = 0; j < ng; j++) {
            fin >> name;
            receiver = lookup(name);
            totals[giver] -= amt / ng;
            totals[receiver] += amt / ng;
        }
    }
    for(int i = 0; i < np; i++) {
        fout << names[i] << totals[i] << endl;
    }
    exit(0);
}
