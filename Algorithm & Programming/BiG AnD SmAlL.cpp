#include <stdio.h>
#include <ctype.h>

int main(){
	
    int t;
    scanf("%d", &t);

    while (t--) {
        char text[101];
        scanf("%s", text);

        int i;
        for (i = 0; text[i] != '\0'; i++) {
            if (i % 2 == 0) {
                text[i] = toupper(text[i]);
            } else {
                text[i] = tolower(text[i]);
            }
        }

        printf("%s\n", text);
    }

    return 0;
}
