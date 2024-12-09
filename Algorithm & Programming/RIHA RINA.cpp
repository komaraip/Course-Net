#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(){

    long int t;
    
    scanf("%ld", &t);
    for(int i = 0; i < t; i++){
        char str1[4];
        char str2[4];
        scanf("%s\n", str1);
        scanf("%s", str2);
		printf("Case #%d: ", i+1);
        for(int i = 0; i < strlen(str1); i++){ 
            if (i == 0 && str1[i] != ' ') { 
                printf("%c", str1[i]);
            } else if (i == 1 && str1[i] != ' '){ 
                printf("%c", str1[i]);
            }
        }
        for(int i = 0; i < strlen(str2); i++){ 
            if (i == 0 && str2[i] != ' ') { 
                printf("%c", str2[i]);
            } else if (i == 1 && str2[i] != ' '){ 
                printf("%c", str2[i]);
            }
        }
        for(int i = 0; i < strlen(str1); i++){ 
            if (i == 2 && str1[i] != ' ') { 
                printf(" %c", str1[i]);
            } else if (i == 3 && str1[i] != ' '){ 
                printf("%c", str1[i]);
            }
        }
        for(int i = 0; i < strlen(str2); i++){ 
            if (i == 2 && str2[i] != ' ') { 
                printf("%c", str2[i]);
            } else if (i == 3 && str2[i] != ' '){ 
                printf("%c", str2[i]);
            }
        }
	}
    return 0;
}
