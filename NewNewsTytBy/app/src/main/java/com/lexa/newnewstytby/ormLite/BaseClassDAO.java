package com.lexa.newnewstytby.ormLite;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Lexa on 09.05.2016.
 */
public class BaseClassDAO extends BaseDaoImpl<BaseClass, Long> {

    protected BaseClassDAO(ConnectionSource connectionSource,
                           Class<BaseClass> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public List<BaseClass> getAllBaseClasses() throws SQLException{
        return this.queryForAll();
    }
}
