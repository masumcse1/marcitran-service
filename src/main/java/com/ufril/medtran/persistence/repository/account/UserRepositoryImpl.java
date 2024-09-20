package com.ufril.medtran.persistence.repository.account;

import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author moin
 */
public class UserRepositoryImpl {

    private static Logger LOGGER = Logger.getLogger(UserRepositoryImpl.class);

    @PersistenceContext
    private EntityManager entityManager;


}
