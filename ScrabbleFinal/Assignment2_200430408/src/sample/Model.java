package sample;

import java.util.Arrays;
import java.util.HashSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Model {
    private int givenPoints[]={1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
    private int addedTotal[];
    private int value = 12;
    private int netPoints;

    private HashSet<String> wordSet;
    private ObservableList<String> wordList=FXCollections.observableArrayList();

    public Model(){
        addedTotal =new int[26];
            Arrays.fill(addedTotal, value);
        wordSet=new HashSet<String>();
        netPoints =0;
    }

    private ObservableList<String> rules=FXCollections.observableArrayList("1. Two letters minimum","2. One letter must be vowel\r\n" +
            "      ▪ A, E, I, O, U or Y","3. 8 letters maximum","4. Cannot have duplicate words");

    public int[] getAddedTotal() {
        return addedTotal;
    }
    public ObservableList<String> getRules() {
        return rules;
    }

    public ObservableList<String> getWordList() {
        return wordList;
    }

    public HashSet<String> getWordSet() {
        return wordSet;
    }
    public int getWordPoints(String word) {
        int p=0;
        int l=word.length();
        for(int i=0;i<l;i++) {
            p+= givenPoints[word.charAt(i)-'A'];
        }
        return p;
    }
    public int getNetPoints() {
        return netPoints;
    }

    public void AddWord(String word) {
        wordList.add(word);
        wordSet.add(word.toUpperCase());
        netPoints +=getWordPoints(word.toUpperCase());
    }

    public void TakePoints(String word) {
        int l=word.length();
        for(int i=0;i<l;i++) {
            addedTotal[word.charAt(i)-'A']--;
        }
    }

    public ObservableList<String> getCountList() {
        ObservableList<String> list=FXCollections.observableArrayList();
        char ch;
        for(int i=65;i<=90;i++) {
            ch=(char) i;
            if(addedTotal[i-65]==0) {
                list.remove(ch+" - No words left");
            }
            else {
                list.add(ch+" - "+ addedTotal[i-65]);
            }
        }
        return list;
    }

    public ObservableList<String> getPointTable() {
        ObservableList<String> list=FXCollections.observableArrayList();
        char ch;
        for(int i=65;i<=90;i++) {
            ch=(char) i;
            list.add(ch+" - "+ givenPoints[i-65]);
        }
        return list;
    }

    public boolean CheckWord(String text) {
        return wordSet.contains(text.toUpperCase());
    }

    public boolean CheckPoints(int[] arr) {
        for(int i=0;i<26;i++) {
            if(arr[i]> addedTotal[i]) {
                return false;
            }
        }
        return true;
    }

    public void reset() {
        for (int i = 0; i <26 ; i++) {
            Arrays.fill(addedTotal, value);
        }wordList.clear();
        wordSet.clear();
        netPoints =0;
    }
}
