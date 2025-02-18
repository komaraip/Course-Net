#include <stdio.h>
#include <string.h>
#include <stdlib.h>

struct Mahasiswa{
	char nama[255];
	int umur;
	Mahasiswa *next;
} *head , *tail , *curr;

void push(char nama[], int umur){
	curr = (Mahasiswa*)malloc(sizeof(Mahasiswa));
	strcpy(curr->nama, nama);
	curr->umur = umur;
	curr->next = NULL;

	if(head == NULL){
		head = tail = curr;
	}else{
		tail->next = curr;
		tail = curr;
	}
}

void pop(){
	if(head == NULL){
		printf("Tidak ada data!\n");
	} else {
		curr = head;
		head = head->next;
		free(curr);
		printf("Data berhasil dihapus\n");
	}
}

void view(){
	curr = head;
	if(head == NULL){
		printf("No Data!");
	}else{
		while(curr != NULL){
			printf("%s(%d) -> ", curr->nama, curr->umur);
			curr = curr->next;
		}
	}
}

int main(){
	int pilih, umur;
	char nama[255];
	
	do{
		system("CLS");
		printf("==========\n");
		printf("Queue List\n");
		printf("==========\n\n");
		view();
		printf("\n\n1. Push Queue\n");
		printf("2. Pop Queue\n");
		printf("3. Exit\n");
		printf("Choose: ");
		scanf("%d", &pilih);
		fflush(stdin);
		
		switch(pilih){
			case 1:
				printf("Masukkan Nama: ");
				scanf("%[^\n]", nama);
				fflush(stdin);
				printf("Masukkan Umur: ");
				scanf("%d", &umur);
				fflush(stdin);
				push(nama, umur);
				break;
			
			case 2:
				pop();
				break;
		}	
	} while(pilih != 3);

	return 0;
}
