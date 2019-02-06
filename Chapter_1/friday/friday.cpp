/*
ID: 10002181
LANG: JAVA
TASK: friday
*/

#include <iostream>
#include <fstream>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>
using namespace std;

int mtab[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

int main() {
    int ndow[7];
    ofstream fout("friday.out");
    ifstream fin("friday.in");
    int n;
    fin >> n;
    int dow = 0; /* day of week: January 13, 1900 was a Saturday = 0 */
    for(int y = 1900; y < 1900 + n; y++) {
        for(int m = 0; m < 12; m++) {
            ndow[dow]++;
            int leap = m == 1 && y % 4 == 0 && (y % 100 != 0 || y % 400 == 0);
            dow = (dow + mtab[m] + leap) % 7;
        }
    }
    for(int i = 0; i < 6; i++) {
        fout << ndow[i]+" ";
    }
    fout << ndow[6] << endl;
    exit(0);
}
