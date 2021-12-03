#include <iostream>

using namespace std;

void insertIth(int a[], int n, int i) {
    int key = a[i];
    int j = i - 1;

    while (j >= 0 && a[j] > key)
    {
        a[j+1] = a[j];
        j = j-1;
    }
    a[j+1] = key;
}

void insertSort(int a[], int n)
{
    int i = 1;
    while (i < n)
    {
        insertIth(a, n, i);
        i++;
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
    int arr[] = {47, 73, 444, 12, 95, 120, 563, 60, 36, 6, 408, 42, 3250, 363, 9};
    int size = sizeof(arr) / sizeof(int);
    
    display(arr, size);
    insertSort(arr, size);
    display(arr, size);

    return 0;
}