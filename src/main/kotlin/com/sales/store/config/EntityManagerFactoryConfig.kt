package com.sales.store.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.JpaVendorAdapter
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import jakarta.persistence.EntityManagerFactory
import javax.sql.DataSource

@Configuration
class EntityManagerFactoryConfig {

    @Autowired
    private lateinit var dataSource: DataSource

    @Bean
    fun entityManagerFactory(): LocalContainerEntityManagerFactoryBean {
        val vendorAdapter: JpaVendorAdapter = HibernateJpaVendorAdapter()

        val factory = LocalContainerEntityManagerFactoryBean()
        factory.jpaVendorAdapter = vendorAdapter
        factory.setPackagesToScan("com.sales.store")
        factory.dataSource = dataSource
        factory.jpaVendorAdapter = vendorAdapter
        factory.setEntityManagerFactoryInterface(jakarta.persistence.EntityManagerFactory::class.java) // set the entityManagerFactoryInterface to Jakarta's EntityManagerFactory

        return factory
    }

    @Bean
    fun transactionManager(emf: EntityManagerFactory): JpaTransactionManager {
        val transactionManager = JpaTransactionManager()
        transactionManager.entityManagerFactory = emf

        return transactionManager
    }
}