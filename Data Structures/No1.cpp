#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct Node {
    char nama[100];
    struct Node* next;
    struct Node* prev;
};

struct Node* head[10];
struct Node* tail[10];
struct Node* current;

int hash(char inputanNama[]) {
    int panjangString = strlen(inputanNama);
    int totalAscii = 0;
    for (int i = 0; i < panjangString; i++) {
        totalAscii += inputanNama[i];
    }
    return totalAscii % 10;
}

void pushHash(char inputanNama[]) {
    struct Node* newNode = (struct Node*)malloc(sizeof(struct Node));
    strcpy(newNode->nama, inputanNama);
    newNode->next = NULL;
    newNode->prev = NULL;

    int index = hash(inputanNama);

    if (head[index] == NULL) {
        head[index] = newNode;
        tail[index] = newNode;
    } else {
        tail[index]->next = newNode;
        newNode->prev = tail[index];
        tail[index] = newNode;
    }
}

void viewAll() {
    for (int i = 0; i < 10; i++) {
        printf("%d: ", i);
        if (head[i] == NULL) {
            printf("NULL\n");
        } else {
            current = head[i];
            while (current != NULL) {
                printf("%s -> ", current->nama);
                current = current->next;
            }
            puts("");
        }
    }
}

int main() {
    int pilih;
    char nama[255];
    
    for (int i = 0; i < 10; i++) {
        head[i] = NULL;
        tail[i] = NULL;
    }

    do {
        system("cls");
        printf("=====List Data=====\n");
        viewAll();
        printf("===================\n\n");
        printf("1. Push Hash\n");
        printf("2. Exit\n");
        printf("Choose: ");
        scanf("%d", &pilih);
        fflush(stdin);

        switch (pilih) {
            case 1:
                printf("Masukkan Nama : ");
                scanf(" %[^\n]", nama);
                fflush(stdin);
                pushHash(nama);
                printf("Nama Berhasil di input");
                getchar();
                break;
        }
    } while (pilih != 2);

    return 0;
}
