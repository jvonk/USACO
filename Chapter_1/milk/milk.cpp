/*
ID: 100021881
LANG: C++
PROG: milk
*/
#include <iostream>
#include <fstream>
#include <string>
#include <algorithm>
#include <vector>

using namespace std;

int main() {
    ifstream fin("milk.in");
    ofstream fout("milk.out");
    int total, numFarmers;
    fin >> total >> numFarmers;
    vector<pair<int, int>> farmers;
    for (int i = 0; i < numFarmers; i++) {
        int price, amount;
        fin >> price >> amount;
        farmers.push_back(make_pair(price, amount));
    }
    sort(farmers.begin(), farmers.end());
    int price = 0, i;
    for (i = 0; i < farmers.size() && total > 0; i++) {
        total-=farmers[i].second;
        price+=farmers[i].first*farmers[i].second;
    }
    if (total!=0) {
        price+=total*farmers[i-1].first;
    }
    fout << price << endl;
    exit(0);
}