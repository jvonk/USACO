/*
ID: 100021881
LANG: C++11
PROG: fc
*/

#include <fstream>
#include <vector>
#include <iomanip>
#include <queue>
#include <ext/pb_ds/assoc_container.hpp>
#include <ext/pb_ds/tree_policy.hpp>


using namespace std;
using namespace __gnu_pbds;

typedef long long ll;
typedef vector<int> vi;
typedef pair<double, double> pdd;
typedef tree<int, null_type, less<int>, rb_tree_tag, tree_order_statistics_node_update> ordered_set;

#define FOR(i, a, b) for (int i = a; i < b; i++)
#define F0R(i, a) for (int i = 0; i < a; i++)

#define mp make_pair
#define pb push_back
#define f first
#define s second

const int MOD = 1000000007;
double PI = 4*atan(1);

vector<pdd> points;

bool comp(pdd a, pdd b) {
    double a1 = acos(a.f/sqrt(a.f*a.f+a.s*a.s));
    double b1 = acos(b.f/sqrt(b.f*b.f+b.s*b.s));
    return (a1<b1);
}

double ccw(pdd p1, pdd p2, pdd p3) {
    return (p2.f-p1.f)*(p3.s-p1.s)-(p2.s-p1.s)*(p3.f-p1.f);
}

double dist (pdd a, pdd b) {
    return sqrt((double)(a.f-b.f)*(a.f-b.f)+(a.s-b.s)*(a.s-b.s));
}

int main() {
    ifstream cin ("fc.in");
    ofstream cout ("fc.out");
    int N; cin >> N; points.resize(N+1);
    for (int i = 1; i <= N; i++) cin >> points[i].f >> points[i].s;
    for (int i = 2; i <= N; i++)
        if (points[i].s<points[1].s || (points[i].s == points[1].s && points[i].f < points[1].f))
            swap(points[1], points[i]);
    for (int i = 2; i <= N; i++) points[i].f -= points[1].f, points[i].s -= points[1].s;
    points[1] = mp(0, 0);
    sort(points.begin()+2, points.end(), comp);
    points[0] = points[N];

    int M = 1;
    for (int i = 2; i <= N; i++) {
        int j = i;
        while (ccw(points[M-1], points[M], points[j])<=0) {
            if (M>1) {
                M--;
                continue;
            }
            else if (j==N) break;
            else j++;
        }
        M++;
        swap(points[M], points[j]);
    }
    double ans = 0;
    for (int i = 0; i < M; i++) ans+=dist(points[i==0?M:i], points[i+1]);
    cout << fixed << setprecision(2) << ans << "\n";
}