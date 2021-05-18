#include<stdio.h>
#include<stdlib.h>
#include<time.h>
#include<string.h>
#include<ctype.h>

#define NUM 3

void randNumber(int rand_ans[]);
int numCheck(const char user_ans[]);
int readUserAns(const char user_ans[], const int com_ans[], int user_eat, int user_bite);

int main(){

    int cnt = 1;
    int end = 1;
    int eat, bite, check;
    int com_ans[NUM];
    char buff[10];

    printf("***Hit & Blow***\n");
    randNumber(com_ans);

    while(end != 0){

        printf("%d回目の挑戦\n", cnt);
        printf("%dつの数字を入力してください >>>", NUM);
        scanf("%s", buff);
        check = numCheck(buff);

            switch (check){
                case 1:
                    printf("%dつの数字を入力してください\n", NUM);
                    break;
                case 2:
                    printf("数字以外の文字が含まれてます\n");
                    break;
                case 3:
                    printf("数字が重複しています\n");
                    break;
                default:
                    break;
        }
        int eat = readUserAns(buff, com_ans, eat, bite);

        if(eat == NUM){
            printf("Congratulations!! ゲームを終了します\n");
            end = 0;
        }
        cnt++;
    }
    return 0;
}

void randNumber(int rand_ans[]){

    int i, j, rand_num;
    srand((unsigned)time(NULL));

    for (i = 0; i < NUM; i++) {
        do {
            rand_num = rand() % 10;

            if(i == 0 && rand_num == 0){
                rand_num = rand() % 10 + 1;
            }
            for (j = 0; j < i; j++){

                if (rand_num == rand_ans[j]){
                    break;
                }
            }
        } while (j < i);

        rand_ans[i] = rand_num;
        //printf("%d", rand_ans[i]); //デバック用
    }
}

int numCheck(const char user_ans[]){

    if(strlen(user_ans) != NUM){
        return 1;
    }

    for(int i = 0; i < NUM; i++){
        if(!isdigit(user_ans[i])){
            return 2;
        }
        for(int j = 0; j < i; j++){
            if(user_ans[i] == user_ans[j]){
                return 3;
            }
        }
    }
    return 0;
}

int readUserAns(const char user_ans[], const int com_ans[], int user_eat, int user_bite){
    user_eat = 0;
    user_bite = 0;

    for(int i = 0; i < NUM; i++){
        for(int j = 0; j < NUM; j++){
            if(user_ans[i] == '0' + com_ans[j]){
                if(i == j){
                    user_eat++;
                }else{
                    user_bite++;
                }
            }
        }
    }
    printf("eat: %d, bite: %d\n",user_eat , user_bite);
    return user_eat;
}
