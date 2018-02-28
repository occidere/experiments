#include<iostream>
#include<cstdio>
#include<algorithm>
using namespace std;

int n, k, arr[1000010], minDiff, cnt;

int getDiff(int a, int b){
    return abs(a+b-k);
}

void cmpMin(int diff){
    if(diff == minDiff) cnt++;
    else if(diff < minDiff){
        cnt = 1;
        minDiff = diff;
    }
}

void calc(int x){
    int *pos = lower_bound(arr, arr+n, k-arr[x]), idx = pos-arr;
    
    if(idx == x){ //myself
        if(idx>0) cmpMin(getDiff(arr[idx], arr[idx-1]));
        if(idx<n-1) cmpMin(getDiff(arr[idx], arr[idx+1]));
    }
    else{ // idx != x
        cmpMin(getDiff(arr[x], arr[idx]));
        if(idx>0) cmpMin(getDiff(arr[x], arr[idx-1]));
        if(idx<n-1) cmpMin(getDiff(arr[x], arr[idx+1]));
    }
}

int main(){
    int i, t;
    for(scanf("%d", &t);t--;){
        scanf("%d%d", &n, &k);
        for(i=0;i<n;i++) scanf("%d", arr+i);
        sort(arr, arr+n);
        minDiff = 0x7fffffff;
        cnt = 0;
        
        for(i=0;i<n;i++) calc(i);
        
        printf("%d\n", cnt%2 ? cnt : (cnt>>1));
    }
}
