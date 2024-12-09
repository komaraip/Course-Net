#include <stdio.h>

int sumNumber(int num) {
    int sum = 0;
    while (num > 0) {
        sum += num % 10;
        num /= 10;
    }
    return sum;
}

int main() {
    int T;
    scanf("%d", &T);
    
    for (int i = 1; i <= T; i++) {
        int A, B;
        scanf("%d %d", &A, &B);
        
        int result = sumNumber(A) * sumNumber(B);
        printf("Case #%d: %d\n", i, result);
    }
    
    return 0;
}

