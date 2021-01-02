package com.fooqoo56.kyogofinder.api.infrastracture.repositoryimpl;

import com.fooqoo56.kyogofinder.api.domain.model.Relation;
import com.fooqoo56.kyogofinder.api.domain.model.User;
import com.fooqoo56.kyogofinder.api.domain.model.UserIds;
import com.fooqoo56.kyogofinder.api.domain.repository.FirestoreRepository;
import java.util.ArrayList;
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
    public void writeUser(final User user, final Integer userId) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getUser(final Integer id) {
        return new User();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserIds getOldestUserId() {
        return new UserIds(new ArrayList<>());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeRelationUser(final Relation relation, final Integer userId) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Relation getRelationUser(final Integer userId) {
        return new Relation();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeRelationFollower(final Relation relation, final Integer userId,
                                      final Integer followerId) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Relation getRelationFollower(final Integer userId, final Integer followerId) {
        return new Relation();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeRelationFollowerFriend(
            final Relation relation, final Integer userId, final Integer followerId,
            final Integer friendId) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Relation getRelationFollowerFriend(final Integer userId, final Integer followerId,
                                              final Integer friendId) {
        return new Relation();
    }
}
