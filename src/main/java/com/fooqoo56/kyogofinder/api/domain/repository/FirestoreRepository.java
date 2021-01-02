package com.fooqoo56.kyogofinder.api.domain.repository;

import com.fooqoo56.kyogofinder.api.domain.model.Relation;
import com.fooqoo56.kyogofinder.api.domain.model.User;
import com.fooqoo56.kyogofinder.api.domain.model.UserIds;
import java.util.concurrent.ExecutionException;

public interface FirestoreRepository {

    /**
     * ユーザの取得
     *
     * @param id ユーザID
     * @return ユーザ情報
     * @throws ExecutionException   ExecutionException
     * @throws InterruptedException InterruptedException
     */
    User getUser(final String id) throws ExecutionException, InterruptedException;

    /**
     * ユーザの取得(リスト)
     *
     * @return ユーザ情報
     * @throws ExecutionException   ExecutionException
     * @throws InterruptedException InterruptedException
     */
    UserIds getOldestUserId() throws ExecutionException, InterruptedException;

    /**
     * ユーザの保存
     *
     * @param user ユーザ情報
     * @throws ExecutionException   ExecutionException
     * @throws InterruptedException InterruptedException
     */
    void writeUser(final User user, final String userId)
            throws ExecutionException, InterruptedException;

    /**
     * Relationのユーザ取得
     *
     * @param userId ユーザID
     * @throws ExecutionException   ExecutionException
     * @throws InterruptedException InterruptedException
     */
    Relation getRelationUser(final String userId)
            throws ExecutionException, InterruptedException;

    /**
     * Relationの保存
     *
     * @param relation タスクの保存情報
     * @param userId   ユーザID
     * @throws ExecutionException   ExecutionException
     * @throws InterruptedException InterruptedException
     */
    void writeRelationUser(final Relation relation, final String userId)
            throws ExecutionException, InterruptedException;

    /**
     * Relationのfollowerの取得
     *
     * @param userId     ユーザID
     * @param followerId フォロワーのユーザID
     * @throws ExecutionException   ExecutionException
     * @throws InterruptedException InterruptedException
     */
    Relation getRelationFollower(final String userId,
                                 final String followerId)
            throws ExecutionException, InterruptedException;

    /**
     * Relationのfollowerの保存
     *
     * @param relation   タスクの保存情報
     * @param userId     ユーザID
     * @param followerId フォロワーのユーザID
     * @throws ExecutionException   ExecutionException
     * @throws InterruptedException InterruptedException
     */
    void writeRelationFollower(final Relation relation, final String userId,
                               final String followerId)
            throws ExecutionException, InterruptedException;

    /**
     * Relationのfollowerのfriendの取得
     *
     * @param userId     ユーザID
     * @param followerId フォロワーのユーザID
     * @param friendId   フォロワーのフレンドのユーザID
     * @throws ExecutionException   ExecutionException
     * @throws InterruptedException InterruptedException
     */
    Relation getRelationFollowerFriend(final String userId,
                                       final String followerId, final String friendId)
            throws ExecutionException, InterruptedException;

    /**
     * Relationのfollowerのfriendの保存
     *
     * @param relation   タスクの保存情報
     * @param userId     ユーザID
     * @param followerId フォロワーのユーザID
     * @param friendId   フォロワーのフレンドのユーザID
     * @throws ExecutionException   ExecutionException
     * @throws InterruptedException InterruptedException
     */
    void writeRelationFollowerFriend(final Relation relation, final String userId,
                                     final String followerId, final String friendId)
            throws ExecutionException, InterruptedException;
}
