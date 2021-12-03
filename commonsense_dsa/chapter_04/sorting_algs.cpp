#include <iostream>
#include <cstdlib>

#define MAX 100
using namespace std;

int main()
{
    int arr[MAX];
    int n;
    
    cout << "Enter a number N: " << endl;
    
    cin >> n;

    // Filling up our array with random values
    for (int i = 0; i < n; i++)
    {
        arr[i] = rand();
    }


    // Outputting unsorted array
    for (int i = 0; i < n; i++)
    {
        cout << arr[i] << " ";
    }

    cout << endl;

    for (int i = 0; i < n; i++)
    {
        for (int j = i + 1; j < n; j++)
        {
            if (arr[j] > arr[i])
            {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }       
    }

    // Display the sorted array
    for (int i = 0; i < n; i++)
    {
        cout << arr[i] << " ";
    }

    cout << endl;
    return 0;
}