package com.fooqoo56.kyogofinder.api.infrastracture.repositoryimpl;

import com.fooqoo56.kyogofinder.api.domain.model.Relation;
import com.fooqoo56.kyogofinder.api.domain.model.User;
import com.fooqoo56.kyogofinder.api.domain.model.UserIds;
import com.fooqoo56.kyogofinder.api.domain.repository.FirestoreRepository;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
@Profile("!test")
public class FirestoreRepositoryImpl implements FirestoreRepository {

    private static final String BASE_PATH = "kyogo/v1/";

    final Firestore firestore;

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeUser(final User user, final String userId)
            throws ExecutionException, InterruptedException {
        final WriteResult writeResult =
                this.firestore.document(getPathOfUser(userId)).set(user).get();

        log.info("Update time: " + writeResult.getUpdateTime());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getUser(final String id) throws ExecutionException, InterruptedException {
        final ApiFuture<DocumentSnapshot> documentFuture =
                this.firestore.document(getPathOfUser(id)).get();

        return documentFuture.get().toObject(User.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserIds getOldestUserId() throws ExecutionException, InterruptedException {
        final CollectionReference collection = this.firestore.collection(BASE_PATH + "relation");

        ApiFuture<QuerySnapshot> querySnapshot = collection
                .orderBy("updatedAt", Query.Direction.ASCENDING)
                .limit(1)
                .get();

        final List<String> userIds =
                querySnapshot.get().getDocuments().stream().map(DocumentSnapshot::getId)
                        .collect(
                                Collectors.toList());

        return new UserIds(userIds);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeRelationUser(final Relation relation, final String userId)
            throws ExecutionException, InterruptedException {
        final WriteResult writeResult =
                this.firestore.document(getPathOfRelationUser(userId)).set(relation)
                        .get();

        log.info("Update time: " + writeResult.getUpdateTime());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Relation getRelationUser(final String userId)
            throws ExecutionException, InterruptedException {
        final ApiFuture<DocumentSnapshot> documentFuture =
                this.firestore.document(getPathOfRelationUser(userId)).get();

        return documentFuture.get().toObject(Relation.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeRelationFollower(final Relation relation, final String userId,
                                      final String followerId)
            throws ExecutionException, InterruptedException {

        final WriteResult writeResult =
                this.firestore.document(getPathOfRelationFollower(userId, followerId)).set(relation)
                        .get();

        log.info("Update time: " + writeResult.getUpdateTime());

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Relation getRelationFollower(final String userId, final String followerId)
            throws ExecutionException, InterruptedException {

        final ApiFuture<DocumentSnapshot> documentFuture =
                this.firestore.document(getPathOfRelationFollower(userId, followerId)).get();

        return documentFuture.get().toObject(Relation.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeRelationFollowerFriend(
            final Relation relation, final String userId, final String followerId,
            final String friendId) throws ExecutionException, InterruptedException {

        final WriteResult writeResult =
                this.firestore
                        .document(getPathOfRelationFollowerFriend(userId, followerId, friendId))
                        .set(relation)
                        .get();

        log.info("Update time: " + writeResult.getUpdateTime());

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Relation getRelationFollowerFriend(final String userId, final String followerId,
                                              final String friendId)
            throws ExecutionException, InterruptedException {

        final ApiFuture<DocumentSnapshot> documentFuture =
                this.firestore
                        .document(getPathOfRelationFollowerFriend(userId, followerId, friendId))
                        .get();

        return documentFuture.get().toObject(Relation.class);
    }

    /**
     * datastoreのuserドキュメントパスの取得
     *
     * @param id ユーザID
     * @return パス
     */
    private String getPathOfUser(final String id) {
        return String.format(BASE_PATH + "user/%s", id);
    }

    /**
     * datastoreのrelationドキュメントパスの取得
     *
     * @param id ユーザID
     * @return パス
     */
    private String getPathOfRelationUser(final String id) {
        return String.format(BASE_PATH + "relation/%s", id);
    }

    /**
     * datastoreのrelationドキュメントのfollowerパスの取得
     *
     * @param id         ユーザID
     * @param followerId フォロワーのユーザID
     * @return パス
     */
    private String getPathOfRelationFollower(final String id, final String followerId) {
        return String.format(BASE_PATH + "relation/%s/follower/%s", id, followerId);
    }

    /**
     * datastoreのrelationドキュメントのfollowerサブコレクションのfriendパスの取得
     *
     * @param id               ユーザID
     * @param followerId       フォロワーのユーザID
     * @param followerFriendId フォロワーのフォローしてるユーザのID
     * @return パス
     */
    private String getPathOfRelationFollowerFriend(final String id, final String followerId,
                                                   final String followerFriendId) {
        return String.format(BASE_PATH + "relation/%s/follower/%s/friend/%s", id, followerId,
                followerFriendId);
    }
}
