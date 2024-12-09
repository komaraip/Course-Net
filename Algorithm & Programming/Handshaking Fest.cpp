#include <stdio.h>
#include <stdlib.h>
 
int main(){  

    long int t,angka,hasil;

	scanf("%ld", &t);
	for(int a = 0; a < t; a++){
		hasil = 0;
		scanf("%ld", &angka);
        for(int i = 0; i < angka; i++){
        	hasil = hasil + i;
        } printf("Case #%d: %ld\n", a+1,hasil);
	}
	
    return 0;  
}
