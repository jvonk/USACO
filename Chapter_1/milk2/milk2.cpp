/*
ID: 100021881
LANG: C++
PROG: milk2
*/

#include <iostream>
#include <fstream>
#include <string>
#include <string>

using namespace std;

#define MAXMILKING 5000

typedef struct Milking  Milking;
struct Milking {
    int begin;
    int end;
};

inline int milkcmp(const void *va, const void *vb) {
    return (((Milking*)va)->begin - ((Milking*)vb)->begin);
}

int main(void) {
    ifstream fin("milk2.in");
    ofstream fout("milk2.out");
    int tmilk, tnomilk;
    int nmilking;
    fin >> nmilking;
    Milking milking[nmilking];
    for(int i = 0; i < nmilking; i++) {
        fin >> milking[i].begin >> milking[i].end;
    }
    qsort(milking, nmilking, sizeof(Milking), milkcmp);
    tmilk = 0;
    tnomilk = 0;
    int cur = 0;
    for(int i = 1; i < nmilking; i++) {
        if(milking[i].begin > milking[cur].end) {
            tnomilk = max(tnomilk,milking[i].begin - milking[cur].end);
            tmilk = max(tmilk,milking[cur].end - milking[cur].begin);
            cur = i;
        } else {
            milking[cur].end = max(milking[cur].end, milking[i].end);
        }
    }
    fout << max(tmilk,milking[cur].end - milking[cur].begin) << tnomilk;
    exit(0);
}
