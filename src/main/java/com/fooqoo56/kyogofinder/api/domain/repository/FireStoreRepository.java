package com.fooqoo56.kyogofinder.api.domain.repository;

import com.fooqoo56.kyogofinder.api.domain.model.User;
import java.util.concurrent.ExecutionException;

public interface FireStoreRepository {

    /**
     * ユーザの取得
     *
     * @param id ユーザID
     * @return ユーザ情報
     * @throws ExecutionException ExecutionException
     * @throws InterruptedException　InterruptedException
     */
    User getUser(final Integer id) throws ExecutionException, InterruptedException;

    /**
     * ユーザの保存
     *
     * @param user ユーザ情報
     * @throws ExecutionException ExecutionException
     * @throws InterruptedException InterruptedException
     */
    void writeUser(final User user) throws ExecutionException, InterruptedException;
}
