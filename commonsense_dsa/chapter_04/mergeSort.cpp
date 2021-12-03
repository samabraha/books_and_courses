#include <iostream>

using namespace std;

void combine(int a[], int s, int m, int e)
{
    int* buffer = new int[e+1];

    int k = s;
    while(k <= e) {
        buffer[k] = a[k];
        k = k + 1;
    }

    int i = s;
    int j = m + 1;
    k = s;

    while (i <= m && j <= e) 
    {
        if(buffer[i] <= buffer[j])
        {
            a[k] = buffer[i];
            i = i+1;
        } 
        else
        {
            a[k] = buffer[j];
            j++;
        }
        k++;
    }

    while (i <= m)
    {
        a[k] = buffer[i];
        i++;
        k++;
    }

    while (j <= e)
    {
        a[k] = buffer[j];
        j++;
        k++;
    }
    
    delete[] buffer;
}

void mergeSort(int a[], int s, int e)
{
    if (s >= e)
    {
        return;
    }

    
    int m = (s + e) / 2;
    mergeSort(a, s, m);
    mergeSort(a, m+1, e);
    combine(a, s, m, e);
}

void mergeSort(int a[], int n)
{
    mergeSort(a, 0, n-1);
}

void display(int a[], int size) {
    for (int i = 0; i < size; i++)
    {
        cout << a[i] << ", ";
    }
    cout << endl;
}

int main()
{
    int a[] = {47, 11, 86, 12, 141, 2436, 1986, 1422, 4, 500, 541, 12, 46};
    int size = sizeof(a) / sizeof(int);

    display(a, size);
    mergeSort(a, size);
    display(a, size);

    return 0;
}