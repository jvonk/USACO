/*
 ID: 100021881
 LANG: C++
 PROG: beads
*/

#include <iostream>
#include <fstream>
#include <string>
#include <algorithm>

using namespace std;

int main() {
    ofstream fout("ride.out");
    ifstream fin("ride.in");
    int n, res;
    string s;
    fin >> n >> s;
    s = s + s;
    for(int i = 0; i < n; i++) {
        char c = s[i];
        int current = 0;
        int j = i;
        for (int state = c!='w'; state <= 2; state++) {
            for (; j < n + i && (s[j] == c || s[j] == 'w'); j++) {
                current++;
            }
            c = s[j];
        }
        res = max(res, current);
    }
    fout << res << endl;
    exit(0);
}
