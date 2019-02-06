/*
ID: 10002181
LANG: C++
TASK: ride
*/

#include <iostream>
#include <fstream>
#include <string>
using namespace std;

int main() {
    ofstream fout("ride.out");
    ifstream fin("ride.in");
    string str[2];
    int res[2] = {1, 1};
    fin >> str[0] >> str[1];
    for(int x = 0; x < 2; x++) {
        for(int i = 0; i < str[x].size(); i++) {
            res[x] *= str[x][i] - 'A' + 1;
        }
    }
    fout << (res[0] % 47 == res[1] % 47 ? "GO" : "STAY") << endl;
    exit(0);
}
