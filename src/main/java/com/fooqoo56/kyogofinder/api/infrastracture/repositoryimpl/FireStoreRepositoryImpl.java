package com.fooqoo56.kyogofinder.api.infrastracture.repositoryimpl;

import com.fooqoo56.kyogofinder.api.domain.model.User;
import com.fooqoo56.kyogofinder.api.domain.repository.FireStoreRepository;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import java.util.concurrent.ExecutionException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
@Profile("!test")
public class FireStoreRepositoryImpl implements FireStoreRepository {

    final Firestore firestore;

    /***
     * {@inheritDoc}
     */
    @Override
    public void writeUser(final User user) throws ExecutionException, InterruptedException {
        final WriteResult writeResult =
                this.firestore.document(getPathOfUser(user.getId())).set(user).get();

        log.info("Update time: " + writeResult.getUpdateTime());
    }

    /***
     * {@inheritDoc}
     */
    @Override
    public User getUser(final Integer id) throws ExecutionException, InterruptedException {
        final ApiFuture<DocumentSnapshot> documentFuture =
                this.firestore.document(getPathOfUser(id)).get();

        return documentFuture.get().toObject(User.class);
    }

    /**
     * datastoreのパスの取得
     *
     * @param id ユーザID
     * @return パス
     */
    private String getPathOfUser(final Integer id) {
        return "user/" + id.toString();
    }
}
