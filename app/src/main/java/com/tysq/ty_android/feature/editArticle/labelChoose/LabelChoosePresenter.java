package com.tysq.ty_android.feature.editArticle.labelChoose;

import android.support.annotation.NonNull;

import com.bit.presenter.BasePresenter;
import com.tysq.ty_android.net.RetrofitFactory;
import com.tysq.ty_android.net.rx.RxParser;
import com.tysq.ty_android.net.rx.RxSingleSubscriber;

import javax.inject.Inject;

import response.LabelResp;

public final class LabelChoosePresenter extends BasePresenter<LabelChooseView> {
    @Inject
    public LabelChoosePresenter(LabelChooseView view) {
        super(view);
    }

    public void loadLabel(String searchContent) {
        RetrofitFactory
                .getApiService()
                .getLabel(0, 20, searchContent)
                .compose(RxParser.handleSingleDataResult())
                .subscribe(new RxSingleSubscriber<LabelResp>(mySelf) {
                    @Override
                    protected void onError(int code, String message) {
                        showErrorMsg(code, message);
                    }

                    @Override
                    protected void onSuccessRes(@NonNull LabelResp value) {
                        mView.onLoadLabel(value.getLabelList());
                    }
                });
    }
}
