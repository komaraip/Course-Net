#include <stdio.h>
#include <stdlib.h>

struct Node {
    int data;
    struct Node* left;
    struct Node* right;
};

struct Node* createNode(int data) {
    struct Node* newNode = (struct Node*)malloc(sizeof(struct Node));
    
    if (newNode == NULL) {
        exit(1);
    }
    
    newNode->data = data;
    newNode->left = NULL;
    newNode->right = NULL;
    
    return newNode;
}

struct Node* insertNode(struct Node* root, int data) {
    if (root == NULL) {
        printf("Data berhasil di input\n\n");
		return createNode(data);
    }
    
    if (data < root->data) {
        root->left = insertNode(root->left, data);
    } else if (data > root->data) {
        root->right = insertNode(root->right, data);
    } else {
        printf("Insert gagal, data sudah ada\n\n");
    }
    
    return root;
}

void preorderTraversal(struct Node* root) {
    if (root != NULL) {
        printf("%d ", root->data);
        preorderTraversal(root->left);
        preorderTraversal(root->right);
    }
}

void inorderTraversal(struct Node* root) {
    if (root != NULL) {
        inorderTraversal(root->left);
        printf("%d ", root->data);
        inorderTraversal(root->right);
    }
}

void postorderTraversal(struct Node* root) {
    if (root != NULL) {
        postorderTraversal(root->left);
        postorderTraversal(root->right);
        printf("%d ", root->data);
    }
}

void freeBinaryTree(struct Node* root) {
    if (root != NULL) {
        freeBinaryTree(root->left);
        freeBinaryTree(root->right);
        free(root);
    }
}

int main() {
    struct Node* root = NULL;
    int choice, num;

    while(1) {
        printf("Binary Tree\n");
        printf("1. Push data\n");
        printf("2. View Preorder\n");
        printf("3. View Inorder\n");
        printf("4. View Postorder\n");
        printf("5. Exit\n");
        printf("Choose: ");
        scanf("%d", &choice);

        if (choice < 1 || choice > 5) {
        	printf("\n");
            continue;
        }

        switch(choice) {
            case 1:
                printf("Masukkan Angka : ");
                scanf("%d", &num);
                root = insertNode(root, num);
                break;
            case 2:
                printf("Preorder: ");
                preorderTraversal(root);
                printf("\n\n");
                break;
            case 3:
                printf("Inorder: ");
                inorderTraversal(root);
                printf("\n\n");
                break;
            case 4:
                printf("Postorder: ");
                postorderTraversal(root);
                printf("\n\n");
                break;
            case 5:
                freeBinaryTree(root);
                exit(0);
        }
    }

    return 0;
}

