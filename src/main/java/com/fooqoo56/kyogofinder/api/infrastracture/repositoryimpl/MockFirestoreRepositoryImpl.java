package com.fooqoo56.kyogofinder.api.infrastracture.repositoryimpl;

import com.fooqoo56.kyogofinder.api.domain.model.Relation;
import com.fooqoo56.kyogofinder.api.domain.model.User;
import com.fooqoo56.kyogofinder.api.domain.repository.FirestoreRepository;
import java.util.concurrent.ExecutionException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
@Profile("test")
public class MockFirestoreRepositoryImpl implements FirestoreRepository {

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeUser(final User user) throws ExecutionException, InterruptedException {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getUser(final Integer id) throws ExecutionException, InterruptedException {
        return new User();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeRelationUser(final Relation relation, final Integer userId)
            throws ExecutionException, InterruptedException {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeRelationFollower(final Relation relation, final Integer userId,
                                      final Integer followerId)
            throws ExecutionException, InterruptedException {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeRelationFollowerFriend(
            final Relation relation, final Integer userId, final Integer followerId,
            final Integer friendId) throws ExecutionException, InterruptedException {

    }
}