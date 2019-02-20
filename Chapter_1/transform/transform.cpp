/*
ID: 100021881
LANG: C++
PROG: transform
*/


#include <iostream>
#include <fstream>
#include <cstring>

using namespace std;

int n = 10;
char before[10][10], after[10][10], temp[10][10];

void rotate() {
    for(int r = 0; r < n; r++) {
        for(int c = 0; c < n; c++) {
            after[c][n - 1 - r] = temp[r][c];
        }
    }
}

void reflect() {
    for(int r = 0; r < n; r++) {
        for(int c = 0; c < n; c++) {
            after[r][n - 1 - c] = temp[r][c];
        }
    }
}

int eqboard() {
    for(int r = 0; r < n; r++) {
        for(int c = 0; c < n; c++) {
            if(before[r][c] != after[r][c]) {
                return 0;
            }
        }
    }
    return 1;
}

int main() {
    ofstream fout("transform.out");
    ifstream fin("transform.in");
    int change = 7;
    fin >> n;
    for(int r = 0; r < n; r++) {
        for(int c = 0; c < n; c++) {
            fin >> before[r][c];
        }
    }
    for(int r = 0; r < n; r++) {
        for(int c = 0; c < n; c++) {
            fin >> after[r][c];
        }
    }
    memcpy(temp, before, sizeof(before));
    for(int i = 1; i <= 3; i++) {
        rotate();
        if(eqboard()) {
            change=i;
            break;
        }
    }
    memcpy(temp, before, sizeof(before));
    reflect();
    for(int i = 0; i <= 3; i++) {
        if(eqboard()) {
            change=4+i>0;
            break;
        }
        rotate();
    }
    memcpy(temp, before, sizeof(before));
    if (eqboard()) {
        change=6;
    }
    fout << change << endl;
    exit(0);
}
