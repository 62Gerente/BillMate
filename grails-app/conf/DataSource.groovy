dataSource {
    pooled = false
    dbCreate = "create-drop"
    url = "jdbc:postgresql://localhost:5432/billmate"
    driverClassName = "org.postgresql.Driver"
    dialect = net.sf.hibernate.dialect.PostgreSQLDialect
    username = "billmate"
    password = "billmate"
}

hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = true
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory' // Hibernate 3
//    cache.region.factory_class = 'org.hibernate.cache.ehcache.EhCacheRegionFactory' // Hibernate 4
    singleSession = true // configure OSIV singleSession mode
}

// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "update"
            url = "jdbc:postgresql://localhost:5432/billmate"
        }
    }
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:postgresql://localhost:5432/billmate"
        }
    }
    production {
        dataSource {
            dbCreate = "update"
            url = "jdbc:postgresql://localhost:5432/billmate"
        }
    }
}
