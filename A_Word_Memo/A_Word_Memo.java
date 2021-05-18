import java.io.*;
import java.util.*;
public class A_Word_Memo {
    static Scanner sc = new Scanner(System.in);
    static Scanner sc2 = new Scanner(System.in);
    static List<String> list = new ArrayList<>(); 
    public static void main(String[] args) {

        File file = new File("note.txt");
        if(file.exists()){
            readnote(file); //note.txtのファイル読み込み
        }
        System.out.println("A word memo");
        shownote(); //note.txtの中身出力
        while(true){
            System.out.println("数字を入力してください");
            System.out.println("1.編集 2.削除 3.終了");
            String num = sc.nextLine();
            switch (num) {
                case "1":
                    writenote(); //note.txtの中身追加
                    break;
                case "2":
                    shownote();
                    deletenote(); //note.txtの中身削除
                    break;
                case "3":
                    System.out.println("終了します");
                    savenote(file); //note.txtのファイル書き込み
                    sc.close();
                    return;
                default:
                    System.out.println("想定していない文字が入力されたので終了します");
                    savenote(file);
                    sc.close();
                    return;
            }
        }
        
        
    }
    private static void shownote(){
        if(list.size() == 0){
            System.out.println("メモはありません");
            return;
        }
        System.out.println("あなたが以前に記録したメモ");
        for(int i = 0; i < list.size(); i++){
            System.out.println(i + ". " + list.get(i));
        }
        System.out.println("となっています");
    }

    private static void writenote(){
        System.out.println("新規メモを作成します");
        System.out.println("内容を入力してください");
        String note_write = sc.nextLine();
        list.add(note_write);
        System.out.println("追加しました");
    }

    private static void deletenote(){
        if(list.size() == 0){
            System.out.println("メモはありません");
            return;
        }
        System.out.printf("メモを削除します。%dから%dまでの中で番号を入力してください\n",0, list.size()-1);
        String index_str = sc.nextLine();
        int index = Integer.parseInt(index_str);
        if(index >= 0 && index < list.size()){
            list.remove(index);
            System.out.println("削除しました");
        }else{
            System.out.println("想定していない文字が入力されました");
            System.out.println("ホームに戻ります");
        }
        
    }

    public static void readnote(File file){
        try {
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis,"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String data;
            while((data = br.readLine()) != null){
                list.add(data);
            }
            br.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (UnsupportedOperationException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void savenote(File file){
        try {
            FileOutputStream fos = new FileOutputStream(file);
            OutputStreamWriter opw = new OutputStreamWriter(fos,"UTF-8");
            BufferedWriter bw = new BufferedWriter(opw);
            for(String Note_element : list){
                bw.write(Note_element);
                bw.newLine();
                bw.flush();
            }
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
