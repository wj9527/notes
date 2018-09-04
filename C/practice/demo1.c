#include <stdlib.h>
#include <stdio.h>

#define SIZE 10

void sort(int *arr,size_t size){
	for(int x = 0 ;x < size ; x++){
		for(int i = x ; i < size ; i++){
			if (*(arr + x) < *(arr + i)){
				int temp = *(arr + x);
				*(arr + x) = *(arr + i);
				*(arr + i) = temp;
			}
		}
	}
}
int main(void){

	int arr[SIZE] = {1,9,8,4,7,3,5,2,0,6,};

	sort(arr,SIZE);

	for(int x = 0 ;x < SIZE ; x++){
		printf("%d\n",arr[x]);
	}
}
