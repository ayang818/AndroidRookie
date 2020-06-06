package com.ayang818.countscore;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Deque;
import java.util.LinkedList;

public class CountActivityViewModel extends ViewModel {
    MutableLiveData<int[]> score;
    MutableLiveData<Deque<int[]>> history;
    int[] init = {0, 0};

    public CountActivityViewModel() {
        score = new MutableLiveData<>();
        history = new MutableLiveData<>();
        Deque<int[]> deque = new LinkedList<>();
        deque.push(init);
        score.setValue(init);
        history.setValue(deque);
    }

    public MutableLiveData<int[]> getScore() {
        return score;
    }

    /**
     * @param pos 0 or 1, 0 means teamA, 1 means teamB
     * @param num 1 or 2 or 3
     */
    public void add(int pos, int num) {
        int[] preScore = score.getValue();
        Deque<int[]> tmpHistory = history.getValue();
        int[] curScore = new int[2];
        for (int i = 0; i < curScore.length; i++) {
            if (pos == i) {
                curScore[i] += num;
            }
            curScore[i] += preScore[i];
        }
        tmpHistory.push(preScore);
        score.setValue(curScore);
        history.setValue(tmpHistory);
    }

    public void reset() {
        score.setValue(init);
    }

    public void back() {
        Deque<int[]> deque = history.getValue();
        if (deque.size() == 0) {
            score.setValue(init);
        } else {
            score.setValue(deque.pop());
        }
    }
}
