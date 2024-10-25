// Online C++ compiler to run C++ program online
#include <bits/stdc++.h>
using namespace std;

int Mutal[6][3] = {{1,3,9},{1,9,3},{3,1,9},{3,9,1},{9,1,3},{9,3,1}};
int DP[61][61][61] = {0,};
int SCV[3] = {0,};
int N;
int dfs(int one, int two, int three)
{
    if(one < 0)
    {
        return dfs(0,two,three);
    }
    if(two < 0)
    {
        return dfs(one,0,three);
    }
    if(three < 0)
    {
        return dfs(one,two,0);
    }
    
    if(DP[one][two][three] != 0)
    {
        return DP[one][two][three];
    }
    
    if(one == 0 && two == 0 && three == 0)
    {
        return 0;
    }
    
    
    DP[one][two][three] = 999999;
    for(int i = 0; i<6; i++)
    {
        DP[one][two][three] = min(DP[one][two][three], dfs(one-Mutal[i][0],two-Mutal[i][1],three-Mutal[i][2])+1);
        
    }
    
    //return 0;
    return DP[one][two][three];
}
int main() {
ios_base::sync_with_stdio(0);
cin.tie(0);
cout.tie(0);    
    cin >> N;
    for(int i = 0; i<N; i++)
    {
        cin >> SCV[i];
    }
    
    int a = SCV[0];
    int b = SCV[1];
    int c = SCV[2];
    
    dfs(a,b,c);
    
    cout << DP[a][b][c];
    

    return 0;
}