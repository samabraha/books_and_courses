#include <iostream>

using namespace std;

void swap(int a[], int x, int y)
{
    int temp = a[x];
    a[x] = a[y];
    a[y] = temp;
}

void bubble(int a[], int n)
{
    for (int i = n - 1; i > 0; i--)
    {
        if (a[i] < a[i - 1])
        {
            swap(a, i, i-1);
        }
    }
}

void bubbleSort(int a[], int n)
{
    for (int i = 0; i < n - 1; i++)
    {
        bubble(a, n);
    }
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
    int arr[] = {416, 14, 34, 35, 22, 303, 126, 4000, 1, 634, 12, 33, 36, 95, 54};
    int size = sizeof(arr) / sizeof(int);
    display(arr, size);
    bubbleSort(arr, size);
    display(arr, size);
    return 0;
}