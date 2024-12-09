#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>
#include <time.h>

void looping_menu(){
	int jumlah_deret, selisih;
		
	printf("Insert 2 number (a b) : ");
	scanf("%d %d", &jumlah_deret,&selisih);
			
	int bilangan = 1;
	for (int i = 0; i < jumlah_deret; i++) {
		printf("%d ", bilangan);
		bilangan += selisih;
	}
			
	printf("\n\n");
}

void initial_name() {
    char nama[51];

    printf("Insert name [5-50] : ");
    scanf(" %[^\n]", nama);

    int panjang_nama = strlen(nama);

    if (panjang_nama < 5 || panjang_nama > 50) {
        printf("Panjang nama tidak valid.\n");
        return;
    }

    srand(time(NULL));

    char huruf1 = 'A' + rand() % 26;
    char huruf2 = 'A' + rand() % 26;

    printf("%c%c", huruf1, huruf2);

    int i;
    for (i = 0; i < panjang_nama; i++) {
        if (i == 0 || (i > 0 && nama[i - 1] == ' ')) {
            printf("%c", toupper(nama[i]));
        }
    }

    printf("\n\n");
}

void triangle(){

    int tinggi;
	int baris,kolom,spasi;

    printf("Insert triangle height : ");
    scanf("%d", &tinggi);

    for (baris = 1; baris <= tinggi; baris++) {
        for (spasi = tinggi - baris; spasi > 0; spasi--) {
            printf(" ");
        }

        for (kolom = 1; kolom <= 2 * baris - 1; kolom++) {
            printf("*");
        }

        printf("\n\n");
    }
}

void grade() {

    int score;
	char grade;
	
    printf("Insert your algo score: ");
    scanf("%d", &score);

    if (score < 0 || score > 100) {
        return grade();
    }

    if (score >= 90) {
        grade = 'A';
        printf("Your grade : %c", grade);
    } else if (score >= 85) {
    	grade = 'A';
        printf("Your grade : %c-", grade);
    } else if (score >= 80) {
    	grade = 'B';
        printf("Your grade : %c+", grade);
    } else if (score >= 75) {
        grade = 'B';
        printf("Your grade : %c", grade);
    } else if (score >= 70) {
    	grade = 'B';
        printf("Your grade : %c-", grade);
    } else if (score >= 65) {
        grade = 'C';
        printf("Your grade : %c", grade);
    } else if (score >= 50) {
        grade = 'D';
        printf("Your grade : %c", grade);
    } else if (score > 0) {
        grade = 'E';
        printf("Your grade : %c", grade);
    } else {
        grade = 'F';
        printf("Your grade : %c", grade);
    }
    
    printf("\n\n");
}

int main(){
	
	printf("Menu:\n");
    printf("1. Looping menu\n");
    printf("2. Initial name\n");
    printf("3. Triangle\n");
    printf("4. Grade\n");
    printf("5. Exit\n");
    
    while(1){
        int choice;
        printf("Choose menu [1..5] :\n");
        if (scanf("%d", &choice) != 1) {
            while (getchar() != '\n') {}
            continue;
        }

        switch(choice){
            case 1:
			    looping_menu();
                break;
            case 2:
                initial_name();
                break;
            case 3:
                triangle();
                break;
            case 4:
                grade();
                break;
            case 5:
                printf("Thank you!\n");
                return 0;
        }
    }
}
