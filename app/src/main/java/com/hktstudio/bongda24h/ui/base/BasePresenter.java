package com.hktstudio.bongda24h.ui.base;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Base class that implements the Presenter interface and provides empty base implementation for
 * attachView() and detachView(). It also handles keeping empty reference to the mvpView that
 * can be accessed from the children classes by calling getMvpView().
 */
public class BasePresenter<T extends MvpView> implements Presenter<T> {

    private List<AsyncTask> taskList = new ArrayList<>();
    private T mMvpView;

    @Override
    public void attachView(T mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void detachView() {
        mMvpView = null;
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    public T getMvpView() {
        return mMvpView;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }

    public void addTask(AsyncTask task){
        taskList.add(task);
    }

    public void cancelAllTask(){
        for(AsyncTask task: taskList){
            if(!task.isCancelled()){
                task.cancel(true);
            }
        }
    }
}

