import java.util.*;
import java.io.*;
import java.math.*;

public class HitandBlow {
    static Scanner sc = new Scanner(System.in);
    final static int answer_size = 3;
    static int eat = 0;
    static int bite = 0;
    static int cnt = 1;
    public static void main(String[] args) {
        
        System.out.println("*** Hit & Blow ***");
        int[] com_ans = randNumber();
        /*for(int a : com_ans){ //デバック用
            System.out.print(a);
        }*/
        int[] user_ans;

        while(true){
            user_ans = readUserAns();
            checkAns(com_ans,user_ans);
            cnt++;

            if(eat == answer_size){
                System.out.println("Congratulations!! ゲームを終了します");
                break;
            }
        }
    }

    public static int[] randNumber(){
        ArrayList<Integer> list = new ArrayList<>();
        int[] rand_ans = new int[answer_size];

        for(int i = 0; i < 10; i++){
            list.add(i);
        }

        while(true){
            Collections.shuffle(list);

            if(list.get(0) == 0){
                continue;
            }
            break;
        }

        for(int i = 0; i < rand_ans.length; i++){
            rand_ans[i] = list.get(i);
        }
        return rand_ans;
    }

    public static int[] readUserAns() {
        eat = 0;
        bite = 0;
        int[] ans = new int[answer_size];
        System.out.printf("%d回目のチャレンジ\n", cnt);
        String num;
        int user_num;

        for(int i = 0; i < ans.length; i++){

            try{
                System.out.printf("%dつ目の数字>>>",i+1);
                num = sc.nextLine();
                user_num = Integer.parseInt(num);
                ans[i] = user_num;
            } catch(NumberFormatException e){
                System.out.println("数字を入力してください");
                i--;
            }
        }
        return ans;
    }

    public static void checkAns(int[] com_ans, int[] user_ans){

        for(int i = 0; i < com_ans.length; i++){
            for(int j = 0; j < user_ans.length; j++){

                if(com_ans[j] == user_ans[i]){
                    if(j == i){
                        eat++;
                    }else{
                        bite++;
                    }
                }
            }
        }
        System.out.printf("eat: %d, bite: %d\n",eat,bite);
    } 
}
