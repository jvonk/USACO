/*
 ID: 100021881
 LANG: C++
 PROG: namenum
*/
#include <iostream>
#include <fstream>
#include <string>
using namespace std;

int main() {
    ofstream out("namenum.out");
    ifstream in("namenum.in");
    ifstream dict("dict.txt");
    string id;
    in >> id;
    bool found = false;
    string name;
    while (dict >> name) {
        if (name.length() == id.length()) {
            string number = "";
            for (int i = 0; i < name.length(); i++) {
                if (name[i] == 'Q' || name[i] == 'Z') goto label;
                if (name[i] < 'Q') number += ((name[i]-'A')/3) + '2';
                else number += ((name[i]-'Q')/3)+'7';
            }
            if (number!=id) label:continue;
            found = true;
            out << name << endl;
        }
    }
    if (!found) out << "NONE" << endl;
}