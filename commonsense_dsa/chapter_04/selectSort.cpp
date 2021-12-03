#include <iostream>
#include <cstdlib>

using namespace std;

void swap(int a[], int x, int y)
{
    int temp = a[x];
    a[x] = a[y];
    a[y] = temp;
}


int locOfSmallest(int a[], int s, int e)
{
    int i = s;
    int j = i;
    for (i; i <= e; i++)
    {
        if (a[i] < a[j])
        {
            j = i;
        }
    }

    return j;
}

void display(int a[], int n)
{
    for (int i = 0; i < n; i++)
    {
        cout << a[i] << ", ";
    }
    cout << endl;
}

void selectSort(int a[], int n)
{
    
    for (int i = 0; i < n-1; i++)
    {
        int j  = locOfSmallest(a, i, n - 1);
        swap(a, i, j);
    }
}

int main()
{
    int arr[] = {102, 33, 212, 23, 7, 44,206, 985, 156, 2, 4641};
    int size = sizeof(arr) / sizeof(int);

    display(arr, size);

    selectSort(arr, size);

    display(arr, size);
    return 0;
}